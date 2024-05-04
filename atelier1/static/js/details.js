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
        .replace(/{{family_src}}/g, card.smallImgUrl)
        .replace(/{{family_name}}/g, card.family ? card.family : "Inconnue")
        .replace(/{{image_src}}/g, card.imgUrl)
        .replace(/{{name}}/g, card.name)
        .replace(/{{description}}/g, card.description)
        .replace(/{{hp}}/g, card.hp)
        .replace(/{{energy}}/g, card.energy)
        .replace(/{{attack}}/g, card.attack)
        .replace(/{{defense}}/g, card.defence);
    clone.firstElementChild.innerHTML= newContent;

    let cardContainer= document.querySelector("#cardDetails");
    cardContainer.appendChild(clone);
}