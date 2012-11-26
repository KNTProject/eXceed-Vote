<%@page import="knt.exceedvote.system.UserSession" import="knt.exceedvote.system.Logging"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
	UserSession user = (UserSession) session.getAttribute("user");
	if (user != null) {
		Logging.logout(request.getRemoteAddr(), user.getUser());
		request.getSession().invalidate();
	} 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>eXceed Vote Login</title>
<link rel="stylesheet" href="normalize.css">
<link rel="stylesheet" href="style.css">
</head>
<body>
<section class="loginform cf">
		<form name="login" action="login" method="POST" accept-charset="utf-8">
		<input type="hidden" name="todo" value="login">
			<ul>
				<li>
					<h1>Username</h1>
					<input type="text" name="user" placeholder="username" required>
				</li>
				<li>
					<h1>Password</h1>
					<input type="password" name="password" placeholder="password" required>
				</li>
				<div style="">
				<li>
					
				</li>
				<li>
					<input type="submit" value="Login">
					<a href="register.jsp"><input type="submit" value="Register"></a>
				</li>
				</div>
			</ul>
		</form>
	</section>

<!-- <form name="login" action="login" method="POST">
         <input type="hidden" name="todo" value="login">
         
User:<input type="text" name="user" /> <br><br>
Password:<input type="password" name="password" />
<br>
        <input type="submit" value="Login">
      </form>
      <br>
<a href="register.jsp">Registration</a> -->
      


</body>
</html>