package jack.weibo;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import weibo4j.examples.oauth2.Log;

public class Login {
	String uid;
	String pwd;
	ClientInfo client = ClientInfo.CLIENT;
	
	public Login(String uid,String pwd){
		this.uid = uid;
		this.pwd = pwd;
	}
	public Login(UserAccount account){
		uid = account.getUid();
		pwd = account.getPasswd();
	}
	public Login(String uid,String pwd,ClientInfo client){
		this.uid = uid;
		this.pwd = pwd;
		this.client = client;
	}
	public void setClient(ClientInfo client){
		this.client = client;
	}
	
	public void login(){
		// TODO Auto-generated method stub
		HttpClient httpClient = new HttpClient();
		String url = "https://api.weibo.com/oauth2/authorize";
		PostMethod postMethod = new PostMethod(url);
		
		String appID = this.client.getId();
		String redirect_uri = this.client.getRedirectURI();
		
		NameValuePair[] data ={				
				new NameValuePair("action", "login"),
				new NameValuePair("display", "default"),
				new NameValuePair("withOfficalFlag", "0"),
				new NameValuePair("withOfficalAccount", ""),
				new NameValuePair("scope", ""),
				new NameValuePair("ticket", ""),
				new NameValuePair("isLoginSina", ""),
				new NameValuePair("response_type", "code"),
				new NameValuePair("regCallback", "http://172.16.106.187/oauth2/authorize?client_id=2672156159&response_type=code&display=default&redirect_uri=http://127.0.0.1/&from=&with_cookie="),
				new NameValuePair("redirect_uri", redirect_uri),
				new NameValuePair("client_id", appID),
				new NameValuePair("state", ""),
				new NameValuePair("verifyToken", "null"),
				new NameValuePair("from", ""),
				new NameValuePair("userId", uid),
				new NameValuePair("passwd", pwd)
		};
		
		
		postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.41 Safari/536.5");
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		postMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		postMethod.setRequestHeader("Referer", "https://api.weibo.com/oauth2/authorize?client_id=2672156159&redirect_uri=http://127.0.0.1/&response_type=code");
		postMethod.setRequestHeader("Accept-Encoding", "gzip,deflate,sdch");
		postMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
		postMethod.setRequestHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
		postMethod.setRequestHeader("Cookie", "");
		
		postMethod.setRequestBody(data);
		int statusCode = 0;
		try {
			statusCode = httpClient.executeMethod(postMethod);
			
			Log.logDebug(statusCode+"");
			Header[] header=postMethod.getResponseHeaders();
			
			for(int i=0;i<header.length;i++)
			{
				Log.logDebug("header: "+header[i].getName());
				Log.logDebug(header[i].getValue());
			}
			} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	

	public static void main(String[] args) {
		UserAccount account = UserAccounts.usermap.get("bigbug04@sina.com");
		Login login = new Login(account.getUid(),"654123");
		login.login();
	}

}
