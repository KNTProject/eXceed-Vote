
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" import="java.io.*,java.util.*"%>
<%@ page import="test.*"%>

<%

//String path = PathPreparer.getRealPath(request);
//ArrayList<Integer> id = DBStatement.getID(path);
//ArrayList<String> text = DBStatement.getText(path);

List<Test> data = DBStatements.getText();


%>
<table border=1>
<tr><td>ID</td><td>Text</td></tr>
<%

for (Test curData : data) {
%>
	<tr><td><%= curData.getId() %></td><td><%= curData.getText() %></td></tr>
<%	

}
%>
</table>

      <form name="AddForm" action="addtext" method="POST">
         <input type="hidden" name="todo" value="add">
         
Add text:<input type="text" name="text" />

        <input type="submit" value="Add">
      </form>

</form>