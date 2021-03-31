$(document).ready(function () {
    let id = sessionStorage.getItem("idUser");

    $.get("http://localhost:8080/rest/users/" + id + "/watchlists", function(listes){
        listes.forEach( liste => ajouter(liste));
    })
    /*à modifier pour le delete
    $(".btn-danger").click(function(){
        alert();
        $(this).parent().parent().parent().remove();
    })*/
});