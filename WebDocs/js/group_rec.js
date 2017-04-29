//gets group recommendations from the back end and displays in revieCOntianer on group recommendation page
$(document).ready(function(){


	//get the recommendations from the back end
	function getRecs(){

		console.log("Facebook user object");
		console.log(fbUserObj);

		//convert fb user id to id compatiple with mahout code
		var user_id = fbUserObj.id % 1000 + 1;

		//create array of the users to send to the backend 
		var members = "[" + user_id + "-" + ((10203219280360494 % 1000) + 1) + "-" + ((100702180472712 % 1000) + 1) + "]"; 

		//call the backend and send this array
		$.ajax({
			url: "http://localhost:8080/suggestions_pageGroup?members=" + members
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