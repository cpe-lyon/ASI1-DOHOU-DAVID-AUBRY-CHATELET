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

    document.querySelectorAll('.card-item').forEach(card => {
        card.addEventListener('click', function() {
            this.classList.toggle('flipped');
        });
    });
}

$(document).ready(function() {
    getMarketCards()
})