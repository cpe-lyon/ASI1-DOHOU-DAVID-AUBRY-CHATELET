function getMarketCards() {
    let cards = fetch("http://localhost:8090/card/all").then(value => {
        if (! value.ok) {
            alert(`Erreur lors de la récupération des cartes : ${value.status}`)
            return
        }
        value.json().then(json => loadMarketCards(json))
    })
}

function loadMarketCards(json) {
    let template = document.querySelector("#bodyTemplate")

    for(const card of json){
        console.log(card)
        let clone = document.importNode(template.content, true)

        newContent= clone.firstElementChild.innerHTML
            .replace(/{{card_name}}/g, card.name)
            .replace(/{{card_id}}/g, card.id)
            .replace(/{{card_description}}/g, card.description)
            .replace(/{{card_family}}/g, card.family)
            .replace(/{{card_affinity}}/g, card.affinity)
            .replace(/{{card_imgUrl}}/g, card.imgUrl)
            .replace(/{{card_smallImgUrl}}/g, card.smallImgUrl)
            .replace(/{{card_energy}}/g, card.energy)
            .replace(/{{card_hp}}/g, card.hp)
            .replace(/{{card_defence}}/g, card.defence)
            .replace(/{{card_attack}}/g, card.attack)
        clone.firstElementChild.innerHTML= newContent

        let cardContainer= document.querySelector("#table-body")
        cardContainer.appendChild(clone)
    }

    document.querySelectorAll('#table-body tr').forEach(row => {
        row.addEventListener('click', function() {
            let id = this.querySelector('td:nth-child(1)').textContent
            let name = this.querySelector('td:nth-child(2)').textContent
            let description = this.querySelector('td:nth-child(3)').textContent
            let family = this.querySelector('td:nth-child(4)').textContent
            let affinity = this.querySelector('td:nth-child(5)').textContent
            let energy = this.querySelector('td:nth-child(6)').textContent
            let hp = this.querySelector('td:nth-child(7)').textContent
            let imgUrl = json.find(card => card.name === name).imgUrl

            // Mettre à jour le contenu de la card-panel
            document.querySelector('.card-panel .card-image').src = imgUrl
            document.querySelector('.card-panel .card-image').style.visibility = 'visible'
            document.querySelector('.card-panel .card-details').innerHTML = `
                <p><strong>ID:</strong> ${id}</p>
                <p><strong>Name:</strong> ${name}</p>
                <p><strong>Description:</strong> ${description}</p>
                <p><strong>Family:</strong> ${family}</p>
                <p><strong>Affinity:</strong> ${affinity}</p>
                <p><strong>Energy:</strong> ${energy}</p>
                <p><strong>HP:</strong> ${hp}</p>
                <button class="sell-button">SELL</button>
            `
        })
    })
}

$(document).ready(function() {
    getMarketCards()
})