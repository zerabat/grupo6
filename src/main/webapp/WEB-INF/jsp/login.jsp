<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Iniciar sesión</title>

<c:url value="/css/bootstrap.css" var="bootstrapCSS" />
<link rel="stylesheet" href="${bootstrapCSS}">
<c:url value="/css/estilos.css" var="estilos" />
<link rel="stylesheet" href="${estilos}">
<script>
	function valida() {
		var email = document.getElementById('email');
		var password = document.getElementById("password");

		if ((email.value == "") || (password.value == "")) {
			window
					.alert("Los campos E-mail y contraseña no pueden estar vacios");
		} else {
			var urlAndParams = "/loginUsuarioFinal/";
			urlAndParams += "?email=" + email.value;
			urlAndParams += "&password=" + password.value;
			console.log(urlAndParams);

			var xhttp = new XMLHttpRequest();
			
			xhttp.open("GET", urlAndParams, true);
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					    window.location.assign("index")
				}else {
					window.location.assign("error")
				}
			};
			var pathname = window.location.pathname;
			xhttp.setRequestHeader("X-TenantID", pathname
					.split('/')[1]);
			xhttp.send();
		
		}

	}
</script>
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
						<form role="form" class="" action="" method="post">
							<div class="form-group">
								<input type="text" id="email" name="email" placeholder="E-mail"
									class="form-control">
							</div>
							<div class="form-group">
								<input type="password" id="password" name="password"
									placeholder="Contraseña" class="form-control">
							</div>
							<button type="submit" class="btn" onclick="valida();">Entrar</button>
						</form>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 google-login">
					<h3>Ingresa también con:</h3>
					<div class="google-login-button">
						<a href="#" class="btn-social"> <i
							class="glyphicon glyphicon-envelope"> Gmail</i>
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
