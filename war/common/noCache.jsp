<%@ page import="java.util.*" %>
<%
Calendar objCal1=Calendar.getInstance();
Calendar objCal2=Calendar.getInstance();
objCal2.set(1970,0,1,0,0,0);
response.setDateHeader("Last-Modified",objCal1.getTime().getTime());
response.setDateHeader("Expires",objCal2.getTime().getTime());
response.setHeader("pragma","no-cache");
response.setHeader("Cache-Control","no-cache");
%>