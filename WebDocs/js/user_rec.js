//gets user recommendations from the backend and displays on the front-end

$(document).ready(function(){


	//get the recommendations from the back end
	function getRecs(){

		console.log("Facebook user object");
		console.log(fbUserObj);

		//convert fb id to be compatible with mahout code
		var user_id = fbUserObj.id % 1000 + 1;

		//call the backend to get the recommendation data
		$.ajax({
			url: "http://localhost:8080/suggestions_page?user_id=" + user_id
		}).then(function(data, status, jqxhr){

			console.log(data);
			
			//loop over the reviews and populate them in the html
			for(var i = 0; i < data.length; i++ ){

				var html_str = `<div class='row top-buffer margin-top' id='suggest_row'>
									<div class='restaurant_img col-md-2 margin-top'>
										<img src=` + data[i].img_Link + ` class='img-responsive'>
									</div>
									<div class='col-md-9'>
										<h1>Recommendation Details</h1> 
										<p>`+ data[i].restaurant_name + `</p>
										<p>`+ data[i].rating +`</p>
									</div>
								</div>`;

				$("#reviewContainer").append(html_str);
			}

		});	
	}

	//wait a second for facebook code to load before running
	setTimeout(getRecs, 1000);


	






});