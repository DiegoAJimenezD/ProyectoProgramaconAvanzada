package co.edu.uniquindio.unieventos.dto.Cupon;

import java.time.LocalDateTime;

public record CrearCuponDTO (
        float descuento,
        LocalDateTime fechaVencimiento,
        String codigo,
        int estadoCupon,
        int tipoCupon,
        String nombre
){
}
