<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->

<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" /> -->

<%-- 	<spring:url value="/css/main.css" var="springCss" /> --%>
<%-- 	<link href="${springCss}" rel="stylesheet" /> --%>


<meta name="google-signin-client_id"
	content="1044122178058-iv6un92qnetg6c58as6lks8gfk529p8e.apps.googleusercontent.com">
<c:url value="/css/main.css" var="jstlCss" />

<link href="${jstlCss}" rel="stylesheet" />

<script src="https://apis.google.com/js/platform.js" async defer></script>
</head>
<body>

	<!-- 	<nav class="navbar navbar-inverse"> -->
	<!-- 		<div class="container"> -->
	<!-- 			<div class="navbar-header"> -->
	<!-- 				<a class="navbar-brand" href="#">Spring Boot</a> -->
	<!-- 			</div> -->
	<!-- 			<div id="navbar" class="collapse navbar-collapse"> -->
	<!-- 				<ul class="nav navbar-nav"> -->
	<!-- 					<li class="active"><a href="#">Home</a></li> -->
	<!-- 					<li><a href="#about">About</a></li> -->
	<!-- 				</ul> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</nav> -->

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
			debugger;
			var profile = googleUser.getBasicProfile();
			console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
			console.log('Name: ' + profile.getName());
			console.log('Image URL: ' + profile.getImageUrl());
			console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
			    
	    	  var xhttp = new XMLHttpRequest();
	    	  xhttp.open("POST", "/loginUsuarioFinalGmail/");
	    	  
	    	  xhttp.onreadystatechange = function() {
	    	    if (this.readyState == 4 && this.status == 200) {
	    	      document.getElementById("demo").innerHTML = this.responseText;
	    	    }
	    	  };
	    	  xhttp.setRequestHeader("X-TenantID", "tenant2");
	    	  xhttp.setRequestHeader("Content-type", "application/json");
	    	  var data= { 
	    		        "id": profile.getId(), 
	    		        "email":  profile.getEmail()
	    	  },
		   	  data= JSON.stringify(data)
	    	  
	    	  
	    	  debugger;
	    	  xhttp.send(data);
			    
			    	  
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
		  
		}
	</script>

</body>

</html>