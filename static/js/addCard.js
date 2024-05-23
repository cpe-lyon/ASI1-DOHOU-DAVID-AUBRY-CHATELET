$(document).ready(function() {
    $('.ui.form').submit(function(event) {
        event.preventDefault(); // Empêche le comportement par défaut de soumission du formulaire

        let formData = {};
        let isFormValid = true;

        $(this).find('input').each(function() {
            const name = $(this).attr('name').replace('card-', ''); // Simplifie le nom de l'attribut pour correspondre à l'API
            const value = $(this).val().trim();

            if (value === '') {
                alert('Veuillez remplir tous les champs.');
                isFormValid = false;
                return false; // Quitte la boucle .each
            }
            formData[name] = $(this).attr('type') === 'number' ? parseInt(value, 10) : value;
        });

        if (!isFormValid) {
            return; // Stoppe la fonction si le formulaire n'est pas valide
        }

        // Envoi de la requête AJAX
        $.ajax({
            url: 'http://localhost:8090/card/add',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                alert('Données soumises avec succès.')
                window.location = './card-list'
            },
            error: function(xhr, status, error) {
                alert('Erreur lors de la soumission des données: ' + error);
            }
        });
        // $.post("http://localhost:8090/card/add",
        // JSON.stringify(formData),
        // function(data, status){
        //     alert("Data: " + data + "\nStatus: " + status);
        // },'json').done(function( response ) {
        //     alert('Données soumises avec succès.')
        //     window.location = '/card-list'
        // }).fail(function (xhr, status, error){
        //     alert('Erreur lors de la soumission des données: ' + error)
        // });
    });
});