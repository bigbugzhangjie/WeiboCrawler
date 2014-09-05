package jack.weibo.crawler;

import java.util.ArrayList;
import java.util.List;

import weibo4j.Friendships;
import weibo4j.model.PostParameter;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

public class GetFriends extends MyWeibo {

	public GetFriends(String token) {
		super(token);
	}

	public static void main(String[] args) throws Exception {
		
		String token = "2.00KMB9lFOgQUTD3988dc579908k9d7";
		String screenName = "bigbug05";
		GetFriends fm = new GetFriends(token);
		List<User> list = fm.getAllFriends(screenName);
		
		for (User u : list) {
			System.out.println(u.getId());
		}
		System.out.println("Found "+ list.size()+" friends.");
	}
	
	public List<User> getAllFriends(String screenName) throws WeiboException{
		long curs = 0;
		long total = 1;
		List<User> list = new ArrayList<User>() ;
		while( list.size()<total){
			UserWapper users = getFriends(screenName,curs);
			list.addAll(users.getUsers());
			total = users.getTotalNumber();
			curs += users.getUsers().size();			
		}
		
		return list;
	}

	public UserWapper getFriends(String screen_name,long cursor)
			throws WeiboException {
		UserWapper ret = User.constructWapperUsers(client.get(
				WeiboConfig.getValue("baseURL") + "friendships/friends.json",
				new PostParameter[] { 
					new PostParameter("screen_name",screen_name),
					new PostParameter("cursor",cursor)}, 
				getToken()));
		return ret;
	}

}
