package jack;

public class ClientInfo {
	
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
