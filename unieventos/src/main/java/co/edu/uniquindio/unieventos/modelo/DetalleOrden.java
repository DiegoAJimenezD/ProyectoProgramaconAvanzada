package co.edu.uniquindio.unieventos.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Builder
public class DetalleOrden {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private ObjectId idEvento;
    private float precio ;
    private String nombreLocalidad;
    private int cantidad;
}
