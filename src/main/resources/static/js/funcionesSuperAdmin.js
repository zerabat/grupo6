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
	var password = document.getElementById("password");
	if (password.value == "") {
    	window.alert("El campo contraseña no puede estar vacio");
	} else {
    	var dataString = 'password='+password.value;

    	$.ajax({
        	type: "put",
        	url: "/loginSuperAdmin/",
        	data: dataString,
        	cache: false,
        	beforeSend: function(xhr){
                	var pathname = window.location.pathname;
                	xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
        	},
        	statusCode: {
            	200: function(data) {
                	window.location.assign("indexSuperAdmin");
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

function crearAdmin() {
	var tenant = document.getElementById("tenant").value;
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	var passwordRepeat = document.getElementById("passwordRepeat").value;
	var nombre = document.getElementById("nombre").value;
	var apellido = document.getElementById("apellido").value;
	var cedula = document.getElementById("cedula").value;
	var passwordAdmin = document.getElementById("passwordAdmin").value;

	if ((tenant == "") || (email == "") || (password == "") ||
    	(passwordRepeat == "") || (nombre == "") ||
    	(apellido == "") || (cedula == "") || (passwordAdmin == "")) {
    	window.alert("Todos los campos son requeridos.");
	} else if (password !== passwordRepeat){
     	window.alert("Las claves no coinciden.");
	} else {
    	var dtos = {
        	apellido: apellido,
        	cedula: cedula,
        	email: email,
        	nombre: nombre,
        	passowd: password,
    	};

    	$.ajax({
        	type: "put",
        	url: "/altaAdministradorTenat/?password=" + passwordAdmin,
        	dataType: "json",
        	data: JSON.stringify(dtos),
        	contentType: "application/json; charset=utf-8",
        	beforeSend: function(xhr){
                	var pathname = window.location.pathname;
                	xhr.setRequestHeader("X-TenantID", tenant);
        	},
        	statusCode: {
            	200: function(data) {
                	alert("Usuario creado.");
                	limpiarValores();
            	},
            	204: function(data) {
                	alert("204: Error al crear.");
            	},
            	403: function(data) {
                	alert("403: Error al crear.");
            	}
        	},
        	error: function(data) {
       		 console.log('error: ' + data);
        	}
    	});
	}

	return false;
}

function crearTenant() {
	var password = document.getElementById("passwordTenant").value;
	var tenant = document.getElementById("nombreNuevoTenant").value;
	console.log('js tenant: ' + tenant);
	if (password == "" || tenant == "") {
    	window.alert("El campo contraseña y/o el campo tenant no pueden estar vacios");
	} else {
    	var dataString = 'nombreTenant=' + tenant + '&password=' + password;

    	$.ajax({
        	type: "put",
        	url: "/crearNuevoTenat/",
        	data: dataString,
        	cache: false,
        	statusCode: {
            	200: function(data) {
           		 alert("Tenant creado correctamente.");
           		 limpiarValores();
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
       		 console.log('error al crear el tenant');
        	}
    	});
	}

	return false;
}


function logout() {
    console.log('inside logout js');
    $.ajax({
   	 type: "get",
   	 url: "/cerrarSesionSuperAdmin/",
   	 cache: false,
   	 beforeSend: function(xhr) {
   		 var pathname = window.location.pathname;
   		 xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
   	 },
   	 statusCode: {
   		 200: function(data) {
   			 window.location.assign("loginSuperAdmin");
   		 }
   	 },
   	 error: function(data) {
   		 console.log('error');
   	 }
    });
}

function limpiarValores() {
    document.getElementById("tenant").value = "";
    document.getElementById("email").value = "";
	document.getElementById("password").value = "";
	document.getElementById("passwordRepeat").value = "";
	document.getElementById("nombre").value = "";
	document.getElementById("apellido").value = "";
	document.getElementById("cedula").value = "";
	document.getElementById("passwordAdmin").value = "";

	document.getElementById("nombreNuevoTenant").value = "";
	document.getElementById("passwordTenant").value = "";
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
    	$("#divTenants").hide();
    	$("#divAdministradores").hide();
     });
	$("#inicio").click(function(){
    	$("#panelIndex").show();
    	$("#divTenants").hide();
    	$("#divAdministradores").hide();
	});
	$("#administradores").click(function(){
    	$("#divAdministradores").show();
    	$("#divTenants").hide();
    	$("#panelIndex").hide();
	});
	$("#tenants").click(function(){
    	$("#divTenants").show();
    	$("#divAdministradores").hide();
    	$("#panelIndex").hide();
	});
}
