$('#myButton').click(function(){
    let nom = $("#nom").val();
    let url = $("#url").val();
    let nbEpisode = $("#nbEpisode").val();
    alert(url);
    $.ajax({
        type: 'POST',
        url: "http://localhost:8080/rest/items/",
        data: JSON.stringify( {
                "titre": nom,
                "url": url,
                "nbEpisodes": nbEpisode
            }
        ),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function() { window.location.href ="/animes"; }
    });
})