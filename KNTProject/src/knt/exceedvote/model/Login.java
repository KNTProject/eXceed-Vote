package knt.exceedvote.model;

public class Login {

	  private String password;
	  private String uid;
	  private int firstlogin;
	  private int tyid;
		
	  
	  public int getTyid() {
		return tyid;
	}

	public void setTyid(int tyid) {
		this.tyid = tyid;
	}

	public int isFirstlogin() {
		return firstlogin;
	}

	public void setFirstlogin(int firstlogin) {
		this.firstlogin = firstlogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getUid() {
		return uid;
	}



	public void setUid(String uid) {
		this.uid = uid;
	}


}
