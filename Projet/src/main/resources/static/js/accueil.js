//Récupère la div qui correspond à la liste à append
let $maListe = $("#liste");


$(document).ready(function () {
    //Ajoute à la session l'ID de l'utilisateur connecté
    let id = document.getElementById("idUser").textContent;
    document.getElementById("idUser").remove();
    sessionStorage.setItem("idUser", id);

    //On récupère les listes de l'utilisateur actif
    $.get("http://localhost:8080/rest/users/" + id + "/watchlists", function(listes){
        for(let i = 0; i < listes.length; i++){
            ajouter(listes[i],i)
        }
    })
});

//Fonction permettant une vignette pour la liste n° J passé en paramètre
//Le j permet d'attribuer un id dynamique aux éléments afin de pouvoir récupérer des éléments particuliers
function ajouter(liste,j){
    // Ajoute une vignette
    $maListe.append('<div class="col"><div class="row g-0 border rounded overflow-hidden flex-md-row shadow-sm h-md-250 position-relative"><div id="test'+j+'" class="col p-4 d-flex flex-column position-static"><strong class="d-inline-block mb-2 montexte">Liste n°'+(j+1) +'</strong><h3 class="mb-0">'+ liste.name +'</h3><p class="card-text mb-auto" style="margin-top: 2.5%;margin-bottom: 2.5%!important;">'+liste.description+'</p>');
    //Récupère tous les animes pour la liste avec l'id : liste.id
    $.get("http://localhost:8080/rest/watchlists/" + liste.id + "/items", function(listes){
        for(let i = 0; i < listes.length; i++){
            //Affiche l'anime dans la vignette de sa liste
            $("#test"+j).append('<small class="form-text text-muted">'+ listes[i].titre +'</small>');
        }
        $("#test"+j).append('<div class="btn-group"><button id="' + liste.id + '" type="button" style="box-sizing: content-box;margin-top: 2%;" class="btn btn-danger">Supprimer</button></div></div> </div></div>')
        supprimer();
    })
    
}
//Fonction permettant de supprimer une liste de la page et dans la BDD
function supprimer(){
    $(".btn-danger").click(function(){
        let $id = $(this).attr('id');
        //Supprime la liste de la BDD
        $.ajax({
            url: "http://localhost:8080/rest/users/delete/"+sessionStorage.getItem("idUser")+"/watchlists/"+$id,
            type: 'DELETE',
            success: function() {alert("Suppression réussi")
            //Supprime la vignette de la liste sur la page
            $(this).parent().parent().parent().remove();}
        });
        window.location.href = "/accueil";
    })
}