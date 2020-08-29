<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stream Assign</title>
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
	width: 39%;
	height: 90%;
	float: left;
	border: 1px solid;
	background-color: #f1f1f1;
	overflow: auto;
}

.right-col {
	width: 1%;
	height: 90%;
	float: left;
	border: 1px solid;
	overflow: auto;
	background-color: #f1f1f1;
}

.center-col {
	width: 60%;
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
</head>
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
                        <li><a href="#">About</a></li>
                        <li><a href="#">Projects</a></li>
                        <li><a href="#">Contact</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/ithakaProjectManagementApp/user/dashboard"><span class="glyphicon glyphicon-log-in">DashBoard</span></a></li>
                        <li><a href="/ithakaProjectManagementApp/logout"><span class="glyphicon glyphicon-log-in"></span>Logout</a></li>
                    </ul>
                </div>
            </div>
             <div class="left-col">
                    <h5>Stream Summary</h5>
                    <table class="table table-responsive">
                  	<tr>
                  		<th>Setup No</th>
                  		<th>Stream Name</th>
                  		<th>User Name</th>
                  		<th>Activity</th>
                  		<th>Current Status</th>
                  		<th>Comment</th>
                  	</tr>
                  </table>
                    
              </div>     
              <div class="center-col">
                  <h5>Running Streams</h5>
                  <!-- setupNo, streamName, batchCount, kickOffDate, dueDate -->
                  <!-- Links, check status, assign -->
                  <table class="table table-responsive">
                  	<tr>
                  		<th>Setup No</th>
                  		<th>Stream Name</th>
                  		<th>Batch Count</th>
                  		<th>Kick Off Date</th>
                  		<th>Due Date</th>
                  		<th>Check Status</th>
                  		<th>Assign</th>
                  	</tr>
                  	<c:forEach items="${inventorylist}" var="inventory">
                  		<tr>
                  			<td>${inventory.setupNo}</td>
                  			<td>${inventory.streamName}</td>
                  			<td>${inventory.batchCount}</td>
                  			<td>${inventory.kickOffDate}</td>
                  			<td>${inventory.dueDate}</td>
                  			<td><a href="${inventory.setupNo}">Status</a></td>
                  			<td><a href="/ithakaProjectManagementApp/user/task/${inventory.setupNo}">Assign to ${loggedUserName}</a></td>
                  		</tr>
                  	
                  	</c:forEach>
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