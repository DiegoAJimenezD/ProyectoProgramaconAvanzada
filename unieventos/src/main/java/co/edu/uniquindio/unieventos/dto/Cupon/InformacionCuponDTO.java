package co.edu.uniquindio.unieventos.dto.Cupon;

import java.time.LocalDateTime;

public record InformacionCuponDTO(
        String id,
        float descuento,
        LocalDateTime fechaVencimiento,
        String codigo,
        String estadoCupon,
        String tipoCupon,
        String nombre
) {

}
