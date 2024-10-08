package co.edu.uniquindio.unieventos.modelo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("usuarios")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    private String cedula;
    private String nombre;
    private String telefono;
    private String direccion;
}
