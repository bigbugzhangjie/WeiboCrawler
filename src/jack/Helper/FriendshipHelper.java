package jack.Helper;

import weibo4j.Friendships;
import jack.Account;
import jack.Accounts;

public class FriendshipHelper {
	
	public static void main(String[] args){
		Account acc = Accounts.list.get("z127513@huawei.com");
		Friendships fs = new Friendships(acc.getToken());
		
	}

}
