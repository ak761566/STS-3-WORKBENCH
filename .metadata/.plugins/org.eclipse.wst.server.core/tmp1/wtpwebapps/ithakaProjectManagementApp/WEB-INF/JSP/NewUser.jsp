<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New User</title>
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
}

.right-col {
	width: 2%;
	height: 90%;
	float: left;
	border: 1px solid;
	overflow: auto;
	background-color: #f1f1f1;
}

.center-col {
	width: 49%;
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
.users-div{
	width: 100%
	padding: 10%;
	margin:20px 0px 100px 50px;
	float:auto;

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
                        <li><a href="/ithakaProjectManagementApp/admin/dashboard"><span class="glyphicon glyphicon-log-in"></span>Dashboard</a></li>
                        <li><li><a href="/ithakaProjectManagementApp/logout"><span class="glyphicon glyphicon-log-in"></span>LogOut</a></li>
                    </ul>
                </div>
            </div>
             <div class="left-col">
				<c:choose>
					<c:when test="${empty usersList }">
						<h3>No User has been Added.</h3>
					</c:when>
					<c:otherwise>
					<div class="users-div">
						<h3>Team Details</h3>
						<table class="table table-responsive">
							<tr>
								<th>User Name</th>
								<th>User Id</th>
								<th>Password</th>
								<th>Enabled</th>
								<th>Email Id</th>
							</tr>
							<c:forEach items="${usersList }" var="user">
								<tr>
									<td>${user.userName}</td>
									<td>${user.userId}</td>
									<td>${user.password}</td>
									<td>${user.enabled}</td>
									<td>${user.emailId}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					</c:otherwise>
				</c:choose>             
              </div>     
              <div class="center-col">
              <div class="form-div">
              	<form name='newUserform' action="/ithakaProjectManagementApp/admin/newUser/save" method='POST'>
					<div class="container" id="frm">
						<div class="form-group">
        					<label for="inputUsername">UserName</label>
        					<input type="text" class="form-control" placeholder="Enter Username"
        						 id="inputUsername" name="userName" pattern="[A-Za-z]{3,}" title="Name sould be minimum 3 character"  required/>
    					</div>
    					<div class="form-group">
        					<label for="inputUid">User Id</label>
        					<input type="text" class="form-control" placeholder="Enter UID" id="inputUid" pattern="[A-Za-z]{3,}" name="userId" 
        					title="Name sould be minimum 3 character" required/>
    					</div>
    					<div class="form-group">
        					<label for="inputPassword">Password</label>
        					<input type="password" class="form-control" placeholder="Enter Password" id="inputPassword" name="password"  required/>
    					</div>
    					<div class="form-group">
        					<label for="inputEnabled">Enabled</label>
        					<input type="text" class="form-control" placeholder="Enter Enabled" id="inputEnabled" pattern="[0-2]" 
        					title="Number should be in range 0-2" name="enabled"  required/>
    					</div>
    					<div class="form-group">
        					<label for="inputEmail">Email Id</label>
        					<input type="text" class="form-control" placeholder="Enter Email" id="inputEmail" name="emailId"  required/>
    					</div>
    					<button type="submit" class="btn btn-primary">Register</button>
    					<button type="reset" class="btn btn-primary">Reset</button>						
					</div>
				</form>
				</div>
              </div>
              <div class="right-col ">
             </div>
            <div class="footer">
            	<c:if test="${not empty message }">
            	  <h5>${message }</h5>
            	</c:if>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
    </body>
    
    </html>