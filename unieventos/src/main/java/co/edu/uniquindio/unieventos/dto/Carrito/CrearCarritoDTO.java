package co.edu.uniquindio.unieventos.dto.Carrito;

import jakarta.validation.constraints.NotBlank;

public record CrearCarritoDTO(
        @NotBlank String idUsuario
) {

}
