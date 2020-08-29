<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
.container {
	width: 100%;
	height: 800px;
	margin: 0;
	padding: 0;
}

.navbar {
	width: 100%;
	height: 5%;
	border-radius: 0;
}

.left-col {
	width: 10%;
	height: 90%;
	float: left;
	border: 1px solid;
	background-color: #f1f1f1;
}

.right-col {
	width: 5%;
	height: 90%;
	float: left;
	border: 1px solid;
	overflow: auto;
	background-color: #f1f1f1;
}

.center-col {
	width: 85%;
	height: 90%;
	float: left;
	border: 1px solid;
	overflow: auto;
	
}

.footer {
	max-width: 100%;
	height: 5%;
}
</style>
<body>
<div class="container">
            <div class="navbar  navbar-inverse">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand" href="#">Logo</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="/ithakaProjectManagementApp/admin/project">Project</a></li>
                        <li><a href="/ithakaProjectManagementApp/admin/newUser">Team</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                       <li><a href="/ithakaProjectManagementApp/logout">Logout</a></li>
                    </ul>
                </div>
            </div>
             <div class="left-col">
              	Admin [${loggedUserName}] Dashboard
              </div>     
              <div class="center-col">
                  <table class="table table-bordered">
                  	<tr>
                  		<th>Setup No</th>
                  		<th>Stream Name</th>
                  		<th>Devloper Name</th>
                  		<th>Activity</th>
                  		<th>Start Time</th>
                  		<th>End Time</th>
                  		<th>Activity Status</th>
                  		<th>Activity Remark</th>
                  	</tr>  
                  </table>
              </div>
              <div class="right-col ">
                    
             </div>
            <div class="footer">
                footer
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
    </body>

</html>