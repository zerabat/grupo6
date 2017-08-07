//muestra el elementro del drop seleccionado
function dropFechaCompra() {
    var x = document.getElementById("dropFecha").value;
    document.getElementById("fechaElegida").innerHTML = "Fecha: " + x;
    var dropFecha = document.getElementById("dropFecha");
    var idReal = dropFecha.options[dropFecha.selectedIndex].id;
    console.log(dropFecha.selectedIndex);
    console.log(idReal);
    document.getElementById("idRealizacion").value = idReal;
}
function dropSectorCompra() {
    var y = document.getElementById("dropSector").value;
    document.getElementById("sectorElegido").innerHTML = "Sector: " + y;
    var dropSector = document.getElementById("dropSector");
    var idSec = dropSector.options[dropSector.selectedIndex].id;
    console.log(dropSector.selectedIndex);
    console.log(idSec);
    document.getElementById("idSector").value = idSec;

    // obtener total entradas
    var cant = document.getElementById("entradas").value;
    // obtener precio sector
    var y = document.getElementById("dropSector").value;
    var res = y.split("$ ");
    // calcular total
    if (isNaN(res[1])){
        var total = (cant * 0) / 29;
    }
    else {
        var total = (cant * res[1]) / 29;
    }
    total = Math.round(total * 100) / 100;
    document.getElementById("precioTotal").innerHTML = "Total: U$S " + total;
    document.getElementById("precioInput").value = total;
}

function obtenerEntradas(){
    var cant = document.getElementById("entradas").value;
    console.log(cant);
    document.getElementById("cantEntradas").innerHTML = "Cantidad de entradas: " + cant;


    // obtener total entradas
    var cant = document.getElementById("entradas").value;
    // obtener precio sector
    var y = document.getElementById("dropSector").value;
    var res = y.split("$ ");
    // calcular total
    if (isNaN(res[1])){
        var total = (cant * 0) / 29;
    }
    else {
        var total = (cant * res[1]) / 29;
    }
    total = Math.round(total * 100) / 100;
    document.getElementById("precioTotal").innerHTML = "Total: U$S " + total;
    document.getElementById("precioInput").value = total;
}

function obtenerIdSector(){
    var dropSector = document.getElementById("dropSector");
    var idSec = dropSector.options[dropSector.selectedIndex].id;
    console.log(dropSector.selectedIndex);
    console.log(idSec);
    document.getElementById("idSector").innerHTML = idSec;
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
                var idReal = data.realizacionEspectaculo[index].id;
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

                var listaFecha = "<option id=\""+idReal+"\"value=\" "+ date.getDate() + "/" + month + "/" + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes() +"\">" + date.getDate() + "/" + month + "/" + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes();
                $("#dropFecha").append(listaFecha);
           }
           for (index in data.realizacionEspectaculo[0].sectores) {
               var sector = data.realizacionEspectaculo[0].sectores[index];

               var sectorYPrecio = "<tr><th value>"+ sector.nombre +"</th><th><span class=\"glyphicon glyphicon-usd\" aria-hidden=\"true\"></span><span id=\"precio\">"+ sector.precio +"</span></th></tr>";
               $("#sectorTabla").append(sectorYPrecio);

               var listaSector = "<option id=\""+sector.id+"\" value=\" "+sector.nombre + " $ " + sector.precio +"\">"+sector.nombre + " $ " + sector.precio;
               $("#dropSector").append(listaSector);



           }
           cargaImagenSala(data.realizacionEspectaculo[0].sala.id);
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
            url: "/obtenerImagenesEspectaculoString/",
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

function cargaImagenSala(id){
     $.ajax({
            url: "/obtenerImagenSalaString/",
             dataType: "json",
             dataSrc: "",
             type: "GET",
             data: {
                 salaId: id
             },
             beforeSend: function(xhr){
                 var pathname = window.location.pathname;
                 xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
         }
        }).then(function(data) {
           document.getElementById("imgModalSala").src= "data:image/jpg;base64,"+data[0];
         });

}


//funcion validar usuario y contraseña(login.jsp)
function valida() {
    var email = document.getElementById('email');
    var password = document.getElementById("password");
    if ((email.value == "") || (password.value == "")) {
        window.alert("Los campos E-mail y contraseña no pueden estar vacios");
    } else {
        var dataString = 'email='+email.value+'&password='+password.value;
        $.ajax({
            type: "get",
            url: "/loginUsuarioFinal/",
            data: dataString,
            cache: false,
            beforeSend: function(xhr){
                    var pathname = window.location.pathname;
                    xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
            },
            statusCode: {
                200: function(data) {
                    console.log('200');
                    window.location.assign("index");
                },
                204: function(data) {
                    console.log('204');
                    alert("Error de usuario o clave.");
                },
                403: function(data) {
                    console.log('403');
                    alert("Error de usuario o clave.");
                }
            },
            error: function(data) {
                console.log('error: ' + data.statusCode);
                // Algo salio mal.
            }
        });
    }

    return false;
}


function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    console.log('ID: ' + profile.getId()); // habría que usar un token
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail()); // podría ser null

    // Chequea que el usuario no se haya logueado antes y no exista la variable de storage
    if (sessionStorage.getItem('myUserEntity') != null) {
        console.log('storage found');
        // Already logged in.
        $('#email').prop('disabled', true);
        $('#password').prop('disabled', true);
        $('#btnEntrar').prop('disabled', true);
    } else {
        console.log('storage NOT found');
        // Crea una variable de storage para chequear luego si el usuario esta logueado
        var myUserEntity = {};
        myUserEntity.Id = profile.getId();
        myUserEntity.Name = profile.getName();

        //Store the entity object in sessionStorage where it will be accessible from all pages of your site.
        sessionStorage.setItem('myUserEntity', JSON.stringify(myUserEntity));

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                window.location.assign("index");
            }
        };
        var urlAndParams = "/loginUsuarioFinalGmail/"

        urlAndParams += "?id=" + profile.getId();
        urlAndParams += "&email=" + profile.getEmail();
        console.log(urlAndParams)
        xhttp.open("POST", urlAndParams,
                true);
        xhttp.setRequestHeader("X-TenantID", window.location.pathname.split( '/' )[1]);
        xhttp.send();
    }
}

function register() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var passwordRepeat = document.getElementById("passwordRepeat").value;
    var nombre = document.getElementById("nombre").value;
    var apellido = document.getElementById("apellido").value;
    var cedula = document.getElementById("cedula").value;

    if ((email == "") || (password == "") ||
        (passwordRepeat == "") || (nombre == "") ||
        (apellido == "") || (cedula == "")) {
        window.alert("Todos los campos son requeridos.");
    } else if (password !== passwordRepeat){
         window.alert("Las claves no coinciden.");
    } else {
        var dtos = {
            apellido: apellido,
            cedula: cedula,
            email: email,
              gmailToken: "",
            nombre: nombre,
            password: password,
        };

        $.ajax({
            type: "put",
            url: "/altaUsuarioFinal/",
            dataType: "json",
            data: JSON.stringify(dtos),
            contentType: "application/json; charset=utf-8",
            beforeSend: function(xhr){
                    var pathname = window.location.pathname;
                    xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
            },
            statusCode: {
                200: function(data) {
                    alert("Usuario registrado.");
                    window.location.assign("index");
                },
                204: function(data) {
                    alert("204: Error al registrar.");
                },
                403: function(data) {
                    alert("403: Error al registrar.");
                }
            },
            error: function(data) {

            }
        });
    }

    return false;
}

function logout() {
    if (sessionStorage.getItem('myUserEntity') != null) {
        sessionStorage.clear();
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
          console.log('User signed out.');
        });

        auth2.disconnect();
    }

    $.ajax({
        type: "get",
        url: "/cerrarSesionUsuario/",
        cache: false,
        beforeSend: function(xhr) {
            var pathname = window.location.pathname;
            xhr.setRequestHeader("X-TenantID", pathname.split('/')[1]);
        },
        statusCode: {
            200: function(data) {
                window.location.assign("index");
            }
 },
        error: function(data) {
            // Algo salio mal.
        }
    });
}
