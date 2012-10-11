package knt.exceedvote.controller;


import knt.exceedvote.ui.LoginDAO;
import knt.exceedvote.ui.VoteDAO;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/*
 * This class is to test for functionality.
 */
public class JUnit4 {

	/*
	 * This method is use to test for id and password setting
	 * 
	 */
	  @Test
	  public final void testUser() {
	    System.out.println("-- Testing method setUid, "
	        + "setTyp and getUid, getTyp --");
	    final String uid = "12345";
	    final String typ = "student";
	    User user = new User();
	    user.setUid(uid);
	    user.setTyp(typ);
	    assertEquals("12345", user.getUid());
	    assertEquals("student", user.getTyp());

	    System.out.println("-- Test ended successfull --");
	  }
	  
	  /*
	   * To test whether user id is exist in
	   * the system or not.
	   */
	  @Test
	  public final void testUserReal() {
	    System.out.println("-- Testing method setUid, "
	        + "setTyp and getUid, getTyp --");
	    final String uid = "123";

	    assertEquals(true, LoginDAO.checkUser(uid));
	    System.out.println("-- Test ended successfull --");
	  }
	  
	
	  /*
	   * To test whether user is able
	   * to login or not.
	   */
	  @Test
	  public final void testLogin() {
	    System.out.println("-- Testing method setUid, "
	        + "setTyp and getUid, getTyp --");
	    final String uid = "123";
	    final String pw = "wda";

	    assertEquals(true, LoginDAO.checkpassword(uid, pw));
	    System.out.println("-- Test ended successfull --");
	  }
	  
	  /*
	   * To test that user is able to vote
	   * and the system records the vote score.
	   */
	  @Test
	  public final void inputVote() {
	    System.out.println("-- Testing method insertVote(), "
	        + "setTyp and getUid, getTyp --");
	    final String uid = "123";
	    final int pid = 1;
	    final int tid = 1;
	    final int votes = 1;

	    assertEquals(true, VoteDAO.insertVote(uid, pid, tid, votes));
	    System.out.println("-- Test ended successfull --");
	  }
	  
	  
	  
}
