function login(event, form) {
    event.preventDefault() // Empêche la soumission standard du formulaire

    const formData = new FormData(form)
    const username = formData.get('username')
    const password = formData.get('password')

    $.ajax({
        url: 'http://localhost:8000/auth/login',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ "username" : username, "password": password }),
        crossDomain: true,
        xhrFields: {
            withCredentials: true
        },
        success: function(output, status, xhr) {
            console.log('Login successful:')
            window.location = './home'
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
}