<%@page import="knt.exceedvote.model.*" import="knt.exceedvote.dao.*" 
import="knt.exceedvote.*" import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" import="knt.exceedvote.system.UserSession" %>
    
<%
	UserSession user = (UserSession) session.getAttribute("user");

	if (user == null) {
		response.sendRedirect("/knt/jsp/login.jsp");
		} else if (user.getUser().getTyid() != 2) {
		response.sendRedirect("/knt/jsp/votemenu.jsp");
	} else {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form name="delt" action="delteam" method="POST">
     <input type="hidden" name="todo" value="delteam">

<%

TeamDAO team = DaoFactory.getInstance("hibernate").getTeamDao();

List<Team> teams = team.getTeams();

for(Team curTeam : teams) {

%>

<input type="radio" name="team" value="<%=curTeam.getTid()%>"> <%=curTeam.getName()%> <br>

<% } %>

<input type="submit" value="Deleate">
</form>

</body>
</html>
<% } %>