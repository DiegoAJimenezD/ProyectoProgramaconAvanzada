package co.edu.uniquindio.unieventos.modelo;

import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class DetalleCarrito {

    private int cantidad;
    private String nombreLocalidad;
    private ObjectId idEvento;
}
