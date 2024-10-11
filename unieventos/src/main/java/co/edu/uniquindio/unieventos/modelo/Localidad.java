package co.edu.uniquindio.unieventos.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder

public class Localidad {
    private float precio;
    private String nombre;
    private int entradasVendidas;
    private int capacidadMaxima;
}
