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