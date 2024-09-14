package co.edu.uniquindio.unieventos.modelo;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("carritos")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
public class Carrito {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private LocalDateTime fecha;
    private List<DetalleCarrito> items;
    private ObjectId idUsuario;
}
