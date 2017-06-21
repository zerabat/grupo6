//carga con la seleccion los dropdowns
function dropdowns(){
	$(".dropdown-menu li a").click(function(){
		  var selText = $(this).text();
		  $(this).parents('.btn-group').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');
});
}


// inicia el calendario(index.jsp)
function cargaCalendario() {
	    $('#calendar').fullCalendar({
	    })
}

//carga data table de espectaculo (index.jsp)
function cargaDatatable(){
	  
		var table= $('#espectaculo').dataTable( {
	    "ajax": {
	           "url": '/verProximosEspectaculosYSusRealizaciones/',
	           "dataType": 'json',
	           "dataSrc": "content",
	           "type": "GET",
	           "data": {
						"_start":"1",
						"_end":"1000"
	                   },
	           "beforeSend": function(xhr){
	                   var pathname = window.location.pathname;
	                   xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
	           }
	    },		            
	    "columns" :  [ 
	          { "data": "id"},
			  { "data": "realizacionEspectaculo.0.fecha",
		    		"render": function (data) {
				          var date = new Date(data);
				          var month = date.getMonth() + 1;							        
				          if (month < 10)
				          	 month= "0" + month;
				          return date.getDate() + "/" + month + "/" + date.getFullYear();
			      	}
				},
	            { "data": "nombre",
	              "render": function ( data, type, full ) {
	            	  return '<a href="infoEspectaculo?id=' + full['id'] + '">' + data + '</a>';
	            	  //return '<a href="infoEspectaculo">' + data + '</a>';
	               }
	            },
	            { "data": "realizacionEspectaculo.0.sala.nombre" },    
	            { "data": "realizacionEspectaculo.0.sala.direccion" }
	         
	     ],		                     
	    "columnDefs": [ 
	        { "visible": false, "targets": [0] }
	                    ],
		});
	}

//toma de la url el id del espectaculo(infoEspectaculo.jsp)
function getGET()
{
    // capturamos la url
    var loc = document.location.href;
    // si existe el interrogante
    if(loc.indexOf('?')>0)
    {
        // cogemos la parte de la url que hay despues del interrogante
        var getString = loc.split('?')[1];
        // obtenemos un array con cada clave=valor
        var GET = getString.split('&');
        var get = {};

        // recorremos todo el array de valores
        for(var i = 0, l = GET.length; i < l; i++){
            var tmp = GET[i].split('=');
            get[tmp[0]] = unescape(decodeURI(tmp[1]));
        }
        return get;
    }
}

//carga la info del espectaculo y sus realizaciones(infoEspectaculo.jsp)
function cargaInfoEspectaculo(){
	var valores=getGET();
	
	 $.ajax({
	        url: "/verEspectaculoYSusRealizaciones/",
	 		dataType: "json",
     		dataSrc: "",
     		type: "GET",
     		data: {
				idEspectaculo: valores["id"]
             },
             beforeSend: function(xhr){
                 var pathname = window.location.pathname;
                 xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
         }
	    }).then(function(data) {
    	   $("#evento").append(data.nombre);
    	   $("#nomEspectaculo").append(data.nombre);
    	   $("#descripcion").append(data.descripcion);
    	   $("#tipoEspectaculo").append(data.tipoEspectaculo[0].nombre);
	       $("#sala").append(data.realizacionEspectaculo[0].sala.nombre);
	       $("#direccion").append(data.realizacionEspectaculo[0].sala.direccion);
	       $("#localidades").append(data.realizacionEspectaculo[0].sala.total_localidad);
	       $("#lugarMapa").append(data.realizacionEspectaculo[0].sala.nombre);

	       //Envia direccion al mapa
	       var direccion = data.realizacionEspectaculo[0].sala.direccion;
	   		// Creamos el Objeto Geocoder
	       var geocoder = new google.maps.Geocoder();
	       //se envia la direccion y se invoca la funcion
	       geocoder.geocode({ 'address': direccion}, geocodeResult);
	       
	       for (index in data.realizacionEspectaculo) {
				var fecha = data.realizacionEspectaculo[index].fecha;
				var date = new Date(fecha);
				var month = date.getMonth() + 1;							        
				if (month < 10) {
					 month= "0" + month;
				}
				var nuevafecha = date.getDate() + "/" + month + "/" + date.getFullYear() + " <b>" + date.getHours() + ":" + date.getMinutes() + "hs</b>" ;
				
				// Si hay mas de una fecha agrega un guion "-" separador
				if (index > 0)
					nuevafecha = " - " + nuevafecha;
					
				$("#fecha").append(nuevafecha);
				
				var fechaLista = "<li><a href=\"#\">" + date.getDate() + "/" + month + "/" + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes() +"</a></li>";
				$("#listaFecha").append(fechaLista);
	       }
	       for (index in data.realizacionEspectaculo[0].sectores) {
	    	   var sector = data.realizacionEspectaculo[0].sectores[index];
	    	   
	    	   var sectorYPrecio = "<tr><th>"+ sector.nombre +"</th><th><span class=\"glyphicon glyphicon-usd\" aria-hidden=\"true\"></span><span id=\"precio\">"+ sector.precio +"</span></th></tr>";
	    	   $("#sector").append(sectorYPrecio );
	    	 
	       }
	     });
	
}


//hacer el llamado fecha hora y sectores precioss
//compra + y - y tambien funcion para seleccionar 

//carga el mapa y genera coordenadas para el
function geocodeResult(results, status) {
    // Verificamos el estatus
    if (status == 'OK') {
       
        var mapOptions = {
            center: results[0].geometry.location,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        mapC = new google.maps.Map($("#map").get(0), mapOptions);
        // fitBounds acercará el mapa con el zoom adecuado de acuerdo a lo buscado
        mapC.fitBounds(results[0].geometry.viewport);
        // Dibujamos un marcador con la ubicación del primer resultado obtenido
        var markerOptions = { position: results[0].geometry.location }
        var marker = new google.maps.Marker(markerOptions);
        marker.setMap(mapC);
    } else {
        
        alert("Geocoding no tuvo éxito debido a: " + status);
    }
}
	

//funcion validar usuario y contraseña(login.jsp)
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
