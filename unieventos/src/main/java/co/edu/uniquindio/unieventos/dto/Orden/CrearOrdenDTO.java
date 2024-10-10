package co.edu.uniquindio.unieventos.dto.Orden;

import co.edu.uniquindio.unieventos.modelo.DetalleOrden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;

public record CrearOrdenDTO(
        @NotBlank String estadoOrden,
        @NotBlank String idCliente, // No puede ser nulo ni vacío
        String idCupon, // Puede ser nulo
        @NotNull LocalDateTime fecha, // No puede ser nulo
        String codigoPasarela, // Puede ser nulo
        @NotEmpty List<DetalleOrden> items, // No puede ser nulo ni vacío
        @Positive float total // Debe ser un número positivo
) {
}
