package jack.Helper;

import weibo4j.Friendships;
import jack.weibo.UserAccount;
import jack.weibo.UserAccounts;

public class FriendshipHelper {
	
	public static void main(String[] args){
		UserAccount acc = UserAccounts.usermap.get("z127513@huawei.com");
		Friendships fs = new Friendships(acc.getToken());
		
	}

}
