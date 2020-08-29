<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
   <%@ taglib prefix="core"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>403</title>
</head>
<body>
	<h1>HTTP Status 403 - Access is denied</h1>
	<core:choose>
		<core:when test="${empty username }">
			<h2>You do not have permission to access this page!</h2>
		</core:when>
		<core:otherwise>
		  <h2>Username : ${username} <br/>
                    You do not have permission to access this page!</h2>
		</core:otherwise>
	</core:choose>
</body>
</html>