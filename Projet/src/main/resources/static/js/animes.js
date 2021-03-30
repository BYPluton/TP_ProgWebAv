let $maListe = $("#liste");

$(document).ready(function () {
    $.get("http://localhost:8080/rest/items", function(animes){
        animes.forEach( anime => ajouter(anime));
    })
    /*à modifier pour le delete
    $(".btn-danger").click(function(){
        alert();
        $(this).parent().parent().parent().remove();
    })*/
});

alert(session.user.pseudo)



function ajouter(anime){
    $maListe.append('<div class="col"><div class="card shadow-sm"><img class="card-img-top" src="'+ anime.url + '"><div class="card-body"><div class="d-flex justify-content-between align-items-center"><p class="card-text">' + anime.titre + '</p><small class="text-muted">' + anime.nbEpisodes +' épisodes</small></div></div><div class="d-flex justify-content-end align-items-center"><div class="btn-group"><button type="button" class="btn btn-danger">Supprimer</button></div></div></div></div>');
}
