package knt.exceedvote.dao;

import knt.exceedvote.model.Login;

public interface UserDAO {

	public boolean checkUser(String uid);

	public boolean checkpassword(String uid, String password);


	public boolean updatetUser(Login newUser);

	public boolean insertUser(String uid);
	
	public Login getUser(String uid);

}