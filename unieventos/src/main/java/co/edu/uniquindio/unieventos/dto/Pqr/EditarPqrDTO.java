package co.edu.uniquindio.unieventos.dto.Pqr;

import co.edu.uniquindio.unieventos.modelo.enums.EstadoPqr;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditarPqrDTO {

    private String id; // ID de la PQRS a editar
    private EstadoPqr estado; // Nuevo estado de la PQRS
    private String respuesta; // Respuesta a la PQRS
}
