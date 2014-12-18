<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="com.loginapp.model.Model" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	
	
%>

<script language="javascript">

        //Start JavaScript Function

        function verify() {
        	
        	 if (document.form1.email.value == "") {
                 alert("Please give the email");
                 return false;
             }
             
             // validaing email address
      		
     		if (document.form1.email.value.indexOf("@") < 1 ||  document.form1.email.value.indexOf("@")+2>=document.form1.email.value.length) {
         	alert("Not a valid e-mail address");
         	return false;
     		}
             
     		//field must take some input

            if (document.form1.passd.value == "") {
                alert("Please give Password");
                document.form1.pass.focus();
                return false;
            }
        	
        }
</script>

</head>
<body BGCOLOR="AQUA">


<form method="POST" action="profile" name="form1">
	<input type="hidden" name="method" 	value="login"></input>
    <!-- Creating Table, having 11rows and 2 columns. -->
    <h2><p align="center">Welcome to my Test Assignment</p></h2>
    <table border="0" align="center" cellpadding="5">
    	<tr>        <td>        </td>        <td>        </td>        </tr>
        <tr>        <td>        </td>        <td>        </td>        </tr>
        <tr>        <td>        </td>        <td>        </td>        </tr>
        <tr>        <td>        </td>        <td>        </td>        </tr>
        <tr>        <td>        </td>        <td>        </td>        </tr>
        <tr>        <td>        </td>        <td>        </td>        </tr>
        <tr>        <td>        </td>        <td>        </td>        </tr>
        <tr>        <td>        </td>        <td>        </td>        </tr>
        <tr>        <td>        </td>        <td>        </td>        </tr>
        <tr>        <td>        </td>        <td>        </td>        </tr>
        <tr>        <td>        </td>        <td>        </td>        </tr>
        <tr>        <td>        </td>        <td>        </td>        </tr>
        <tr>        <td>        </td>        <td>        </td>        </tr>
    </table>
    <p align="center">
    <% 
    String userFound = null;
	String incorrectPassword = null;
	String invalidEmail = null;
    if(session.getAttribute("userValue") != null)
    {
    	request.getRequestDispatcher("welcome.jsp").forward(request, response);
    }

    if(session.getAttribute("invalidEmail") != null)
    {
    	Model mo = (Model) session.getAttribute("invalidEmail");
    	invalidEmail = mo.getInvalidEmail();
    }
    
    if(session.getAttribute("emailRegistered") != null)
    {
    	Model mo = (Model) session.getAttribute("emailRegistered");
    	userFound = mo.getEmailFound(); 
    }
    
    if(session.getAttribute("incorrectPassword") != null)
    {
    	Model mo = (Model) session.getAttribute("incorrectPassword");
    	incorrectPassword = mo.getIncorrectPassword(); 
    }
	
	if(userFound != null)
	{
		out.println(userFound);
	}
	
	if(incorrectPassword != null)
	{
		out.println(incorrectPassword);
	}
	
	if(invalidEmail != null)
	{
		out.println(invalidEmail);
	}
	%>
	</p>
	<table border="0" align="center" cellpadding="5">
	
	<tr>
        <!-- Creting First Columns -->
        <td><strong>Email:</strong></td>

        <!-- Creating Second Columns -->
        <td>
            <!-- Textbox -->
            <input type="text" name="email" placeholder="me@example.com"/>
        </td>
        <!-- Close Second row -->
    </tr>
	
	<tr>
            <td><strong>Password:</strong></td>
            <td>
                <!-- Password Field -->
                <input type="password" name="passd""/>
            </td>
        </tr>
	<tr align="center">

			<td>
			</td>
            <td>
                <!--Submit Button, Function verify need to be called when we process
                submit button-->
                <input type="submit" value="login" onClick="return (verify());"/>
            </td>

            
        </tr>
	
	</table>
	<p align="center">New User?<a href="/profile?method=newUser">Signup</a>
	</p>
	
</form>	
	
    
</body>
</html>