function getMarketCards() {
    let cards = fetch("http://localhost:8090/market/all").then(value => {
        if (! value.ok) {
            alert(`Erreur lors de la récupération des cartes : ${value.status}`)
            return
        }
        value.json().then(json => loadMarketCards(json))
    })
}

function loadMarketCards(json) {
    let template = document.querySelector("#bodyTemplate")

    for(const el of json){
        console.log(el)
        let clone = document.importNode(template.content, true)

        newContent= clone.firstElementChild.innerHTML
            .replace(/{{card_name}}/g, el.card.name)
            .replace(/{{card_id}}/g, el.card.id)
            .replace(/{{card_description}}/g, el.card.description)
            .replace(/{{card_family}}/g, el.card.family)
            .replace(/{{card_affinity}}/g, el.card.affinity)
            .replace(/{{card_imgUrl}}/g, el.card.imgUrl)
            .replace(/{{card_smallImgUrl}}/g, el.card.smallImgUrl)
            .replace(/{{card_energy}}/g, el.card.energy)
            .replace(/{{card_hp}}/g, el.card.hp)
            .replace(/{{card_defence}}/g, el.card.defence)
            .replace(/{{card_attack}}/g, el.card.attack)
            .replace(/{{card_price}}/g, el.price)
        clone.firstElementChild.innerHTML= newContent

        let cardContainer= document.querySelector("#table-body")
        cardContainer.appendChild(clone)
    }

    document.querySelectorAll('#table-body tr').forEach(row => {
        row.addEventListener('click', function() {
            let name = this.querySelector('td:nth-child(1)').textContent;
            let description = this.querySelector('td:nth-child(2)').textContent;
            let family = this.querySelector('td:nth-child(3)').textContent;
            let affinity = this.querySelector('td:nth-child(4)').textContent;
            let energy = this.querySelector('td:nth-child(5)').textContent;
            let hp = this.querySelector('td:nth-child(6)').textContent;
            let price = this.querySelector('td:nth-child(7)').textContent;
            let imgUrl = json.find(card => card.card.name === name).card.imgUrl;

            // Mettre à jour le contenu de la card-panel
            document.querySelector('.card-panel .card-image').src = imgUrl;
            document.querySelector('.card-panel .card-details').innerHTML = `
                <p><strong>Name:</strong> ${name}</p>
                <p><strong>Description:</strong> ${description}</p>
                <p><strong>Family:</strong> ${family}</p>
                <p><strong>Affinity:</strong> ${affinity}</p>
                <p><strong>Energy:</strong> ${energy}</p>
                <p><strong>HP:</strong> ${hp}</p>
                <p><strong>Price:</strong> $${price}</p>
            `;
        });
    });
}

$(document).ready(function() {
    getMarketCards()
})