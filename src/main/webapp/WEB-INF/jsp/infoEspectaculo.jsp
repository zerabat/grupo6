<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Info Espectaculo</title>
        <!-- scripts -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <c:url value="/js/jquery-3.2.1.min.js" var="jqueryJS" />
        <c:url value="/js/bootstrap.min.js" var="bootstrapJS" />
        <c:url value="/js/funciones.js" var="funcionesJS" />
       <!-- <script type="text/javascript"   src="https://maps.google.com/maps/api/js?key=AIzaSyBWuPoNiIST3MEBiTTl3fFLs0tuQH1noDQ"></script>-->

        <script src="${jqueryJS}"></script>
        <script src="${bootstrapJS}"></script>
        <script src="${funcionesJS}"></script>
        <!-- scripts -->
        <script
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBWuPoNiIST3MEBiTTl3fFLs0tuQH1noDQ">
        </script>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="google-signin-client_id" content="1044122178058-iv6un92qnetg6c58as6lks8gfk529p8e.apps.googleusercontent.com">
        <script src="https://www.paypalobjects.com/api/checkout.js"></script>
        <script src="https://apis.google.com/js/platform.js" async defer></script>

        <script type="text/javascript">
            $( document ).ready(function() {
                gapi.load('auth2', function() {
                    gapi.auth2.init();

                    console.log('gapi load');
                });

                cargaInfoEspectaculo();
                cantEntradas();
                cargaImagenEsp();
                dropSectorCompra();
                dropFechaCompra();
                });
        </script>
        <!-- styles -->
        <c:url value="/css/bootstrap.css" var="bootstrapCSS" />
        <link rel="stylesheet" href="${bootstrapCSS}">
        <!-- styles -->
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
                <a class="navbar-brand" href="index"><h3>TicketYa!</h3></a>
              </div>
              <!--Inicio de menu-->
              <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                  <li><a href="index">Inicio<span class="sr-only">(current)</span></a></li>
                  <li class="active dropdown">
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

                <c:choose>
                    <c:when test="${username != null}">
                        <ul class="nav navbar-nav navbar-right">
                           <li><a href="index" id="btnUser">${username}</a></li>
                           <a class="btn btn-link" title="Salir" id="btnLogout" onClick="logout();"><span class="glyphicon glyphicon-log-out"></span></a>
                        </ul>
                      </c:when>

                      <c:otherwise>
                         <ul class="nav navbar-nav navbar-right">
                           <li><a href="login">Iniciar Sesion</a></li>
                           <li><a href="register">Registrarse</a></li>
                        </ul>
                    </c:otherwise>
                </c:choose>
              </div>
            </div>
            </div>
        </header>
        <div class="container" id="principalInfo">
            <div class="col-md-12 container" id="barraUbicacion">
                <section class="post "></section>
                  <div class="paginado">
                      <ul class="breadcrumb">
                        <li><a href="index">Inicio</a></li>
                        <li><a id="tipoEspectaculo" href="#"></a></li>
                        <li class="active" id="nomEspectaculo"></li>
                      </ul>
                  </div>
            </div>


            <div class="col-md-7" id=imagenEspectaculo>
                <img id="imgEsp" src="" class="img-responsive-info" alt="" width="100%" height= "100%">
            </div>

            <div class="col-md-5 responsive nowrap" id=infoEspectaculo  >
                <div class="panel panel-primary" id=panelEspectaculo>
                    <div class="panel-heading">
                        <h3 class="panel-title" id="evento"></h3>
                        <a href="#" class="glyphicon glyphicon-heart-empty" aria-hidden="true" title="Suscribirse" id="iconoCorazon"></a>
                    </div>

                    <div class="panel-body">
                        <div class="panelEspectaculo" id="panelScroll">
                            <p id="descripcion"></p>
                            <p id="fecha"><b>Fecha:  </b></p>
                            <p id="sala"><b>Lugar:  </b></p>
                            <p id="direccion"><b>Direccion:  </b></p>
                            <p id="localidades"><b>Localidades a la venta:  </b></p>
                        </div>

                        <c:choose>
                            <c:when test="${username != null}">
                                <button class="btn btn-success btn-group-justified" type=" button" data-toggle="modal" data-target="#myModal" id="btnComprar">Comprar</button>
                            </c:when>

                            <c:otherwise>
                                <a href="login">Debe iniciar sesion para comprar.</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>

            <div class="col-md-3 responsive nowrap" >
                <div class="panel panel-info" id="pagosPanel">
                    <div class="panel-heading">
                        <h3 class="panel-title">Medios de pago</h3>
                    </div>
                    <div class="panel-body" id="mediosPagos" >
                       <c:url value="/img/paypal.png" var="paypal" />
                        <img src="${paypal}" class="img-responsive-info" alt="" width="100%" height= "100%" >
                    </div>
                </div>

                <div class="panel panel-primary " id="eticketPanel">
                    <div class="panel-body" id="eticket" >
                        <c:url value="/img/Eticket.png" var="eticket" />
                        <img src="${eticket}" class="img-responsive-info" alt="" width="100%" height= "100%" >
                    </div>
                </div>
            </div>

            <div class="col-md-4 responsive">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Entradas</h3>
                    </div>
                    <div class="panel-body" id="promocion">
                        <p>Sectores y precios</p>
                        <table id="sectorTabla">
                        </table>
                      </div>
                </div>
            </div>

            <div class="col-md-5 responsive nowrap" id="mapa" >
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title" id="lugarMapa"><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span> </h3>
                    </div>
                    <div class="panel-body" id="map">
                        <!-- carga el mapa de la ubicacion del lugar -->
                    </div>

                </div>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog" id="modalCompra">

                  <!-- Modal content-->
                  <div class="modal-content col-md-12">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                      <h4 class="modal-title">Compra de entradas</h4>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="btn-group-vertical col-md-3" role="group" aria-label="..." >
                                <div class="dropdown">
                                    <select class="btn btn-primary " aria-labelledby="dropFecha" id="dropFecha" onchange="dropFechaCompra();">
                                        <option value="" disabled selected>Seleccionar fecha</option>
                                    </select>
                                </div>
                                <div class="dropdown">
                                    <select class="btn btn-primary " aria-labelledby="dropSector" id="dropSector" onchange="dropSectorCompra();">
                                      <option value="" disabled selected>Seleccionar sector</option>
                                  </select>
                                </div>
                                <div class="centerButton">
                                    <p>Cantidad de entradas</p>
                                    <div class="input-group">
                                      <span class="input-group-btn">
                                          <button type="button" class="btn btn-danger btn-sm btn-number"  data-type="minus" data-field="quant[2]">
                                            <span class="glyphicon glyphicon-minus"></span>
                                          </button>
                                      </span>
                                      <input type="text" name="quant[2]" class="form-control input-sm input-number" value="1" min="1" max="100">
                                      <span class="input-group-btn">
                                          <button type="button" class="btn btn-success btn-sm btn-number" data-type="plus" data-field="quant[2]">
                                              <span class="glyphicon glyphicon-plus"></span>
                                          </button>
                                      </span>
                                    </div>
                                </div>

                            </div>

                            <div class="imagenSala col-md-6">
                                <img src="" class="imgSala img-responsive-info" alt="" width="100%" height= "100%"  id="imgModalSala">
                            </div>
                            <div class="col-md-3">
                                <p id="fechaElegida"></p>
                                <p id="sectorElegido"></p>
                            </div>
                        </form>
                    </div>

                        <div class="modal-footer col-md-12">
                            <c:choose>
                                <c:when test="${username != null}">

                                    <button id="paypal-button-container" class="btn"></button>

                                    <script>
                                        paypal.Button.render({

                                            env: 'sandbox',

                                            client: {
                                                sandbox: 'AW43RVR0-LJVhcNZmLhU8r0GzPTxubhbKrs321gOv-T5-OiDqQ7KNLGCfRjd7eRxXMHSnsFWW07y0Bf2',
                                                production: 'TicketYa'
                                            },

                                            commit: true,

                                            payment: function(data, actions) {

                                                return actions.payment.create({
                                                    payment: {
                                                        transactions: [
                                                            {
                                                                 // Aca hay que cargarle el monto y la moneda cuando se hace el pego
                                                                amount: { total: '0.01', currency: 'USD' }
                                                            }
                                                        ]
                                                    }
                                                });
                                            },

                                            // onAuthorize() se ejecuta cuando ya se realiz� el pago
                                            onAuthorize: function(data, actions) {

                                                var xhttp = new XMLHttpRequest();
                                                var urlAndParams = "/comprarEntradaEspectaculo/"

                                                urlAndParams += "&email=" + "maucarr2-buyer@gmail.com" ;
                                                urlAndParams += "?idRealizacion=" + 1 ;
                                                urlAndParams += "?idSector=" + 1;
                                                console.log(urlAndParams)
                                                xhttp.open("POST", urlAndParams,
                                                        true);
                                                xhttp.setRequestHeader("X-TenantID", window.location.pathname.split( '/' )[1]);
                                                xhttp.send();

                                                return actions.payment.execute().then(function() {
                                                    window.alert('Pago realizado con exito!');
                                                });
                                            }

                                        }, '#paypal-button-container');

                                    </script>
                                </c:when>

                                <c:otherwise>
                                    <a href="login">Debe iniciar sesion para comprar.</a>
                                </c:otherwise>
                            </c:choose>
                          <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        </div>
                 </div>

             </div>

                </div>
        </div>


        <footer>
               <nav class="navbar navbar-default" id="footerNav">
                <div class="container" id="footer">
                    <ul class="nav navbar-nav navbar-left">
                        <li>
                            <a id="aFotter" href="#"><h4 id="h4Fotter">TicketYa!</h4></a>
                        </li>
                        <li>
                            <p id="pFooter">Proyecto Fin de carrera - Tecnologo en informatica</p>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><p id="pFooter">Santiago Tabarez, Veronica Perez y Camilo Orquera</p></li>
                    </ul>
                </div>
            </nav>
        </footer>
    </body>
</html>
