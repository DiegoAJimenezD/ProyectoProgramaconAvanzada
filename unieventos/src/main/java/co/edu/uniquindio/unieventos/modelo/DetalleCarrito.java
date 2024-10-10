package co.edu.uniquindio.unieventos.modelo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleCarrito {

    private int cantidad;
    private String nombreLocalidad;
    private String idEvento;
}
