package co.edu.uniquindio.unieventos.dto.Evento;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record EditarEventoDTO(
        @NotBlank String id, // No puede ser nulo ni vacío
        @NotBlank String nombre, // No puede ser nulo ni vacío
        @NotNull LocalDateTime fecha, // No puede ser nulo
        @NotBlank @Pattern(regexp = "^(DEPORTE|CONCIERTO|CULTURAL|MODA|BELLEZA)$") String tipoEvento,
// Debe ser uno de los tipos permitidos
        @NotBlank String direccion, // No puede ser nulo ni vacío
        @NotBlank String ciudad, // No puede ser nulo ni vacío
        @NotNull @DecimalMin(value = "0.0", inclusive = false) float precioGeneral, // Debe ser mayor que 0
        @NotNull @DecimalMin(value = "0.0", inclusive = false) float precioVIP // Debe ser mayor que 0
) {
}
