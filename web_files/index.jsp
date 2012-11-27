<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  


<%
 response.setStatus(301);
 response.setHeader( "Location", "http://localhost:8088/main.jsp" );
 response.setHeader( "Connection", "close" );
 %>

 
 <html>
 
 Не сработал редирект на <a href="http://localhost:8088/main.jsp">http://localhost:8088/main.jsp </a> 
 
 </html>