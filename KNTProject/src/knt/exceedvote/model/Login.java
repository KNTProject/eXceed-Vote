package knt.exceedvote.model;

/**
 * Model for Login Object
 * @author Thomas Raudenbusch
 *
 */
public class Login {

	  private String password;
	  private String uid;
	  private int firstlogin;
	  private int tyid;
		
	  public Login(String uid, String password, int tyid, int firstlogin){
		  this.uid = uid;
		  this.password = password;
		  this.firstlogin = firstlogin;
		  this.tyid = tyid;
	  }
	  
	  public Login(String password, String uid){
		  this.uid = uid;
		  this.password = password;
		  this.firstlogin = 0;
		  this.tyid = 1;
	  }
	  
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
