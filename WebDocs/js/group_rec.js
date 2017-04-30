//gets group recommendations from the back end and displays in revieCOntianer on group recommendation page
$(document).ready(function(){

	//function to get parameter from url
	var getUrlParameter = function getUrlParameter(sParam) {
	    var sPageURL = window.location.search.substring(1),
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

	//get the recommendations from the back end
	function getRecs(){

		console.log("Facebook user object");
		console.log(fbUserObj);

		//convert fb user id to id compatiple with mahout code
		var user_id = fbUserObj.id % 1000 + 1;

		//create array of the users to send to the backend 
		//var members = "[" + user_id + "-" + ((10203219280360494 % 1000) + 1) + "-" + ((100702180472712 % 1000) + 1) + "]"; 

		//call the backend and send this array
		console.log(getUrlParameter("members"));
		$.ajax({
			url: "http://localhost:8080/suggestions_pageGroup?members=" + getUrlParameter("members")
		}).then(function(data, status, jqxhr){

			console.log(data);
			
			//loop over the reviews and populate them in the html
			for(var i = 0; i < data.length; i++ ){
                var html_str = `<div class='row top-buffer margin-top' id='suggest_row'>
									<div class='restaurant_img col-md-2 margin-top'>
										<img src=` + data[i].img_Link + ` class='img-responsive'>
									</div>
									<div class='col-md-9'>
										<h1>`+ data[i].restaurant_name + `</h1> 
										<p>`+ data[i].rating +`</p>
										<br>
									</div>
								</div>`;

				$("#reviewContainer").append(html_str);

			}

		});	
	}


	setTimeout(getRecs, 1000);


	






});