<%@page import="knt.exceedvote.dao.PollDAO" import="knt.exceedvote.model.Poll" import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; 	charset=utf-8" />
    <meta http-equiv="Content-Language" content="en" />
    <meta http-equiv="imagetoolbar" content="no" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="author" content="www.studio7designs.com" />
    <meta name="robots" content="all, follow" />
    <meta name="googlebot" content="index, follow, snippet, archive" />
    <link rel="stylesheet" href="./stylesheets/screen.css" type="text/css"  media="screen, projection" />
    <link rel="shortcut icon" href="favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="page">
	
      <h3 class="header">
        <a href="./index.html" title="Home">
          eXceed Camp#9
         
          <span>
          </span></a></h3>
		<ul class="topmenu">
        <li>
        <a href="contact.html" onfocus="blur()">Contact</a></li>       
        <li>
        <a href="project.html" onfocus="blur()">Projects</a></li>
        <li>
        <a href="index.html" onfocus="blur()">Main</a></li>
        </ul>
		
		<div class="main">
			<% List<Poll> polls = PollDAO.getPolls(null);

			for (Poll p : polls) {
			%>
		
			<div class="bluebox">
          <a href="#" class="big">
            <img src="./images/<%=p.getName()%>.jpg" width="288" height="224" alt="Sample Photo" title="Sample Photo" /></a>
          <div class="right">
            <form name="pollchoose" action="pollchoose" method="POST">
		         <input type="hidden" name="todo" value="pollchoose">
				 <input type="hidden" name="pollid" value="<%=p.getPid()%>">				
        		 <h1 ><font size="10" ><a ><%=p.getName() %></a></font></h1> 
        		 
     		</form>
            <p>
            <%=p.getDescription() %> </p>
            
          </div>
          <!-- /right-->
          <div class="cleaner">
            &nbsp;
          </div>
         </div>
         
         
         
         <!-- /bluebox-->
			<% 	
			}
			%>
			
			
			
			
		</div>
	</div>

</body>
</html>