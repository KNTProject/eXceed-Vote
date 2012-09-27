package test;


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
@WebServlet("/jsp/addtext")
public class Controller extends HttpServlet {
   private static final long serialVersionUID = 1L;
  
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      doPost(request, response);  // Same as doPost()
   }
 
   @Override
   protected void doPost(HttpServletRequest request,
         HttpServletResponse response) throws ServletException, IOException {
      // Retrieve the current session, or create a new session if no session exists.
      HttpSession session = request.getSession(true);

      // Retrieve the shopping cart of the current session.

 
      // For dispatching the next Page
      String nextPage = "";
      String todo = request.getParameter("todo");

      String text = request.getParameter("text");
      
      if (todo.equals("add")){
    	 
    	  DBStatements.setText(text);
    	  //not sure why I need the project path /knt/ this makes it not very dynamic
    	  nextPage = "/knt/jsp/test.jsp";
      }
 
      
         response.sendRedirect(nextPage);
         return;

   }
}