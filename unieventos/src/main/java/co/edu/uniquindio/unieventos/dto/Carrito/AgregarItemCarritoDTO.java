package co.edu.uniquindio.unieventos.dto.Carrito;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AgregarItemCarritoDTO(
        @NotNull String id,
        @NotBlank String idEvento,
        @NotBlank String localidad,
        @Positive int cantidad
) {
}
