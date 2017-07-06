//carga con la seleccion los dropdowns
function dropdowns(){
	$(".dropdown-menu li a").click(function(){
		  var selText = $(this).text();
		  $(this).parents('.btn-group').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');
});     
}

//Funcion para seleccionar entradas
function cantEntradas() {
    $('.btn-number').click(function(e){
        e.preventDefault();
        
        var fieldName = $(this).attr('data-field');
        var type      = $(this).attr('data-type');
        var input = $("input[name='"+fieldName+"']");
        var currentVal = parseInt(input.val());
        if (!isNaN(currentVal)) {
            if(type == 'minus') {
                var minValue = parseInt(input.attr('min')); 
                if(!minValue) minValue = 1;
                if(currentVal > minValue) {
                    input.val(currentVal - 1).change();
                } 
                if(parseInt(input.val()) == minValue) {
                    $(this).attr('disabled', true);
                }
    
            } else if(type == 'plus') {
                var maxValue = parseInt(input.attr('max'));
                if(!maxValue) maxValue = 9999999999999;
                if(currentVal < maxValue) {
                    input.val(currentVal + 1).change();
                }
                if(parseInt(input.val()) == maxValue) {
                    $(this).attr('disabled', true);
                }
    
            }
        } else {
            input.val(0);
        }
    });
    $('.input-number').focusin(function(){
       $(this).data('oldValue', $(this).val());
    });
    $('.input-number').change(function() {
        
        var minValue =  parseInt($(this).attr('min'));
        var maxValue =  parseInt($(this).attr('max'));
        if(!minValue) minValue = 1;
        if(!maxValue) maxValue = 9999999999999;
        var valueCurrent = parseInt($(this).val());
        
        var name = $(this).attr('name');
        if(valueCurrent >= minValue) {
            $(".btn-number[data-type='minus'][data-field='"+name+"']").removeAttr('disabled')
        } else {
            alert('Sorry, the minimum value was reached');
            $(this).val($(this).data('oldValue'));
        }
        if(valueCurrent <= maxValue) {
            $(".btn-number[data-type='plus'][data-field='"+name+"']").removeAttr('disabled')
        } else {
            alert('Sorry, the maximum value was reached');
            $(this).val($(this).data('oldValue'));
        }
        
        
    });
    $(".input-number").keydown(function (e) {
            // Allow: backspace, delete, tab, escape, enter and .
            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 190]) !== -1 ||
                 // Allow: Ctrl+A
                (e.keyCode == 65 && e.ctrlKey === true) || 
                 // Allow: home, end, left, right
                (e.keyCode >= 35 && e.keyCode <= 39)) {
                     // let it happen, don't do anything
                     return;
            }
            // Ensure that it is a number and stop the keypress
            if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                e.preventDefault();
            }
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
        // agarramos la parte de la url que hay despues del interrogante
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
	    	   
	    	   var sectorYPrecio = "<tr><th value>"+ sector.nombre +"</th><th><span class=\"glyphicon glyphicon-usd\" aria-hidden=\"true\"></span><span id=\"precio\">"+ sector.precio +"</span></th></tr>";
	    	   $("#sectorTabla").append(sectorYPrecio );
	    	   
	    	   var listaSector = "<li><a href=\"#\">" +sector.nombre +"</a></li>";
	    	   $("#listaSector").append(listaSector );
	    	 
	       }
	     });
	
}

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
	
function cargaImagenEsp(){
	var valores=getGET();
	
	 $.ajax({
	        url: "/obtenerImagenesEspectaculoString",
	 		dataType: "json",
     		dataSrc: "",
     		type: "GET",
     		data: {
     			espectaculoId: valores["id"]
             },
             beforeSend: function(xhr){
                 var pathname = window.location.pathname;
                 xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
         }
	    }).then(function(data) {
    	   document.getElementById("imgEsp").src= "data:image/jpg;base64,"+data[0];   
	     });
	
}
function cargaImagenSala(){
	var valores=getGET();
	
	 $.ajax({
	        url: "/obtenerImagenesEspectaculo",
	 		dataType: "json",
     		dataSrc: "",
     		type: "GET",
     		data: {
     			espectaculoId: valores["id"]
             },
             beforeSend: function(xhr){
                 var pathname = window.location.pathname;
                 xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
         }
	    }).then(function(data) {
    	   document.getElementById("imgEsp").src= "data:image/jpg;base64,"+data[0];   
	     });
	
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
					location.assign("error")
				}
			};
			var pathname = window.location.pathname;
			xhttp.setRequestHeader("X-TenantID", pathname
					.split('/')[1]);
			xhttp.send();
		
		}
	}


function onSignIn(googleUser) {
	var profile = googleUser.getBasicProfile();
	console.log('ID: ' + profile.getId()); // habría que usar un token 
	console.log('Name: ' + profile.getName());
	console.log('Image URL: ' + profile.getImageUrl());
	console.log('Email: ' + profile.getEmail()); // podría ser null
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
//				document.getElementById("demo").innerHTML = this.responseText;
		}
	};
	var urlAndParams = "/loginUsuarioFinalGmail/"
		
	urlAndParams += "?id=" + profile.getId();
	urlAndParams += "&email=" + profile.getEmail();
	console.log(urlAndParams)
	xhttp.open("POST", urlAndParams,
			true);
	xhttp.setRequestHeader("X-TenantID", window.location.pathname.split( '/' )[1]);
// 	  var data= { 
//	        "id": profile.getId(), 
//	        "email":  profile.getEmail()
//	    },
// 	  data= JSON.stringify(data)
	  xhttp.send();
}
	// 			    $.post( "/loginUsuarioFinalGmail/", { id: "John", email: "2pm" } );

// 			    	  $.ajax({
// 			    		    url: "ajax.aspx?ajaxid=4",
// 			    		    data: { 
// 			    		        "id": profile.getId(), 
// 			    		        "email":  profile.getEmail()
// 			    		    },
// 			    		    cache: false,
// 			    		    type: "POST",
// 			    		    success: function(response) {
// 			    		    	console.log('ANDAAAAAAAA0'); 
// 			    		    },
// 			    		    error: function(xhr) {

// 			    		    }
// 			    		});
// 			Y.io("/loginUsuarioFinalGmail/", {
// 			    method: "POST",
// 			    data: {
// 			      "entry[data]": Y.one("#entryText").get(profile.getId())
// 			    },
// 			    on: {
// 			      success: function(transactionid, response, arguments) {
// 			        alert("entry successfully added");
// 			      },
// 			      failure: function(transactionid, response, arguments) {
// 			        alert("add entry failed: " + response.responseText);
// 			      }
// 			    }
// 			  });
