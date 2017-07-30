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

//carga data table de espectaculo (index.jsp)
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
	          { "data": "tipoEspectaculo.0.nombre" }

	     ],
		});
	}

//carga data tabla de salas
function cargaDataTableSalas(){
    var table= $('#tablaSala').DataTable( {
        "destroy": true,
	    "ajax": {
	           "url": '/obtenerSalas/',
	           "dataType": 'json',
	           "dataSrc": "",
	           "type": "GET",
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
                "<button type=\"button\" class=\"btnEditarSala btn btn-default btn-sm\" data-toggle=\"modal\" data-target=\"#modalEditar\"><span style=\"font-size:12px;\" class=\"pull-right hidden-xs showopacity glyphicon glyphicon-pencil\"></span></button>" + " " +
                "<button type=\"button\" class=\"btnEliminarSala btn btn-danger btn-sm\" data-toggle=\"modal\" data-target=\"#modalEliminar\"><span style=\"font-size:12px;\" class=\"pull-right hidden-xs showopacity glyphicon glyphicon-trash\"></span></button>"
    	       }

	     ],

		});
    editar_salas("#tablaSala tbody", table);
    obtener_id_sala("#tablaSala tbody", table);
	}

function editar_salas(tbody, table){
	$(tbody).on("click", "button.btnEditarSala", function(){
		var data = table.row( $(this).parents("tr")).data();
	    var id= $("#idSala").val (data.id);
            nombre = $("#nombreSala").val (data.nombre);
            direccion = $("#direccionSala").val(data.direccion);
            total_localidad = $("#cantLocalidadesSala").val(data.total_localidad);
	})

}
function obtener_id_sala(tbody, table){
	$(tbody).on("click", "button.btnEliminarSala", function(){
		var data = table.row( $(this).parents("tr")).data();
		var id = $("#idSalaE").val (data.id);

	})

}

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
                //traditional: true,
                beforeSend: function(xhr){
                    var pathname = window.location.pathname;
                    xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
                    console.log("tenant: " + pathname.split('/')[1]);
                },
                success: function(data){
                    console.log("exitos");
                },
                error: function(data){
                    console.log("no anda: " + data);
                }
            });

        }

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
	          { "data": "cedula" }

	     ],
	     "columnDefs": [
	        	        { "visible": false, "targets": [1] }
	        	                    ],

		});
	}


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
