<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar sesion</title>
    
	<c:url value="/css/bootstrap.css" var="bootstrapCSS" />
    <link rel="stylesheet" href="${bootstrapCSS}">
	<c:url value="/css/estilos.css" var="estilos" />
    <link rel="stylesheet" href="${estilos}">
	<c:url value="/css/font-awesome.min.css" var="fontAwesome" />
    <link rel="stylesheet" href="${fontAwesome}">
  </head>
  
  <body>
  <div class="content">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <h1>Iniciar Sesión</h1>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-6 col-sm-offset-3 form-cont">
          <div class="form-top">
            <div class="form-top-left">
                <p>Ingresa E-mail y contraseña</p>
            </div>
            <div class="form-top-right">
              <span class="glyphicon glyphicon-log-in">
            </div>
          </div>
          <div class="form-bottom">
          <spring:url value="/users" var="userActionUrl" />

			<form:form class="form-horizontal" method="post" action="${loginUsuarioFinal}">
              <div class="form-group">
                <input type="text" name="email" placeholder="E-mail" class="form-control">
              </div>
              <div class="form-group">
                <input type="password" name="password" placeholder="Contraseña" class="form-control">
              </div>
              <button type="submit" class="btn">Entrar</button>
            </form:form>
          </div>
       </div>
      </div>
      <div class="row">
        <div class="col-sm-12 google-login">
          <h3>Ingresa también con: </h3>
          <div class="google-login-button">
            <a href="#" class="btn-social">
              <i class="fa fa-envelope" > Gmail</i>
            </a>


          </div>

        </div>
      </div>
    </div>

  </div>

    <!-- Scripts -->
	<c:url value="/js/jquery-3.2.1.min.js" var="jqueryJS" />
	<c:url value="/js/bootstrap.min.js" var="bootstrapJS" />
    <script src="${jqueryJS}"></script>
    <script src="${bootstrapJS}"></script>
	<!-- Scripts -->
  </body>
</html>
