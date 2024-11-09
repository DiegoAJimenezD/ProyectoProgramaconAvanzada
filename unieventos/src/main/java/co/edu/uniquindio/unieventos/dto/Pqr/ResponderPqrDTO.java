package co.edu.uniquindio.unieventos.dto.Pqr;

import jakarta.validation.constraints.NotBlank;

public record ResponderPqrDTO(
        @NotBlank String id, // No puede ser nulo ni vacío
        @NotBlank String respuesta // No puede ser nulo ni vacío
) {
}

