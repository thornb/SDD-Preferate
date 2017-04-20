$(document).ready(function(){


	//get the recommendations from the back end
	function getRecs(){

		console.log("Facebook user object");
		console.log(fbUserObj);

		var user_id = fbUserObj.id % 1000 + 1;

		$.ajax({
			url: "http://localhost:8080/suggestions_page?user_id=" + user_id
		}).then(function(data, status, jqxhr){

			console.log(data);
			
			//loop over the reviews and populate them in the html
			for(var i = 0; i < data.length; i++ ){

				$("#reviewContainer").append("<div class='row top-buffer' id='suggest_row'><div class='col-md-2 align-left'></div><div class='col-md-9'><h1>Recommendation Details</h1> <p>"+ data[i].restaurant_name + "</p> <p>"+ data[i].rating +"</p> </div></div>");

			}

		});	
	}


	setTimeout(getRecs, 1000);


	






});