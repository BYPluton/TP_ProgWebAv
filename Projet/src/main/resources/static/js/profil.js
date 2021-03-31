let $username = $("#username");
let $pseudo = $("#pseudo");
let $password = $("#password");

$(document).ready(function () {
    $.get("http://localhost:8080/rest/users/", sessionStorage.getItem("idUser"), function(data){
        $username.append('<p>Username : ' + data[0].name + '</p>');
        $pseudo.append('<p>Pseudo : ' + data[0].pseudo + '</p>');
        $password.append('<p>Password : ' + data[0].password + '</p>');
    })
});