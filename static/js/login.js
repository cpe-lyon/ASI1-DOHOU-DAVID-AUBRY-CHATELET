function login(event, form) {
    event.preventDefault(); // Empêche la soumission standard du formulaire

    const formData = new FormData(form);
    const username = formData.get('username');
    const password = formData.get('password');

    debugger
    fetch('http://localhost:8090/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ "username" : username, "password": password }),
            credentials: 'include' // Nécessaire pour les cookies avec CORS
        })
        .then(response => {
            if (response.status === 401 || response.status === 404) {
                throw new Error('Unauthorized: Incorrect username or password.');
            } else {
                throw new Error('Something went wrong on the server.');
            }
        })
        .then(data => {
            console.log('Login successful:', data);
            // Vous pouvez rediriger l'utilisateur ou faire d'autres traitements ici
        })
        .catch(error => {
            console.error('Error:', error);
            alert(error.message); // Affiche une alerte en cas d'erreur
        });
}