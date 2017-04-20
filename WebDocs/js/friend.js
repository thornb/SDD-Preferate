$(document).ready(function(){
	$.getJSON("http://rpipreferate.com:8080/getUser?id="+friendID, function(result){
		$.each(result, function(i, field){
			for (var y=0; y<field.length; y++){
				document.getElementById("profile pic").setAttribute("src", "http://graph.facebook.com/" + friendID + "/picture?type=large");
				document.getElementById("name").innerHTML = field["name"];
				document.getElementById("diet").innerHTML = "<h3>Dietary Restrictions:</h3><br>" + field["diet_type"];
				document.getElementById("numReviews").innerHTML = "<h3>Reviews Completed:</h3><br>" + field["numReviews"];
				document.getElementById("numGroups").innerHTML = "<h3>Number of Groups:</h3><br>" + field["numGroups"];
				var places = "";
				var z=0;
				for(z=0; z<field["restaurants"].length; z++){
					places += (field["restaurants"][z] + "<br>");
				}
				document.getElementById("placesVisited").innerHTML = "<h3>Places Recently Visited:</h3><br>"+ places;
				var group = "";
				for(z=0; z<field["groups"].length; z++){
					group ="<div class=\"col-md-3\"><img src=\"images/user-default.png\" " +
					"class=\"img-responsive\" alt=\"Cinque Terre\" width=\"304\" height=\"236\">" + 
					"</div><div class=\"col-md-9\"><h3>" + field["groups"][z].name + "</h3></div>";
					var div = createElement("div");
					div.className = "row";
					div.innerHTML = group;
					document.getElementById("scoller").appendChild(div);
					group = "";
				}
  				var review = "";
				for(z=0; z<field["reviews"]; z++){
					review = "<div class=\"col-md-12 top-buffer\" id=\"review"+z+"\">" +
					"<p><strong>"+field["reviews"][z].restaurantName+"</strong></p>" + 
					"<p>Stars:"+field["reviews"][z].stars+"</p>"+
					"<p>Comments: "+field["reviews"][z].comments+"</p></div>";
					var div = createElement("div");
					div.className = "row";
					div.innerHTML = review;
					document.getElementById("reviews").nextSibling.insertBefore(div);
					review = "";
				}
				
			}
        });
    });
});