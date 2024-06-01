$(document).ready(function () {
    $.ajax({
        url: 'http://localhost:8000/auth/check',
        type: 'POST',
        contentType: 'application/json',
        xhrFields: {withCredentials: true},
        crossDomain: true,
        success: function (response) {
            console.log('check GOOD: ' + response)
        },
        error: function (xhr, status, error) {
            alert('Vous n\'êtes pas autorisé à accéder à ce contenu \n Veuillez vous connecter')
            window.location = './login'
        }
    })
})