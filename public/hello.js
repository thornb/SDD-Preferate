$(document).ready(function() {
    $.ajax({
        url: "http://45.55.197.195:8080/greeting?name=Brandon"
    }).then(function(data) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content);
    });
});