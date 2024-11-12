package co.edu.uniquindio.unieventos.dto.Evento;

import co.edu.uniquindio.unieventos.modelo.Localidad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.List;

public record EditarEventoDTO(
        @NotBlank String id, // No puede ser nulo ni vacío
        @NotBlank String nombre, // No puede ser nulo ni vacío
        @NotNull LocalDateTime fecha, // No puede ser nulo
        @NotBlank @Pattern(regexp = "^(DEPORTE|CONCIERTO|CULTURAL|MODA|BELLEZA)$") String tipoEvento,
// Debe ser uno de los tipos permitidos
        @NotBlank String direccion, // No puede ser nulo ni vacío
        @NotBlank String ciudad, // No puede ser nulo ni vacío
        List<Localidad> localidades,
        String imagenPortada,
        String imagenLocalidades
) {
}
