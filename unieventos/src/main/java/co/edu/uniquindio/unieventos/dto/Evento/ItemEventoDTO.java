package co.edu.uniquindio.unieventos.dto.Evento;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record ItemEventoDTO(
        @Length(max = 50) String id, // Máximo 50 caracteres
        @Length(max = 100) String nombre, // Máximo 100 caracteres
        @Length(max = 20) @Pattern(regexp = "^(DEPORTE|CONCIERTO|CULTURAL|MODA|BELLEZA)$") String tipoEvento,// Debe ser uno de los tipos permitidos
        @Length(max = 100) String ciudad, // Máximo 100 caracteres
        LocalDateTime fecha, // No requiere validación
        @Length(max = 100) String direccion, // Máximo 100 caracteres
        String urlImagenPoster // Máximo 200 caracteres
) {
}
