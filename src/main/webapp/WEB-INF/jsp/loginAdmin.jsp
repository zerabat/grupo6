<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="google-signin-client_id" content="1044122178058-iv6un92qnetg6c58as6lks8gfk529p8e.apps.googleusercontent.com">
        <title>Iniciar sesion administradores</title>
        <!-- Scripts -->
            <c:url value="/js/jquery-3.2.1.min.js" var="jqueryJS" />
            <c:url value="/js/funcionesAdmin.js" var="funcionesJS" />
            <c:url value="/js/bootstrap.min.js" var="bootstrapJS" />
            <script src="https://apis.google.com/js/platform.js" async defer></script>
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
                    <h1>Iniciar Sesion Administradores</h1>
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
                        <form role="form" action="" method="POST">
                            <div class="form-group">
                                <input type="text" id="email" name="email" placeholder="E-mail"
                                    class="form-control">
                            </div>
                            <div class="form-group">
                                <input type="password" id="password" name="password"
                                    placeholder="Contrase�a" class="form-control">
                            </div>
<!--                             <button type="submit" class="btn">Entrar</button> -->
                            <button type="submit" class="btn" onclick="return valida()">Entrar</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 google-login">
                    <h3>Ingresa tambien con:</h3>
                    <div class="google-login-button">
                        <a href="#" class="btn-social"> <i
                            class="glyphicon glyphicon-envelope"> Gmail</i>
                        </a>
<!-- <div class="g-signin2 " data-onsuccess="onSignIn"></div> -->


                    </div>

                </div>
            </div>
        </div>

    </div>


</body>
</html>

