package co.edu.uniquindio.unieventos.dto.Pqr;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ResponderPqrDTO(
        @NotBlank String id, // No puede ser nulo ni vacío
        @NotBlank String respuesta // No puede ser nulo ni vacío
) {
}

