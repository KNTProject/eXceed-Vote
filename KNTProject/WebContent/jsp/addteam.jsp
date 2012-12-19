<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="knt.exceedvote.system.UserSession"%>
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

    <form method="post" action="UploadTeamServlet"
        enctype="multipart/form-data">
 
Team Name: <input type="text" name="tname" > <br><br>
          Select file to upload: <input type="file" name="dataFile"
            id="fileChooser" /><br />
            Select file to upload: <input type="file" name="dataFile"
            id="fileChooser" /><br />
            Select file to upload: <input type="file" name="dataFile"
            id="fileChooser" /><br />
<input type="submit" value="Submit" />

</form>
    <h2>${requestScope.message}</h2>
</body>
</html>
<% } %>