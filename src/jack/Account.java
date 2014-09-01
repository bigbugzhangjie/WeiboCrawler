package jack;

public class Account{
	String uid = "";
	String email;
	String token;
	String passwd="";
	
	public Account(){}
	
	public Account(String email,String token){
		this.email = email;
		this.token = token;
	}
	public Account(String email,String token,String id){
		this.email = email;
		this.token = token;
		this.uid = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPasswd(){
		return passwd;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
