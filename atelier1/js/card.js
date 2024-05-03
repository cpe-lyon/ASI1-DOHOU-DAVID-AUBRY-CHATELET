let _cardlist = [
        {
            family_src:"https://imgc.allpostersimages.com/img/print/affiches/marvel-super-hero-squad-iron-man-standing_a-G-9448041-4985690.jpg",
            family_name:"Jose",
            image_src:"https://imgc.allpostersimages.com/img/print/affiches/marvel-super-hero-squad-iron-man-standing_a-G-9448041-4985690.jpg",
            date:"14h",
            comment:"3 comments",
            like: "17 likes",
            button: "Buy"
        },
        {
            family_src:"https://media.giphy.com/media/l4q8hciiYNT5RGi4w/giphy.gif",
            family_name:"John",
            image_src:"https://media.giphy.com/media/l4q8hciiYNT5RGi4w/giphy.gif",
            date:"1h",
            comment:"345 comments",
            like: "1000 likes",
            button: "Read"
        },
        {
            family_src:"http://www.superherobroadband.com/app/themes/superhero/assets/img/superhero.gif",
            family_name:"Elliot",
            image_src:"http://www.superherobroadband.com/app/themes/superhero/assets/img/superhero.gif",
            date:"142h",
            comment:"0 comment",
            like: "1 likes",
            button: "Read"
        }

    ];

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