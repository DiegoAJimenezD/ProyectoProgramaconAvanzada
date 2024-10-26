package co.edu.uniquindio.unieventos.dto.Pqr;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record InformacionPqrDTO(
        @NotBlank String id, // No puede ser nulo ni vacío
        @NotBlank String tipo, // No puede ser nulo ni vacío
        @NotBlank String descripcion, // No puede ser nulo ni vacío
        @NotBlank String estado, // No puede ser nulo ni vacío
        @NotNull LocalDateTime fechaCreacion, // No puede ser nulo
        LocalDateTime fechaRespuesta, // Opcional, puede ser nulo
        String respuesta, // Opcional, puede ser nulo
        @NotBlank String idCliente // No puede ser nulo ni vacío
) {
}
