<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        	<meta charset="utf-8">
        	<meta name="viewport" content="width=device-width, initial-scale=1.0">
        	<title>Iniciar sesión super administradores</title>
        	<!-- Scripts -->
            	<c:url value="/js/jquery-3.2.1.min.js" var="jqueryJS" />
            	<c:url value="/js/funcionesSuperAdmin.js" var="funcionesJS" />
            	<c:url value="/js/bootstrap.min.js" var="bootstrapJS" />
            	<script src="${jqueryJS}"></script>
            	<script src="${funcionesJS}"></script>
            	<script src="${bootstrapJS}"></script>

        	<!-- Scripts -->
        	<!-- Styles -->
             	<c:url value="/css/bootstrap.css" var="bootstrapCSS" />
            	<c:url value="/css/estilos.css" var="estilos" />
            	<link rel="stylesheet" href="${bootstrapCSS}">
            	<link rel="stylesheet" href="${estilos}">
        	<!-- Styles -->

        	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
        	<script src="https://www.paypalobjects.com/api/checkout.js"></script>

    </head>

    <body>
    	<div class="content">
        	<div class="container">
            	<div class="row">
                	<div class="col-sm-12">
                    	<h1>Iniciar Sesión Super Administradores</h1>
                	</div>
            	</div>

            	<div class="row">
                	<div class="col-sm-6 col-sm-offset-3 form-cont">
                    	<div class="form-top">
                        	<div class="form-top-left">
                            	<p>Ingrese contraseña</p>
                        	</div>
                        	<div class="form-top-right">
                            	<span class="glyphicon glyphicon-log-in">
                        	</div>
                    	</div>

                    	<div class="form-bottom">
                        	<form role="form" action="" method="POST">
                            	<div class="form-group">
                                	<input type="password" id="password" name="password"
                                    	placeholder="Contraseña" class="form-control">
                            	</div>

                            	<button type="submit" class="btn" onclick="return valida();">Entrar</button>
                        	</form>
                    	</div>
                	</div>
            	</div>
        	</div>
    	</div>
    </body>
</html>
