package co.edu.uniquindio.unieventos.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.bson.types.ObjectId;


@AllArgsConstructor
@Builder
public class DetalleCarrito {

    private int cantidad;
    private String nombreLocalidad;
    private ObjectId idEvento;
}
