package co.edu.uniquindio.unieventos.modelo;

import co.edu.uniquindio.unieventos.modelo.enums.EstadoPqr;
import co.edu.uniquindio.unieventos.modelo.enums.TipoPqr;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("pqrs")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
public class Pqr {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private TipoPqr tipo; // Petición, Queja, Reclamo o Sugerencia
    private String descripcion; // Descripción detallada del PQRS
    private EstadoPqr estado; // Estado de la solicitud (Ej. "En proceso", "Resuelto", "Cerrado")
    private LocalDateTime fechaCreacion; // Fecha de creación del PQRS
    private LocalDateTime fechaRespuesta; // Fecha en que se dio respuesta
    private String respuesta; // Respuesta a la solicitud
    private String idCliente; // Referencia al cliente que realizó la solicitud
}