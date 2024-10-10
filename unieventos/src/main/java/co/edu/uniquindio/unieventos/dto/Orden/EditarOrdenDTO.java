package co.edu.uniquindio.unieventos.dto.Orden;

import java.time.LocalDateTime;
import java.util.List;

public record EditarOrdenDTO(
        int estadoOrden,
        String codigoPasarela
        //Pago pago
) {
}
