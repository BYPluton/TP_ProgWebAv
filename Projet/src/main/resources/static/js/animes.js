let $maListe = $("#liste");

$(document).ready(function () {
    //Récupère tous les animes
    $.get("http://localhost:8080/rest/items", function(animes){
        animes.forEach( anime => ajouter(anime));
        supprimer();
    })
    
});

//Fonction qui permet de gérer un click de suppression pour un anime
function supprimer(){
    $(".btn-danger").click(function(){
        let $id = $(this).attr('id');
        //Requête de suppression d'un anime dans la BDD
        $.ajax({
            url: "http://localhost:8080/rest/items/delete/"+$id,
            type: 'DELETE',
            success: function() {}
        });
        $(this).parent().parent().parent().remove();
    })
}

//Fonction qui ajoute un anime dans la liste des animes
function ajouter(anime){
    $maListe.append('<div class="col"><div class="card shadow-sm"><img class="card-img-top" src="'+ anime.url + '"><div class="card-body"><div class="d-flex justify-content-between align-items-center"><p class="card-text">' + anime.titre + '</p><small class="text-muted">' + anime.nbEpisodes +' épisodes</small></div></div><div class="d-flex justify-content-end align-items-center"><div class="btn-group"><button id="' + anime.id + '" type="button" class="btn btn-danger">Supprimer</button></div></div></div></div>');
}
