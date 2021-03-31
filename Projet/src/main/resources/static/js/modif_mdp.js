let $mdp = $("#mdp");

function modiferMDP(){
    $.ajax({
        url: "http://localhost:8080/rest/users/"+sessionStorage.getItem("idUser"),
        type: 'PATCH',
        data : $mdp.val(),
        success: function() {alert("Votre mot de passe à été modifié avec succès !")}
    });
}