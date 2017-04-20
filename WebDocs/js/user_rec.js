$(document).ready(function(){


	//get the recommendations from the back end
	$.ajax({
		url: "http://localhost:8080/suggestions_page"
	}).then(function(data, status, jqxhr){
		console.log(data);
		console.log(status);
		console.log(jqxhr);
	});






});