<%@ page contentType="text/html; charset=UTF-8" import="ai.aw.Sqlite" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>LAN IBRAE</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<style type="text/css"> 
html,body{margin:0;padding:0}
body{font: 76% arial,sans-serif}
p{margin:0 10px 10px}
a{display:block;color: #981793;padding:10px}
div#header h1{height:80px;line-height:80px;margin:0;
  padding-left:10px;background: #EEE;color: #79B30B}
div#content p{line-height:1.4}
div#navigation{background:#B9CAFF}
div#extra{background:#FF8539}
div#footer{background: #333;color: #FFF}
div#footer p{margin:0;padding:5px 10px}
 
div#wrapper{float:left;width:1350px}
div#content{margin: 0 450px}
div#navigation{float:left;width:450px;margin-left:-1350px}
div#extra{float:left;width:450px;margin-left:-450px}
div#footer{clear:left;width:1350px}
div#header{clear:left;width:1350px}
</style>
</head>
<body>

<%
  String ipparam="",macparam="",descparam = "";

  
  if ("IP".equals(request.getParameter("CRITERIA"))) {
    ipparam = request.getParameter("PARAMETER").trim();
  }
  if ("MAC".equals(request.getParameter("CRITERIA"))) {
    macparam = request.getParameter("PARAMETER").trim();
  }
  if ("DESCR".equals(request.getParameter("CRITERIA"))) {
    descparam = request.getParameter("PARAMETER").trim();
  }
  
%>

<div id="container">
<div id="header">
<h1>IP PLAN - Internal</h1>
<p>
<form name="form1" method="post" action="main.jsp">
<input type="text" name="PARAMETER" value="<%=ipparam%>"><input type="submit" value="> IP адрес"><input type="hidden" name="CRITERIA" value="IP">
</form>
<form name="form3" method="post" action="main.jsp">
<input type="text" name="PARAMETER" value="<%=macparam%>"><input type="submit" value="> MAC адрес"><input type="hidden" name="CRITERIA" value="MAC">
</form>
<form name="form2" method="post" action="main.jsp">
<input type="text" name="PARAMETER" value="<%=descparam%>"><input type="submit" value="> Розетка,Описание"><input type="hidden" name="CRITERIA" value="DESCR">
</form>
</p>

</div>
</div>

<%
if ("IP".equals(request.getParameter("CRITERIA"))) {
	out.println("PATHResource="+request.getSession().getServletContext().getResource("/")+"FIN");
	//out.println("PATHEntry="+request.getSession().getServletContext().getEntry("/")+"FIN");
	//out.println("PATHDataFile="+request.getSession().getServletContext().getDataFile("/")+"FIN");
	
	Sqlite sqlite = new Sqlite();
	out.println(sqlite.showIP(ipparam));
}	

%>
<!--
///////////////////////////////////////////////////////////////////////////////
/////// IP search /////////////////////////////////////////////////////////////
  if ("IP".equals(request.getParameter("CRITERIA"))) {

    $query = "SELECT * FROM ipdesc WHERE ip = '{$ipparam}'";
    mysql_query($query);
    $result = mysql_query($query);
    while ($row = mysql_fetch_array($result, MYSQL_NUM)) {
      printf("IP: %s  descr: %s <br>", $row[0], $row[2]);
    }
    mysql_free_result($result);


//    $query = "SELECT * FROM arp WHERE ip IN (SELECT ip FROM arp WHERE mac IN (SELECT mac FROM arp WHERE ip='{$ipparam}'))";
    $query = "SELECT * FROM arp WHERE ip = '{$ipparam}' ORDER BY timelast DESC";
    mysql_query($query);
    $result = mysql_query($query);
    while ($row = mysql_fetch_array($result, MYSQL_NUM)) {
      printf("IP: %s  MAC: %s  last: %s <br>", $row[1], $row[3], $row[5]); 
      $query2 = "SELECT * FROM ports WHERE mac = '{$row[3]}' ORDER BY timelast DESC";
      $result2 = mysql_query($query2);
      while ($row2 = mysql_fetch_array($result2, MYSQL_NUM)) {
        printf("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ipsw: %s  port: %s  last: %s <br>", $row2[4], $row2[3], $row2[6]);

        $query3 = "SELECT * FROM crossport WHERE ipsw = '{$row2[4]}' AND port = '{$row2[3]}'"; 
        $result3 = mysql_query($query3);
        while ($row3 = mysql_fetch_array($result3, MYSQL_NUM)) {
          printf("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Розетка: %s <br>", $row3[1]);

        }
        mysql_free_result($result3);

        $query3 = "SELECT * FROM ports WHERE ipsw = '{$row2[4]}' AND port = '{$row2[3]}' ORDER BY timelast DESC";
        $result3 = mysql_query($query3);
        if(mysql_num_rows($result3) > 1) {
        while ($row3 = mysql_fetch_array($result3, MYSQL_NUM)) {
          printf("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MAC: %s  last: %s <br>", $row3[2], $row3[6]);

        }
        }
        mysql_free_result($result3);

      } 
      mysql_free_result($result2); 
    }
    mysql_free_result($result);

  }
///////////////////////////////////////////////////////////////////////////////

-->

