package co.edu.uniquindio.unieventos.dto.Carrito;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record  AgregarItemCarritoDTO(
    String idEvento,
    String localidad,
    int cantidad
) {
}
