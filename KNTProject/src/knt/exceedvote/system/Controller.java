package knt.exceedvote.system;


import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.joda.time.DateTime;

import com.sun.media.sound.AlawCodec;

import knt.exceedvote.dao.DaoFactory;
import knt.exceedvote.dao.PollDAO;
import knt.exceedvote.dao.UserDAO;
import knt.exceedvote.dao.VoteDAO;
import knt.exceedvote.dao.hibernate.DaoFactoryImpl;
import knt.exceedvote.dao.hibernate.UserDAOImpl;
import knt.exceedvote.dao.hibernate.VoteDAOImpl;
import knt.exceedvote.model.*;


/**
 * This class is the Controller which handels the requests and returns from jsp pages
 * the 'todo' attribut tells the controller what is todo
 * @author Thomas Raudenbusch
 *
 */

public class Controller extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   private UserDAO userDao;
   private VoteDAO voteDao;
   private PollDAO pollDao;

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
		    throws IOException, ServletException {
	  // response.sendRedirect(getServletName());
   }
 
   /**
    * Main method in this class. It handles in- and output to webpages
    * @param request
    * to get input from a jsp webpage
    * @param response
    * to send output to a jsp webpage
    */
   @Override
   public void doPost(HttpServletRequest request,
         HttpServletResponse response) throws ServletException, IOException {
      // Retrieve the current session, or create a new session if no session exists.
		userDao = DaoFactory.getInstance("hibernate").getUserDao();
		voteDao = DaoFactory.getInstance("hibernate").getVoteDao();
		pollDao = DaoFactory.getInstance("hibernate").getPollDao();
 
      // For dispatching the next Page
      String nextPage = "";
      String todo = request.getParameter("todo");
      HttpSession session = request.getSession(true);
      System.out.println(todo);
      if (todo == null){
          System.out.println("No todo found!");

    	  nextPage = "/knt/jsp/login.jsp";
    	  response.sendRedirect(nextPage);
    	 return;
      }
      
      //Handle changing language
      if (todo.equals("lang")){
    	  UserSession userSession = (UserSession) session.getAttribute("user");
    	  userSession.setLanguage(request.getParameter("lang"));
		   nextPage = "/knt/jsp/votemenu.jsp";
		  response.sendRedirect(nextPage);
		  return;
      }
      
      
      //Handle login and authentification to the website
      if (todo.equals("login")){
    	 
    	  String user = request.getParameter("user");
    	  String password = request.getParameter("password");   	  
    	  
    	  if (user == null || password == null || user.equals("") || password.equals("")){
    		  System.out.println("wrong null");
    		  nextPage = "/knt/jsp/login.jsp";
    		  response.sendRedirect(nextPage);
    		  return;
    	  } 

    	  if (user.endsWith(" ")){
    		  user = user.substring(0, user.length() - 1);

    	  }
    
    	  
    	  if(!userDao.checkUser(user)) {
    		  System.out.println("wrong id");
    		  nextPage = "/knt/jsp/login.jsp";
    		  response.sendRedirect(nextPage);
    		  return;
    	  }
    	  if(!userDao.checkpassword(user, password)){
    		  System.out.println("wrong pw");
    		  nextPage = "/knt/jsp/login.jsp";
    		  response.sendRedirect(nextPage);
    		  return;  
    	  }
		   Login userObj = userDao.getUser(user);
    	  UserSession userobject = new UserSession();
		  session.setAttribute("user", userobject);
		  Logging.login(request.getRemoteAddr() , userObj);
		  userobject.setUser(userObj);
		   
    	  	if (userObj.getTyid() == 2) {
    	  		 nextPage = "/knt/jsp/ecadmin.jsp";
    	  		 
    	         response.sendRedirect(nextPage);
    	         return;
    	  	} else {
    	  		
    	  userobject.setVoted(pollDao.getVoted(userObj));
    	  userobject.setNotVotedYet(pollDao.getNotVotedYet(userObj));
    	  userobject.setAllPolls(pollDao.getAll());
		   DateTime countdown = Countdown.getDate();
		   if (countdown != null) userobject.setCountdown(countdown);
		   else userobject.setCountdown(new DateTime(2099, 01, 01, 0, 0));
		 

    	  if(userObj.getFirstlogin() == 1){
    		  System.out.println("first login");
    		   nextPage = "/knt/jsp/changepw.jsp";
    		  response.sendRedirect(nextPage);
    		  return;
    	  }

    if (userobject.getCountdown().isAfter(new DateTime()) ) nextPage = "/knt/jsp/votemenu.jsp";
    else nextPage = "/knt/jsp/results.jsp";
    response.sendRedirect(nextPage);
    return;
  	}
      }

      //Change pw
      if(todo.equals("changepw")){
    	  String password = request.getParameter("password");
    	  UserSession userSession = (UserSession) session.getAttribute("user");
    	  try {
			password = PasswordHash.createHash(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  if(userDao.updatetUser(new Login(userSession.getUser().getUid(), password, userSession.getUser().getTyid(), 0))) nextPage = "/knt/jsp/votemenu.jsp"; 
    	  else nextPage = "/knt/jsp/changepw.jsp";
          response.sendRedirect(nextPage);
          return;
      }
      
      //Handle the choose of a poll
      if (todo.equals("pollchoose")){
    	  
   
    	  String pollid = request.getParameter("pollid");
    	  int pid = 0;
	if(pollid == null){
		nextPage = "votemenu.jsp";
        response.sendRedirect(nextPage);
        return;
	}else
	{
    	  try{
    		  pid = Integer.parseInt(pollid);
      } catch (Exception e) {
  		nextPage = "votemenu.jsp";
        response.sendRedirect(nextPage);
        return;
      }
	}
	Poll poll = pollDao.getPoll(pid);
	session.setAttribute("poll", poll);
	nextPage = "/knt/jsp/voting.jsp";
    response.sendRedirect(nextPage);
    return;
      }
      
      //Handle the voting
      if (todo.equals("voteteam")){
    	  
    	  int team = Integer.parseInt(request.getParameter("team").toString());
    	  Poll poll = (Poll) session.getAttribute("poll");
    	  UserSession userSession = (UserSession) session.getAttribute("user");
    	  Vote alreadyvoted = voteDao.checkVote(userSession.getUser().getUid(), poll);
    	  
    	  if(alreadyvoted != null) {
    		  alreadyvoted.setTid(team);
    		  if(voteDao.updateVote(alreadyvoted)) {
    	    	  nextPage = "/knt/jsp/votemenu.jsp";
    	    	  session.removeAttribute("poll");
    	    	  response.sendRedirect(nextPage);
    	          return;
    	    	  
    	    	  } else {
    	    		  nextPage = "/knt/jsp/voting.jsp";
        	    	  response.sendRedirect(nextPage);
        	          return;
    	    	  }  
      } else if(voteDao.insertVote(new Vote(userSession.getUser().getUid(), poll.getPid(), team, 1))) {

    	  session.removeAttribute("poll");
    	  userSession.setVoted(pollDao.getVoted(userSession.getUser()));
    	  userSession.setNotVotedYet(pollDao.getNotVotedYet(userSession.getUser()));
    	  nextPage = "/knt/jsp/votemenu.jsp";
    	  response.sendRedirect(nextPage);
          return;
      } else 
    		  nextPage = "/knt/jsp/voting.jsp";
    	  response.sendRedirect(nextPage);
          return;
      }
      
      //Handle the registration for a new user
      if (todo.equals("register")){
    		
    		String kuid = request.getParameter("kuid");
    		if(userDao.insertUser(kuid)) { 
    			nextPage = "login.jsp";
	      	  response.sendRedirect(nextPage);
	          return;
    		} else { 
			nextPage = "register.jsp";
      	  response.sendRedirect(nextPage);
          return;
    		}
}
      
      if (todo.equals("delete")){
  		int pid = Integer.parseInt(request.getParameter("pollid"));
  	  UserSession userSession = (UserSession) session.getAttribute("user");

	  voteDao.deleteVote(userSession.getUser(), pid);
	  userSession.setVoted(pollDao.getVoted(userSession.getUser()));
	  userSession.setNotVotedYet(pollDao.getNotVotedYet(userSession.getUser()));
		  nextPage = "voted.jsp";
    	  response.sendRedirect(nextPage);
          return;
      }

      System.out.println("No action found!");
       	nextPage = "login.jsp";
         response.sendRedirect(nextPage);
         return;

   }
   
}