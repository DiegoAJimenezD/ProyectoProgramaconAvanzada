package co.edu.uniquindio.unieventos.dto.Carrito;

import co.edu.uniquindio.unieventos.modelo.DetalleCarrito;

import java.time.LocalDateTime;
import java.util.List;

public record InformacionCarritoDTO(
        LocalDateTime fecha,
        String idUsuario,
        List<DetalleCarrito> items
) {
}
