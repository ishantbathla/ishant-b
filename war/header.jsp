<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.loginapp.model.Model" %>

<%

if(session.getAttribute("userValue") == null)
{
	request.getRequestDispatcher("login.jsp").forward(request, response);
	
}

Model mo = (Model) session.getAttribute("userValue");
String fname = mo.getFirstName();
%>

<div style='float: right;'>
	<a href="/profile?method=homePage">Welcome <%=fname%></a>|
	<a href="/profile?method=viewProfile">My Profile</a>|
	<a href="/profile?method=viewAllProfile">See All Profile</a>|
	<a href="/profile?method=signout&logout=true">Sign Out</a>
</div>