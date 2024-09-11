package co.edu.uniquindio.unieventos.modelo;

import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class DetalleOrden {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private ObjectId idEvento;
    private float precio ;
    private String nombreLocalidad;
    private int cantidad;
}
