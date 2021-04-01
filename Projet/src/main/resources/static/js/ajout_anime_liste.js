let $liste1 = $("#liste1");
let $liste2 = $("#liste2");
let $liste3 = $("#liste3");
let $liste4 = $("#liste4");

$(document).ready(function () {
    //Récupère les watchlists de l'utilisateur actif pour les ajouter dans les selects 1 et 3
    $.get("http://localhost:8080/rest/users/" + sessionStorage.getItem("idUser") + "/watchlists", function(response){
        for(let i = 0; i < response.length; i++){
            ajouterListe(response[i],$liste1);
            ajouterListe(response[i],$liste3);
        }
    })
    //Ajoute tous les animes dans le select n°2
    $.get("http://localhost:8080/rest/items", function(animes){
        animes.forEach(anime => ajouterAnime(anime,$liste2));
    })
});

//Ajoute la watchlist l dans le select "liste"
function ajouterListe(l,liste){
    liste.append('<option value=' + l.id + '>' + l.name +'</option>');
}

//Ajoute l'anime dans le select "liste"
function ajouterAnime(anime,liste){
    liste.append('<option value=' + anime.id + '>' + anime.titre +'</option>');
}

//Modifie l'affichage du select 4 pour y ajouter uniquement les animes présents dans la liste sélectionnée dans le select 3
function onChangeSelect(){
    document.getElementById("liste4").length = 1;
    if($liste3.val() >= 0){
        //Requête pour récupérer les animes d'une watchlist
        $.get("http://localhost:8080/rest/watchlists/"+$liste3.val()+"/items", function(animes){
        animes.forEach( anime => ajouterAnime(anime,$liste4));
    })
    }
}

//Fonction du bouton d'ajout
function bouton_ajouter(){
    //Vérifie si ce n'est pas les valeurs par défaut dans les selects
    if($liste1.val() >= 0 && $liste2.val() >= 0){
        //Requête d'ajout d'un anime dans une liste
        $.ajax({
            url: "http://localhost:8080/rest/watchlists/"+$liste1.val()+"/items/"+$liste2.val(),
            type: 'POST',
            success: function() {alert("Ajout réussi")}
        });
    }
    else alert("Veuillez sélectionner des valeurs corrects !");
}

//Fonction du bouton de suppresion
function bouton_suppr(){
    //Vérifie si ce n'est pas les valeurs par défaut dans les selects
    if($liste3.val() >= 0 && $liste4.val() >= 0){
        //Requête de suppression d'un anime dans une liste
        $.ajax({
            url: "http://localhost:8080/rest/watchlists/"+$liste3.val()+"/items/"+$liste4.val(),
            type: 'DELETE',
            success: function() {alert("Suppression réussie")}
        });
    }
    else alert("Veuillez sélectionner des valeurs corrects !");
}