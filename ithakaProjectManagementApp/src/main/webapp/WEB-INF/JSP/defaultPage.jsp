<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Default Page</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">

  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-blue fixed-top">
        <a class="navbar-brand" href="#">
          <img class="logo" src="assets/images/innodata-logo.png"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link underline-from-center" href="#">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item underline-from-center">
              <a class="nav-link" href="assets/images/porticoWorkFlow.pdf" target="_blank">Document</a>
            </li>
            <li class="nav-item underline-from-center">
              <a class="nav-link underline-from-center" href="/ithakaProjectManagementApp/admin/dashboard">
              <span class="glyphicon glyphicon-log-in"></span>Admin</a>
            </li>
            <li class="nav-item underline-from-center">
              <a class="nav-link underline-from-center" href="/ithakaProjectManagementApp/user/dashboard">
              <span class="glyphicon glyphicon-log-in"></span>User</a>
            </li>
          </ul>
        </div>
    </nav>

    <!-- Page Content -->
    <div class="container container-1">

      <!-- Portfolio Item Heading -->
      

      <!-- Portfolio Item Row -->
      <div class="row">

        <div class="col-md-10 workflow">
          <h3 class="my-4"><span>PORTICO Work Management</span></h1>
          <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
              <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
              <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
              <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
            <div class="carousel-item active">
              <img class="d-block w-100" src="assets/images/pageimage.jpg" alt="First slide">
            </div>
            <div class="carousel-item">
              <img class="d-block w-100" src="assets/images/pageimage1.png" alt="Second slide">
            </div>
            <div class="carousel-item">
              <img class="d-block w-100" src="assets/images/pageimage2.png" alt="Third slide">
            </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
        </div>
        <div class="col-md-2">
         <!--  <h3 class="my-3">Project Description</h3>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae. Sed dui lorem, adipiscing in adipiscing et, interdum nec metus. Mauris ultricies, justo eu convallis placerat, felis enim.</p>
         -->

         <div class="sidebar-lists sidebar-demos sidebar-link-header">
           <h4 class="my-4"><span>Project&#x00A0;Related&#x00A0;Links</span></h4>
          <ul>
            <li><a href="http://workbench-innodata.portico.org/workbench/conprep" target="_blank">Workbench</a></li>
            <li><a href="https://porticojira.ithaka.org" target="_blank">Jira</a></li>
            <li><a href="https://xen.ithaka.org" target="_blank">Citrix</a></li>
            <li><a href="http://audit.portico.org/Portico/#!loginView" target="_blank">Audit&#x00A0;Site</a></li>
            <li><a href="http://pr2ptgpprd31.ithaka.org:9080/jenkins" target="_blank">Jenkins</a></li>
            </ul>
           <h4><span>Other Links</span></h4>
           <ul>
             <li><a href="http://mail.innodata.com" target="_blank">Innodata&#x00A0;Mail</a></li>
             <li><a href="http://gamsindia.innodata.net" target="_blank">Global&#x00A0;AMS</a></li>
             </ul>
          </div>

        
        </div>

      </div>
      <!-- /.row -->
    </div>
    <div class="container">
      
      <!-- Portfolio Item Heading -->
      
      <h3 class="my-4 top-margin"><span>Portico Guidlines</span></h3>
      <!-- Portfolio Item Row -->
      <div class="row">
      <!-- Related Projects Row -->
      

      <div class="row guidline-class">

        <div class="col-md-3 col-sm-6 mb-4">
          <a href="assets/images/workflowchart.pdf" target="_blank">
            <img class="img-fluid" src="assets/images/workflow.png" alt="WorkFlow">
          </a>
        </div>

        <div class="col-md-2 col-sm-6 mb-4">
          <a href="assets/images/jiradesc.png" target="_blank">
            <img class="img-fluid" src="assets/images/jira.png" alt="Jira">
          </a>
        </div>

        <div class="col-md-2 col-sm-6 mb-4">
          <a href="#" target="_blank">
            <img class="img-fluid tod-img" src="assets/images/tod.png" alt="TOD">
          </a>
        </div>

        <div class="col-md-2 col-sm-6 mb-4">
          <a href="assets/images/quicktipsdesc.png" target="_blank">
            <img class="img-fluid" src="assets/images/quick-tips.png" alt="QuickTips">
          </a>
        </div>
        
        <div class="col-md-3 col-sm-6 mb-4">
          <a href="assets/images/softwaredesc.jpg" target="_blank">
            <img class="img-fluid" src="assets/images/software.png" alt="Software">
          </a>
        </div>

      </div>
      </div>
      </div>
      <!-- /.row -->
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-blue">
      <div class="container">
        <p class="m-0 text-center text-white">Portico Task Management System 2018</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>

  </body>

</html>
