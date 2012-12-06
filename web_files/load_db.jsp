<%@ include file="main.jsp" %>

<%

    Sqlite sqlite = new Sqlite();
    //sqlite.LoadDB();
    out.println("<pre>" + sqlite.LoadDB() + "</pre>");

%>



