package jack.weibo;

import jack.utility.FileTools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import weibo4j.Oauth;
import weibo4j.Timeline;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

public class GetToken {
	/***
	 * 模拟登录并得到登录后的Token
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static AccessToken get(String username, String password)
			throws HttpException, IOException {
		String clientId = WeiboConfig.getValue("client_ID");
		String redirectURI = WeiboConfig.getValue("redirect_URI");
		String url = WeiboConfig.getValue("authorizeURL");

		PostMethod postMethod = new PostMethod(url);
		// 应用的App Key
		postMethod.addParameter("client_id", clientId);
		// 应用的重定向页面
		postMethod.addParameter("redirect_uri", redirectURI);
		// 模拟登录参数
		// 开发者或测试账号的用户名和密码
		postMethod.addParameter("userId", username);
		postMethod.addParameter("passwd", password);
		postMethod.addParameter("isLoginSina", "0");
		postMethod.addParameter("action", "submit");
		postMethod.addParameter("response_type", "code");
		HttpMethodParams param = postMethod.getParams();
		param.setContentCharset("UTF-8");
		// 添加头信息
		List<Header> headers = new ArrayList<Header>();
		headers.add(new Header("Referer",
				"https://api.weibo.com/oauth2/authorize?client_id=" + clientId
						+ "&redirect_uri=" + redirectURI
						+ "&from=sina&response_type=code"));
		headers.add(new Header("Host", "api.weibo.com"));
		headers.add(new Header("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; rv:11.0) Gecko/20100101 Firefox/11.0"));
		HttpClient client = new HttpClient();
		client.getHostConfiguration().getParams()
				.setParameter("http.default-headers", headers);
		client.executeMethod(postMethod);
		int status = postMethod.getStatusCode();
		System.out.println(status);
		if (status != 302) {
			System.out.println("token刷新失败");
			return null;
		}
		// 解析Token
		Header location = postMethod.getResponseHeader("Location");
		if (location != null) {
			String retUrl = location.getValue();
			int begin = retUrl.indexOf("code=");
			if (begin != -1) {
				int end = retUrl.indexOf("&", begin);
				if (end == -1)
					end = retUrl.length();
				String code = retUrl.substring(begin + 5, end);
				System.out.println("code=" + code);
				if (code != null) {
					Oauth oauth = new Oauth();
					try {
						AccessToken token = oauth.getAccessTokenByCode(code);
						return token;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	/**
	 * 发微博
	 * 
	 * @param token
	 *            认证Token
	 * @param content
	 *            微博内容
	 * @return
	 * @throws Exception
	 */
	public static boolean sinaSendWeibo(String token, String content)
			throws Exception {
		boolean flag = false;
		Timeline timeline = new Timeline(token);

		// Timeline timeline = new Timeline(token);
		// timeline.client.setToken(token);

		try {
			timeline.updateStatus(content);
			flag = true;
		} catch (WeiboException e) {
			flag = false;
			System.out.println(e.getErrorCode());
		}
		return flag;
	}

	
	public static void getFromFile(File f) throws Exception{		
		List<String> lines = FileTools.getLineList(f);
		List<String> ret = new ArrayList<String>();
		for (String line : lines) {
			String[] col = line.split("\t");
			if (col.length >= 2) {
				String u = col[0];
				String p = col[1];
				String t = "";
				try {
					AccessToken at = get(u, p);
					t = at.getAccessToken();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ret.add(u + "\t" + p + "\t" + t);
			}
		}

		if (ret.size() > 0) {
			for (String s : ret) {
				System.out.println(s);
			}
		}

	}

	public static void main(String[] args) throws Exception {
//		File f = new File("E:/workspace/WeiboCrawler/doc/accounts.txt");
//		getFromFile(f);
	
		getOne();
	}

	public static void getOne() throws HttpException, IOException{
		 AccessToken at = get("bigbug04@sina.com", "654123");
		 System.out.println(at.getAccessToken());

		// sinaSendWeibo(at.getAccessToken(),"测试呢");
	}
}
