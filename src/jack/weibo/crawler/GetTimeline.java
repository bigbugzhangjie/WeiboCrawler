package jack.weibo.crawler;

import java.util.HashMap;
import java.util.Map;

import weibo4j.model.PostParameter;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
import weibo4j.util.ArrayUtils;
import weibo4j.util.WeiboConfig;

public class GetTimeline extends MyWeibo {

	public GetTimeline(String token) {
		super(token);
	}

	public StatusWapper get()
			throws WeiboException {
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("count", "100");
		PostParameter[] parList = ArrayUtils.mapToArray(map);
		return Status.constructWapperStatus(client.get(
				WeiboConfig.getValue("baseURL")
						+ "statuses/friends_timeline.json", parList, access_token));
	}

}
