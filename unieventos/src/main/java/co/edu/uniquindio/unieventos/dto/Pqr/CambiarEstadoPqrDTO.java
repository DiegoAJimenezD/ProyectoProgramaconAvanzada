package co.edu.uniquindio.unieventos.dto.Pqr;

import jakarta.validation.constraints.NotBlank;

public record CambiarEstadoPqrDTO(
        @NotBlank String id, // No puede ser nulo ni vacío
        @NotBlank String nuevoEstado // No puede ser nulo ni vacío
) {
}
