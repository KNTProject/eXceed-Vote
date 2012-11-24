<%@page import="knt.exceedvote.system.UserSession"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%
	UserSession user = (UserSession) session.getAttribute("user");
	if (user == null) {
		response.sendRedirect("/knt/jsp/login.jsp");
	} else {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>eXceed Vote First Login</title>

</head>
<body>
eXceed Vote Login <br><br>


      <form name="login" action="login" method="POST">
         <input type="hidden" name="todo" value="changepw">
         
New Password:<input type="password" name="password" /> <br><br>

<br>
        <input type="submit" value="Set password and login">
      </form>

</body>
</html>
<% } %>