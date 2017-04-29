//Javascript for make/edit group
$(document).ready(function(){



	//localhost;8080/createGroup?owner_id=_&group_name=Hello&members=1-2-3


	//when the user clicks create group
	$("#CreateGroup").click(function(){
		

		var friends = [];
            
        $.each($("input[name='friend']:checked"), function(){            
            friends.push($(this).val());
        });
        //alert("My friends are: " + friends.join(", "));

        friend_str = friends.join("-");

        friend_str += "-" + user_id;

   		//make API call
   		var createURL = "http://localhost:8080/createGroup?owner_id=" + encodeURIComponent(user_id) +
    	                                             "&group_name=" + encodeURIComponent($("#gname").val()) +
    	                                             "&members=" + encodeURIComponent(friend_str);

    	

    	$.ajax({
    		url: createURL
    	}).then(function(data, status, jqxhr){
    		console.log(data);

    		getGroups();

    	});


		
	});



});
