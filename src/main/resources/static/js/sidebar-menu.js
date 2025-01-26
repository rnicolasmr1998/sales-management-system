// Proporciona un objeto que gestiona el estado de visibilidad de una barra lateral (sidebar)
function sidebarMenu() {
    return {
        show: false,
        openSidebar() {
            this.show = true;
        },
        closeSidebar() {
            this.show = false;
        },
        isVisible() {
            return this.show === true;
        }
    };
}