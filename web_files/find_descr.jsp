<%@ include file="main.jsp" %>

<%

	descparam = request.getParameter("FIND_DESCR").trim();
  
	Sqlite sqlite = new Sqlite();
	out.println("<pre>" + sqlite.findDESCR(descparam) + "</pre>");

%>

