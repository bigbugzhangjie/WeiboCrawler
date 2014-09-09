package jack.weibo;

/**
 * 每个user token有限制，理想的做法是申请一堆马甲，
 * 然后创建一个token pool，定期获取各个马甲的token，
 * 任何需要token的程序都来pool里取token，不需要关心user、passwd、token
 * @author zhangjie05-ghq
 *
 */
public class TokenService {
	
	public static String getToken(){
		//TODO
		return null;
	}

	public static void main(String[] args) {

	}

}
