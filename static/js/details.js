window.onload = loadCardsDetails()

function loadCardsDetails() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    if (! urlParams.get('id')) { return }

    const cardId = urlParams.get('id');

    fetch(`http://tp.cpe.fr:8083/card/${cardId}`).then((response) => {
        if (!response.ok) { alert("Card not found"); return;}
        response.json().then(card => {
            loadCardInTemplate(card);
        })
    })
}

function loadCardInTemplate(card) {
    let template = document.querySelector("#detailsTemplate");
    let clone = document.importNode(template.content, true);

    newContent= clone.firstElementChild.innerHTML
        .replace(/{{card_small_img}}/g, card.smallImgUrl)
        .replace(/{{card_family}}/g, card.family ? card.family : "Inconnue")
        .replace(/{{card_affinity}}/g, card.affinity)
        .replace(/{{card_img}}/g, card.imgUrl)
        .replace(/{{card_name}}/g, card.name)
        .replace(/{{card_descritpion}}/g, card.description)
        .replace(/{{card_hp}}/g, card.hp)
        .replace(/{{card_energy}}/g, card.energy)
        .replace(/{{card_attack}}/g, card.attack)
        .replace(/{{card_defence}}/g, card.defence)
        .replace(/{{card_id}}/g, card.id)
        .replace(/{{card_price}}/g, card.price);
    clone.firstElementChild.innerHTML= newContent;

    let cardContainer= document.querySelector("#cardDetails");
    cardContainer.appendChild(clone);
}