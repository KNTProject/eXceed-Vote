<%@page import="knt.exceedvote.dao.hibernate.DaoFactoryImpl,knt.exceedvote.dao.*,knt.exceedvote.model.Team,java.util.List"
import="knt.exceedvote.system.UserSession,org.joda.time.DateTime,java.sql.Blob,java.io.*"

%>
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

	<!-- TIMER Logic and Style 
	
	We should try to push this timer code in a separate file
	-->

<style type="text/css">
.numbers {
    border-style: ridge;    /* options are none, dotted, dashed, solid, double, groove, ridge, inset, outset */
    border-width: 2px;
    border-color: #666666;  /* change the border color using the hexadecimal color codes for HTML */
    background: #222222;    /* change the background color using the hexadecimal color codes for HTML */
    padding: 2px 0px;
    width: 30px;
    text-align: center; 
    font-family: Arial; 
    font-size: 11px;
    font-weight: bold;    /* options are normal, bold, bolder, lighter */
    font-style: normal;   /* options are normal or italic */
    color: #FFFFFF;       /* change color using the hexadecimal color codes for HTML */
}
.title {    /* the styles below will affect the title under the numbers, i.e., “Days”, “Hours”, etc. */
    border: none;    
    padding: 0px;
    width: 55px;
    text-align: center; 
    font-family: Arial; 
    font-size: 10px; 
    font-weight: normal;  /* options are normal, bold, bolder, lighter */
    color: black;       /* change color using the hexadecimal color codes for HTML */
    background: transparent;    /* change the background color using the hexadecimal color codes for HTML */
}
#table {
    width: 400px;
    border: none;    /* options are none, dotted, dashed, solid, double, groove, ridge, inset, outset */
    margin: 0px auto;
    position: relative;    /* leave as "relative" to keep timer centered on your page, or change to "absolute" then change the values of the "top" and "left" properties to position the timer */
    top: 0px;        /* change to position the timer */
    left: 0px;        /* change to position the timer; delete this property and it's value to keep timer centered on page */
}
</style>

<script type="text/javascript">

/*
Count down until any date script-
By JavaScript Kit (www.javascriptkit.com)
Over 200+ free scripts here!
Modified by Robert M. Kuhnhenn, D.O. 
on 5/30/2006 to count down to a specific date AND time,
on 10/20/2007 to a new format, and 1/10/2010 to include
time zone offset.
*/

var current="Winter is here!";   //-->enter what you want the script to display when the target date and time are reached, limit to 20 characters
var year=2013;    //-->Enter the count down target date YEAR
var month=12;      //-->Enter the count down target date MONTH
var day=21;       //-->Enter the count down target date DAY
var hour=18;      //-->Enter the count down target date HOUR (24 hour clock)
var minute=38;    //-->Enter the count down target date MINUTE
var tz=-5;        //-->Offset for your timezone in hours from UTC (see http://wwp.greenwichmeantime.com/index.htm to find the timezone offset for your location)

//    DO NOT CHANGE THE CODE BELOW!
var montharray=new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");

function countdown(yr,m,d,hr,min){
    theyear=yr;themonth=m;theday=d;thehour=hr;theminute=min;
    var today=new Date();
    var todayy=today.getYear();
    if (todayy < 1000) {todayy+=1900;}
    var todaym=today.getMonth();
    var todayd=today.getDate();
    var todayh=today.getHours();
    var todaymin=today.getMinutes();
    var todaysec=today.getSeconds();
    var todaystring1=montharray[todaym]+" "+todayd+", "+todayy+" "+todayh+":"+todaymin+":"+todaysec;
    var todaystring=Date.parse(todaystring1)+(tz*1000*60*60);
    var futurestring1=(montharray[m-1]+" "+d+", "+yr+" "+hr+":"+min);
    var futurestring=Date.parse(futurestring1)-(today.getTimezoneOffset()*(1000*60));
    var dd=futurestring-todaystring;
    var dday=Math.floor(dd/(60*60*1000*24)*1);
    var dhour=Math.floor((dd%(60*60*1000*24))/(60*60*1000)*1);
    var dmin=Math.floor(((dd%(60*60*1000*24))%(60*60*1000))/(60*1000)*1);
    var dsec=Math.floor((((dd%(60*60*1000*24))%(60*60*1000))%(60*1000))/1000*1);
    if(dday<=0&&dhour<=0&&dmin<=0&&dsec<=0){
        document.getElementById('count2').innerHTML=current;
        document.getElementById('count2').style.display="block";
        document.getElementById('count2').style.width="390px";
        document.getElementById('dday').style.display="none";
        document.getElementById('dhour').style.display="none";
        document.getElementById('dmin').style.display="none";
        document.getElementById('dsec').style.display="none";
        document.getElementById('days').style.display="none";
        document.getElementById('hours').style.display="none";
        document.getElementById('minutes').style.display="none";
        document.getElementById('seconds').style.display="none";
        document.getElementById('spacer1').style.display="none";
        document.getElementById('spacer2').style.display="none";
        return;
    }
    else {
        document.getElementById('count2').style.display="none";
        document.getElementById('dday').innerHTML=dday;
        document.getElementById('dhour').innerHTML=dhour;
        document.getElementById('dmin').innerHTML=dmin;
        document.getElementById('dsec').innerHTML=dsec;
        setTimeout("countdown(theyear,themonth,theday,thehour,theminute)",1000);
    }
}
</script>



<script src="lang/jquery-1.8.3.js"></script>
<script type="text/javascript" language="javascript">
 
$(function() {
    var language = '<%=user.getLanguage()%>';
    $.ajax({
        url: 'lang/languages.xml',
        success: function(xml) {
            $(xml).find('translation').each(function(){
                var id = $(this).attr('id');
                var text = $(this).find(language).text();
                $("." + id).html(text);
            });
        }
    });
});
</script>







<meta http-equiv="Content-Type" content="text/html; 	charset=utf-8" />
    <meta http-equiv="Content-Language" content="en" />
    <meta http-equiv="imagetoolbar" content="no" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="author" content="www.studio7designs.com" />
    <meta name="robots" content="all, follow" />
    <meta name="googlebot" content="index, follow, snippet, archive" />
    <link rel="stylesheet" href="/knt/filestore/css/screen.css" type="text/css"  media="screen, projection" />
    <link rel="shortcut icon" href="favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>eXceed Vote</title>
</head>
<%

	DateTime cd = user.getCountdown();
%>
<body onload="countdown(<%=cd.getYear() %>,<%= cd.getMonthOfYear() %>,<%=cd.getDayOfMonth() %>,<%=cd.getHourOfDay() %>,<%=cd.getMinuteOfHour()%>)">




	<div class="page">
	
	<div class="header1">
	<form name="lang" action="translate" method="POST">
	<input type="hidden" name="todo" value="lang">
        <input type="submit" name="lang" value="thai"/>
      	|
      	<input type="submit" name="lang" value="english"/>
      </form>
      </div>
	
      <h3 class="header">
      
      
        <a href="#" title="Home">
          eXceed Camp#9
         
          <span>
          </span></a></h3>
		<ul class="topmenu">
        <li><a href="contact.jsp" onfocus="blur()"><div class="contact">Contact</div></a></li>       
        <li><a href="teams.jsp" onfocus="blur()"><div class="team">Teams</div></a></li>
		<li><a href="notvoted.jsp"><div class="nvoted">Not yet voted</div></a></li>
		<li><a href="voted.jsp"><div class="avoted">Already voted</div></a></li>        
		<li><a href="votemenu.jsp"><div class="menu">Main</div></a></div></li>        
	 </ul>
		<div class="main">
			<% 

			

			//for (Poll p : user.getAllPolls()) {
			for(Team t : user.getAllTeams()){

			%>
			
		
			<div class="bluebox">
          <a href="javascript:newWin('/knt/filestore/teamimage/<%= t.getImage1() %>', 'Bigger Image', '737', '599')" class="big">
            <img src="/knt/filestore/teamimage/<%= t.getImage1() %>" width="288" height="224" alt="Sample Photo" title="Sample Photo" /></a>
          <div class="right">
          <h1><font size="4"><%=t.getName() %></font></h1>
          
          <a href="javascript:newWin('/knt/filestore/teamimage/<%= t.getImage2() %>', 'Bigger Image', '737', '599')" class="small1">
              <img src="/knt/filestore/teamimage/<%= t.getImage2() %>" width="149" height="109" alt="Sample Photo" title="Sample Photo" /></a>
            <a href="javascript:newWin('/knt/filestore/teamimage/<%= t.getImage3() %>', 'Bigger Image', '737', '599')" class="small2">
              <img src="/knt/filestore/teamimage/<%= t.getImage3() %>" width="149" height="109" alt="Sample Photo" title="Sample Photo" /></a>
          
          </div>
          <!-- /right-->
          <div class="cleaner">
            &nbsp;
          </div>
         </div>
         
         
         
         <!-- /bluebox-->
			<% 	
			}
			%>
			
			
			
			
		</div>
	</div>
	
	<!-- TIMER Table -->
<table id="table" border="0">
    <tr>
        <td align="center" colspan="6"><div class="numbers" id="count2" style="padding: 10px; "></div></td>
    </tr>
    <tr id="spacer1">
        <td align="center" ><div class="title" ></div></td>
        <td align="center" ><div class="numbers" id="dday"></div></td>
        <td align="center" ><div class="numbers" id="dhour"></div></td>
        <td align="center" ><div class="numbers" id="dmin"></div></td>
        <td align="center" ><div class="numbers" id="dsec"></div></td>
        <td align="center" ><div class="title" ></div></td>
    </tr>
    <tr id="spacer2">
        <td align="center" ><div class="title" ></div></td>
        <td align="center" ><div class="title" id="days">Days</div></td>
        <td align="center" ><div class="title" id="hours">Hours</div></td>
        <td align="center" ><div class="title" id="minutes">Minutes</div></td>
        <td align="center" ><div class="title" id="seconds">Seconds</div></td>
        <td align="center" ><div class="title" ></div></td>
    </tr>
</table>

<script type="text/javascript">
	function newWin(url,name, width, height) { 
	window.open(url,name,'scrollbars=yes,resizable=yes, width=' + width + ',height='+height);
	}</script>

</body>
</html>
<% } %>