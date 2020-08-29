<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Task Status</title>
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
              </div>     
              <div class="center-col">
              	<!-- 
              		model.addObject("setupNo", setupNo);
					model.addObject("activity", activity);
					model.addObject("userName", user.getUserName());
              	 -->
                <div class="form-div">
              	<form name='newUserform' action="/ithakaProjectManagementApp/updateActivityStatus/completeStatus" method='POST'>
					<div class="container" id="frm">
						<div class="form-group">
        					<label for="setupNumber">Setup Number</label>
        					<input type="text" class="form-control"
        						 id="setupNumber" name="setupNo" value="${setupNo}" required/>
    					</div>
    					<div class="form-group">
        					<label for="username">User Name</label>
        					<input type="text" class="form-control" id="userName" value="${userName}"
        						 name="userName" required/>
    					</div>
    					<div class="form-group">
        					<label for="activity">Activity</label>
        					<input type="text" class="form-control"
        					 id="activity" name="activity" value="${activity}"  required/>
    					</div>
    					<div class="form-group">
        					<label for="state">Activity-State</label>
        					
        					<input type="text" class="form-control"
        					 id="activity" name="activity_status" value="${activity_status}"  required/>
    					</div>
    					<div class="form-group">
        					<label for="comment">Comment</label>
        					<br/>
        					<textarea rows="6" cols="60" name="activity_comment" required="required"></textarea>
    					</div>
    					<button type="submit" class="btn btn-primary">Submit</button>
    					<!-- <button type="reset" class="btn btn-primary">Reset</button> -->						
					</div>
				</form>
				</div>
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