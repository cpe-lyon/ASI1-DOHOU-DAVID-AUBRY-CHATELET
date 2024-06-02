$(document).ready(function() {
    $('.add-card-form').submit(function(event) {
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
            url: 'http://localhost:8000/card/add',
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