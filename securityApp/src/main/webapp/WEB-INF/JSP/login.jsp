<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
  <%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login</title>
</head>
<body onload="document.f.j_username.focus();">
<!-- <body> -->
	<core:if test="${SPRING_SECURITY_LAST_EXCEPTION != null }">
		<div class="errorblock">
			Unsuccessful login attempt, try again. <br/> Caused:
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</core:if>	
<div class="container">
	<div class="row">
		<form name='f' action="<core:url value='j_spring_security_check'/>" method='POST'>
			<table>
				<tr>
					<td>User Name</td>
					<td><input type="text" name="j_username"/>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="j_password"/>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="LogIn"/></td>
				</tr>
			</table>
			
		</form>
		
		
	</div>

</div>

</body>
</html>