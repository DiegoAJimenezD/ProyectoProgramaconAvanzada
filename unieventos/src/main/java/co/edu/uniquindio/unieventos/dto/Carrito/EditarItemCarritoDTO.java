package co.edu.uniquindio.unieventos.dto.Carrito;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record EditarItemCarritoDTO(
        @NotBlank String idCarrito,
        @NotBlank String idEvento,
        @NotBlank @Length(max = 50) String localidad,
        @Positive int cantidad
) {
}
