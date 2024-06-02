function parseJwt(token) {
    if (!token) return null;
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
}

function getCookie(cname) {
    const name = cname + "=";
    const decodedCookie = decodeURIComponent(document.cookie);
    const ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

$(document).ready(function() {
    const token = getCookie("token");

    const user = token ? parseJwt(token) : null;
    console.log("User:", user); // Debugging parsed user data

    if (user && user.user_id) {
        $('#username').text(user.sub || 'John Doe'); // Update username
        $('#userwallet').text(user.wallet + '$'); // Update wallet amount
    } else {
        $('#username').text('Login / Sign Up');
        $('#userwallet').hide(); // Hide wallet info
    }
});

$(document).ready(function() {
    const token = getCookie("token");
    const user = token ? parseJwt(token) : null;

    if (user && user.user_id) {
        $('#username').text(user.sub || 'John Doe'); // Affiche le nom d'utilisateur
        $('#userwallet').text(user.wallet + '$'); // Affiche les informations de portefeuille
        $('#user-link').attr("href", "#"); // Neutralise le lien si connecté
        $('#logout-tooltip').show(); // Affiche l'icône de déconnexion
    } else {
        $('#username').text('Login / Sign Up');
        $('#userwallet').hide(); // Masque les informations de portefeuille
        $('#logout-tooltip').hide(); // Masque l'icône de déconnexion
    }

    $('#logout-tooltip').click(function() {
        document.cookie = 'token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;'; // Supprime le cookie
        window.location.href = '/home.html'; // Redirige vers la page de connexion
    });
});