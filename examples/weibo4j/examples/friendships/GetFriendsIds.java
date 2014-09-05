package weibo4j.examples.friendships;

import weibo4j.Friendships;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.WeiboException;

public class GetFriendsIds {

	public static void main(String[] args) {
//		String access_token = args[0];
//		String uid = args[1];
		
		String access_token = "2.00KMB9lFOgQUTD3988dc579908k9d7";
				//"2.00lIaqwBOgQUTD4856ba063cVl3r8B";
				//"2.00KMB9lFOgQUTD3988dc579908k9d7";//args[0];
		String uid = "5281829734";//"5281829734";//"1771998764";//"1785692303";//args[1];
		
		
		Friendships fm = new Friendships(access_token);
		try {
			String[] ids = fm.getFriendsIdsByUid(uid);
			for(String s : ids){
				Log.logInfo(s);
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}

	}

}
