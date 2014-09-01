package weibo4j.examples.friendships;

import weibo4j.Friendships;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;

public class GetFriendsById {

	public static void main(String[] args) {
//		String access_token = args[0];
//		String id = args[1];
		
		String access_token = "2.00lIaqwBbrB3ICb8c7570b00W4UQ2B";//args[0];
		String id = "1771998764";//"1771998764";//"1785692303";//args[1];
		
		
		Friendships fm = new Friendships(access_token);
		try {
			UserWapper users = fm.getFriendsByID(id);
			for(User u : users.getUsers()){
				System.out.println(u.toString());
			}
			System.out.println(users.getNextCursor());
			System.out.println(users.getPreviousCursor());
			System.out.println(users.getTotalNumber());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

	}

}
