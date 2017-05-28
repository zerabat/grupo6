<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->

<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<meta name="google-signin-client_id"
	content="1044122178058-iv6un92qnetg6c58as6lks8gfk529p8e.apps.googleusercontent.com">
<c:url value="/css/main.css" var="jstlCss" />

<link href="${jstlCss}" rel="stylesheet" />

<script src="https://apis.google.com/js/platform.js" async defer></script>
</head>
<body>

	<div class="container">

		<div class="starter-template">
			<h1>Spring Boot Web JSP Example</h1>
			<h2>Message: ${message}</h2>
		</div>

	</div>
	<div class="g-signin2" data-onsuccess="onSignIn"></div>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script>
		function onSignIn(googleUser) {
			var profile = googleUser.getBasicProfile();
			console.log('ID: ' + profile.getId()); // habría que usar un token 
			console.log('Name: ' + profile.getName());
			console.log('Image URL: ' + profile.getImageUrl());
			console.log('Email: ' + profile.getEmail()); // podría ser null
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
// 					document.getElementById("demo").innerHTML = this.responseText;
				}
			};
			var urlAndParams = "/loginUsuarioFinalGmail/"
				
			urlAndParams += "?id=" + profile.getId();
			urlAndParams += "&email=" + profile.getEmail();
			console.log(urlAndParams)
			xhttp.open("POST", urlAndParams,
					true);
			xhttp.setRequestHeader("X-TenantID", window.location.pathname.split( '/' )[1]);
// 	    	  var data= { 
//   		        "id": profile.getId(), 
//   		        "email":  profile.getEmail()
//   		    },
// 	    	  data= JSON.stringify(data)
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
	</script>

</body>

</html>