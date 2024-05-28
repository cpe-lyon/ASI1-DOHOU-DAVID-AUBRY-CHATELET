function getMarketCards(user_id) {
    let cards = fetch("http://localhost:8090/user/"+user_id).then(value => {
        if (! value.ok) {
            alert(`Erreur lors de la récupération des cartes : ${value.status}`)
            return
        }
        value.json().then(json => loadMarketCards(json))
    })
}

function loadMarketCards(json) {
    let template = document.querySelector("#bodyTemplate")

    for(const card of json["cardsOfUser"]){
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
            let imgUrl = json["cardsOfUser"].find(card => card.name === name).imgUrl

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

            var modal = document.getElementById("myModal")
            var btn = document.querySelector(".sell-button")
            var span = document.getElementsByClassName("close")[0]

            btn.onclick = function() {
                modal.style.display = "block"
                document.getElementById("cardId").innerHTML = "Value = " + "'" + id + "'"
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

function parseJwt (token) {
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
    for(let i = 0; i <ca.length; i++) {
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

    getMarketCards(user_id)

    $('.sell-card-form').submit(function(event) {
        event.preventDefault()

        let formData = {}
        let isFormValid = true

        $(this).find('input').each(function() {
            const name = $(this).attr('name').replace('card-', '')
            const value = $(this).val().trim()

            if (value === '') {
                alert('Veuillez remplir tous les champs.')
                isFormValid = false
                return false
            }
            formData[name] = $(this).attr('type') === 'number' ? parseInt(value, 10) : value
        })

        if (!isFormValid) {
            return
        }

        $.ajax({
            url: 'http://localhost:8090/card/add',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
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