<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User DashBoard</title>
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
	width: 2%;
	height: 90%;
	float: left;
	border: 1px solid;
	background-color: #f1f1f1;
}
.center-col {
	width: 96%;
	height: 90%;
	float: left;
	border: 1px solid;
	overflow: auto;
	
}

.right-col {
	width: 2%;
	height: 90%;
	float: left;
	border: 1px solid;
	overflow: auto;
	background-color: #f1f1f1;
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
                    <a class="navbar-brand" href="#">
						<img src="<c:url value="/assets/images/PorticoLogo.png"/>" width="80px" height="30px"/>
					</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <!-- <li><a href="#">Home</a></li> -->
                        <li class="active"><a href="/ithakaProjectManagementApp/user/assignStream">Assign Task</a></li>
                        <li><a href="/ithakaProjectManagementApp/user/history">Work-History</a></li>
                        <li><a href="/ithakaProjectManagementApp/user/reviewedPage">Add RevisedStreams</a></li>
                        <!-- <li><a href="/ithakaProjectManagementApp/admin/project">Project</a></li>
                        <li><a href="/ithakaProjectManagementApp/admin/newUser">Team</a></li> -->
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                    	<li><a href="/ithakaProjectManagementApp/user/dashboard"><span class="glyphicon glyphicon-log-in">[${loggedUserName}] DashBoard</span></a></li>
                       <li><a href="/ithakaProjectManagementApp/logout">Logout</a></li>
                    </ul>
                </div>
            </div>
             <div class="left-col">
              	
              </div>     
              <div class="center-col">
              	<c:choose>
              		<c:when test="${empty runningRevisedStreams}"></c:when>
              		<c:otherwise>
              			Portico FeedBack Implementation!
              			<table class="table table-bordered">
                  			<tr>
                  				<th>Setup No</th>
                  				<th>Stream Name</th>
                  				<th>User Name</th>
                  				<th>Activity</th>
                  				<th>Start Time</th>
                  				<th>Time ELapsed</th>
                  				<th>Activity Status</th>
                  				<th>Update Activity Status</th>
                  			</tr> 
                  			<c:forEach items="${runningRevisedStreams}" var="transaction">
						    <tr>
                  				<td>${transaction.setupNo}</td>
                  				<td>${transaction.revised_stream}</td>
                  				<td>${transaction.userName}</td>
                  				<td>${transaction.activity}</td>
                  				<td>${transaction.activity_start_date}</td>
                  				<td>${transaction.activity_elapsed_time}</td>
                  				<td>${transaction.activity_status}</td>
                  				<td>
                  				<div class="dropdown btn-group">
                  					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
      									Bulk Action <span class="caret"></span>
    								</a>
    										<ul class="dropdown-menu">
                  								<li><a href="/ithakaProjectManagementApp/user/updateActivityStatus/pause/${transaction.setupNo}/${transaction.activity}">Pause</a></li>
                  								<li><a href="/ithakaProjectManagementApp/user/updateActivityStatus/resume/${transaction.setupNo}/${transaction.activity}">Resume</a></li>
                  								<li><a href="/ithakaProjectManagementApp/user/updateActivityStatus/addIssue/${transaction.setupNo}/${transaction.activity}">Next Issue</a></li>
                  								<li><a href="/ithakaProjectManagementApp/user/updateActivityStatus/complete/${transaction.setupNo}/${transaction.activity}">Activity Complete</a></li>
                  								<li><a href="/ithakaProjectManagementApp/user/updateActivityStatus/deliverStream/${transaction.setupNo}">Deliver Revised Stream</a></li>
                  							</ul>
    							</div>
                  			</td>
                  			<%-- <td>${transaction.activity_comment}</td> --%>
                  		</tr>             	
                  	</c:forEach>
                  	</table>
              		</c:otherwise>
              	</c:choose>
              	<c:choose>
              		<c:when test="${empty runningStreams}">
              			Your DashBoard is Empty!
              			Please visit to Assign Stream page.
              		</c:when>
              		<c:otherwise>
              		Running Streams
              		<table class="table table-bordered">
                  	<tr>
                  		<th>Setup No</th>
                  		<th>Stream Name</th>
                  		<th>User Name</th>
                  		<th>Activity</th>
                  		<th>Start Time</th>
                  		<!-- <th>Time ELapsed</th> -->
                  		<th>Activity Log</th>
                  		<th>Activity Status</th>
                  		<th>Update Activity Status</th>
                  	</tr> 
 					<c:forEach items="${runningStreams}" var="transaction">
                  		<tr>
                  			<td>${transaction.setupNo}</td>
                  			<td>${transaction.streamName}</td>
                  			<td>${transaction.userName}</td>
                  			<td>${transaction.activity}</td>
                  			<td>${transaction.activity_start_date}</td>
                  			<%-- <td>${transaction.activity_elapsed_time}</td> --%>
                  			<td>${transaction.activity_comment}</td>
                  			<td>${transaction.activity_status}</td>
                  			<td>
                  				<div class="dropdown btn-group">
                  					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
      									Bulk Action <span class="caret"></span>
    								</a>
    									<ul class="dropdown-menu">
                  							<li><a href="/ithakaProjectManagementApp/user/updateActivityStatus/pause/${transaction.setupNo}/${transaction.activity}">Pause</a></li>
                  							<li><a href="/ithakaProjectManagementApp/user/updateActivityStatus/resume/${transaction.setupNo}/${transaction.activity}">Resume</a></li>
                  							<li><a href="/ithakaProjectManagementApp/user/updateActivityStatus/addIssue/${transaction.setupNo}/${transaction.activity}">Next Issue</a></li>
                  							<li><a href="/ithakaProjectManagementApp/user/updateActivityStatus/complete/${transaction.setupNo}/${transaction.activity}">Activity Complete</a></li>
                  							<%-- <li><a href="/ithakaProjectManagementApp/user/updateActivityStatus/hold/${transaction.setupNo}/${transaction.activity}">On Hold</a></li> --%>
                  							<li><a href="/ithakaProjectManagementApp/user/updateActivityStatus/moreDetails/${transaction.setupNo}/${transaction.activity}">Stream Details</a></li>
                  							<li><a href="/ithakaProjectManagementApp/user/updateActivityStatus/deliverStream/${transaction.setupNo}">Deliver Stream</a></li>
                  						</ul>
    							</div>
                  			</td>
                  			<%-- <td>${transaction.activity_comment}</td> --%>
                  		</tr>
                  	</c:forEach>
                  </table>
                 </c:otherwise>
              	</c:choose>
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