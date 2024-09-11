package co.edu.uniquindio.unieventos.modelo;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document("ordenes")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Orden {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private ObjectId idCliente;
    private LocalDate fecha;
    private String codigoPasarela;
    private List<DetalleOrden> items;
    private Pago pago;
    private float total;
    private ObjectId idCupon;
}
