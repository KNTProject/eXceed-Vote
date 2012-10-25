package knt.exceedvote.system;


import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import knt.exceedvote.dao.UserDAO;
import knt.exceedvote.dao.VoteDAO;

/**
 * This class is the Controller which handels the requests and returns from jsp pages
 * the 'todo' attribut tells the controller what is todo
 * @author Thomas Raudenbusch
 *
 */

public class Controller extends HttpServlet {
   private static final long serialVersionUID = 1L;
  
 /**
  * doGet should do the same like toPost (its no difference)
  */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      doPost(request, response);  // Same as doPost()
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
      HttpSession session = request.getSession(true);

 
      // For dispatching the next Page
      String nextPage = "";
      String todo = request.getParameter("todo");

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
    
    	  
    	  if(!UserDAO.checkUser(user)) {
    		  System.out.println("wrong id");
    		  nextPage = "/knt/jsp/login.jsp";
    		  response.sendRedirect(nextPage);
    		  return;
    	  }
    	  if(!UserDAO.checkpassword(user, password)){
    		  System.out.println("wrong pw");
    		  nextPage = "/knt/jsp/login.jsp";
    		  response.sendRedirect(nextPage);
    		  return;  
    	  }
    	  if(UserDAO.checkFirstlogin(user)){
    		  System.out.println("first login");
    		  nextPage = "/knt/jsp/changepw.jsp";
    		   UserSession userobject = new UserSession();
    		   userobject.setUid(user);
    		   session.setAttribute("user", userobject);	
    		  response.sendRedirect(nextPage);
    		  return;
    	  }
    UserSession userobject = new UserSession();
    userobject.setUid(user);
    session.setAttribute("user", userobject);	 
	nextPage = "/knt/jsp/votemenu.jsp";
      }

      //Change pw
      if(todo.equals("changepw")){
    	  String password = request.getParameter("password");
    	  UserSession userSession = (UserSession) session.getAttribute("user");
    	  if(UserDAO.updatetUser(userSession.uid, password)) nextPage = "/knt/jsp/votemenu.jsp"; 
    	  else nextPage = "/knt/jsp/changepw.jsp"; 
      }
      
      //Handle the choose of a poll
      if (todo.equals("pollchoose")){
    	  
   
    	  String pollid = request.getParameter("pollid");
    	  int pid = 0;
	if(pollid == null){
		nextPage = "badPID";
		return;
	}else
	{
    	  try{
    		  pid = Integer.parseInt(pollid);
      } catch (Exception e) {
    	  System.out.println(e);
    	  nextPage = "badPID";
    	  return;
      }
	}
	
	session.setAttribute("pid", pid);
	nextPage = "/knt/jsp/voting.jsp";

      }
      
      //Handle the voting
      if (todo.equals("voteteam")){
    	  
    	  int team = Integer.parseInt(request.getParameter("team").toString());
    	  int pid = Integer.parseInt(session.getAttribute("pid").toString());
    	  UserSession userSession = (UserSession) session.getAttribute("user");


    	  if(VoteDAO.insertVote(userSession.getUid(), pid, team, 1)) {
    	  nextPage = "/knt/jsp/votemenu.jsp";
    	  } else {
    		  nextPage = "/knt/jsp/voting.jsp";
    	  }
    	  
    	  
    	  
      }
      
      //Handle the registration for a new user
      if (todo.equals("register")){
	
	String kuid = request.getParameter("kuid");
	UserDAO.insertUser(kuid);
	nextPage = "login.jsp";
	
}
      // Send the next website, should never been 'null' here
         response.sendRedirect(nextPage);
         return;

   }
}