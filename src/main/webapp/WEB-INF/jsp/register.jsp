<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
          <meta charset="utf-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">

          <title>Registrarse</title>

          <c:url value="/css/bootstrap.css" var="bootstrapCSS" />
          <c:url value="/css/estilos.css" var="estilos" />
          <c:url value="/js/funciones.js" var="funcionesJS" />
          <link rel="stylesheet" href="${bootstrapCSS}">
          <link rel="stylesheet" href="${estilos}">
          <script src="${funcionesJS}"></script>
      </head>

      <body>
              <div class="content">
                  <div class="container">
                      <div class="row">
                          <div class="col-sm-12">
                              <h1>Formulario de Registro</h1>
                          </div>
                      </div>

                      <div class="row">
                          <div class="col-sm-6 col-sm-offset-3 form-cont">
                              <div class="form-top">
                                  <div class="form-top-left">
                                      <p>Registrate en nuestro sitio</p>
                                  </div>

                                  <div class="form-top-right">
                                      <span class="glyphicon glyphicon-user">
                                  </div>
                              </div>

                              <div class="form-bottom">
                                  <form role="form" class="" action="index.html" method="post">
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

                                      <button type="submit" onclick="return register();" class="btn">Registrarse</button>
                                  </form>
                              </div>
                          </div>
                      </div>

<!--                       <div class="row"> -->
<!--                           <div class="col-sm-12 google-login"> -->
<!--                               <h3>Ingresa tambien con: </h3> -->

<!--                               <div class="google-login-button"> -->
<!--                                   <a href="#" class="btn-social"> -->
<!--                                       <i class="glyphicon glyphicon-envelope"> Gmail</i> -->
<!--                                   </a> -->
<!--                               </div> -->
<!--                           </div> -->
<!--                       </div> -->
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
