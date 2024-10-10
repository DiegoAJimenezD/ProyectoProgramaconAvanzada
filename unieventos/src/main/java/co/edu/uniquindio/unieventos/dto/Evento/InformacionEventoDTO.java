package co.edu.uniquindio.unieventos.dto.Evento;

import java.time.LocalDateTime;

public record InformacionEventoDTO(
        String id,
        String nombre,
        String ciudad,
        LocalDateTime fecha,
        String direccion,
        String urlImagenPoster
) {
}
