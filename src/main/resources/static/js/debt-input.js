document.addEventListener('DOMContentLoaded', function () {
    let debtInputs = ['supplierDebtSoles', 'supplierDebtDollars', 'debt'];

    debtInputs.forEach(function (id) {
        let inputElement = document.getElementById(id);
        if (inputElement) {
            inputElement.addEventListener('input', function () {
                inputElement.value = inputElement.value
                    .replace(/[^0-9.]/g, "")  // Permitir solo números y un punto decimal
                    .replace(/(\..*)\./g, '$1'); // Evitar múltiples puntos decimales
            });
        }
    });
});