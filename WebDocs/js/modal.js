$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').focus()
});

$(document).ready(function(){
$('#save').click(function(){
    ++id;
    var this_id=id.toString();
    list.push(this_id);
    ++id;
    var this_id_parent=id.toString();
    var x=document.getElementById("name_restaurant").value;

    var div = document.createElement('div');

    div.className = 'row top-buffer';

    //div.id='review1';
    // div.style.background='#e2d5d7';
    // div.style.height="80%";

    div.id=this_id_parent;
    div.style.background='#e2d5d7';
    div.style.height="80%";

    div2=document.createElement('button');
    div2.className="btn btn-primary col-md-6";
    //div2.id="#edit_button2";
    div2.id=this_id;
    div2.setAttribute("data-toggle","modal");
    div2.setAttribute("data-target","#editModal");
    div2.setAttribute("align","center");
    div2.setAttribute("onclick","myFunc(this.id)");
    div2.innerHTML='<h1>Edit Review</h1>';

    div3=document.createElement('p1');
    div3.className="row";
    div3.innerHTML='check';
    div3.id=this_id+"c";

    //div.appendChild(div2);
    div.innerHTML='<h1>Name of Restaurant:'+x+'testforvalidation</h1>\
     <h3>Stars:</h3>\
      <h3>Comments:</h3>';
   
     // document.getElementById(this_id).value=x;

     // document.getElementById('body').appendChild(div2);
     //div.appendChild(div3);
     //div.appendChild(div2);
     var x=(document.getElementById('review_table').appendChild(div));
     x.appendChild(div3);
     x.appendChild(div2);
     //.appendChild(div2);


     //document.div.appendChild(div2);


});
});


function isNormalInteger(str) {
    var n = Math.floor(Number(str));
    return String(n) === str && n >= 0;
}

//window.onload = function() {
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
//}

function myFunc(arg){
  var element = document.getElementById(arg).parentNode;
  var menu = element.getElementsByTagName('p1')[0];
  //menu.innerHTML="See if first is changing";
  document.getElementById("rest_name_restaurant").value=menu.innerHTML;
  //var x=document.getElementById("rest_name_restaurant").value;
  $('#edit').click(function(){
  	var element = document.getElementById(arg).parentNode;
    var menu = element.getElementsByTagName('p1')[0];
    str=arg+"c";
    menu=document.getElementById(str);
	var x=document.getElementById("rest_name_restaurant").value;
	alert("HELOO"+arg);
	menu.innerHTML=x;
	return;
  	// element = document.getElementById(arg).parentNode;
   //  var x=document.getElementById("rest_name_restaurant").value;
   //  element.getElementsByTagName('h1')[0].innerHTML=x;
   });
  // var x=modalonclick();
  //element.getElementsByTagName('h1')[0].innerHTML=x;
  
  alert("PASSS"+arg);
}

$(document).ready(function(){
	$.getJSON("http://rpipreferate.com:8080/reviews", function(result){
    $.each(result, function(i, field){
    //for (var i=0;i<result.length;i++){
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
    div2.setAttribute("onclick","myFunc(this.id)");
    div2.innerHTML='<h1>Edit Review</h1>';
    //var x=(field.restaurant_id).toString();
    div.innerHTML='<h1>Name of Restaurant:testfortesting2---->'+field+'----getting JSON data</h1>\
     <h3>Stars:</h3>\
      <h3>Comments:</h3>';
   
     var x=(document.getElementById('review_table').appendChild(div));
     x.appendChild(div2);
 
        });
    });
});