function successMessageAlert() {
    return {
        show: true,
        isAlertVisible() {
            return this.show === true;
        },
        hideAlert() {
            this.show = false;
        }
    };
}