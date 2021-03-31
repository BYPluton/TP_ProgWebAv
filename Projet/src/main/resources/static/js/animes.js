let $maListe = $("#liste");

$(document).ready(function () {
    $.get("http://localhost:8080/rest/items", function(animes){
        animes.forEach( anime => ajouter(anime));
        supprimer();
    })
    
});

function supprimer(){
    $(".btn-danger").click(function(){
        let $id = $(this).attr('id');
        $.ajax({
            url: "http://localhost:8080/rest/items/delete/"+$id,
            type: 'DELETE',
            success: function() {}
        });
        $(this).parent().parent().parent().remove();
    })
}
function ajouter(anime){
    $maListe.append('<div class="col"><div class="card shadow-sm"><img class="card-img-top" src="'+ anime.url + '"><div class="card-body"><div class="d-flex justify-content-between align-items-center"><p class="card-text">' + anime.titre + '</p><small class="text-muted">' + anime.nbEpisodes +' épisodes</small></div></div><div class="d-flex justify-content-end align-items-center"><div class="btn-group"><button id="' + anime.id + '" type="button" class="btn btn-danger">Supprimer</button></div></div></div></div>');
}
