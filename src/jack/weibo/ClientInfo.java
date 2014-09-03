package jack.weibo;

public class ClientInfo {
	public static final ClientInfo CLIENT = new ClientInfo(
			"4062999603",
			"dfb9e861370509ebddd75832f238b00b",
			"http://127.0.0.1:8080");

	String id;
	String sercret;
	String redirectURI;
	
	public ClientInfo(){}
	
	public ClientInfo(String id, String secr, String uri){
		this.id = id;
		this.sercret = secr;
		this.redirectURI = uri;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSercret() {
		return sercret;
	}
	public void setSercret(String sercret) {
		this.sercret = sercret;
	}
	public String getRedirectURI() {
		return redirectURI;
	}
	public void setRedirectURI(String redirectURI) {
		this.redirectURI = redirectURI;
	}
	
}
