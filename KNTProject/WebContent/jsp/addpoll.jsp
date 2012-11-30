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

    <form method="post" action="UploadServlet"
        enctype="multipart/form-data">
 
Question (header): <input type="text" name="pname" > <br><br>
Description:
 <textarea name="description" cols="25" rows="5">
Enter your description here...
</textarea><br>

        Select file to upload: <input type="file" name="dataFile"
            id="fileChooser" /><br />


<input type="submit" value="Submit" />

</form>

</body>
</html>
<% } %>