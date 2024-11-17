package co.edu.uniquindio.unieventos.dto.Carrito;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

public record InformacionCarritoDTO(
        @NotNull LocalDateTime fecha,
        @NotBlank @Length(max = 36) String idUsuario,
        @NotNull List<DetalleItemCarritoDTO> items
) {
}
