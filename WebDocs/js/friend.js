//Displays user info from db for friend profile page
$(document).ready(function(){


	//function to get parameter from url
	var getUrlParameter = function getUrlParameter(sParam) {
	    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
	        sURLVariables = sPageURL.split('&'),
	        sParameterName,
	        i;

	    for (i = 0; i < sURLVariables.length; i++) {
	        sParameterName = sURLVariables[i].split('=');

	        if (sParameterName[0] === sParam) {
	            return sParameterName[1] === undefined ? true : sParameterName[1];
	        }
	    }
	};


	//get user info from server
 	$.ajax({
 		url: "http://localhost:8080/getUser?user_id="+ getUrlParameter("id")
 	}).then(function(data, status, jqxhr){

 		console.log(data);

 		console.log(data.user_name);


 		//populate values in html
 		$("#name").append(data.user_name);
 		$("#diet").append(data.diet);
 		$("#user_allergy").append(data.user_allergy);
 		$("#gluten").append(data.gluten);
 		$("#kosher").append(data.kosher);
 		$("#lactose").append(data.lactose);
 		$("#meats").append(data.meats);
 		$("#eating_environment").append(data.eating_environment);

 		$("#profile_pic").attr('src', "http://graph.facebook.com/" + getUrlParameter("id") + "/picture?type=large");

 	});

});