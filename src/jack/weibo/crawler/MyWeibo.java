package jack.weibo.crawler;

import weibo4j.Weibo;
import weibo4j.http.HttpClient;
/**
 * 
 * @author zhangjie05-ghq
 * 2014年9月4日
 */
public class MyWeibo extends Weibo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyWeibo(String token){
		this.access_token = token;
	}
	
	public static HttpClient getClient(){
		return client;
	}
	public String getToken(){
		return this.access_token;
	}
	
}
