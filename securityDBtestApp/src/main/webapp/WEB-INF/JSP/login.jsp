<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body onload="document.loginform.username.focus();">
<h3>Custom Login Form</h3>


<form name='loginform' action="<core:url value="j_spring_security_check" />" method='POST'>
	<h1>Spring Security Login Form (Database Authentication)</h1>

	<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' /></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit"
				  value="submit" /></td>
				  <td><input name="reset" type="reset"
				  value="reset" /></td>
			</tr>
	</table>

	<%-- <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> --%>

	

</form>

</body>
</html>