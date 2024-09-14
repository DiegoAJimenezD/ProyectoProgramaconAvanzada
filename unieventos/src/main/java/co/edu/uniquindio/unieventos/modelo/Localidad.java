package co.edu.uniquindio.unieventos.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder

public class Localidad {
    private float precio;
    private String nombre;
    private int entradasVendidas;
    private int capacidadMaxima;
}
