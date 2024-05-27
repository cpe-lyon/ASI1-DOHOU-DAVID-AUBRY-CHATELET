function getAllCards(){
    let cards = fetch("http://localhost:8090/card/all").then(value => {
        if (! value.ok) {
            alert(`Erreur lors de la récupération des cartes : ${value.status}`)
            return
        }
        value.json().then(json => loadCards(json))
    })
}

function getCardById(id){
    let card = fetch("http://localhost:8090/card/"+id).then(value => {
        if (! value.ok) {
            alert(`Erreur lors de la récupération de la carte : ${value.status}`)
            return
        }
        value.json().then(json => loadCards(json))
    })
}

function loadCards(json) {
    let template = document.querySelector("#selectedCard")

    for(const card of json){
        console.log(card)
        let clone = document.importNode(template.content, true)

        newContent= clone.firstElementChild.innerHTML
            .replace(/{{card_id}}/g, card.id)
            .replace(/{{card_name}}/g, card.name)
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

        let cardContainer= document.querySelector("#gridContainer")
        cardContainer.appendChild(clone)
    }
}

// function showCardDetails(id) {
//     location.href = `./details.html?id=${id}`
// }