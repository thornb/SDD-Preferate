//Javascript for make/edit group
$(document).ready(function(){

	var count = 0;

	//when the user clicks creat group
	$("#CreateGroup").click(function(){
		
		if(count == 0){
			count++;

			//add the group
			$("#oldGroup").append(`<div class="col-md-offset-2">
  				 			<h3>Moes Knows</h3>
                <ul id="oldGroupMembers">
                  <li>Ryan Sherman</li>
                  <li>Shreya Patel</li>
                </ul>
                <!-- <button class="btn-default" id="addMem1">Add member</button> -->
                <button type="button" class="btn btn-block" data-toggle="modal" id="addMem1" data-target="#myModal2" >Add Member</button>
  				 		</div>
  				 		<div class="col-md-9">
  				 		
  				 		</div>`);

		}

		else if(count == 1){

			//add the new group
			$("#newGroup").append($("#gname").val());

			$("#newGroupMembers").append("<li>John Tusa</li>");
			$("#newGroupMembers").append("<li>Shreya Patel</li>");

			$("#newRow").append(`<button type="button" class="btn btn-block" data-toggle="modal" id="addMem1" data-target="#myModal2" >Add Member</button>`);	
		}


	});

	//add the new user
	$("#AddMembersModal").click(function(){
		$("#oldGroupMembers").append("<li>John Tusa</li>");
	});



});