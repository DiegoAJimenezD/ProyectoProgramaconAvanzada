package co.edu.uniquindio.unieventos.dto.Orden;

import co.edu.uniquindio.unieventos.modelo.DetalleOrden;

import java.time.LocalDateTime;
import java.util.List;

public record CrearOrdenDTO(
        int estadoOrden,
        String idCliente,
        String idCupon,
        LocalDateTime fecha,
        String codigoPasarela,
        List<DetalleOrden> items,
        float total
) {
}
