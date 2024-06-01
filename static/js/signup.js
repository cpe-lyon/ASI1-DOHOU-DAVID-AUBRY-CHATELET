$(document).ready(function() {
    $('.login-form').submit(function(event) {
        event.preventDefault()

        let formData = {}
        let isFormValid = true

        $(this).find('input').each(function() {
            const name = $(this).attr('name').replace('user-', '')
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
            url: 'http://localhost:8000/user/register',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                alert('Le compte à bien été créé! Vous pouvez désormais vous connecter')
                window.location = './login'
            },
            error: function(xhr, status, error) {
                alert('Erreur lors de la soumission des données: ' + error)
            }
        })
    })
})