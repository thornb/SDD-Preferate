$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').focus()
})

$('#review_button').click(function(){

		var tr;
		tr.append('<div class="row" id="review_row">');
        tr.append('<div class="col-md-12 top-buffer" id="review1">');
		tr.append("<h1>Name of Restaurant:</h1>");
		tr.append("<h3>Stars:</h3>");
		tr.append("<h3>Comments:"+"WHJE"+ "</h3>")
		tr.append('/div');
		tr.append('/div');
		$('#review_table').append(tr);
});