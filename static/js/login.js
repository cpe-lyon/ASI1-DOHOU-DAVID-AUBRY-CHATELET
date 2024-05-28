function login(event, form) {
    event.preventDefault() // Empêche la soumission standard du formulaire

    const formData = new FormData(form)
    const username = formData.get('username')
    const password = formData.get('password')

    $.ajax({
        url: 'http://localhost:8090/auth/login',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ "username" : username, "password": password }),
        success: function(response) {
            console.log('Login successful:', response)
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