//Deals withe the review page functionality to add/edit reviews and calls the database

$(document).ready(function(){
$('#save').click(function(){
    //++id;
    var temp=0;
    id++;
    while (temp!=1){
      var pass=0;
      for (var t=0;t<current_ids.length;t++){
        if ((id).toString()==(current_ids[t]).toString()){
          id++;
          pass=1;
        }
      }
      if (pass==0){
        temp=1;
      }
    }
    current_ids.push(id);
    var pass_id=id;
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

    div2.style.background='#000000';

    div2.id=this_id;
    div2.setAttribute("data-toggle","modal");
    div2.setAttribute("data-target","#editModal");

    div2.setAttribute("align","center");
    div2.setAttribute("onclick","return myFunc("+this_id_parent+","+this_id+")");
    div2.innerHTML='<h1>Edit Review</h1>';

    div3=document.createElement('p1');
    div3.className="row";
    div3.innerHTML='check';
    div3.id=this_id+"c";

    div.innerHTML='<h1>Name of Restaurant: '+nr+'</h1>\
     <h3>Food Rating: '+Number(fr)+'</h3>\
     <h3>Menu Rating: '+Number(mr)+'</h3>\
      <h3>Service Rating: '+Number(sr)+'</h3>\
      <h3>Comments: '+c+'</h3>' ;
   
     var x=(document.getElementById('review_table').appendChild(div));
     x.appendChild(div2);
   searchText(this_id,3,fr,mr,sr,this_id_parent,c,nr);
});
});

//restaurant_review,user_id,food_rating,menu_rating,service_rating,restaurant_id,comments
function searchText(restaurant_review1,user_id1,food_rating1,menu_rating1,service_rating1,restaurant_id1,comments1,restaurant_name1) {
    //var temp=parseInt(restaurant_review1);

    var search = {
    restaurant_review: Number(restaurant_review1),
    user_id: user_id,
    food_rating: Number(food_rating1),
    menu_rating: Number(menu_rating1),
    service_rating: Number(service_rating1),
    restaurant_id: 11,
    comments: comments1,
    restaurant_name: restaurant_name1
    }
    $.ajax({
    type: "POST",
    //contentType : 'text/json; charset=utf-8', //use Default contentType
    dataType : 'json',
    url: "http://localhost:8080/addreview",
    data: search, // Note it is important without stringifying
    success :function(result) {
      console.log("SUCCESS");
    }
    });
   }

function UpdateReview(restaurant_review1,user_id1,food_rating1,menu_rating1,service_rating1,restaurant_id1,comments1,restaurant_name1) {
    // var search = {
    // restaurant_review : parseInt(restaurant_review1),
    // user_id : 3,
    // food_rating : parseFloat(food_rating1),
    // menu_rating : parseFloat(menu_rating1),
    // service_rating: parseFloat(service_rating1),
    // restaurant_id: parseInt(restaurant_id1),
    // comments: comments1.toString(),
    // restaurant_name: restaurant_name1.toString()
    // }
    // $.ajax({
    // type: "POST",
    // contentType : 'application/json; charset=utf-8', //use Default contentType
    // dataType : 'json',
    // url: "http://localhost:8080/changereview",
    // data: JSON.stringify(search), // Note it is important without stringifying
    // success :function(result) {
    //  console.log("SUCCESS");
    // }
    // });
  var search = {
    restaurant_review: Number(restaurant_review1),
    user_id: user_id,
    food_rating: Number(food_rating1),
    menu_rating: Number(menu_rating1),
    service_rating: Number(service_rating1),
    restaurant_id: 11,
    comments: comments1,
    restaurant_name: restaurant_name1
    }
    $.ajax({
    type: "POST",
    //contentType : 'text/json; charset=utf-8', //use Default contentType
    dataType : 'json',
    url: "http://localhost:8080/changereview",
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

function myFunc(arg,child){
  var element=document.getElementById(arg);

  //var child=(element.getElementsByTagName('button')[0]).id;
  var att1 = element.getElementsByTagName('h1')[0];
  var att2 = element.getElementsByTagName('h3')[0];
  console.log("ATT2:",att2);
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
  UpdateReview(child,3,fr,mr,sr,3,c,nr);
  return;
   });
  return;
}

$(document).ready(function(){
  $.getJSON("http://localhost:8080/reviews", function(result){
    //var whattodo=JSON.parse(result);
  $.each(result,function(i,field){
    for (var y=0;y<field.length;++y){
    // var whattodo=JSON.parse(field);
      //current_ids.push(field[y].restaurant_review);
  var temp_id=field[y].restaurant_review;
  current_ids.push(temp_id);
    var this_id=temp_id.toString();
    list.push(this_id);
    ++temp_id;
    var this_id_parent=temp_id.toString();
    var nr=field[y].name;
    var fr=field[y].food_rating;
    var mr=field[y].menu_rating;
    var sr=field[y].service_rating;
    var c=field[y].comments;


    var div = document.createElement('div');

    div.className = 'row top-buffer';

    div.id=this_id_parent;
    div.style.background='#e2d5d7';
    div.style.height="80%";

    div2=document.createElement('button');
    div2.className="btn btn-primary col-md-12";

    div2.style.background='#000000';
    div2.id=this_id;
    div2.setAttribute("data-toggle","modal");
    div2.setAttribute("data-target","#editModal");

    div2.setAttribute("align","center");
    div2.setAttribute("onclick","return myFunc("+this_id_parent+","+this_id+")");
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
    }
    
  });
});
});


