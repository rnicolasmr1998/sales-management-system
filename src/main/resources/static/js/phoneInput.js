document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll("input[type='tel']").forEach(function (input) {
        input.addEventListener("input", function () {
            this.value = this.value.replace(/[^0-9+\s]/g, "").substring(0, 9);
        });
    });
});
