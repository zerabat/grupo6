<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="es">
	<head>
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Inicio</title>
	    
		<c:url value="/css/bootstrap.css" var="bootstrapCSS" />
	    <link rel="stylesheet" href="${bootstrapCSS}">
	</head>
	
	<body>
		<header>
			<nav class="navbar navbar-default navbar-static-top" role="navigation">
				<div class="container-fluid">
					<div class="navbar-header">
					    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Desplegar / Ocultar Menu</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
					    </button>
					    
				    	<a class="navbar-brand" href="#">TicketYa!</a>
				    </div>
				  
				    <!--Inicio de menu-->
			        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		        	    <ul class="nav navbar-nav">
				            <li class="active"><a href="#">Inicio<span class="sr-only">(current)</span></a></li>
				          
				            <li class="dropdown">
				                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Espectaculos <span class="caret"></span></a>

                                <ul class="dropdown-menu" role="menu">
					                <li><a href="#">Musica</a></li>
					                <li><a href="#">Teatro</a></li>
					                <li><a href="#">Deportes</a></li>
					                <li><a href="#">Infantiles</a></li>
				                </ul>
				            </li>
				          
				            <li><a href="#">Contacto</a></li>
				        </ul>
				        
    				    <form class="navbar-form navbar-left" role="search">
    						<div class="form-group">
    							<input type="text" class="form-control" placeholder="Buscar">
    			            </div>
    				            
    				        <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
    				    </form>
    				    
    			        <ul class="nav navbar-nav navbar-right">
    			            <li><a href="login">Iniciar Sesión</a></li>
    			            <li><a href="register">Registrarse</a></li>
    			        </ul>
			        </div>
                </div>
			</nav>
	    </header>
	    
	    <!--Carrousel-->
        <div class="col-md-9">
            <div id="carousel_index" class="carousel slide" data-ride="carousel">
                <!--Indicador-->
                <ol class="carousel-indicators">
                      <li data-target="#carousel_index" data-slide-to="0" class="active"></li>
                      <li data-target="#carousel_index" data-slide-to="1"></li>
                      <li data-target="#carousel_index" data-slide-to="2"></li>
                </ol>

                <!--Contenedor del slide-->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
						<c:url value="/img/gorillaz.jpeg" var="imgGorillaz" />
                        <img src="${imgGorillaz}" class="img-responsive" alt="" width="100%" >
                        <div class="carousel-caption hidden-xs hidden-sm">
                            <div class="banner">
                                <h3>GORILLAZ en Uruguay</h3>
                                <p>Primavera 0</p>
                            </div>
                        </div>
                    </div>

                    <div class="item">
						<c:url value="/img/calamaro.jpg" var="imgCalamaro" />
                        <img src="${imgCalamaro}" class="img-responsive" alt="" width="100%" >
                        <div class="carousel-caption hidden-xs hidden-sm">
                            <div class="banner">
                                <h3>Calamaro</h3>
                                <p>Auditorio del Sodre</p>
                            </div>
                        </div>
                    </div>
                    <div class="item">
						<c:url value="/img/midachi.jpg" var="imgMidachi" />
                        <img src="${imgMidachi}" class="img-responsive" alt="" width="100%"  >
                        <div class="carousel-caption hidden-xs hidden-sm">
                            <div class="banner">
                                <h3>MIDACHI</h3>
                                <p>Agosto en Landia</p>
                            </div>
                        </div>
                    </div>
                </div>
                <!--Controles-->
                <a href="#carousel_index" class="left carousel-control" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Anterior</span>
                </a>

                <a href="#carousel_index" class="right carousel-control" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Siguiente</span>
                </a>
            </div>
        </div>


        <aside class="col-md-3 hidden-xs hidden-sm">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Calendario</h3>
                </div>

                <div class="panel-body">
                    Panel content
                </div>
            </div>
        </aside>
	
	
	
	
	    <footer></footer>
		
		<!-- Scripts -->
		<c:url value="/js/jquery-3.2.1.min.js" var="jqueryJS" />
		<c:url value="/js/bootstrap.min.js" var="bootstrapJS" />
	    <script src="${jqueryJS}"></script>
	    <script src="${bootstrapJS}"></script>
		<!-- Scripts -->
	</body>

</html>