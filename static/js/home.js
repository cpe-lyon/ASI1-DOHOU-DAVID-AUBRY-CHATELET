$(document).ready(function() {
    $.ajax({
        url: 'http://localhost:8090/auth/check',
        type: 'POST',
        contentType: 'application/json',
        xhrFields: { withCredentials: true },
        crossDomain: true,
        success: function(response) {
            alert('check GOOD: ' + response)
        },
        error: function(xhr, status, error) {
            switch (xhr.status) {
                case 404:
                case 401:
                    throw new Error('Unauthorized: Incorrect username or password.')
                default:
                    alert('Erreur lors de la soumission des données: ' + error)
            }
        }
    })
})