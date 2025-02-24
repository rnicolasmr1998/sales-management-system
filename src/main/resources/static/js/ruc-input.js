document.addEventListener("DOMContentLoaded", function () {
    let rucInput = document.getElementById('ruc');
    if (rucInput) {
        rucInput.addEventListener('input', function () {
            rucInput.value = rucInput.value.replace(/[^0-9+\s]/g, "").substring(0, 11);
        });
    }
});