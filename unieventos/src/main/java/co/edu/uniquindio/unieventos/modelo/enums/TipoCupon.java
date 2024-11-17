package co.edu.uniquindio.unieventos.modelo.enums;

public enum TipoCupon {
    MULTIPLE("multiple"),
    UNICO("unico");


    private final String nombreTipoCupon;

    TipoCupon(String nombreTipoCupon) {
        this.nombreTipoCupon = nombreTipoCupon;
    }

    public String getNombreTipoCupon() {
        return nombreTipoCupon;
    }
}
