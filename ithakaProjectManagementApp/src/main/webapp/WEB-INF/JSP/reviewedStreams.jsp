<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reviewed Streams</title>
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
	width: 49%;
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
	width: 50%;
	height: 90%;
	float: left;
	border: 1px solid;
}

.footer {
	max-width: 100%;
	height: 5%;
}

form {
	width: 40%;
	align: center;
}

.form-div {
	width: 100% padding: 10%;
	margin: 20px 0px 100px 200px;
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
             	<h5>Stream Pre-Delivery Status</h5>
             	<table class="table table-responsive">
             		<tr>
             			<th>SETUP NO</th>
             			<th>STREAM NAME</th>
             			<th>DELIVERED BY</th>
             			<!-- <th>REMARK</th> -->
             			<th>DELIVERED ON</th>
             			<th>STATUS</th>
             		</tr>
             		
             	</table>
              </div>     
              <div class="center-col">
              <div class="form-div">
              									
              	<form name='taskAssign' action="/ithakaProjectManagementApp/user/reviewPoints/assign" method="post">
					<div class="container" id="frm">
						<div class="form-group">
        					<label for="inputUsername">User Name</label>
        					<input type="text" class="form-control" 
        					placeholder="Enter Username" 
        					id="inputUsername" name="userName" value="${userName}"  required/>
    					</div>
    					<div class="form-group">
        					<label for="inputUserId">User Id</label>
        					<input type="text" class="form-control" 
        					placeholder="Enter UserId" 
        					id="inputUserId" name="userId" value="${loggedUserId}"  required/>
    					</div>
    					<div class="form-group">
        					<label for="inputSetupNo">Setup Number</label>
        					<input type="text" class="form-control" 
        					placeholder="Setup Number" 
        					id="inputSetupNo" name="setupNo"  required/>
    					</div>
    					<div class="form-group">
        					<label for="inputStreamName">Stream Name</label>
        					<input type="text" class="form-control" 
        					placeholder="Stream Name" 
        					id="inputSetupNo" pattern="[a-zA-Z\s\-]+" name="revised_stream"  required/>
    					</div>
    					<div class="form-group">
        					<label for="inputActivity">Activity</label>
        					<select name="activity">
        						<option value="reviewPoints-transform">Customer-reviewPoints-Transform</option>
        						<option value="reviewPoints-profile">Customer-reviewPoints-Profile</option>
        						<option value="reviewPoints-quickTips">Customer-reviewPoints-QuickTips</option>
        						<option value="reviewPoints-jira">Customer-reviewPoints-Jira's</option>
        						<option value="reviewPoints-Other">Customer-reviewPoints-Other</option>
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
             </div>
            <div class="footer">
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>