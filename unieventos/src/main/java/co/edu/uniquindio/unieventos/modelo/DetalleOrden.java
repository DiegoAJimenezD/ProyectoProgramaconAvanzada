package co.edu.uniquindio.unieventos.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DetalleOrden {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String idEvento;
    private float precio;
    private String nombreLocalidad;
    private int cantidad;
}
