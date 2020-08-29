<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stream Report</title>
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
	width: 12%;
	height: 90%;
	float: left;
	border: 1px solid;
	background-color: #f1f1f1;
}
.center-col {
	width: 86%;
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
                       <!--  <li class="active"><a href="#">Home</a></li>
                        <li><a href="#">About</a></li>
                        <li><a href="#">Projects</a></li>
                        <li><a href="#">Contact</a></li> -->
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/ithakaProjectManagementApp/admin/dashboard">Admin [${loggedUserName}] Dashboard</a></li>
                       <li><a href="/ithakaProjectManagementApp/logout">Logout</a></li>
                    </ul>
                </div>
            </div>
             <div class="left-col">
             	Streams Statements
				<div class="form-div">
					<div class="dropdown btn-group">
                  		<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
      						Report Action <span class="caret"></span>
    					</a>
    					<ul class="dropdown-menu">
                  			<li><a href="/ithakaProjectManagementApp/report/currentMonth">Current Month</a></li>
                  			<li><a href="/ithakaProjectManagementApp/report/currentMonthPreviousMonth">Current Month plus Previous Month</a></li>
                  		 </ul>
    				</div>
				
			 </div>      
            </div>     
              <div class="center-col">
              <c:choose>
              	<c:when test="${empty completedStreams}">
              	<div class="form-div">
              			Monthly Delivered Streams
              		<table class="table table-responsive">
						<tr>
							<th>Setup No</th>
							<th>Stream Name</th>
							<th>Stream Type</th>
							<th>Batch Count</th>
							<th>Kick Off Date</th>
							<th>Delivery Date</th>
							<th>Elapsed Days</th>
							<th>Monthly Elapsed Days</th>
							<th>Stream Status</th>
						</tr>       
						<c:forEach items="${MonthlyCompletedStreams}" var="completedStream">
							<tr>
								<td>${completedStream.setupNo}</td>
								<td>${completedStream.streamName}</td>
								<td>${completedStream.streamType}</td>
								<td>${completedStream.batchCount}</td>
								<td>
									<fmt:formatDate value="${completedStream.kickOffDate}" pattern="dd MMM, yyyy"/>
								</td>
								<td>
									<fmt:formatDate value="${completedStream.deliveryDate}" pattern="dd MMM, yyyy"/>
								</td>
								<td>${completedStream.elapsedDays}</td>
								<td>${completedStream.monthlyElapsedDays}</td>
								<td>${completedStream.streamStatus}</td>
							</tr>
						</c:forEach>		
              		</table>
              	</div>
              	</c:when>
              	<c:otherwise>
              	
              	<div class="form-div">
              		Delivered Streams
              		<table class="table table-responsive">
						<tr>
							<th>Setup No</th>
							<th>Stream Name</th>
							<th>Stream Type</th>
							<th>Batch Count</th>
							<th>Kick Off Date</th>
							<th>Delivery Date</th>
							<th>Elapsed Days</th>
							<!-- <th>Monthly Elapsed Days</th> -->
							<th>Stream Status</th>
						</tr>       
						<!-- completedStreams 
							select setupNo, streamName, streamType, batchCount, kickOffDate, deliveryDate, streamStatus
						-->       
						<c:forEach items="${completedStreams}" var="completedStream">
							<tr>
								<td>${completedStream.setupNo}</td>
								<td>${completedStream.streamName}</td>
								<td>${completedStream.streamType}</td>
								<td>${completedStream.batchCount}</td>
								<%-- <td>${completedStream.kickOffDate}</td> --%>
								<td>
									<fmt:formatDate value="${completedStream.kickOffDate}" pattern="dd MMM, yyyy"/>
								</td>
								<%-- <td>${completedStream.deliveryDate}</td> --%>
								<td>
									<fmt:formatDate value="${completedStream.deliveryDate}" pattern="dd MMM, yyyy"/>
								</td>
								<td>${completedStream.elapsedDays}</td>
								<%-- <td>${completedStream.monthlyElapsedDays}</td> --%>
								<td>${completedStream.streamStatus}</td>
							</tr>
						</c:forEach>		
              		</table>
              	</div>
              </c:otherwise>
             </c:choose>
              	<div class="form-div">
              		Running Streams
              		<table class="table table-responsive">
						<tr>
							<th>Setup No</th>
							<th>Stream Name</th>
							<th>Batch Count</th>
							<th>Stream Type</th>
							<th>Kick Off Date</th>
							<th>Elapsed Days</th>
							<th>Days Elapsed<br>(current Month)</th>
						</tr>
						<c:forEach items="${inCompleteStreams}" var="incompletedStream">
							<tr>
								<td>${incompletedStream.setupNo}</td>
								<td>${incompletedStream.streamName}</td>
								<td>${incompletedStream.batchCount}</td>
								<td>${incompletedStream.streamType}</td>
								<%-- <td>${incompletedStream.kickOffDate}</td> --%>
								<td>
									<fmt:formatDate value="${incompletedStream.kickOffDate}" pattern="dd MMM, yyyy"/>
								</td>
								<td>${incompletedStream.elapsedDays}</td>
								<td>${incompletedStream.monthlyElapsedDays}</td>
							</tr>
						</c:forEach>
					</table>
              	</div>
              
              <!-- <div class="form-div">
              	<form name='newUserform' action="/ithakaProjectManagementApp/admin/newUser/save" method='POST'>
					<div class="container" id="frm">
						<div class="form-group">
        					<label for="inputUsername">UserName</label>
        					<input type="text" class="form-control" placeholder="Enter Username"
        						 id="inputUsername" name="userName" pattern="[A-Za-z]{3,}" title="Name sould be minimum 3 character"  required/>
    					</div>
    			
    					<button type="submit" class="btn btn-primary">Register</button>
    					<button type="reset" class="btn btn-primary">Reset</button>						
					</div>
				</form> -->
				</div>
              </div>
              <div class="right-col ">
             </div>
            <div class="footer">
            	<%-- <c:if test="${not empty message }">
            	  <h5>${message }</h5>
            	</c:if> --%>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>