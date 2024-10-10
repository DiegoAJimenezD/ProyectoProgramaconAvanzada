package co.edu.uniquindio.unieventos.dto.Cupon;

import java.time.LocalDateTime;

public record EditarCuponDTO (
        float descuento,
        LocalDateTime fechaVencimiento,
        String codigo,
        int estadoCupon,
        int tipoCupon,
        String nombre
){
}
