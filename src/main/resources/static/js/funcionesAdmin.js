function activeSidebar(){
    // elementos de la lista
     var menues = $(".sideAdmin li");

     // manejador de click sobre todos los elementos
     menues.click(function() {
        // eliminamos active de todos los elementos
        menues.removeClass("active");
        // activamos el elemento clicado.
        $(this).addClass("active");
     });
}

//funcion validar usuario y contraseña(loginAdmin.jsp)
function valida() {
    var email = document.getElementById('email');
    var password = document.getElementById("password");
    if ((email.value == "") || (password.value == "")) {
        window.alert("Los campos E-mail y contraseña no pueden estar vacios");
    } else {
        var dataString = 'email='+email.value+'&password='+password.value;

        $.ajax({
            type: "get",
            url: "/loginAdministradorTenant/",
            data: dataString,
            cache: false,
            beforeSend: function(xhr){
                    var pathname = window.location.pathname;
                    xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
            },
            statusCode: {
                200: function(data) {
                    window.location.assign("indexAdmin");
                },
                204: function(data) {
                    alert("Error de usuario o clave.");
                },
                403: function(data) {
                    alert("Error de usuario o clave.");
                }
            },
            error: function(data) {
                // Algo salio mal.
            }
        });
    }

    return false;
}

//carga data table de espectaculo (indexAdmin.jsp)
function cargaDataTableEspectaculo(){
	var table= $('#tablaEspectaculo').DataTable( {
        "destroy": true,
	    "ajax": {
	           "url": '/obtenerEspectaculos/',
	           "dataType": 'json',
	           "dataSrc": "",
	           "type": "GET",
	           "data": {
					"adminEmail":"admin@ticketya.com"
                 },

               "beforeSend": function(xhr){
	                   var pathname = window.location.pathname;
	                   xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
	           }
	    },
	    "columns" :  [
	          { "data": "id"},
			  { "data": "nombre"},
	          { "data": "descripcion" },
	          { "data": "tipoEspectaculo.0.nombre" },
              { "defaultContent":
                "<button type=\"button\" class=\"btnEditarEspectaculo btn btn-default btn-sm\" data-toggle=\"modal\" data-target=\"#modalEditarEsp\"><span style=\"font-size:13px; padding-right: 5px;\" class=\"hidden-xs showopacity glyphicon glyphicon-pencil\"></span>Editar</button>"
                //"<button type=\"button\" class=\"btnEliminarSala btn btn-danger btn-sm disabled\" data-toggle=\"modal\" data-target=\"#modalEliminar\"><span style=\"font-size:12px;\" class=\"pull-right hidden-xs showopacity glyphicon glyphicon-trash\"></span></button>"
    	       }
	     ],
         "columnDefs": [
                    { "width": "20%", "targets": 1 },
                    { "width": "50%", "targets": 2 },
                    { "width": "15%", "targets": 3 }
         ],
		});
        editar_espectaculos("#tablaEspectaculo tbody", table);
}
    //carga los datos para poder editar sala
function editar_espectaculos(tbody, table){
	$(tbody).on("click", "button.btnEditarEspectaculo", function(){
		var data = table.row( $(this).parents("tr")).data();
	    var idEsp = $("#idEspectaculo").val (data.id);
        var  nombre = $("#nombreEspetaculo").val (data.nombre);
        var  descripcion = $("#descripcionEspectaculo").val(data.descripcion);
        var  tipoEspectaculo = $("#tipoEspectaculo").val(data.tipoEspectaculo[0].id);
        var  nombreTipo = $("#nombreTipo").val(data.tipoEspectaculo[0].nombre);

	})

}

//modificar o editar espectaculo
function btnEditarEspectaculo(){
    var id = document.getElementById("idEspectaculo");
    var nombre = document.getElementById("nombreEspetaculo");
    var descripcion = document.getElementById("descripcionEspectaculo");
    var idtipoEspectaculo = document.getElementById("tipoEspectaculo");
    var nombreTipo = document.getElementById("nombreTipo");

    var infoEspectaculo = {
                          descripcion: descripcion.value,
                          id: id.value,
                          nombre: nombre.value,
                          tipoEspectaculo: [
                            {
                              id: idtipoEspectaculo.value,
                              nombre: nombreTipo.value
                            }
                          ]
                        };

    if ((nombre.value == "") || (descripcion.value == "") || (idtipoEspectaculo.value == "")) {
        window.alert("Los campos nombre,descripcion e id tipo de espectaculo no pueden estar vacios");
    } else {
            $.ajax({
                url: "/modificarEspectaculo/?email=" + "admin@ticketya.com",
                dataType: "json",
                type: "PUT",
                data: JSON.stringify(infoEspectaculo),
                contentType: "application/json; charset=utf-8",
                beforeSend: function(xhr){
                    var pathname = window.location.pathname;
                    xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
                    console.log("tenant: " + pathname.split('/')[1]);
                },
                statusCode: {
                    200: function(data) {
                        $('#modalEditarEsp').modal('hide');
                        alert("Sus cambios fueron guardados con exito!");
                        cargaDataTableEspectaculo()
                    },
                    204: function(data) {
                        $('#modalEditarEsp').modal('hide');
                        alert("Error al guardar los nuevos cambios.");
                    },
                    403: function(data) {
                        $('#modalEditarEsp').modal('hide');
                        alert("Error al guardar los nuevos cambios.");
                    }
                },
                error: function(data) {
                        console.log(data);
                }
            });
        }
        return false;
}
//crea espectaculo
function btnCrearEspectaculo(){
    var nombre = document.getElementById("nombreEspetaculoC");
    var descripcion = document.getElementById("descripcionEspC");
    var idtipoEspectaculoC = document.getElementById("tipoEspectaculoC");
    var nombreTipoC = document.getElementById("nombreTipoC");

    var crearEspectaculo = {
                          descripcion: descripcion.value,
                          nombre: nombre.value,
                          tipoEspectaculo: [
                            {
                              id: idtipoEspectaculoC.value,
                              nombre: nombreTipoC.value
                            }
                          ]
                        };

    if ((nombre.value == "") || (descripcion.value == "") || (idtipoEspectaculoC.value == "")) {
        window.alert("Los campos nombre,descripcion e id tipo de espectaculos no pueden estar vacios");
    } else {
            $.ajax({
                url: "/altaEspectaculo/?email=" + "admin@ticketya.com",
                dataType: "json",
                type: "POST",
                data: JSON.stringify(crearEspectaculo),
                contentType: "application/json; charset=utf-8",
                beforeSend: function(xhr){
                    var pathname = window.location.pathname;
                    xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
                    console.log("tenant: " + pathname.split('/')[1]);
                },
                statusCode: {
                    200: function(data) {
                        $('#modalCrearE').modal('hide');
                        alert("Sus cambios fueron guardados con exito!");
                        cargaDataTableEspectaculo()
                    },
                    204: function(data) {
                        $('#modalCrearE').modal('hide');
                        alert("Error al guardar los nuevos cambios.");
                    },
                    403: function(data) {
                        $('#modalCrearE').modal('hide');
                        alert("Error al guardar los nuevos cambios.");
                    }
                },
                error: function(data) {
                    console.log("error");
                }
            });
        }
        return false;
}
//carga data tabla de salas
function cargaDataTableSalas(){
    var table= $('#tablaSala').DataTable( {
        "destroy": true,
	    "ajax": {
	           "url": "/obtenerSalas/",
	           "dataType": 'json',
	           "dataSrc": "",
	           "type": "GET",
               "data": {
                    "email":"admin@ticketya.com"
                   },
	           "beforeSend": function(xhr){
	                   var pathname = window.location.pathname;
	                   xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
	           }
	    },
	    "columns" :  [
	          { "data": "id"},
			  { "data": "nombre"},
	          { "data": "direccion" },
	          { "data": "total_localidad" },
	          { "defaultContent":
                "<button type=\"button\" class=\"btnEditarSala btn btn-default btn-sm\" data-toggle=\"modal\" data-target=\"#modalEditar\"><span style=\"font-size:13px; padding-right: 5px;\" class=\"hidden-xs showopacity glyphicon glyphicon-pencil\"></span>Editar</button>"
                //"<button type=\"button\" class=\"btnEliminarSala btn btn-danger btn-sm disabled\" data-toggle=\"modal\" data-target=\"#modalEliminar\"><span style=\"font-size:12px;\" class=\"pull-right hidden-xs showopacity glyphicon glyphicon-trash\"></span></button>"
    	       }

	     ],

		});
    editar_salas("#tablaSala tbody", table);
    obtener_id_salaEliminar("#tablaSala tbody", table);
}
//carga los datos para poder editar sala
function editar_salas(tbody, table){
	$(tbody).on("click", "button.btnEditarSala", function(){
		var data = table.row( $(this).parents("tr")).data();
	    var id= $("#idSala").val (data.id);
        var nombre = $("#nombreSala").val (data.nombre);
        var direccion = $("#direccionSala").val(data.direccion);
        var total_localidad = $("#cantLocalidadesSala").val(data.total_localidad);
	})

}

function obtener_id_salaEliminar(tbody, table){
	$(tbody).on("click", "button.btnEliminarSala", function(){
		var data = table.row( $(this).parents("tr")).data();
		var id = $("#idSalaE").val (data.id);

	})

}
//modificar o editar sala
function btnEditarSala(){
    var id = document.getElementById("idSala");
    var nombre = document.getElementById("nombreSala");
    var direccion = document.getElementById("direccionSala");
    var cantLocalidades = document.getElementById("cantLocalidadesSala");

    var infoSala = {
                direccion: direccion.value,
                id: id.value,
                nombre: nombre.value,
                total_localidad: cantLocalidades.value
               };

    if ((nombre.value == "") || (direccion.value == "") || (cantLocalidades.value == "")) {
        window.alert("Los campos nombre,direccion y localidades no pueden estar vacios");
    } else {
            $.ajax({
                url: "/modificarSala/?email=" + "admin@ticketya.com",
                dataType: "json",
                type: "POST",
                data: JSON.stringify(infoSala),
                contentType: "application/json; charset=utf-8",
                beforeSend: function(xhr){
                    var pathname = window.location.pathname;
                    xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
                    console.log("tenant: " + pathname.split('/')[1]);
                },
                statusCode: {
                    200: function(data) {
                        $('#modalEditar').modal('hide');
                        alert("Sus cambios fueron guardados con exito!");
                        cargaDataTableSalas()
                    },
                    204: function(data) {
                        $('#modalEditar').modal('hide');
                        alert("204 Error al guardar los nuevos cambios.");
                    },
                    403: function(data) {
                        $('#modalEditar').modal('hide');
                        alert("403 Error al guardar los nuevos cambios.");
                    }
                },
                error: function(data) {

                }
            });
        }
        return false;
}
//crear Sala
function btnCrearSala(){
    var nombre = document.getElementById("nombreSalaC");
    var direccion = document.getElementById("direccionSalaC");
    var cantLocalidades = document.getElementById("cantLocalidadesSalaC");

    var crarSala = {
                direccion: direccion.value,
                nombre: nombre.value,
                total_localidad: cantLocalidades.value
               };

    if ((nombre.value == "") || (direccion.value == "") || (cantLocalidades.value == "")) {
        window.alert("Los campos nombre,direccion y localidades no pueden estar vacios");
    } else {
            $.ajax({
                url: "/altaNuevaSala/?email=" + "admin@ticketya.com",
                dataType: "json",
                type: "PUT",
                data: JSON.stringify(crarSala),
                contentType: "application/json; charset=utf-8",
                beforeSend: function(xhr){
                    var pathname = window.location.pathname;
                    xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
                    console.log("tenant: " + pathname.split('/')[1]);
                },
                statusCode: {
                    200: function(data) {
                        $('#modalCrearS').modal('hide');
                        alert("Sus cambios fueron guardados con exito!");
                        cargaDataTableSalas()
                    },
                    204: function(data) {
                        $('#modalCrearS').modal('hide');
                        alert("Error al guardar los nuevos cambios.");
                    },
                    403: function(data) {
                        $('#modalCrearS').modal('hide');
                        alert("Error al guardar los nuevos cambios.");
                    }
                },
                error: function(data) {

                }
            });
        }
        return false;
}
//carga data tabla de porteros
function cargaDataTablePorteros(){
    var tableSala= $('#tablaPortero').DataTable( {
	    "ajax": {
            "destroy": true,
	        "url": '/obtenerPorteros/',
	        "dataType": 'json',
	        "dataSrc": "",
	        "type": "GET",
	        "data": {
					"email":"admin@ticketya.com"
                },
	        "beforeSend": function(xhr){
	                   var pathname = window.location.pathname;
	                   xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
	           }
	    },
	    "columns" :  [
	          { "data": "id"},
	          { "data": "password"},
			  { "data": "nombre"},
	          { "data": "apellido" },
	          { "data": "cedula" },
              { "defaultContent":
                "<button type=\"button\" class=\"btnEditarPortero btn btn-default btn-sm\" data-toggle=\"modal\" data-target=\"#modalEditarPortero\"><span style=\"font-size:13px; padding-right: 5px;\" class=\"hidden-xs showopacity glyphicon glyphicon-pencil\"></span>Editar</button>"
                //"<button type=\"button\" class=\"btnEliminarSala btn btn-danger btn-sm disabled\" data-toggle=\"modal\" data-target=\"#modalEliminar\"><span style=\"font-size:12px;\" class=\"pull-right hidden-xs showopacity glyphicon glyphicon-trash\"></span></button>"
    	       }

	     ],
	     "columnDefs": [
	        	        { "visible": false, "targets": 1 },
                        { "width": "15%", "targets": 0 },
                        { "width": "28%", "targets": 2 },
                        { "width": "28%", "targets": 3 },
                        { "width": "15%", "targets": 4 }
	        	       ],

		});
	}
//crear nuevo portero
function btnCrearPortero(){
        var nombre = document.getElementById("nombrePorteroC");
        var apellido = document.getElementById("apellidoPorteroC");
        var cedula = document.getElementById("cedulaPorteroC");
        var pass = document.getElementById("passwordPortero");

        var crarPortero = {
                        apellido: apellido.value,
                        cedula: cedula.value,
                        nombre: nombre.value,
                        password: pass.value
                        };

        if ((nombre.value == "") || (apellido.value == "") || (cedula.value == "")|| (pass.value == "")) {
            window.alert("Los campos nombre,apellido, cedula y contraseña no pueden estar vacios");
        } else {
                $.ajax({
                    url: "/altaPortero/?email=" + "admin@ticketya.com",
                    dataType: "json",
                    type: "PUT",
                    data: JSON.stringify(crarPortero),
                    contentType: "application/json; charset=utf-8",
                    beforeSend: function(xhr){
                        var pathname = window.location.pathname;
                        xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
                        console.log("tenant: " + pathname.split('/')[1]);
                    },
                    statusCode: {
                        200: function(data) {
                            $('#modalCrearP').modal('hide');
                            alert("Se guardado con exito su nuevo portero!");
                            cargaDataTablePorteros()
                        },
                        204: function(data) {
                            $('#modalCrearP').modal('hide');
                            alert("Error al guardar cambios.");
                        },
                        403: function(data) {
                            $('#modalCrearP').modal('hide');
                            alert("Error al guardar cambios.");
                        }
                    },
                    error: function(data) {
                        console.log(data);
                    }
                });
            }
            return false;
    }
//muestra los diferentes div del menu
function sidebarAdmin(){
	 $("#nombreTenant").click(function(){
        $("#panelIndex").show();
        $("#divEspectaculos").hide();
        $("#divSalas").hide();
        $("#divRealizaciones").hide();
        $("#divReportes").hide();
        $("#divPortero").hide();
	 });
    $("#inicio").click(function(){
        $("#panelIndex").show();
        $("#divEspectaculos").hide();
        $("#divSalas").hide();
        $("#divRealizaciones").hide();
        $("#divReportes").hide();
        $("#divPortero").hide();
    });
    $("#portero").click(function(){
        $("#divPortero").show();
        $("#divEspectaculos").hide();
        $("#divSalas").hide();
        $("#divRealizaciones").hide();
        $("#divReportes").hide();
        $("#panelIndex").hide();
    });
    $("#espectaculos").click(function(){
        $("#divEspectaculos").show();
        $("#divPortero").hide();
        $("#divSalas").hide();
        $("#divRealizaciones").hide();
        $("#divReportes").hide();
        $("#panelIndex").hide();
    });
    $("#salas").click(function(){
        $("#divSalas").show();
        $("#divPortero").hide();
        $("#divEspectaculos").hide();
        $("#divRealizaciones").hide();
        $("#divReportes").hide();
        $("#panelIndex").hide();
    });
    $("#realizaciones").click(function(){
        $("#divRealizaciones").show();
        $("#divPortero").hide();
        $("#divEspectaculos").hide();
        $("#divSalas").hide();
        $("#divReportes").hide();
        $("#panelIndex").hide();
    });
    $("#reportes").click(function(){
        $("#divReportes").show();
        $("#divPortero").hide();
        $("#divEspectaculos").hide();
        $("#divSalas").hide();
        $("#divRealizaciones").hide();
        $("#panelIndex").hide();
    });


}

function logout() {
    console.log('inside logout js');
    $.ajax({
        type: "get",
        url: "/cerrarSesionAdmin/",
        cache: false,
        beforeSend: function(xhr) {
            var pathname = window.location.pathname;
            xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
        },
        statusCode: {
            200: function(data) {
                window.location.assign("indexAdmin");
            }
        },
        error: function(data) {
            console.log('error');
        }
    });
}
