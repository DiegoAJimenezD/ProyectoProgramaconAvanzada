package co.edu.uniquindio.unieventos.dto.Evento;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record FiltroEventoDTO(
        @Length(max = 100) String nombre,
        @Length(max = 10) @Pattern(regexp = "^(DEPORTE|CONCIERTO|CULTURAL|MODA|BELLEZA|null)?$") String tipo,
        @Length(max = 100) String ciudad
) {
}
