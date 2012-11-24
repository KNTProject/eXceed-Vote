<%@page import="knt.exceedvote.dao.hibernate.DaoFactoryImpl"%>
<%@page import="java.util.Iterator" import="knt.exceedvote.system.UserSession"%>
<%@page import="knt.exceedvote.dao.*" import="knt.exceedvote.model.*" import="java.util.List"%>
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
<title>Insert title here</title>
</head>
<body>

<h1>Simple Vote</h1>

<form name="vote" action="vote" method="POST">
<input type="hidden" name="todo" value="voteteam">
<%

	Poll poll = (Poll) session.getAttribute("poll");

	Iterator<Team> teams = poll.getTeams().iterator();

while(teams.hasNext()){
	Team team = teams.next();

	%>


    <input type="radio" name="team" value="<%=team.getTid()%>"><%=team.getName()%><br>

<% 
	}

%>
<br><br>
        <input type="submit" value="Choose this Team">

</form>



</body>
</html>
<% } %>