//Javascript for make/edit group
$(document).ready(function(){

	var count = 0;

	//localhost;8080/createGroup?owner_id=_&group_name=Hello&members=1-2-3

	//when the user clicks creat group

	// $("#CreateGroup").click(function)(){

	// }

	// for (var i=0;i<friend_objects;i++){
		
	// }
	$("#CreateGroup").click(function(){
		
		// if(count == 0){
		// 	count++;
		var friends = [];
		 $("button").click(function(){
            
            $.each($("input[name='friend']:checked"), function(){            
                friends.push($(this).val());
            });
            alert("My friends are: " + friends.join(", "));
        });
// if (document.getElementById('friend1').checked==0) {
//             friends.push(document.getElementById('friend1').innerHTML);
//         }

             var x=document.getElementById("gname").value;
			//add the group
             var div = document.createElement('div');
             div.className = 'col-md-offset-2';
             div.innerHTML='<h3>'+x+'</h3>';

             var div2=document.createElement('div');
             div2.id=count.toString();
             div2.innerHTML='<h3>'+'working'+'</h3>';

             for (var i = 0; i < friends.arrayLength; i++) {
    				alert(friends[i]);
    			    div2.innerHTML=div2.innerHTML+'<h3>'+friends[i]+'</h3>';

    						//Do something
			}
             //div.append(div2);
             (document.getElementById('oldGroup').appendChild(div)).appendChild(div2);
             

		// 	if(count == 0){
		// 	count++;

		// 	//add the group
		// 	$("#oldGroup").append(`<div class="col-md-offset-2">
  // 				 			<h3>Moes Knows</h3>
  //               <ul id="oldGroupMembers">
  //                 <li>Ryan Sherman</li>
  //                 <li>Shreya Patel</li>
  //               </ul>
  //               <!-- <button class="btn-default" id="addMem1">Add member</button> -->
  //               <button type="button" class="btn btn-block" data-toggle="modal" id="addMem1" data-target="#myModal2" >Add Member</button>
  // 				 		</div>
  // 				 		<div class="col-md-9">
  				 		
  // 				 		</div>`);

		// }

		// else if(count == 1){

		// 	//add the new group
		// 	$("#newGroup").append($("#gname").val());

		// 	$("#newGroupMembers").append("<li>John Tusa</li>");
		// 	$("#newGroupMembers").append("<li>Shreya Patel</li>");

		// 	$("#newRow").append(`<button type="button" class="btn btn-block" data-toggle="modal" id="addMem1" data-target="#myModal2" >Add Member</button>`);	
		// }

			count++;
	});

	//add the new user
	$("#AddMembersModal").click(function(){
		$("#oldGroupMembers").append("<li>John Tusa</li>");
	});



});
