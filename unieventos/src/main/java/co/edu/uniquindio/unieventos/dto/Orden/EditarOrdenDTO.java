package co.edu.uniquindio.unieventos.dto.Orden;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EditarOrdenDTO(
        @NotBlank String id, // No puede ser nulo ni vacío
        @NotBlank @Pattern(regexp = "^(ACTIVO|CANCELADO)$") String estadoOrden,
// No puede ser nulo ni vacío; debe ser uno de los estados permitidos
        String codigoPasarela // Puede ser nulo
) {
}
