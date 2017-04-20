$( document ).ready(function() {
    console.log( "ready!" );


    console.log("In jquery fbreponce");


    $('#submitButton').click(function(){

    	console.log( $("input:radio[name='optionsRadios']:checked").val() );

    	//Prepare all data to be sent to server 

    	var user_id = fbUserObj.id;
    	var user_name = fbUserObj.name;

    	console.log(fbUserObj);

    	var diet_type = $("input:radio[name='optionsRadios']:checked").val();

    	//check which values are checked for allergies
    	var user_allergy = "";
    	if($("#Milk").is(':checked')){
    		user_allergy += "Milk-";
    	}
    	if($("#Peanuts").is(':checked')){
    		user_allergy += "Peanuts-";
    	}
    	if($("#Eggs").is(':checked')){
    		user_allergy += "Eggs-";
    	}
    	if($("#Wheat").is(':checked')){
    		user_allergy += "Wheat";
    	}

    	//if none were checked, enter none
    	if(user_allergy == ""){
    		user_allergy = "None";
    	}

    	console.log(user_allergy);

    	var gluten = $("input:radio[name='optionsRadiosgluten']:checked").val();
    	var kosher = $("input:radio[name='optionsRadioskosher']:checked").val();
    	var lactose = $("input:radio[name='optionslactose']:checked").val();
    	

    	var meats = "";
    	if($("#NoMeat").is(':checked')){
    		meats += "NoMeat-";
    	}
    	if($("#Pork").is(':checked')){
    		meats += "Pork-";
    	}
    	if($("#Beef").is(':checked')){
    		meats += "Beef";
    	}
    	//if none were checked, enter none
    	if(meats == ""){
    		meats = "None";
    	}


    	var eating_enviroment = $("input:radio[name='optionsEating']:checked").val();;

    	//encodeURIComponent(

    	//now that we have all the attributes from the form, build the url
    	var insertURL = "http://localhost:8080/addOrEditUser?user_id=" + encodeURIComponent(user_id) +
    	                                             "&user_name=" + encodeURIComponent(user_name) +
    	                                             "&diet_type=" + encodeURIComponent(diet_type) +
    	                                             "&user_allergy=" + encodeURIComponent(user_allergy) +
    	                                             "&gluten=" + encodeURIComponent(gluten) +
    	                                             "&kosher=" + encodeURIComponent(kosher) +
    	                                             "&lactose=" + encodeURIComponent(lactose) +
    	                                             "&meats=" + encodeURIComponent(meats) +
    	                                             "&eating_environment=" + encodeURIComponent(eating_enviroment);

    	
    	console.log(insertURL);

    	//Push data to server with get request
    	// $.get( insertURL, function(data){
    	// 	console.log(data);
    	// });

    	$.ajax({
    		url: insertURL
    	}).then(function(data, status, jqxhr){
    		console.log(data);
    	});


    });

    


});