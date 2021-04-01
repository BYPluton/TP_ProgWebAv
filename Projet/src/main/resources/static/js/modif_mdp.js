let $mdp = $("#mdp");

function modiferMDP(){
    //Requête qui modifie le mot de passe d'un utilisateur
    $.ajax({
        url: "http://localhost:8080/rest/users/"+sessionStorage.getItem("idUser")+"/"+$mdp.val(),
        type: 'PATCH',
        success: function() {alert("Votre mot de passe à été modifié avec succès !")}
    });
}