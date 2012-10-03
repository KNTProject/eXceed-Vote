<%@page import="test.User"%>
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
eXceed Vote Login <br><br>


      <form name="login" action="login" method="POST">
         <input type="hidden" name="todo" value="login">
         
User:<input type="text" name="user" /> <br><br>
Password:<input type="password" name="password" />
<br>
        <input type="submit" onClick="Check()" value="Login">
      </form>
      

</body>
</html>