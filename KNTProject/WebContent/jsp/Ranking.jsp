<%@page import="knt.exceedvote.model.Ranking"%>
<%@page import="knt.exceedvote.dao.DaoFactory"%>
<%@page import="knt.exceedvote.dao.RankingDAO"%>
<%@page import="knt.exceedvote.system.UserSession" import="knt.exceedvote.system.Logging"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ranking</title>
</head>
<body>
<% 
	
	RankingDAO rankDAO = new Ranking();
	Ranking[] ranking = rankDAO.getTeamWinner();
	for(Ranking rank : ranking){
		System.out.println(rank.getPid() + " " + rank.getTid() + " " + rank.getVotes());
	}
%>
</body>
</html>