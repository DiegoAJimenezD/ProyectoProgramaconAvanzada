package co.edu.uniquindio.unieventos.modelo;

import co.edu.uniquindio.unieventos.modelo.enums.Ciudad;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoEvento;
import co.edu.uniquindio.unieventos.modelo.enums.TipoEvento;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("eventos")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Evento {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String imagenPortada;
    private EstadoEvento estado;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String imagenLocalidades;
    private TipoEvento tipo;
    private LocalDateTime fecha;
    private Ciudad ciudad;
    private List<Localidad> localidades;
}
