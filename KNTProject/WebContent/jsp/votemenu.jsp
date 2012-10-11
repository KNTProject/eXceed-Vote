<%@page import="knt.exceedvote.ui.PollDAO" import="knt.exceedvote.model.Poll" import="java.util.List"%>

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

<% List<Poll> polls = PollDAO.getPolls(null);

for (Poll p : polls) {
	%>
<h2>Poll-ID: <%=p.getPid() %><br></h2>
<h3>Question: <%=p.getName() %><br></h3>
Description: <%=p.getDescription() %><br>

<form name="pollchoose" action="pollchoose" method="POST">
		         <input type="hidden" name="todo" value="pollchoose">
				 <input type="hidden" name="pollid" value="<%=p.getPid()%>">				
        <input type="submit" value="Choose this poll">
      </form>
	<br><br>
<% 	
}
%>

</body>
</html>