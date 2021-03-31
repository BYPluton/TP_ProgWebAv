let $maListe = $("#liste");

$(document).ready(function () {
    let id = document.getElementById("idUser").textContent;
    document.getElementById("idUser").remove();
    sessionStorage.setItem("idUser", id);



    $.get("http://localhost:8080/rest/users/" + id + "/watchlists", function(listes){
        listes.forEach( liste => ajouter(liste));
    })
    /*à modifier pour le delete
    $(".btn-danger").click(function(){
        alert();
        $(this).parent().parent().parent().remove();
    })*/
});


function ajouter(liste){
    $maListe.append('<div class="col"><div class="row g-0 border rounded overflow-hidden flex-md-row shadow-sm h-md-250 position-relative"><div class="col p-4 d-flex flex-column position-static"><strong class="d-inline-block mb-2 montexte">Liste n°1</strong><h3 class="mb-0">'+ liste.name +'</h3><p class="card-text mb-auto" style="margin-top: 2.5%;margin-bottom: 2.5%!important;">Liste contenant les animes que je dois voir.</p><div class="btn-group"><button type="button" style="box-sizing: content-box;" class="btn btn-danger">Supprimer</button></div></div> </div></div>');
}
