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
//carga data table de espectaculo (index.jsp)
function cargaDatatableEspectaculo(){
    var table= $('#tablaEspectaculo').dataTable( {
	    "ajax": {
	           "url": '/obtenerEspectaculos/',
	           "dataType": 'json',
	           "dataSrc": "",
	           "type": "GET",
	           "bDestroy": true,
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
function cargaDatatableSalas(){
    var table= $('#tablaSala').dataTable( {
	    "ajax": {
	           "url": '/obtenerSalas/',
	           "dataType": 'json',
	           "dataSrc": "",
	           "type": "GET",
	           "bDestroy": true,
	            "beforeSend": function(xhr){
	                   var pathname = window.location.pathname;
	                   xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
	           }
	    },
	    "columns" :  [
	          { "data": "id"},
			  { "data": "nombre"},
	          { "data": "direccion" },
	          { "data": "cantidad" }

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
