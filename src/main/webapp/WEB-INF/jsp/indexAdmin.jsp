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
        <c:url value="/js/funcionesAdmin.js" var="funcionesAdminJS" />
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
    <c:if test="${admin == null}">
        <script>
            window.location.assign("loginAdmin");
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
            <ul class="nav navbar-nav navbar-right">
              <c:choose>
                   <c:when test="${admin != null}">
                        <ul class="nav navbar-nav navbar-right">
                          <li><a href="index" id="btnUser">${admin}</a></li>
                          <a class="btn btn-link" title="Salir" id="btnLogout" onClick="logout();"><span class="glyphicon glyphicon-log-out"></span></a>
                       </ul>
                     </c:when>

                     <c:otherwise>
                        <ul class="nav navbar-nav navbar-right">
                          <li><a href="loginAdmin">Iniciar Sesión</a></li>
                       </ul>
                   </c:otherwise>
               </c:choose>
            </ul>


          </div>
        </div>
        </div>
    </header>

        <nav class="navbar navbar-default sidebar" role="navigation" id="sidebarAdmin">
          <div class="container-fluid">
            <div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
              <ul class="nav navbar-nav sideAdmin" id="sideAdmin">
                <li class="active"><a id="inicio" href="#">Inicio<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
                <li class="dropdown">
                  <a id="usuarios" href="#" class="dropdown-toggle" data-toggle="dropdown">Usuarios <span class="caret"></span><span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span></a>
                  <ul class="dropdown-menu forAnimate" role="menu">
                    <li><a id="portero" onclick="cargaDataTablePorteros();" href="#">Portero</a></li>
                  </ul>
                </li>
                <li ><a id="espectaculos" onclick="cargaDataTableEspectaculo();" href="#">Espectaculos<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-star"></span></a></li>
                <li ><a id="salas" onclick="cargaDataTableSalas();" href="#">Salas<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
                <li ><a id="sectores" href="#">Sectores<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-star"></span></a></li>
                <li ><a id="realizaciones" onclick="cargaDatatableRealizaciones();" href="#">Realizaciones<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-tags"></span></a></li>
<!--                 <li ><a id="reportes" href="#">Reportes<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-stats"></span></a></li> -->
              </ul>
            </div>
          </div>
        </nav>

    <div class="container" id="dashboard">
        <div id="panelIndex" class="col-md-12" >
        	<div class="panel panel-success" id="panelContenedor">
    			  <div class="panel-heading">Bienvenido</div>
    			  <div class="panel-body" id="panelAdmin">
	    			  <p>Aqui encontras el ABM de tu pagina, podras ingresar usuarios porteros, agregar espectáctulos, salas, relaizaciones</p>
	    			  
    			  </div>
    		</div>

        </div>

        <div id="divPortero"  style="display:none;">
            <div class="col-md-12" >
                <ul class="breadcrumb">
                  <li><a href="#">Inicio</a></li>
                  <li>Usuarios</li>
                  <li class="active">Portero</li>
                </ul>
            </div>
            <!-----------Portero---------------->
            <div class="col-md-12" id="datatable">
                <div class="table-responsive">
                    <table id="tablaPortero" class="table table-striped table-hover">
                        <thead>
                             <tr class="success">
                                 <th>id</th>
                                 <th>password</th>
                                 <th>Nombre</th>
                                 <th>Apellido</th>
                                 <th>Cedula</th>
                                 <th><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalCrearP"><span style="padding-right: 6px;" class="glyphicon glyphicon-plus" style=padding-right: 5px;></span>Crear</button></th>
                             </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div>
                <form id="frmCrearPortero" action="" method="PUT" >
                    <input type="hidden" id="idPorteroC" name="idEspectaculoC" value="0">
                    <!-- Modal -->
                    <div class="modal fade" id="modalCrearP" tabindex="-1" role="dialog" aria-labelledby="modalCrearLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="modalCrearLabel">Formulario para crear usuario Portero</h4>
                                </div>
                                <div class="modal-body">
                                    <table class="formularioTabla">
                                        <tr>
                                            <th><label for="nombrePorteroC" class=" control-label">Nombre</label></th>
                                            <th><div class="col-sm-12"><input id="nombrePorteroC" name="nombrePorteroC" type="text" class="form-control"  autofocus></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="apellidoPorteroC" class=" control-label">Apellido</label></th>
                                            <th><div class="col-sm-12"><input id="apellidoPorteroC" name="apellidoPorteroC" type="text" class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="cedulaPorteroC" class="control-label">Cedula</label></th>
                                            <th><div class="col-sm-12"><input type="number" min="0" step="1" id="cedulaPorteroC" name="cedulaPorteroC" type="text" class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="passwordPortero" class="control-label">Contraseña</label></th>
                                            <th><div class="col-sm-12"><input  id="passwordPortero" name="passwordPortero" type="text" class="form-control" ></div></th>
                                        </tr>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" id="crear-port" class="btn btn-primary" onclick="return btnCrearPortero();">Crear</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal -->
                </form>
            </div>
            <div>
                <form id="frmEditarEspectaculo" action="" method="PUT" >
                    <input type="hidden" id="idPortero" name="idPortero" value="0">
                    <!-- Modal -->
                    <div class="modal fade" id="modalEditarPortero" tabindex="-1" role="dialog" aria-labelledby="modalEditarLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="modalEditarLabel">Formulario para editar usuario Portero</h4>
                                </div>
                                <div class="modal-body">
                                    <table class="formularioTabla">
                                        <tr>
                                            <th><label for="nombrePorteroE" class=" control-label">Nombre</label></th>
                                            <th><div class="col-sm-12"><input id="nombrePorteroE" name="nombreEspetaculo" type="text" class="form-control"  autofocus></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="apellidoPorteroE" class=" control-label">Apellido</label></th>
                                            <th><div class="col-sm-12"><input id="apellidoPorteroE" name="apellidoPorteroE" type="text" class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="cedulaPorteroE" class="control-label">Cedula</label></th>
                                            <th><div class="col-sm-12"><input type="number" min="0" step="1" id="cedulaPorteroE" name="cedulaPorteroE" type="text" class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="passwordPorteroE" class="control-label">Contraseña</label></th>
                                            <th><div class="col-sm-12"><input  id="passwordPorteroE" name="passwordPorteroE" type="password" class="form-control" ></div></th>
                                        </tr>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" id="editar-portero" class="btn btn-primary" onclick="return btnEditarPortero();">Guardar</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal -->
                </form>
            </div>
        </div>
        <div id="divEspectaculos" style="display:none;">
            <div class="col-md-12" >
                <ul class="breadcrumb">
                  <li><a href="#">Inicio</a></li>
                  <li class="active">Espectaculos</li>
                </ul>
            </div>
            <!---------Espectaculos--------->
            <div class="col-md-12" id="datatable">
                <div class="table-responsive">
                    <table id="tablaEspectaculo" class="table table-striped table-hover ">
	                       <thead>
	                           <tr class="success">
	                             <th>Id</th>
	      	                     <th>Nombre</th>
	      	                     <th>Descripcion</th>
	                             <th>Tipo Espectaculo</th>
                                 <th><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalCrearE"><span class="glyphicon glyphicon-plus" style="padding-right: 5px";></span>Crear</button></th>
	                           </tr>
	                       </thead>
	                 </table>
                </div>
            </div>
            <div>
                <form id="frmCrearEspectaculo" action="" method="POST" >
                    <input type="hidden" id="idEspectaculoC" name="idEspectaculoC" value="0">
                    <!-- Modal -->
                    <div class="modal fade" id="modalCrearE" tabindex="-1" role="dialog" aria-labelledby="modalCrearLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="modalCrearLabel">Formulario para crear Espectaculo</h4>
                                </div>
                                <div class="modal-body">
                                    <table class="formularioTabla">
                                        <tr>
                                            <th><label for="nombreEspetaculoC" class=" control-label">Nombre</label></th>
                                            <th><div class="col-sm-12"><input id="nombreEspetaculoC" name="nombreEspetaculoC" type="text" class="form-control"  autofocus></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="descripcionEspC" class=" control-label">Descripcion</label></th>
                                            <th><div class="col-sm-12"><input id="descripcionEspC" name="descripcionEspC" type="text" class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="tipoEspectaculoC" class="control-label">Id Tipo de espectaculo</label></th>
                                            <th><div class="col-sm-12"><input type="number" min="0" step="1" id="tipoEspectaculoC" name="tipoLocalidadesC" type="text" class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="nombreTipoC" class="control-label">Nombre Tipo</label></th>
                                            <th><div class="col-sm-12"><input  id="nombreTipoC" name="nombreTipoC" type="text" class="form-control" ></div></th>
                                        </tr>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" id="crear-esp" class="btn btn-primary" onclick="return btnCrearEspectaculo();">Crear</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal -->
                </form>
            </div>
            <div>
                <form id="frmEditarEspectaculo" action="" method="PUT" >
                    <input type="hidden" id="idEspectaculo" name="idEspectaculo" value="0">
                    <!-- Modal -->
                    <div class="modal fade" id="modalEditarEsp" tabindex="-1" role="dialog" aria-labelledby="modalEditarLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="modalEditarLabel">Formulario para editar Espectaculo</h4>
                                </div>
                                <div class="modal-body">
                                    <table class="formularioTabla">
                                        <tr>
                                            <th><label for="nombreEspetaculo" class=" control-label">Nombre</label></th>
                                            <th><div class="col-sm-12"><input id="nombreEspetaculo" name="nombreEspetaculo" type="text" class="form-control"  autofocus></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="descripcionEsp" class=" control-label">Descripcion</label></th>
                                            <th><div class="col-sm-12"><input id="descripcionEspectaculo" name="descripcionEsp" type="text" class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="tipoEspectaculo" class="control-label">Id Tipo Espectaculo</label></th>
                                            <th><div class="col-sm-12"><input type="number" min="0" step="1" id="tipoEspectaculo" name="tipoLocalidadesC" type="text" class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="nombreTipo" class="control-label">Nombre Tipo</label></th>
                                            <th><div class="col-sm-12"><input  id="nombreTipo" name="nombreTipo" type="text" class="form-control" ></div></th>
                                        </tr>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" id="editar-espectaculo" class="btn btn-primary" onclick="return btnEditarEspectaculo();">Guardar</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal -->
                </form>
            </div>
        </div>
        <div id="divSalas" style="display:none;">
            <div class="col-md-12" >
                <ul class="breadcrumb">
                  <li><a href="#">Inicio</a></li>
                  <li class="active">Salas</li>
                </ul>
            </div>
            <!---------Salas------->
            <div class="col-md-12" id="datatable">
                <div class="table-responsive">
                    <table id="tablaSala" class="table table-striped table-hover">
                        <thead>
                             <tr class="success">
                             	 <th>idSala</th>
                                 <th>Nombre</th>
                                 <th>Direccion</th>
                                 <th>Cantidad de localidaddes</th>
                                 <th><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalCrearS"><span style="padding-right: 5px;" class="glyphicon glyphicon-plus"></span>Crear</button></th>
                             </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div>
                <form id="frmCrearSala" action="" method="PUT" >
                    <input type="hidden" id="idSalaC" name="idSalaC" value="0">
                    <!-- Modal -->
                    <div class="modal fade" id="modalCrearS" tabindex="-1" role="dialog" aria-labelledby="modalCrearLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="modalCrearLabel">Formulario para crear Sala</h4>
                                </div>
                                <div class="modal-body">
                                    <table class="formularioTabla">
                                        <tr>
                                            <th><label for="nombreC" class="col-sm-2 control-label">Nombre</label></th>
                                            <th><div class="col-sm-12"><input id="nombreSalaC" name="nombreSalaC" type="text" class="form-control"  autofocus></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="direccionC" class="col-sm-2 control-label">Direccion</label></th>
                                            <th><div class="col-sm-12"><input id="direccionSalaC" name="direccionSalaC" type="text" class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="cantLocalidadesC" class="col-sm-2 control-label">Localidades</label></th>
                                            <th><div class="col-sm-12"><input type="number" min="0" step="1" id="cantLocalidadesSalaC" name="cantLocalidadesSalaC" type="text" class="form-control" ></div></th>
                                        </tr>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" id="crear-sala" class="btn btn-primary" onclick="return btnCrearSala();">Guardar</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal -->
                </form>
            </div>
            <div>
                <form id="frmEditarSala" action="" method="POST" >
                    <input type="hidden" id="idSala" name="idSala" value="0">
                    <!-- Modal -->
                    <div class="modal fade" id="modalEditar" tabindex="-1" role="dialog" aria-labelledby="modalEditarLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="modalEditarLabel">Formulario para editar Sala</h4>
                                </div>
                                <div class="modal-body">
                                    <table class="formularioTabla">
                                        <tr>
                                            <th><label for="nombre" class="col-sm-2 control-label">Nombre</label></th>
                                            <th><div class="col-sm-12"><input id="nombreSala" name="nombreSala" type="text" class="form-control"  autofocus></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="direccion" class="col-sm-2 control-label">Direccion</label></th>
                                            <th><div class="col-sm-12"><input id="direccionSala" name="direccionSala" type="text" class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="cantLocalidades" class="col-sm-2 control-label">Localidades</label></th>
                                            <th><div class="col-sm-12"><input type="number" min="0" step="1" id="cantLocalidadesSala" name="cantLocalidadesSala" type="text" class="form-control" ></div></th>
                                        </tr>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" id="editar-sala" class="btn btn-primary" onclick="return btnEditarSala();">Guardar</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal -->
                </form>
            </div>

<!--             <div> -->
<!--                 <form id="frmEliminarSala" action="" method="POST"> -->
<!--                     <input type="hidden" id="idSalaE" name="idSalaE" value=""> -->
<!--                     Modal -->
<!--                     <div class="modal fade" id="modalEliminar" tabindex="-1" role="dialog" aria-labelledby="modalEliminarLabel"> -->
<!--                         <div class="modal-dialog" role="document"> -->
<!--                             <div class="modal-content"> -->
<!--                                 <div class="modal-header"> -->
<!--                                     <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
<!--                                     <h4 class="modal-title" id="modalEliminarLabel">Eliminar Sala</h4> -->
<!--                                 </div> -->
<!--                                 <div class="modal-body"> -->
<!--                                     Esta seguro de eliminar la sala?<strong data-name=""></strong> -->
<!--                                 </div> -->
<!--                                 <div class="modal-footer"> -->
<!--                                     <button type="button" id="eliminar-sala" class="btn btn-primary" onclick="btnEliminarSala();">Aceptar</button> -->
<!--                                     <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     Modal -->
<!--                 </form> -->
            </div>
       
         <div id="divSector" style="display:none;">
            <div class="col-md-12" >
                <ul class="breadcrumb">
                  <li><a href="#">Inicio</a></li>
                  <li class="active">Sector</li>
                </ul>
            </div>
            <!--------Sectores------->
            <div class="col-md-12" id="datatable">
                <div class="table-responsive">
                    <table id="tablaSector" class="table table-striped table-hover">
                        <thead>
                             <tr class="success">
                             	 <th><label for="idSala" class="col-sm-2 control-label">Ingrese id de sala</label></th>
                                 <th><div class="col-sm-4"><input id="idSalaSector" name=""idSalaSector"" type="number" class="form-control"  autofocus></div></th>
                                 <th><button type="button" class="btn btn-primary btn-sm" ><span style="padding-right: 5px;" class="glyphicon glyphicon-search"></span>Buscar sectores</button></th>
                                 <th><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalCrearSec"><span style="padding-right: 5px;" class="glyphicon glyphicon-plus"></span>Crear</button></th>
                             </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div>
                <form id="frmCrearSala" action="" method="PUT" >
                    <input type="hidden" id="idSectorC" name="idSectorC" value="0">
                    <!-- Modal -->
                    <div class="modal fade" id="modalCrearSec" tabindex="-1" role="dialog" aria-labelledby="modalCrearLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="modalCrearLabel">Formulario para crear Sector</h4>
                                </div>
                                <div class="modal-body">
                                    <table class="formularioTabla">
                                        <tr>
                                            <th><label for="nombreC" class="col-sm-2 control-label">Nombre</label></th>
                                            <th><div class="col-sm-12"><input id="nombreSectorC" name="nombreSectorC" type="text" class="form-control"  autofocus></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="capacidadSectorC" class="col-sm-2 control-label">Capacidad</label></th>
                                            <th><div class="col-sm-12"><input id="capacidadSectorC" name="capacidadSectorC" type="text" class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="idSalaSec" class="col-sm-2 control-label">Id Sala</label></th>
                                            <th><div class="col-sm-12"><input type="number" min="0" step="1" id="idSalaSecC" name="idSalaSec"  class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="nombreSalaSec" class="col-sm-2 control-label">Id Sala</label></th>
                                            <th><div class="col-sm-12"><input type="text" min="0" step="1" id="nombreSalaSecC" name="nombreSalaSec"  class="form-control" ></div></th>
                                        </tr>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" id="crear-sala" class="btn btn-primary" onclick="return btnCrearSector();">Guardar</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal -->
                </form>
            </div>
            <div>
                <form id="frmEditarSector" action="" method="POST" >
                    <input type="hidden" id="idSala" name="idSala" value="0">
                    <!-- Modal -->
                    <div class="modal fade" id="modalEditarSector" tabindex="-1" role="dialog" aria-labelledby="modalEditarLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="modalEditarLabel">Formulario para editar Sector</h4>
                                </div>
                                <div class="modal-body">
                                    <   <table class="formularioTabla">
                                        <tr>
                                            <th><label for="nombreC" class="col-sm-2 control-label">Nombre</label></th>
                                            <th><div class="col-sm-12"><input id="nombreSector" name="nombreSectorC" type="text" class="form-control"  autofocus></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="capacidadSectorC" class="col-sm-2 control-label">Capacidad</label></th>
                                            <th><div class="col-sm-12"><input id="capacidadSector" name="capacidadSectorC" type="text" class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="idSalaSec" class="col-sm-2 control-label">Id Sala</label></th>
                                            <th><div class="col-sm-12"><input type="number" min="0" step="1" id="idSalaSec" name="idSalaSec"  class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="nombreSalaSec" class="col-sm-2 control-label">Id Sala</label></th>
                                            <th><div class="col-sm-12"><input type="text" min="0" step="1" id="nombreSalaSec" name="nombreSalaSec"  class="form-control" ></div></th>
                                        </tr>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" id="editar-sala" class="btn btn-primary" onclick="return btnEditarSector();">Guardar</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal -->
                </form>
            </div>
        </div>

        <div id="divRealizaciones" style="display:none;">
            <div class="col-md-12" >
                <ul class="breadcrumb">
                  <li><a href="#">Inicio</a></li>
                  <li class="active">Realizaciones</li>
                </ul>
            </div>
            <!--------Realizaciones---------->
            <div class="col-md-12" id="datatable">
                <div class="table-responsive">
                    <table id="tablarealizaciones" class="table table-striped table-hover ">
                        <thead>
                             <tr class="success">
                              	 <th>idEspectaculo</th>
                                 <th>Nombre</th>
                                 <th>Fecha</th>
                                 <th>Sala</th>
                                 <th>Direccion Sala</th>
                                 <th><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalCrearReal"><span style="padding-right: 6px;" class="glyphicon glyphicon-plus" style=padding-right: 5px;></span>Crear</button></th>
                             </tr>
                        </thead>
                    </table>
                </div>
            </div>
             <div>
                <form id="frmCrearRealizacion" action="" method="Post" >
                    <input type="hidden" id="idRealizacion" name="idRealizacion" value="0">
                    <!-- Modal -->
                    <div class="modal fade" id="modalCrearReal" tabindex="-1" role="dialog" aria-labelledby="modalCrearLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="modalCrearLabel">Formulario para crear Realizaciones de espectaculos</h4>
                                </div>
                                <div class="modal-body">
                                    <table class="formularioTabla">
                                        <tr>
                                            <th><label for="" class=" control-label">Id Espectaculo</label></th>
                                            <th><div class="col-sm-12"><input id="idEspecReal" name="idEspecReal" type="number" class="form-control"  autofocus></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="" class=" control-label">Fecha</label></th>
                                            <th><div class="col-sm-12"><input id="fecha" name="fecha" type="text" class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="" class="control-label">Id Sala</label></th>
                                            <th><div class="col-sm-12"><input type="number" min="0" step="1" id="idSalaReal" name="idSalaReal" type="text" class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="" class="control-label">Id Sector</label></th>
                                            <th><div class="col-sm-12"><input  id="idSectorReal" name="idSectorReal" type="text" class="form-control" ></div></th>
                                        </tr>
                                        <tr>
                                            <th><label for="" class="control-label">Precio Sector</label></th>
                                            <th><div class="col-sm-12"><input  id="precioSector" name="precioSector" type="text" class="form-control" ></div></th>
                                        </tr>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" id="crear-port" class="btn btn-primary" onclick="return btnCrearPortero();">Crear</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal -->
                </form>
            </div>
        </div>
  </div>
<!--         <div id="divReportes" style="display:none;"> -->
<!--             <div class="col-md-12" > -->
<!--                 <ul class="breadcrumb"> -->
<!--                   <li><a href="#">Inicio</a></li> -->
<!--                   <li class="active">Reportes</li> -->
<!--                 </ul> -->
<!--             </div> -->
<!--             -----------Reportes--------- -->
<!--             <div class="col-md-12" id="datatable"> -->
<!--                 <div class="table-responsive"> -->
<!--                     <table class="table table-striped table-hover "> -->
<!--                         <thead> -->
<!--                              <tr class="success"> -->
<!--                                  <th>Cantidad de entradas</th> -->
<!--                                  <th>Evento</th> -->
<!--                                  <th>Recaudado</th> -->
<!--                              </tr> -->
<!--                         </thead> -->
<!--                     </table> -->
<!--                 </div> -->

<!--             </div> -->
<!--         </div> -->
   


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
