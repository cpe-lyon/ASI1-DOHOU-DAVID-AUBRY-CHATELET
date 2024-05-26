document.addEventListener('DOMContentLoaded', function() {
    fetchCards();
});

function fetchCards() {
    fetch('http://localhost:8090/market/all')
        .then(response => response.json())
        .then(cards => {
            const tableBody = document.getElementById('table-body');
            cards.forEach(card => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${card.nom}</td>
                    <td>${card.description}</td>
                    <td>${card.famille}</td>
                    <td>${card.affinite}</td>
                    <td>${card.energie}</td>
                    <td>${card.hp}</td>
                    <td>${card.prix}$</td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error loading the cards:', error);
        });
}