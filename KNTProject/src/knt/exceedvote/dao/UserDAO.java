package knt.exceedvote.dao;

import knt.exceedvote.model.Login;

public interface UserDAO {

	public boolean checkUser(String uid);

	public boolean checkpassword(String uid, String password);

	public boolean checkFirstlogin(String uid);

	public boolean updatetUser(Login newUser);

	public void insertUser(String uid);

}