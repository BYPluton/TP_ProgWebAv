let $liste1 = $("#liste1");
let $liste2 = $("#liste2");
let $liste3 = $("#liste3");
let $liste4 = $("#liste4");

$(document).ready(function () {
    $.get("http://localhost:8080/rest/users/" + sessionStorage.getItem("idUser") + "/watchlists", function(response){
        for(let i = 0; i < response.length; i++){
            ajouterListe(response[i],$liste1);
            ajouterListe(response[i],$liste3);
        }
    })
    $.get("http://localhost:8080/rest/items", function(animes){
        animes.forEach(anime => ajouterAnime(anime,$liste2));
    })
});

function ajouterListe(l,liste){
    liste.append('<option value=' + l.id + '>' + l.name +'</option>');
}

function ajouterAnime(anime,liste){
    liste.append('<option value=' + anime.id + '>' + anime.titre +'</option>');
}

function onChangeSelect(){
    if($liste3.val() >= 0){
        /*
        REQUETE GET SUR ITEMS WATCHLIST
        $.get("http://localhost:8080/rest/watchlists/items", function(animes){
        document.getElementById("liste4").length = 1;
        animes.forEach( anime => ajouterAnime(anime,$liste4));
    })*/
    }
}

function bouton_ajouter(){
    if($liste1.val() >= 0 && $liste2.val() >= 0){
        /*
        REQUETE POST ICI
        */
        window.location.href = "/accueil";
    }
    else alert("Veuillez sélectionner des valeurs corrects !");
}

function bouton_suppr(){
    if($liste3.val() >= 0 && $liste4.val() >= 0){
        /*
        REQUETE DELETE ICI
        */
        window.location.href = "/accueil";
    }
    else alert("Veuillez sélectionner des valeurs corrects !");
}