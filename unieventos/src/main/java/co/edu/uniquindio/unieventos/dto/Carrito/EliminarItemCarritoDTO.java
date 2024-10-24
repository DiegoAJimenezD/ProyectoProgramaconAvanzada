package co.edu.uniquindio.unieventos.dto.Carrito;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EliminarItemCarritoDTO(
        @NotBlank String idCuenta,
        @NotBlank String idEvento,
        @NotBlank @Length(max = 50) String localidad
) {
}
