package co.edu.uniquindio.unieventos.modelo.enums;

public enum TipoEvento {
    DEPORTE("Deporte"),
    CONCIERTO("Concierto"),
    CULTURAL("Cultural"),
    MODA("Moda"),
    BELLEZA("Belleza");

    private final String nombreTipoEvento;

    TipoEvento(String nombreTipoEvento) {
        this.nombreTipoEvento = nombreTipoEvento;
    }


    public String getNombreTipoEvento() {
        return nombreTipoEvento;
    }
}
