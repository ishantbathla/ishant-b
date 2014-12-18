<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.loginapp.model.Model" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1"> -->
<title>All Visitor Record</title>
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
%>
</head>


<body BGCOLOR="SILVER">
<%@ include file="header.jsp" %>

<!-- Creating Table -->
    <table border="0" align="center" cellpadding="7">
	<tr>        <td>        </td>        <td>        </td>        </tr>
	<tr>        <td>        </td>        <td>        </td>        </tr>
	<tr>        <td>        </td>        <td>        </td>        </tr>
	<tr>        <td>        </td>        <td>        </td>        </tr>
	<tr>        <td>        </td>        <td>        </td>        </tr>
	<h2><p align="center">Please click on the email id you want to edit or delete</p></h2>
    <%
    int userSize = 0;
    Model model = new Model();
    String lastname="";
   	String age="";
   	String gender="";
   	String email="";
   	String firstName="";
   	String address="";
    if(session.getAttribute("userValue") == null)
    {
    	request.getRequestDispatcher("login.jsp").forward(request, response);
    	
    }
    else
    {
    	model = (Model)session.getAttribute("allUser");
    	userSize = model.getAllEmail().size();
    	
    }
    if(userSize != 0)
    {
    %>
    <tr align="center">
    <td align="center"><strong>First name</strong></td>
    <td align="center"><strong>Last name</strong></td>
    <td align="center"><strong>Email</strong></td>
    <td align="center"><strong>Age</strong></td>
    <td align="center"><strong>Gender</strong></td>
    <td align="center"><strong>Address</strong></td>
    </tr>
    <% 
    }
    for(int allUser = 0; allUser < userSize; allUser++)
    {
    firstName= model.getAllFirstName().get(allUser);
    lastname = model.getAllLastName().get(allUser);
    email = model.getAllEmail().get(allUser);
    age = model.getAllAge().get(allUser);
    gender = model.getAllGender().get(allUser);
    address = model.getAllAddress().get(allUser);
    %>
    <tr align="center">
    <td><%=firstName %></td>
    <td><%=lastname %></td>
    <td><a href="/profile?method=otherProfile&id=<%=email %>"><%=email %></td>
    <td><%=age %></td>
    <td><%=gender %></td>
    <td><%=address %></td>
    </tr>
    <%
    }
    %>
    </table>



</body>
</html>