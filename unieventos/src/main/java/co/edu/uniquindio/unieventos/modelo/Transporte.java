package co.edu.uniquindio.unieventos.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("transporte")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Transporte {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private TipoViaje tipoViaje;
    private int numParajeros;
    private double precio;
    private String descripcion;

}
