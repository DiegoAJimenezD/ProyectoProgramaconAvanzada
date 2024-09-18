package co.edu.uniquindio.unieventos.dto.Evento;

import java.time.LocalDateTime;

public record CrearEventoDTO(
        String nombre,
        String lugar,
        String ciudad,
        LocalDateTime fecha,
        String categoria,
        String direccion,
        float precioGeneral,
        float precioVIP
) {
}
