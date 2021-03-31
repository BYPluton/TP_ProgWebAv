let $maListe = $("#liste");

$(document).ready(function () {
    let id = document.getElementById("idUser").textContent;
    document.getElementById("idUser").remove();
    sessionStorage.setItem("idUser", id);


    $.get("http://localhost:8080/rest/users/" + id + "/watchlists", function(listes){
        for(let i = 0; i < listes.length; i++){
            ajouter(listes[i],i)
        }
        supprimer();
    })
});


function ajouter(liste,j){
    $maListe.append('<div class="col"><div class="row g-0 border rounded overflow-hidden flex-md-row shadow-sm h-md-250 position-relative"><div id="test'+j+'" class="col p-4 d-flex flex-column position-static"><strong class="d-inline-block mb-2 montexte">Liste n°'+(j+1) +'</strong><h3 class="mb-0">'+ liste.name +'</h3><p class="card-text mb-auto" style="margin-top: 2.5%;margin-bottom: 2.5%!important;">'+liste.description+'</p>');
    //$("#test"+j).append('<small class="form-text text-muted"> anime 1</small>');
    $.get("http://localhost:8080/rest/watchlists/" + liste.id + "/items", function(listes){
        for(let i = 0; i < listes.length; i++){
            $("#test"+j).append('<small class="form-text text-muted">'+ listes[i].titre +'</small>');
        }
        $("#test"+j).append('<div class="btn-group"><button id="' + liste.id + '" type="button" style="box-sizing: content-box;margin-top: 2%;" class="btn btn-danger">Supprimer</button></div></div> </div></div>')
    })
    
}
function supprimer(){
    $(".btn-danger").click(function(){
        let $id = $(this).attr('id');
        $.ajax({
            url: "http://localhost:8080/rest/users/delete/"+sessionStorage.getItem("idUser")+"/watchlists/"+$id,
            type: 'DELETE',
            success: function() {alert("Suppression réussi")
            $(this).parent().parent().parent().remove();}
        });
        window.location.href = "/accueil";
    })
}

function ajoutAnimeListe(anime,id){
    
}