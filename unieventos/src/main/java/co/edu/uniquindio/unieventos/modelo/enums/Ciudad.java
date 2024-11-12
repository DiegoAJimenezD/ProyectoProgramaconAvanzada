package co.edu.uniquindio.unieventos.modelo.enums;

public enum Ciudad {
    ARMENIA("Armenia"),
    BARRANQUILLA("Barranquilla"),
    BOGOTA("Bogotá"),
    BUCARAMANGA("Bucaramanga"),
    CALI("Cali"),
    CARTAGENA("Cartagena"),
    CUCUTA("Cúcuta"),
    IBAGUE("Ibagué"),
    MANIZALES("Manizales"),
    MEDELLIN("Medellín"),
    MONTERIA("Montería"),
    NEIVA("Neiva"),
    PASTO("Pasto"),
    PEREIRA("Pereira"),
    POPAYAN("Popayán"),
    RIONEGRO("Rionegro"),
    SANTA_MARTA("Santa Marta"),
    SINCELEJO("Sincelejo"),
    TUNJA("Tunja"),
    VILLAVICENCIO("Villavicencio");

    private final String nombreCiudad;

    Ciudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }
}
