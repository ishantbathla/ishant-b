<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.loginapp.model.Model" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Signup</title>

<script language="javascript">

        //Start JavaScript Function

        function verify() {

            //for field must take some input

            if (document.form1.firstName.value == "") {
                alert("Please give the name");
                document.form1.name.forus();
                return false;
            }
            if (document.form1.lastName.value == "") {
                alert("Please give the name");
                document.form1.name.forus();
                return false;
            }

            //for field must take some input

            if (document.form1.email.value == "") {
                alert("Please give the email");
                return false;
            }
            
            // validaing email address
     		
    		if (document.form1.email.value.indexOf("@") < 1 ||  document.form1.email.value.indexOf("@")+2>=document.form1.email.value.length) {
        	alert("Not a valid e-mail address");
        	return false;
    		}
            // for field must take some input

            if (document.form1.age.value == "") {
                alert("Please give age");
                document.form1.age.focus();
                return false;
            }

            // alert thrown when age limit is below 18 and above 60

            if (document.form1.age.value < 18 || document.form1.age.value > 60) {
                alert("Please give Age range between 18 and 60");
                document.form1.age.focus();
                return false;
            }

            // Gender must be selected

            if (document.form1.gender[0].checked == false &&
                    document.form1.gender[1].checked == false) {
                alert("Please select gender");
                document.form1.gender.focus();
                return false;
            }

            //field must take some input

            if (document.form1.myaddress.value == "") {
                alert("Please give address");
                document.form1.myaddress.focus();
                return false;
            }

           

            //field must take some input

            if (document.form1.passd.value == "") {
                alert("Please give Password");
                document.form1.pass.focus();
                return false;
            }

            

          

            if (document.form1.r_passd.value == "") {
                alert("Please retype your password");
                document.form1.r_pass.focus();
                return false;
            }

            //password and confirm password must matched

            if ((document.form1.pass.value) != (document.form1.r_pass.value)) {
                alert("Your password does not match");
                document.form1.r_pass.value == "";
                document.form1.r_pass.focus();
                return false;
            }
            return( true );
        }
    </script>

<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	
	
	
%>

</head>
<body BGCOLOR="AQUA">

<!-- Creating Form -->


<form method="POST" action="profile" name="form1">
	<input type="hidden" name="method" 	value="CreateAccount"></input>
    <!-- Creating Table -->
    <table border="0" align="center" cellpadding="5">
    <tr>        <td>        </td>        <td>        </td>        </tr>
    <tr>        <td>        </td>        <td>        </td>        </tr>
    <tr>        <td>        </td>        <td>        </td>        </tr>
    <tr>        <td>        </td>        <td>        </td>        </tr>
    <tr>        <td>        </td>        <td>        </td>        </tr>
    <tr>        <td>        </td>        <td>        </td>        </tr>
    </table>
    <p align="center">Already have an account?<a href="/profile?method=alreadyRegistered">Login</a>
    <br></br>
    <% 
    
    String feildEmpty = null;
    String passMismatch = null;
    String invalidEmail = null;
    String newUser = null;
    if(session.getAttribute("userValue") != null)
    {
    	request.getRequestDispatcher("welcome.jsp").forward(request, response);
    }

    if(session.getAttribute("emptyFeild") != null)
    {
    	Model mo = (Model) session.getAttribute("emptyFeild");
    	feildEmpty = mo.getFeildEmpty(); 
    }
    if(session.getAttribute("passMismatch") != null)
    {
    	Model mo = (Model) session.getAttribute("passMismatch");
    	passMismatch = mo.getPassMismatch();
    }

    if(session.getAttribute("invalidEmail") != null)
    {
    	Model mo = (Model) session.getAttribute("invalidEmail");
    	invalidEmail = mo.getInvalidEmail();
    }
    
    if(session.getAttribute("newUser") != null)
    {
    	Model mo = (Model) session.getAttribute("newUser");
    	newUser = mo.getNewUser(); 
    }
    
    	if(passMismatch != null)
    	{
    		out.println(passMismatch);
    		
    	}
    	if(feildEmpty != null)
    	{
    		out.println(feildEmpty);
    	}
    	if(invalidEmail != null)
    	{
    		out.println(invalidEmail);
    	}
    	if(newUser != null)
    	{
    		out.println(newUser);
    	}
    	
    
    %>
    </p>
    <table border="0" align="center" cellpadding="5">

        <!-- Start First Row -->
        <tr>
            <!-- Creating First Column -->
            <td><strong>First Name:</strong></td>

            <!-- Creating Second Columns -->
            <td>
                <!-- TextBox -->
                <input type="text" name="firstName"/>
            </td>
            <!-- Close First Row -->
        </tr>
        <tr>
            <!-- Creating First Column -->
            <td><strong>Last Name:</strong></td>

            <!-- Creating Second Columns -->
            <td>
                <!-- TextBox -->
                <input type="text" name="lastName"/>
            </td>
            <!-- Close First Row -->
        </tr>
        
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
            <td><strong>Age:</strong></td>
            <td>
                <!-- Textbox -->
                <input type="text" name="age" size="2"/>
            </td>
        </tr>

        <tr>
            <td><strong>Gender:</strong></td>
            <td>
                <!-- Radio Butong -->
                <input type="radio" name="gender" value="Male"/>Male
                <input type="radio" name="gender" value="Female"/>Female

            </td>
        </tr>

        

        
        <tr>
            <td><strong>Address:</strong></td>
            <td>
                <!-- TextArea -->
                <textarea rows="5" cols="20" name="myaddress"/></textarea>
            </td>
        </tr>

        <tr>
            <td><strong>Password:</strong></td>
            <td>
                <!-- Password Field -->
                <input type="password" name="passd"/>
            </td>
        </tr>

        <tr>
            <td><strong>Retype Password:</strong></td>
            <td>
                <!-- Password Field -->
                <input type="password" name="r_passd"/>
            </td>
        </tr>

        <tr align="center">

            <td>
                <!--Submit Button, Function verify need to be called when we process
                submit button-->
                <input type="submit" value="signup" onClick="return (verify());"/>
            </td>

            <td>
                <!--Reset Button-->
                <input type="reset">
            </td>
        </tr>

        <!--Table Close-->
    </table>

    <!--Form Close -->
</form>

</body>
</html>