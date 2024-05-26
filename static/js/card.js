//http://localhost:8090/card/all
//http://tp.cpe.fr:8083/cards
let cards = fetch("http://localhost:8090/card/all").then(value => {
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
            .replace(/{{card_small_img}}/g, card.smallImgUrl)
            .replace(/{{card_name}}/g, card.name)
            .replace(/{{card_img}}/g, card.imgUrl)
            .replace(/{{card_id}}/g, card.id);
        clone.firstElementChild.innerHTML= newContent;

        let cardContainer= document.querySelector("#gridContainer");
        cardContainer.appendChild(clone);
    }
}

function showCardDetails(id) {
    location.href = `./details.html?id=${id}`
}