<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>login</title>

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
}

.right-col {
	width: 20%;
	height: 90%;
	float: left;
}

.center-col {
	width: 60%;
	height: 90%;
	float: left;
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
                    </ul>
                </div>
            </div>
             <div class="left-col">
              </div>     
              <div class="center-col">
              	<div class="form-div">
                 <form name='loginform' action="<c:url value="j_spring_security_check" />" method='POST'>
					<c:if test="${param.error == 'true'}">
						Login Failed <br />
						Reason: ${SessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
					</c:if>
					<div class="container" id="frm">
						<div class="form-group">
        					<label for="inputUsername">User Id</label>
        					<input type="text" class="form-control" placeholder="Enter Username" id="inputUsername" name="j_username"  required/>
    					</div>
    					<div class="form-group">
        					<label for="inputPassword">Password</label>
        					<input type="password" class="form-control" placeholder="Enter Password" id="inputPassword" name="j_password"  required/>
    					</div>
    					<button type="submit" class="btn btn-primary">Login</button>
    					<button type="reset" class="btn btn-primary">Reset</button>						
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