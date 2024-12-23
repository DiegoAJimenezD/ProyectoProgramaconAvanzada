package co.edu.uniquindio.unieventos.dto.Evento;

import co.edu.uniquindio.unieventos.modelo.Localidad;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CrearEventoDTO(
        @NotBlank String nombre, // No puede ser nulo ni vacío
        @NotBlank String ciudad, // No puede ser nulo ni vacío
        @NotNull @Future LocalDateTime fecha, // No puede ser nulo y debe estar en el futuro
        @NotBlank String tipoEvento, // No puede ser nulo ni vacío
        @NotBlank String direccion, // No puede ser nulo ni vacío
        @NotBlank String descripcion,
        List<Localidad> localidades,
        String imagenPortada,
        String imagenLocalidades
) {
}
