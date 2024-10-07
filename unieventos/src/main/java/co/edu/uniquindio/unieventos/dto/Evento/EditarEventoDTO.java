package co.edu.uniquindio.unieventos.dto.Evento;

import java.time.LocalDateTime;

public record EditarEventoDTO(
        String id,
        String nombre,
        LocalDateTime fecha,
        String categoria,
        String direccion,
        float precioGeneral,
        float precioVIP
) {
}
