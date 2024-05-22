//https://localhost:8000/card/all
let cards = fetch("http://tp.cpe.fr:8083/cards").then(value => {
    if (! value.ok) {
        alert(`Erreur lors de la récupération des cartes : ${value.status}`)
        return
    }
    value.json().then(json => loadCards(json))
})


function loadCards(json) {
    let template = document.querySelector("#selectedCard");

    for(const card of json){
        console.log(card)
        let clone = document.importNode(template.content, true);

        newContent= clone.firstElementChild.innerHTML
            .replace(/{{family_src}}/g, card.smallImgUrl)
            .replace(/{{family_name}}/g, card.family)
            .replace(/{{image_src}}/g, card.imgUrl)
            .replace(/{{cardId}}/g, card.id);
        clone.firstElementChild.innerHTML= newContent;

        let cardContainer= document.querySelector("#gridContainer");
        cardContainer.appendChild(clone);
    }
}

function showCardDetails(id) {
    location.href = `./details.html?id=${id}`
}