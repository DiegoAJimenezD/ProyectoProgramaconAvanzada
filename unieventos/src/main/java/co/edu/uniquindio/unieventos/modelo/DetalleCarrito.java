package co.edu.uniquindio.unieventos.modelo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleCarrito {

    private String idEvento;
    private float precio;
    private String nombreLocalidad;
    private int cantidad;
}
