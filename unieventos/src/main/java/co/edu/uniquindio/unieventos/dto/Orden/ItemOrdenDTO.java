package co.edu.uniquindio.unieventos.dto.Orden;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ItemOrdenDTO(
        @NotBlank String id,
        @NotBlank @Length(max = 100) String idEvento,
        @NotBlank String nombreLocalidad,
        float precio,
        int cantidad
) {
}
