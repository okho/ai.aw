<%@ include file="main.jsp" %>

<%

	macparam = request.getParameter("FIND_MAC").trim();
  
	Sqlite sqlite = new Sqlite();
	out.println("<pre>" + sqlite.findMAC(macparam) + "</pre>");

%>


