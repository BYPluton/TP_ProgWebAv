$('#myButton').click(function(){
    let nom = $("#nom").val();
    let description =$("#description").val();
    $.ajax({
        type: 'POST',
        url: "http://localhost:8080/rest/users/"+sessionStorage.getItem("idUser")+"/watchlists/",
        data: JSON.stringify( {
                "name": nom,
                "description" : description
            }
        ),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function() { window.location.href ="/accueil"; }
    });
})