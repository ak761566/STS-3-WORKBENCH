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
	width: 20%;
	height: 90%;
	float: left;
	border: 1px solid;
	background-color: #f1f1f1;
}

.right-col {
	width: 0%;
	height: 90%;
	float: left;
	border: 1px solid;
	overflow: auto;
	background-color: #f1f1f1;
}

.center-col {
	width: 80%;
	height: 90%;
	float: left;
	border: 1px solid;
	overflow: auto;
	
}
form{
	width: 80%;
	align: center;
}

.form-div{
	width: 80%
	padding: 10%;
	margin:20px 500px, 0px, 500px;
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
                    <a class="navbar-brand" href="#">
						<img src="<c:url value="/assets/images/PorticoLogo.png"/>" width="80px" height="30px"/>
					</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="/ithakaProjectManagementApp/admin/project">Project</a></li>
                        <li><a href="/ithakaProjectManagementApp/admin/newUser">Team</a></li>
                        <li><a href="/ithakaProjectManagementApp/admin/streamReports">Project Report</a></li>
                        <li><a href="/ithakaProjectManagementApp/admin/teamReports">Team Report</a></li>
                    </ul>
                    	
                    <ul class="nav navbar-nav navbar-right">
                    	<li><a href="/ithakaProjectManagementApp/admin/dashboard">Admin [${loggedUserName}] Dashboard</a></li>
                       <li><a href="/ithakaProjectManagementApp/logout">Logout</a></li>
                    </ul>
                </div>
            </div>
             <div class="left-col">
             <div class="form-div" id="frm">
             <form name='newUserform' action="/ithakaProjectManagementApp/admin/search" method='POST'>
					<div class="container" id="frm">
						<div class="form-group">
        					<label for="inputSearch">Search Text</label>
        					<input type="text" class="form-control" placeholder="Please enter search text here"
        						 id="inputSearch" name="searchText" title="Search Text Required"  required/>
    					</div>
    					<button type="submit" class="btn btn-primary">SEARCH</button>
    					<button type="reset" class="btn btn-primary">Reset</button>						
					</div>
				</form>	
             </div>
              	
              </div>     
              <div class="center-col">
             <c:choose>
             	<c:when test="${not empty searchResults}">
             		Search Result
             		<table class="table table-bordered">
                  	<tr>
                  		<th>Setup No</th>
                  		<th>Stream Name</th>
                  		<th>Developer Name</th>
                  		<th>Activity</th>
                  		<th>Start Time</th>
                  		<th>Elapsed Days</th>
                  		<th>Activity Status</th>
                  		<th>Activity Remark</th>
                  	</tr>
                  	  <c:forEach items="${searchResults}" var="searchResult">
                  	  		<tr>
                  	  		<td>${searchResult.setupNo }</td>
                  	  		<td>${searchResult.streamName }</td>
                  	  		<td>${searchResult.userName}</td>
                  	  		<td>${searchResult.activity }</td>
                  	  		<td>${searchResult.activity_start_date}</td>
                  	  		<td>${searchResult.activity_elapsed_time}</td>
                  	  		<td>${searchResult.activity_status }</td>
                  	  		<td>${searchResult.activity_comment}</td>
                  	  	</tr>
                  	  </c:forEach>
                  </table>
              	</c:when>
             </c:choose>
             	
              <c:choose>
              	<c:when test="${empty runningStreams}">
              		No running activities!
              	</c:when>
              	<c:otherwise>
              	Running Streams 
              	<table class="table table-bordered">
                  	<tr>
                  		<th>Setup No</th>
                  		<th>Stream Name</th>
                  		<th>Developer Name</th>
                  		<th>Activity</th>
                  		<th>Start Time</th>
                  		<th>Elapsed Days</th>
                  		<th>Activity Status</th>
                  		<th>Activity Remark</th>
                  	</tr>
                  	  <c:forEach items="${runningStreams}" var="stream">
                  	  	<tr>
                  	  		<td>${stream.setupNo }</td>
                  	  		<td>${stream.streamName }</td>
                  	  		<td>${stream.userName}</td>
                  	  		<td>${stream.activity }</td>
                  	  		<td>${stream.activity_start_date}</td>
                  	  		<td>${stream.activity_elapsed_time}</td>
                  	  		<td>${stream.activity_status }</td>
                  	  		<td>${stream.activity_comment}</td>
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