<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>hello</title>
</head>
<body>
	<h1>Name: ${title}</h1>
	<h1>Description : ${message}</h1>
	
	<br/>
<%-- <sec:authorize access="hasRole('ROLE_USER')">
	<c:url value="j_spring_security_logout" var="logoutUrl"/>
	
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	</form>

</sec:authorize> --%>
	<h2> <a href="/securityDBtestApp/admin/welcome">Click Here For Admin Login</a></h2>
</body>
</html>