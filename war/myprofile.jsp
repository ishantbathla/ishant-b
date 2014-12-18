<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.loginapp.model.Model" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Profile</title>

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

          return true;

         
        }
    </script>

<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	
	
	
%>


</head>
<body BGCOLOR="SILVER">
<%@ include file="header.jsp" %>
<form method="POST" action="profile" name="form1">
	<input type="hidden" name="method" 	value="UpdateAccount"></input>
    <!-- Creating Table -->
    <table border="0" align="center" cellpadding="5">
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
    
    
    String lname="";
	String age="";
	String gender="";
	String email="";
	String password="";
	String address="";
	String male="";
	String female="";
    String feildEmpty="";
    String proUpdated="";
    if(session.getAttribute("userValue") == null)
    {
    	request.getRequestDispatcher("login.jsp").forward(request, response);
    	
    }
    else
    {
    	
    	lname = mo.getLastName();
    	age = mo.getAge();
    	gender = mo.getGender();
    	email = mo.getEmail();
    	password = mo.getPassword();
    	address = mo.getAddress();
    	proUpdated = mo.getProfileUpdated();
    }

    if(session.getAttribute("emptyFeild") != null)
    {
    	mo = (Model) session.getAttribute("emptyFeild");
    	feildEmpty = mo.getFeildEmpty(); 
    }
    if(feildEmpty != null)
    {
    	out.println(feildEmpty);
    }
    if(proUpdated != "" && proUpdated != null)
    {
    	out.println(proUpdated);
    	mo.setProfileUpdated("");
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
                <input type="text" name="firstName" value=<%=fname %>></input>
            </td>
            <!-- Close First Row -->
        </tr>
        <tr>
            <!-- Creating First Column -->
            <td><strong>Last Name:</strong></td>

            <!-- Creating Second Columns -->
            <td>
                <!-- TextBox -->
                <input type="text" name="lastName" value=<%=lname %>></input>
            </td>
            <!-- Close First Row -->
        </tr>
        
		<tr>
        <!-- Creting First Columns -->
        <td><strong>Email:</strong></td>

        <!-- Creating Second Columns -->
        <td>
            <input type="text" name="em" value=<%=email %> disabled></input>
        </td>
        <!-- Close Second row -->
        </tr>

        <tr>
            <td><strong>Age:</strong></td>
            <td>
                <!-- Textbox -->
                <input type="text" name="age" size="2" value=<%=age %>></input>
            </td>
        </tr>

        <tr>
            <td><strong>Gender:</strong></td>
            <td>
            	<% 
            	if(gender.equalsIgnoreCase("male"))
            	{
            		male="checked";
            	}
            	else
            	{
            		female="checked";
            	}
            	
            	%>
                <!-- Radio Butong -->
                <input type="radio" name="gender" value="Male" <%=male %>/>Male
                <input type="radio" name="gender" value="Female"<%=female %>/>Female
                
            </td>
        </tr>

        

        
        <tr>
            <td><strong>Address:</strong></td>
            <td>
                <!-- TextArea -->
                
                <textarea rows="5" cols="20" name="myaddress" ><%=address %></textarea>
            </td>
        </tr>

		
		<tr>
        <!-- Creting First Columns -->
        <td><strong>Password:</strong></td>

        <!-- Creating Second Columns -->
        <td>
            <input type="password" name="passd" value=<%=password %>></input>
        </td>
        <!-- Close Second row -->
        </tr>

        <tr>
		
		        
        <tr align="center">
			<td>
			</td>
            <td>
                <!--Submit Button, Function verify need to be called when we process
                submit button-->
                <input type="submit" value="Update Profile" onClick="return (verify());"/>
            </td>

            
        </tr>

        <!--Table Close-->
    </table>
<p align="center"><a href="/profile?method=deleteProfile">Delete your Profile</a></p>
    <!--Form Close -->
</form>


</body>
</html>