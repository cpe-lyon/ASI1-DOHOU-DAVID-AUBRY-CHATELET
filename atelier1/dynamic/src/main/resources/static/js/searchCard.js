

function getCard(form) {
    event.preventDefault()
    fetch("http://tp.cpe.fr:8083/cards").then((response) => {

        if (! response.ok) {
            alert(`Impossible de contacter l'api : erreur ${response.status}`)
            return
        }
        response.json().then(value => {
            if (! Array.isArray(value)) {
                alert("Réponse incorrecte de l'api, un tableau était attendu.")
                return
            }
            searchCard(value, form)
        })
    })
}

function searchCard(cards, form) {
    let formData = new FormData(form);
    let cardName = formData.get("search").toString()

    for (let card of cards) {
        if (card.name.toLowerCase() === cardName.toLowerCase()) {
            addCardToPage(card)
            return
        }
    }
    alert("Card not found")
}

function addCardToPage(card) {
    console.log(card)
    let template = document.querySelector("#selectedCard");
    let clone = document.importNode(template.content, true);
    newContent= clone.firstElementChild.innerHTML
        .replace(/{{family_src}}/g, card.smallImgUrl)
        .replace(/{{family_name}}/g, card.family)
        .replace(/{{img_url}}/g, card.imgUrl)
        .replace(/{{name}}/g, card.name)
        .replace(/{{description}}/g, card.description)
        .replace(/{{hp}}/g, card.hp)
        .replace(/{{energy}}/g, card.energy)
        .replace(/{{attack}}/g, card.attack)
        .replace(/{{defense}}/g, card.defence);
    clone.firstElementChild.innerHTML= newContent;

    let cardContainer= document.querySelector("#cardContainer");
    cardContainer.appendChild(clone);
}





