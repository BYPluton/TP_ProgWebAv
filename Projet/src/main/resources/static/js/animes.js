/*$("#monbouton").click(function(){
    $("#liste").append("<div class=\"col\"><div class=\"card shadow-sm\"><img class =\"card-img-top\" src=\"../static/css/img/anime_db/naruto.jpeg\"><div class=\"card-body\" ><div class=\"d-flex justify-content-between align-items-center\"><p class=\"card-text\">Naruto</p> <small class=\"text-muted\">220 épisodes</small></div></div><div class=\"d-flex justify-content-end align-items-center\"><div class=\"btn-group\"> <button type=\"button\" class=\"btn btn-danger\">Supprimer</button></div></div></div></div>");
})

$(".btn-danger").click(function(){
    alert("ça marche pas")
    $(this).parent().parent().parent().remove();
})
$(document).ready(function () {
    
});*/
$.get("http://localhost:8080/rest/items", function(data){
    alert("Data: " + data);
})
