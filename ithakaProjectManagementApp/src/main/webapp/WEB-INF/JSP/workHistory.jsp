<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Work History</title>
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
	width: 49%;
	height: 90%;
	float: left;
	border: 1px solid;
	background-color: #f1f1f1;
	overflow:auto;
}

.right-col {
	width: 49%;
	height: 90%;
	float: left;
	border: 1px solid;
	overflow: auto;
	background-color: #f1f1f1;
	overflow:auto;
}

.center-col {
	width: 2%;
	height: 90%;
	float: left;
	border: 1px solid;
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
                        <!-- <li class="active"><a href="#">Home</a></li>
                        <li><a href="#">About</a></li>
                        <li><a href="#">Projects</a></li>
                        <li><a href="#">Contact</a></li> -->
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                    	 <li><a href="/ithakaProjectManagementApp/user/dashboard"><span class="glyphicon glyphicon-log-in">[${loggedUserName}] DashBoard</span></a></li>
                         <li><a href="/ithakaProjectManagementApp/logout"><span class="glyphicon glyphicon-log-in"></span>Logout</a></li>
                    </ul>
                </div>
            </div>
             <div class="left-col">
             	<c:choose>
             		<c:when test="${empty completedStreamsList}">
             			No stream delivered by you!
             		</c:when>
             		<c:otherwise>
                   		List of Streams which are delivered by You!
	                   <table class="table table-responsive">
	                    <tr>
	                    	<th>Setup No</th>
	                    	<th>Stream Name</th>
	                    	<th>Stream Type</th>
	                    	<th>batchCount</th>
	                    	<th>Kick Off Date</th>
	                    	<th>Delivery Date</th>
	                    </tr>
	                   	<c:forEach items="${completedStreamsList}" var="stream">
	                   		<c:choose>
	                   			<%-- <c:when test="${fn:contains(stream.streamType, 'PushedBack-stream')}">
	                   			</c:when>
	                   			 --%>
	                   			<c:when test="${stream.streamType == 'PushedBack-Stream'}">
	                   			</c:when>
	                   			<c:otherwise>
	                   			<tr>
	                   				<td>${stream.setupNo}</td>
	                   				<td>${stream.streamName}</td>
	                   				<td>${stream.streamType}</td>
	                   				<td>${stream.batchCount}</td>
	                   				<td>${stream.kickOffDate}</td>
	                   				<td>${stream.deliveryDate}</td>
	                   			</tr>	
	                   			</c:otherwise>
	                   		</c:choose>
	                   	</c:forEach>
	                   </table>
	                </c:otherwise>
     	        </c:choose>
              </div>     
              <div class="center-col">
              </div>
              <div class="right-col ">
              	<c:choose>
                   <c:when test="${empty finishedActivity}">
                   	No Record found of streams activities completed by you!		
                   </c:when>
                   <c:otherwise>
                   	Streams Activity Completed by You!
                   	<table class="table table-responsive">
                    <tr>
                    	<th>Setup No</th>
                    	<th>Stream Name</th>
                    	<th>Activity</th>
                    	<th>Activity Elapsed Time</th>
                    </tr>
                    <c:forEach items="${finishedActivity}" var="activity">
                     <tr>
                    		<td>${activity.setupNo}</td>
                    		<td>${activity.streamName}</td>
                    		<td>${activity.activity}</td>
                    		<td>${activity.activity_elapsed_time}</td>
                    	</tr>
                     
                     
                    	
                    </c:forEach>
                   </table>
                  </c:otherwise>
                </c:choose>
                   
              <!-- Reviewed Streams -->
              
              <c:choose>
             		<c:when test="${empty finishedRevisedActivity}">
             		</c:when>
             		<c:otherwise>
                   		Portico Reviewed Streams
	                   <table class="table table-responsive">
	                    <tr>
	                    	<th>Setup No</th>
	                    	<th>Stream Name</th>
	                    	<th>Stream Type</th>
	                    	<th>Kick Off Date</th>
	                    	<th>Delivery Date</th>
	                    </tr>
	                   	<c:forEach items="${finishedRevisedActivity}" var="stream">
	                   			<tr>
	                   				<td>${stream.setupNo}</td>
	                   				<td>${stream.revised_stream}</td>
	                   				<td>Portico Reviewed Stream</td>
	                   				<td>${stream.activity_start_date}</td>
	                   				<td>${stream.activity_end_date}</td>
	                   			</tr>
	                   	</c:forEach>
	                   </table>
	                </c:otherwise>
     	        </c:choose>
                    
             </div>
            <div class="footer">
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
