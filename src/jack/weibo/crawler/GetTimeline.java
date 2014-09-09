package jack.weibo.crawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import weibo4j.model.PostParameter;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
import weibo4j.util.ArrayUtils;
import weibo4j.util.WeiboConfig;

public class GetTimeline extends MyWeibo {
	public static long SLEEP_BASIC = 120000; //每轮至少sleep 60秒
	public static long SLEEP_DELTA = 3000; // 每发行一条重复status，多sleep 3秒

	public GetTimeline(String token) {
		super(token);
	}
	public StatusWapper get() throws WeiboException {
		return get(100);
	}
	/**
	 * 
	 * @param count	返回status的数目，最大不超过100
	 * @return
	 * @throws WeiboException
	 */
	public StatusWapper get(int count) throws WeiboException {		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("count", String.valueOf(count));
		PostParameter[] parList = ArrayUtils.mapToArray(map);
		return Status.constructWapperStatus(client.get(
				WeiboConfig.getValue("baseURL")
						+ "statuses/friends_timeline.json", parList, access_token));
	}
	
	public StatusWapper get(String sid) throws WeiboException {		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("since_id", sid);
		PostParameter[] parList = ArrayUtils.mapToArray(map);
		return Status.constructWapperStatus(client.get(
				WeiboConfig.getValue("baseURL")
						+ "statuses/friends_timeline.json", parList, access_token));
	}

	public static void main(String[] args) throws Exception{
//		unitTest();
		onlineCrawl();
	}
	
	public static void onlineCrawl(){
		FileWriter w = null;		
		try{
		String token = "2.00KMB9lFOgQUTD3988dc579908k9d7";
		GetTimeline tl = new GetTimeline(token);
		StatusWapper wap;
		HashSet<String> prev = new HashSet<String>();	// 上轮获取的status
		HashSet<String> curr = new HashSet<String>();	// 本轮获取的status
		
		Date date = new Date();
		String timestamp = "";
		timestamp = date.getMonth()+"."+date.getDay()+"."+date.getHours()+"."+date.getMinutes();
//		timestamp = Calendar.get(Calendar.MONTH)+"."+Calendar.get(Calendar.DAY_OF_MONTH);
		
		w = new FileWriter(
				new File("E:/workspace/WeiboCrawler/data/"
						+ "friendstimeline-bigbug05-"+timestamp+".json")); //
		int dupNum = 0; // 本轮重复status数目；
		long sleep = 0; // 两轮间的时间间隔毫秒数；
		while(true){
			try{
			 wap = tl.get();
			 for(Status s : wap.getStatuses()){
					String id = s.getId();
					curr.add(id);
					if(!prev.contains(id)){
						w.write(s.getJSONObject().toString()+"\n");
					}else{
						dupNum ++;
					}
			 }
			 w.flush();
			 prev = curr;
			 sleep = SLEEP_BASIC + dupNum*SLEEP_DELTA; // 休息10秒，每重复1条多休息3秒；
			 System.out.println("Found "+dupNum +" duplicated status this round. ");
			 System.out.println("Sleep "+(int)(sleep/1000) +" seconds.");
			 try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 dupNum = 0;
			}catch(WeiboException e){
				e.printStackTrace();
				try {
					Thread.sleep(300000); // 休息5分钟
				} catch (InterruptedException ee) {
					ee.printStackTrace();
				}
			}
		}
		}catch(IOException we){
			we.printStackTrace();
		}finally{
			try {
				w.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void unitTest() throws WeiboException{
		String token = "2.00KMB9lFOgQUTD3988dc579908k9d7";
		GetTimeline tl = new GetTimeline(token);
		StatusWapper wap = tl.get();
		System.out.println("==================================");
		System.out.println("Got "+wap.getStatuses().size()+" status:");
		int i=0;
		for(Status s : wap.getStatuses()){
			i++;
			System.out.println("\t"+i+":"+ s.getJSONObject().toString());
		}		
	}
}
