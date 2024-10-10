package co.edu.uniquindio.unieventos.dto.Cupon;

import java.time.LocalDateTime;

public record FiltroCuponDTO (
        String codigo,
        int estadoCupon,
        String nombre
){
}
