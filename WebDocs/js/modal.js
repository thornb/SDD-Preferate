 // $('#myModal').on('shown.bs.modal', function () {
 //  $('#myInput').focus()
 // });

$(document).ready(function(){
$('#save').click(function(){
    ++id;
    var this_id=id.toString();
    list.push(this_id);
    ++id;
    var this_id_parent=id.toString();
    var nr=document.getElementById("name_restaurant").value;
    var fr=document.getElementById("food_rating").value;
    var mr=document.getElementById("menu_rating").value;
    var sr=document.getElementById("service_rating").value;
    var c=document.getElementById("comment_restaurant").value;

    var div = document.createElement('div');

    div.className = 'row top-buffer';

    div.id=this_id_parent;
    div.style.background='#e2d5d7';
    div.style.height="80%";

    div2=document.createElement('button');
    div2.className="btn btn-primary col-md-12";

    div2.id=this_id;
    div2.setAttribute("data-toggle","modal");
    div2.setAttribute("data-target","#editModal");

    div2.setAttribute("align","center");
    div2.setAttribute("onclick","return myFunc("+this_id_parent+")");
    div2.innerHTML='<h1>Edit Review</h1>';

    div3=document.createElement('p1');
    div3.className="row";
    div3.innerHTML='check';
    div3.id=this_id+"c";

    div.innerHTML='<h1>Name of Restaurant: '+nr+'</h1>\
     <h3>Food Rating: '+fr+'</h3>\
     <h3>Menu Rating: '+mr+'</h3>\
      <h3>Service Rating: '+sr+'</h3>\
      <h3>Comments: '+c+'</h3>' ;
   
     var x=(document.getElementById('review_table').appendChild(div));
     x.appendChild(div2);
 	 searchText(this_id,3,fr,mr,sr,this_id_parent,c);
});
});

//restaurant_review,user_id,food_rating,menu_rating,service_rating,restaurant_id,comments
function searchText(restaurant_review1,user_id1,food_rating1,menu_rating1,service_rating1,restaurant_id1,comments1) {
    var search = {
    restaurant_review : parseInt(restaurant_review1),
    user_id : 3,
    food_rating : parseFloat(food_rating1),
    menu_rating : parseFloat(menu_rating1),
    service_rating: parseFloat(service_rating1),
    restaurant_id: parseInt(restaurant_id1),
    comments: comments1.toString()
    }
    $.ajax({
    type: "POST",
    contentType : 'application/json; charset=utf-8',/ //use Default contentType
    dataType : 'json',
    url: "http://rpipreferate.com:8080/addreview",
    data: search, // Note it is important without stringifying
    success :function(result) {
    	console.log("SUCCESS");
    }
    });
   }

function UpdateReview(restaurant_review1,user_id1,food_rating1,menu_rating1,service_rating1,restaurant_id1,comments1) {
    var search = {
    restaurant_review : parseInt(restaurant_review1),
    user_id : 3,
    food_rating : parseFloat(food_rating1),
    menu_rating : parseFloat(menu_rating1),
    service_rating: parseFloat(service_rating1),
    restaurant_id: parseInt(restaurant_id1),
    comments: comments1.toString()
    }
    $.ajax({
    type: "POST",
    contentType : 'application/json; charset=utf-8', //use Default contentType
    dataType : 'json',
    url: "http://rpipreferate.com:8080/changereview",
    data: search, // Note it is important without stringifying
    success :function(result) {
    	console.log("SUCCESS");
    }
    });
   }

function isNormalInteger(str) {
    var n = Math.floor(Number(str));
    return String(n) === str && n >= 0;
}


function modalonclick(arg){
	var element = document.getElementById(arg).parentNode;
    var menu = element.getElementsByTagName('p1')[0];
    str=arg+"c";
    menu=document.getElementById(str);
	var x=document.getElementById("rest_name_restaurant").value;
	alert("HELOO"+arg);
	menu.innerHTML=x;
	return;

}

function myFunc(arg){
  var element=document.getElementById(arg);

  var att1 = element.getElementsByTagName('h1')[0];
  var att2 = element.getElementsByTagName('h3')[0];
  var att3 = element.getElementsByTagName('h3')[1];
  var att4 = element.getElementsByTagName('h3')[2];
  var att5 = element.getElementsByTagName('h3')[3];

  var att1_temp=(att1.innerHTML).split(":");
  var att2_temp=(att2.innerHTML).split(":");
  var att3_temp=(att3.innerHTML).split(":");
  var att4_temp=(att4.innerHTML).split(":");
  var att5_temp=(att5.innerHTML).split(":");

  document.getElementById("rest_name_restaurant").value=att1_temp[1];
  document.getElementById("rest_food_rating").value=att2_temp[1];
  document.getElementById("rest_menu_rating").value=att3_temp[1];
  document.getElementById("rest_service_rating").value=att4_temp[1];
  document.getElementById("rest_comment_restaurant").value=att5_temp[1];


  $('#edit').unbind().click(function(){
  	element = document.getElementById(arg);
    var att1 = element.getElementsByTagName('h1')[0];
  	var att2 = element.getElementsByTagName('h3')[0];
  	var att3 = element.getElementsByTagName('h3')[1];
  	var att4 = element.getElementsByTagName('h3')[2];
  	var att5 = element.getElementsByTagName('h3')[3];

    var nr=document.getElementById("rest_name_restaurant").value;
    var fr=document.getElementById("rest_food_rating").value;
    var mr=document.getElementById("rest_menu_rating").value;
    var sr=document.getElementById("rest_service_rating").value;
    var c=document.getElementById("rest_comment_restaurant").value;

    var att1_temp=(att1.innerHTML).split(":");
    var att2_temp=(att2.innerHTML).split(":");
    var att3_temp=(att3.innerHTML).split(":");
    var att4_temp=(att4.innerHTML).split(":");
    var att5_temp=(att5.innerHTML).split(":");

    att1.innerHTML=att1_temp[0]+": "+nr;
    att2.innerHTML=att2_temp[0]+": "+fr;
    att3.innerHTML=att3_temp[0]+": "+mr;
    att4.innerHTML=att4_temp[0]+": "+sr;
    att5.innerHTML=att5_temp[0]+": "+c;
	UpdateReview(arg,3,fr,mr,sr,c,3);
	return;
   });
  return;
}

$(document).ready(function(){
	$.getJSON("http://rpipreferate.com:8080/reviews", function(result){
    $.each(result, function(i, field){
    //for (var i=0;i<result.length;i++){
    for (var y=0;y<field.length;y++){
        	++id;
    var this_id=id.toString();
    list.push(this_id);
    ++id;
    var this_id_parent=id.toString();
    var x=document.getElementById("name_restaurant").value;

    var div = document.createElement('div');

    div.className = 'row top-buffer';

    div.id=this_id_parent;
    div.style.background='#e2d5d7';
    div.style.height="80%";

    div2=document.createElement('button');
    div2.className="btn btn-primary col-md-6";
    div2.id=this_id;
    div2.setAttribute("data-toggle","modal");
    div2.setAttribute("data-target","#editModal");
    div2.setAttribute("align","center");
    div2.setAttribute("onclick","myFunc("+this_id_parent+")");
    div2.innerHTML='<h1>Edit Review</h1>';
    //var x=(field.restaurant_id).toString();

    div.innerHTML='<h1>Name of Restaurant:testfortesting2---->'+field[y].comments+'----getting JSON data</h1>\
     <h3>Stars:</h3>\
      <h3>Comments:</h3>';
   
     var x=(document.getElementById('review_table').appendChild(div));
     x.appendChild(div2);
 	}
        });
    });
});

