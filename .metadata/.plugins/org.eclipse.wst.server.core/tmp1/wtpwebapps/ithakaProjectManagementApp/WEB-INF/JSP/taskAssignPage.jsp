<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Task Assign</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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
             	<h5>Stream Status</h5>
             	<table class="table table-responsive">
             		<tr>
             			<th>SETUP NO</th>
             			<th>STREAM NAME</th>
             			<th>USER NAME</th>
             			<th>REMARK</th>
             			<th>ACTIVITY</th>
             			<th>STATUS</th>
             		</tr>
             		<c:forEach items="${listTransaction }" var="transaction">
             			<tr>
             				<td>${transaction.setupNo}</td>
             				<td>${transaction.streamName}</td>
             				<td>${transaction.userName}</td>
             				<td>${transaction.activity_comment}</td>
             				<td>${transaction.activity}</td>
             				<td>${transaction.activity_status}</td>
             			</tr>
             		</c:forEach>
             	</table>
              </div>     
              <div class="center-col">
              <div class="form-div">
              									
              	<form name='taskAssign' action="/ithakaProjectManagementApp/user/task/assign" method="post">
					<div class="container" id="frm">
						<div class="form-group">
        					<label for="inputUsername">User Name</label>
        					<input type="text" class="form-control" 
        					placeholder="Enter Username" 
        					id="inputUsername" name="userName" value="${loggedUserName}"  required/>
    					</div>
    					<div class="form-group">
        					<label for="inputUserId">User Id</label>
        					<input type="text" class="form-control" 
        					placeholder="Enter UserId" 
        					id="inputUserId" name="userId" value="${userInfo.userId}"  required/>
    					</div>
    					<div class="form-group">
        					<label for="inputSetupNo">Setup Number</label>
        					<input type="text" class="form-control" 
        					placeholder="Setup Number" 
        					id="inputSetupNo" name="setupNo"  value="${setupNo}"  required/>
    					</div>
    					<div class="form-group">
        					<label for="inputActivity">Activity</label>
        					<select name="activity">
        						<option value="notStarted">Not Started</option>
        						<option value="transform">Transform</option>
        						<option value="profile">Profile</option>
        						<option value="quickTips">Quick Tips</option>
        						<option value="transform">Transform</option>
        						<option value="review-transform">Review-Transform</option>
        						<option value="review-profile">Review-Profile</option>
        						<option value="review-quickTips">Review-QuickTips</option>
        						<option value="review-transform">Review-Transform</option>
        					</select>
    					</div>
    					<div class="form-group">
        					<label for="inputSetupNo">Remark</label>
        					<br/>
        					<textarea rows="4" cols="50" name="activity_comment"></textarea>
    					</div>
    					<button type="submit" class="btn btn-primary">Assign</button>
    					<!-- <button type="reset" class="btn btn-primary">Reset</button> -->						
					</div>
				</form>
			  </div>
              </div>
              <div class="right-col ">
                    right side nav
             </div>
            <div class="footer">
                footer
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
    </body>
    </html>