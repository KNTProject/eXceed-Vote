<%@page import="knt.exceedvote.com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>eXceed Vote Login</title>
<script type="text/javascript">
function Check () {

	
	
}
</script>
<%

User user = (User) session.getAttribute("user");

%>
</head>
<body>
eXceed Vote Register <br><br>


      <form name="register" action="register" method="POST">
         <input type="hidden" name="todo" value="register">
         
KU User ID:<input type="text" name="kuid" /> <br><br>
<br>
        <input type="submit" value="Register">
      </form>
      <br>
You receive a password for your login.
<br>
<a href="login.jsp">back</a>
</body>
</html>