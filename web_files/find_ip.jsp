<%@ include file="main.jsp" %>

<%

	ipparam = request.getParameter("FIND_IP").trim();
  
	Sqlite sqlite = new Sqlite();
	out.println(sqlite.findIP(ipparam));

%>



