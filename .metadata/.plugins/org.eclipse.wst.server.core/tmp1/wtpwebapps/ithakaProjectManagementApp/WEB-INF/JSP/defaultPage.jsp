<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Default Page</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <style>
         .container{
                width:100%;
                height:800px;
                margin:0;
  	            padding:0;
              }
              
          .navbar {
              width:100%;
              height:5%;
	          border-radius: 0;
	          
            }
            
           .left-col{
              width:20%;
              height:90%;
              float:left;
              border: 1px solid;
              background-color: #f1f1f1;
            }
            .right-col{
              width:20%;
              height:90%;
              float:left;
              border: 1px solid;
              overflow: auto;
              background-color: #f1f1f1;
            }
            .center-col{
              width:60%;
              height:90%;
              float:left;
              border: 1px solid;
            }
           .footer{
            max-width:100%;
             height:5%;
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
                        <li><a href="/ithakaProjectManagementApp/admin/dashboard"><span class="glyphicon glyphicon-log-in"></span>Admin</a></li>
        				<li><a href="/ithakaProjectManagementApp/user/dashboard"><span class="glyphicon glyphicon-log-in"></span>User</a></li>
                    </ul>
                </div>
            </div>
             <div class="left-col">
              </div>     
              <div class="center-col">
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