package co.edu.uniquindio.unieventos.dto.Carrito;

import co.edu.uniquindio.unieventos.modelo.DetalleCarrito;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record InformacionCarritoDTO(
        @NotNull LocalDateTime fecha,
        @NotBlank @Length(max = 36) String idUsuario,
        @NotNull List<DetalleCarrito> items
) {
}
