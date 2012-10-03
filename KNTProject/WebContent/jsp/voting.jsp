<%@page import="test.*" import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
int pid = Integer.parseInt(session.getAttribute("pid").toString());
session.setAttribute("pid", pid);

List<PollChoice> teams = PollChoiceDAO.getChoices(pid);

for (PollChoice p : teams){
	
	List<Team> team = TeamDAO.getTeam(p.getTid());	
	if (team.get(0) == null){} else {
	
%>

    <input type="radio" name="team" value="<%=team.get(0).getTid()%>"><%=team.get(0).getName()%><br>

<% 
	}
}
%>
<br><br>
        <input type="submit" value="Choose this Team">

</form>



</body>
</html>