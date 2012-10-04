package test;


import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 

public class Controller extends HttpServlet {
   private static final long serialVersionUID = 1L;
  
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      doPost(request, response);  // Same as doPost()
   }
 
   @Override
   public void doPost(HttpServletRequest request,
         HttpServletResponse response) throws ServletException, IOException {
      // Retrieve the current session, or create a new session if no session exists.
      HttpSession session = request.getSession(true);

      // Retrieve the shopping cart of the current session.

 
      // For dispatching the next Page
      String nextPage = "";
      String todo = request.getParameter("todo");


      if (todo.equals("login")){
    	 
    	  String user = request.getParameter("user");
    	  String password = request.getParameter("password");   	  
    	  
    	  if (user == null || password == null || user.equals("") || password.equals("")){
    		  System.out.println("wrong null");
    		  nextPage = "badlogin";
    		  response.sendRedirect(nextPage);
    		  return;
    	  } 

    	  if (user.endsWith(" ")){
    		  user = user.substring(0, user.length() - 1);
    		  System.out.println(user);
    	  }
    
    	  
    	  if(!LoginDAO.checkUser(user)) {
    		  System.out.println("wrong id");
    		  nextPage = "badlogin";
    		  response.sendRedirect(nextPage);
    		  return;
    	  }
    	  if(!LoginDAO.checkpassword(user, password)){
    		  System.out.println("wrong pw");
    		  nextPage = "badlogin";
    		  response.sendRedirect(nextPage);
    		  return;  
    	  }
    User userobject = new User();
    userobject.setUid(user);
    session.setAttribute("user", userobject);	 
    nextPage = "/knt/jsp/votemenu.jsp";
      }
      
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
      if (todo.equals("voteteam")){
    	  
    	  int team = Integer.parseInt(request.getParameter("team").toString());
    	  int pid = Integer.parseInt(session.getAttribute("pid").toString());
    	  User user = (User) session.getAttribute("user");
    	  System.out.println(team);
    	  System.out.println(pid);
    	  System.out.println(user.getUid());

    	  VoteDAO.insertVote(user.getUid(), pid, team, 1);
    	  nextPage = "/knt/jsp/votemenu.jsp";
    	  
      }

         response.sendRedirect(nextPage);
         return;

   }
}