<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta charset="utf-8">
        <title>Info Espectaculo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <c:url value="/js/jquery-3.2.1.min.js" var="jqueryJS" />
        <c:url value="/js/bootstrap.min.js" var="bootstrapJS" />
        <script src="${jqueryJS}"></script>
        <script src="${bootstrapJS}"></script>
        
        <c:url value="/css/bootstrap.css" var="bootstrapCSS" />
	    <link rel="stylesheet" href="${bootstrapCSS}">

    </head>
    <body>
        <header>
            <div class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container" id="barraNavegador">
              <div class="navbar-header ">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                  <span class="sr-only">Desplegar / Ocultar Menu</span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><h3>TicketYa!</h3></a>
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
                  <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-search"></span></button>
                </form>
                <ul class="nav navbar-nav navbar-right">
                  <li><a href="login.html">Iniciar Sesión</a></li>
                  <li><a href="registro.html">Registrarse</a></li>
                </ul>
              </div>
            </div>
            </div>
        </header>
        <div class="container" id="principalInfo">
            <div class="col-md-12 container" id="barraUbicacion">
                <section class="post "></section>
                  <div class="paginado">
                      <ul class="breadcrumb">
                        <li><a href="#">Inicio</a></li>
                        <li><a href="#">Musica</a></li>
                        <li class="active">Espectaculo</li>
                      </ul>
                  </div>
            </div>


            <div class="col-md-7" id=imagenEspectaculo>
                <c:url value="/img/gorillaz.jpeg" var="imgGorillaz" />
                <img src="${imgGorillaz}" class="img-responsive-info" alt="" width="100%" height= "100%">
            </div>

            <div class="col-md-5 responsive nowrap" id=infoEspectaculo  >
                <div class="panel panel-primary" id=panelEspectaculo>
                  <div class="panel-heading">
                    <h3 class="panel-title">Gorillaz</h3>
                  </div>
                  <div class="panel-body">
                    <div class="panelEspectaculo" id="panelScroll">
                        <p>Primavera 0 presenta Gorillaz por primera vez en Uruguay ndnsnvcncncnc</p>
                        <p>Descripcion: lalalllal</p>
                        <p>Precios: van desde 950</p>
                        <p>Lugar: Velodromo Municipal </p>
                        <p>Fecha: 13 de diciembre de 2017</p>
                        <p>Medios de pago: Tarjetas de credito, Paypal</p>
                        <p>Fecha: 13 de diciembre de 2017</p>
                        <p>Fecha: 13 de diciembre de 2017</p>
                    </div>
                    <button class="btn btn-success btn-group-justified" type=" button" id="btnComprar">Comprar</button>
                 </div>

                </div>
            </div>

            <div class="col-md-3 responsive nowrap" >
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">Medios de pago</h3>
                    </div>
                    <div class="panel-body" id="mediosPagos" >
                        la info que haga falta
                    </div>

                </div>
            </div>
            <div class="col-md-4" >
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">Promociones</h3>
                    </div>
                    <div class="panel-body" id="promocion">
                    Solo con tarjetas de credito 2x1
                    </div>

                </div>
            </div>
            <div class="col-md-5 responsive nowrap" id="mapa" >
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Lugar: La trastienda</h3>
                    </div>
                    <div class="panel-body" id="map">
                        <script>
                            function initMap() {
                                var uluru = {lat: -34.881665, lng: -56.171321};
                                var map = new google.maps.Map(document.getElementById('map'), {
                                  zoom: 15,
                                  center: uluru
                                });

                            mapa.marker = new google.maps.Marker({
                            position: uluru,
                            draggable: true
                            });
                            mapa.marker.setMap(mapa.map);

                        }
                        </script>
                        <script async defer
                            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBWuPoNiIST3MEBiTTl3fFLs0tuQH1noDQ&callback=initMap">
                        </script>
                    </div>

                </div>
            </div>

        </div>

        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-xs-6">
                        <p>Proyecto Fin de carrera - Tecnólogo en informática </p>
                        <p>Grupo 6 - Santiago Tabarez, Verónica Pérez y Camilo Orquera</p>
                    </div>
                    <div class="col-xs-6">
                        <ul class="list-inline text-right">
                            <li><a href="#">Inicio</a></li>

                        </ul>

                    </div>
                </div>

            </div>
        </footer>
    </body>
</html>
