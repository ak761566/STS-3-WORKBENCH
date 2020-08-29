<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stream Delivery</title>
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
	width: 20%;
	height: 90%;
	float: left;
	border: 1px solid;
	background-color: #f1f1f1;
}

.right-col {
	width: 20%;
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
}

.footer {
	max-width: 100%;
	height: 5%;
}
form{
	width: 40%;
	align: center;
}

.form-div{
	width: 100%
	padding: 10%;
	margin:20px 0px 100px 200px;
}
</style>

</head>
<body>
	<div class="container">
		<div class="navbar  navbar-inverse">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">
						<img src="<c:url value="/assets/images/PorticoLogo.png"/>" width="80px" height="30px"/>
				</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Projects</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/ithakaProjectManagementApp/user/dashboard"><span class="glyphicon glyphicon-log-in">[${loggedUserName}] DashBoard</span></a></li>
                    <li><a href="/ithakaProjectManagementApp/logout"><span class="glyphicon glyphicon-log-in"></span>Logout</a></li>

				</ul>
			</div>
		</div>
		<div class="left-col"></div>
		<div class="center-col">
		<!-- 
			setupNo
			streamName
			activity
			activity_status
			userName
			
		if any activity status is WIP then stream can't be deliver. If all activities are complete then
		calculate elapsed days by substracting initial start_date of first acitivity being picked and today date
		 -->
		 	
				<c:choose>
					<c:when test="${empty wipActivities}">
						<!-- ${completeActivites} -->
								<c:choose>
									<c:when test="${empty completeActivites}">
											<!-- model.addObject("elapsedDays", elapsedDays);
									model.addObject("userName", user.getUserName());
									model.addObject("readyStream", readyStream); 
								-->
									<c:if test="${not empty  readyStream}">
										<table class="table table-responsive">
											<tr>
												<th>Setup No</th>
												<th>Stream Name</th>
												<th>Elapsed Days</th>
												<th>Status</th>
												<th>Delivery Date</th>
											</tr>
												<tr>
													<td>${readyStream.setupNo}</td>
													<td>${readyStream.streamName}</td>
													<td>${readyStream.elapsedDays}</td>
													<td>${readyStream.streamStatus}</td>
													<td>${readyStream.deliveryDate}</td>
												</tr>
												<tr>
													<td colspan="5">Stream Delivered Successfully!</td>
												</tr>
										</table>
									</c:if>
							
									</c:when>
							<c:otherwise>
								<table class="table table-responsive">
									<tr>
										<th>Setup No</th>
										<th>Stream Name</th>
										<th>Activity</th>
										<th>Activity Status</th>
										<th>Dev Name</th>
									</tr>
									
									<c:forEach items="${completeActivites}" var="activity">
										<tr>
											<td>${activity.setupNo }</td>
											<c:set var="setupNo" scope="session" value="${activity.setupNo }"/>
											<td>${activity.streamName}</td>
											<c:set var="streamName" scope="session" value="${activity.streamName}"/>
											<td>${activity.activity}</td>
											<td>${activity.activity_status}</td>
											<td>${activity.userName}</td>
										</tr>
									</c:forEach>
								</table>
							<div class="form-div">
								<form action="/ithakaProjectManagementApp/user/streamComplete/<c:out value="${setupNo}"/>/<c:out value="${streamName}"/>" method="post">
									<label for="setupNo">Setup Number</label>
									<input type="text" class="form-control" placeholder="Setup Number"
        						 id="setupNo" name="setupNo" value="<c:out value="${setupNo}"/>" required/>
        						  
        						  <label for="streamName">Stream Name</label>
        							<input type="text" class="form-control"
        							 id="streamName" name="streamName"
        						  	title="Stream Name" value="<c:out value="${streamName}"/>"  required/>
									<br/>
									<br/>
									<!-- select datediff(now(), min(activity_start_date)) from transaction; -->
									<button type="submit" class="btn btn-primary">Deliver Stream</button>
								</form>
							</div>
						</c:otherwise>
					</c:choose>
					</c:when>
					<c:otherwise>
						<table class="table table-responsive">
							<tr>
								<th>Setup No</th>
								<th>Stream Name</th>
								<th>Activity</th>
								<th>Activity_Status</th>
								<th>Activity Comment</th>
								<th>User Name</th>
							</tr>
			
						<c:forEach items="${wipActivities}" var="activity">
							<tr>
								<td>${activity.setupNo}</td>
								<td>${activity.streamName}</td>
								<td>${activity.activity}</td>
								<td>${activity.activity_status}</td>
								<td>${activity.activity_comment}</td>
								<td>${activity.userName}</td>
							</tr>
						</c:forEach>
					</table>
			
					</c:otherwise>
				</c:choose>
				
						 	
		</div>
		<div class="right-col"></div>
		<div class="footer">
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>