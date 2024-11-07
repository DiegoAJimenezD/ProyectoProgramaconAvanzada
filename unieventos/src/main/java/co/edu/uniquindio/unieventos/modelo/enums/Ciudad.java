package co.edu.uniquindio.unieventos.modelo.enums;

public enum Ciudad {
    BOGOTA("Bogotá"),
    MEDELLIN("Medellín"),
    CALI("Cali"),
    BARRANQUILLA("Barranquilla"),
    CARTAGENA("Cartagena"),
    CUCUTA("Cúcuta"),
    BUCARAMANGA("Bucaramanga"),
    PEREIRA("Pereira"),
    SANTA_MARTA("Santa Marta"),
    IBAGUE("Ibagué"),
    MANIZALES("Manizales"),
    PASTO("Pasto"),
    MONTERIA("Montería"),
    VILLAVICENCIO("Villavicencio"),
    ARMENIA("Armenia"),
    NEIVA("Neiva"),
    POPAYAN("Popayán"),
    SINCELEJO("Sincelejo"),
    TUNJA("Tunja"),
    RIONEGRO("Rionegro");


    private final String nombreCiudad;

    Ciudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }


    public String getNombreCiudad() {
        return nombreCiudad;
    }
}
