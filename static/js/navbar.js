function toggleDropdown() {
    const dropdownMenu = document.getElementById('dropdown-menu');
    if (dropdownMenu.style.display === 'none') {
        dropdownMenu.style.display = 'block';
    } else {
        dropdownMenu.style.display = 'none';
    }
}

// Ferme le menu déroulant lors du clic à l'extérieur
window.onclick = function(event) {
    if (!event.target.matches('.ui.left.floated.header, .ui.left.floated.header *')) {
        document.getElementById('dropdown-menu').style.display = 'none';
    }
}