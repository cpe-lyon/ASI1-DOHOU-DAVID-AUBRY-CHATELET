function getMarketCards() {
    let cards = fetch("http://localhost:8000/market/all").then(value => {
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
            let id = this.querySelector('td:nth-child(1)').textContent
            let name = this.querySelector('td:nth-child(2)').textContent
            let description = this.querySelector('td:nth-child(3)').textContent
            let family = this.querySelector('td:nth-child(4)').textContent
            let affinity = this.querySelector('td:nth-child(5)').textContent
            let energy = this.querySelector('td:nth-child(6)').textContent
            let hp = this.querySelector('td:nth-child(7)').textContent
            let price = this.querySelector('td:nth-child(8)').textContent
            let imgUrl = json.find(card => card.card.name === name).card.imgUrl

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
                <p><strong>Price:</strong> $${price}</p>
                <button class="buy-button" data-id="${id}" data-name="${name}">BUY</button>
            `

            var modal = document.getElementById("myModal")
            var btn = document.querySelector(".buy-button")
            var span = document.getElementsByClassName("close")[0]

            btn.onclick = function() {
                const cardName = this.getAttribute('data-name');
                const modalTitle = document.querySelector('#myModal .modal-content h2');
                modalTitle.textContent = `do you want to buy the card ${name} for ${price}?`;
                modal.style.display = "block"
            }
            span.onclick = function() {
                modal.style.display = "none"
            }

            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none"
                }
            }
        })
    })
}

function parseJwt(token) {
    var base64Url = token.split('.')[1]
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/')
    var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
    }).join(''))

    return JSON.parse(jsonPayload)
}

function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

$(document).ready(function() {
    const user_id = parseJwt(getCookie("token"))["user_id"]

    getMarketCards()

    $('.buy-form').submit(function(event) {
        event.preventDefault()

        let button = document.querySelector('.buy-button')
        let offerId = button.getAttribute("data-id");

        $.ajax({
            url: 'http://localhost:8000/market/buy',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ "userId": user_id, "marketOfferId": offerId }),
            xhrFields: { withCredentials: true },
            crossDomain: true,
            success: function(response) {
                alert('Données soumises avec succès.')
                window.location = './card'
            },
            error: function(xhr, status, error) {
                alert('Erreur lors de la soumission des données: ' + error)
            }
        })
    })
})