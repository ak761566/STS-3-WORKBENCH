<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>admin</title>
</head>
<body>
<h1>Name: ${title }</h1>
	<h1>Description : ${message}</h1>
	
	<c:if test="${pageContext.request.userPrincipal.name !=null }">
		<h2>
			Welcome : ${user } |
			<a href="<c:url value="/logout" />">LogOut</a> 
		</h2>
	</c:if>

</body>
</html>