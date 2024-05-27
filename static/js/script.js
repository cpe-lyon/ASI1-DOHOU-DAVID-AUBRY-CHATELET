document.querySelectorAll('.card-item').forEach(card => {
    card.addEventListener('click', function() {
        this.classList.toggle('flipped');
    });
});