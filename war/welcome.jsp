<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.loginapp.model.Model" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	
	
	
%>

</head>
<body BGCOLOR="SILVER">
<%
if(session.getAttribute("userValue") == null)
{
	request.getRequestDispatcher("login.jsp").forward(request, response);
	
}
%>
<%@ include file="header.jsp" %>
<h2>Welcome after login or Account Creation</h2><br/>
<h3>From the above header you can navigate to your profile where you can view, update and delete your profile or you can sign out of the application</h3>

</body>
</html>