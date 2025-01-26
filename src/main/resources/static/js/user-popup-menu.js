// Función que me permite controlar de manera sencilla la visibilidad del menú emergente.
function userPopupMenu() {
    return {
        show: false,
        toggleVisibility() {
            this.show = !this.show;
        },
        close() {
            this.show = false;
        },
        isVisible() {
            return this.show === true;
        }
    };
}