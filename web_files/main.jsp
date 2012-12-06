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

	if (request.getParameter("FIND_IP") != null) ipparam = request.getParameter("FIND_IP").trim();
	if (request.getParameter("FIND_MAC") != null) macparam = request.getParameter("FIND_MAC").trim();
	if (request.getParameter("FIND_DESCR") != null) descparam = request.getParameter("FIND_DESCR").trim();
  
%>

<div id="container">
<div id="header">
<h1>IP PLAN - Internal</h1>
<p>
<form name="form1" method="post" action="run">
<input type="text" name="FIND_IP" value="<%=ipparam%>"><input type="submit" value="> IP адрес">
</form>
<form name="form3" method="post" action="run">
<input type="text" name="FIND_MAC" value="<%=macparam%>"><input type="submit" value="> MAC адрес">
</form>
<form name="form2" method="post" action="run">
<input type="text" name="FIND_DESCR" value="<%=descparam%>"><input type="submit" value="> Розетка,Описание">
</form>
</p>

</div>
</div>

<form name="form2" method="post" action="run">
<input type="submit" value="refresh DataBase"><input type="hidden" name="LOAD_DB" value="LOAD_DB">
</form>
