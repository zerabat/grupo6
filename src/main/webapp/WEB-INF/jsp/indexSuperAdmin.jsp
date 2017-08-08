<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

   	 <title>Administrador</title>

    	<!-- scripts -->
    	<c:url value="/js/jquery-3.2.1.min.js" var="jqueryJS" />
    	<c:url value="/js/bootstrap.min.js" var="bootstrapJS" />
    	<c:url value="/js/funcionesSuperAdmin.js" var="funcionesAdminJS" />
    	<c:url value="/js/jquery.dataTables.js" var="datatableJS" />
    	<script src="${jqueryJS}"></script>
    	<script src="${bootstrapJS}"></script>
    	<script src="${funcionesAdminJS}"></script>
    	<script src="${datatableJS}"></script>
    	<!-- scripts -->
    	<!-- estilos -->
   	 <c:url value="/css/bootstrap.css" var="bootstrapCSS" />
    	<link rel="stylesheet" href="${bootstrapCSS}">
    	<c:url value="/css/admin.css" var="adminCSS" />
    	<link rel="stylesheet" href="${adminCSS}">
    	<!-- estilos -->
    	<script type="text/javascript">
   			 $( document ).ready(function() {
   				 sidebarAdmin();
                	activeSidebar();
   				 });
    	</script>
</head>

<body>
    <c:if test="${superAdmin == null}">
    	<script>
        	window.location.assign("loginSuperAdmin");
    	</script>
	</c:if>

	<header>
    	<div class="navbar navbar-default sidebar" role="navigation" id="headerAdmin">
    	<div id="sidebarAdmin">
      	<div class="navbar-header ">
        	<a class="navbar-brand" href="#" id="nombreTenant"><h3>TicketYa!</h3></a>
      	</div>
      	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        	<ul class="nav navbar-nav">
          	<li class="active"><a href="#">Panel administrador<span class="sr-only">(current)</span></a></li>
        	</ul>
        	<c:choose>
            	<c:when test="${superAdmin != null}">
                 	<ul class="nav navbar-nav navbar-right">
                   	<li><a href="index" id="btnUser">${superAdmin}</a></li>
                   	<a class="btn btn-link" title="Salir" id="btnLogout" onClick="logout();"><span class="glyphicon glyphicon-log-out"></span></a>
                	</ul>
              	</c:when>
        	</c:choose>
      	</div>
    	</div>
    	</div>
	</header>

    	<nav class="navbar navbar-default sidebar" role="navigation" id="sidebarAdmin">
      	<div class="container-fluid">
        	<div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
          	<ul class="nav navbar-nav sideAdmin">
            	<li class="active"><a id="inicio" href="#">Inicio<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
            	<li class="dropdown">
              	<a id="usuarios" href="#" class="dropdown-toggle" data-toggle="dropdown">Usuarios <span class="caret"></span><span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span></a>
              	<ul class="dropdown-menu forAnimate" role="menu">
                	<li><a id="administradores"href="#">Administradores</a></li>
              	</ul>
            	</li>
            	<li ><a id="tenants" href="#">Tenants<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-star"></span></a></li>
          	</ul>
        	</div>
      	</div>
    	</nav>

	<div class="container" id="dashboard">
    	<div id="panelIndex" class="col-md-12" >
   		 <div class="panel panel-success" id="panelContenedor">
   			   <div class="panel-heading">Super Administrador</div>
   			   <div class="panel-body" id="panelAdmin">
   				   <p>Bienvenido</p>
   			   </div>
   		 </div>

    	</div>

    	<div id="divAdministradores"  style="display:none;">
        	<div class="col-md-12" >
            	<ul class="breadcrumb">
              	<li><a href="#">Inicio</a></li>
              	<li>Usuarios</li>
              	<li class="active">Administradores</li>
            	</ul>
        	</div>
        	<!-----------Administrador---------------->
        	<div class="col-md-10">
            	<!-- Inputs -->
        		 <div class="form-bottom">
                 	<form role="form" class="" action="index.html" method="post">
                		 <div class="form-group">
                         	<input type="text" id="tenant" name="tenant" placeholder="Nombre del tenant en el que se creara el admin" class="form-control" required>
                     	</div>

                     	<div class="form-group">
                         	<input type="text" id="email" name="email" placeholder="E-mail" class="form-control" required>
                     	</div>

                     	<div class="form-group">
                         	<input type="password" id="password" name="password" placeholder="Contraseña" class="form-control" required>
                     	</div>

                     	<div class="form-group">
                         	<input type="password" id="passwordRepeat" name="passwordRepeat" placeholder="Repetir Contraseña" class="form-control" required>
                     	</div>

                     	<div class="form-group">
                         	<input type="text" id="nombre" name="nombre" placeholder="Nombre" class="form-control" required>
                     	</div>

                     	<div class="form-group">
                         	<input type="text" id="apellido" name="apellido" placeholder="Apellido" class="form-control" required>
                     	</div>

                     	<div class="form-group">
                         	<input type="text" id="cedula" name="cedula" placeholder="Cedula" class="form-control" required>
                     	</div>

                     	<div class="form-group">
                         	<input type="password" id="passwordAdmin" name="passwordAdmin" placeholder="Ingrese su clave de Super Admin" class="form-control" required>
                     	</div>


                     	<button type="submit" onclick="return crearAdmin();" class="btn">Crear</button>
                 	</form>
             	</div>
        	</div>
    	</div>
    	<div id="divTenants" style="display:none;">
        	<div class="col-md-12" >
            	<ul class="breadcrumb">
              	<li><a href="#">Inicio</a></li>
              	<li class="active">Tenants</li>
            	</ul>
        	</div>
        	<!---------Tenant--------->
        	<div class="col-md-10">
       		 <!-- inputs -->
       		 <div class="form-bottom">
                 	<form role="form" class="" action="" method="post">
   	        		 <div class="form-group">
   	                 	<input type="text" id="nombreNuevoTenant" name="nombreNuevoTenant" placeholder="Nombre de tenant a crear" class="form-control" required>
   	             	</div>

   	             	<div class="form-group">
   	                 	<input type="password" id="passwordTenant" name="passwordTenant" placeholder="Ingrese su clave de Super Admin" class="form-control" required>
   	             	</div>
   	             	<button type="submit" onclick="return crearTenant();" class="btn">Crear</button>
                 	</form>
             	</div>
        	</div>
    	</div>
	</div>


	<footer>
    	<nav class="navbar navbar-default " id="footerNav">
        	<div  id="footer">
            	<ul class="nav navbar-nav navbar-left">
                	<li>
                    	<a id="aFotter" href="#"><h4 id="h4Fotter">TicketYa!</h4></a>
                	</li>
            	</ul>
            	<ul class="nav navbar-nav navbar-left">
                	<li>
                    	<p id="pFooter">Proyecto Fin de carrera - Tecnólogo en informática</p>
                	</li>
            	</ul>
            	<ul class="nav navbar-nav navbar-right">
                	<li><p id="pFooter">Santiago Tabárez, Verónica Pérez y Camilo Orquera</p></li>
            	</ul>
        	</div>
    	</nav>
	</footer>



</body>

</html>
