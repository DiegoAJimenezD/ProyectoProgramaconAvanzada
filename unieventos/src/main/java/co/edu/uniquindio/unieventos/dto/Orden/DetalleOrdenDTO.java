package co.edu.uniquindio.unieventos.dto.Orden;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.List;

public record DetalleOrdenDTO(
        @NotBlank String id, // No puede ser nulo ni vacío
        @NotBlank @Pattern(regexp = "^(ACTIVO|CANCELADO)$") String estadoOrden,
// No puede ser nulo ni vacío; debe ser uno de los estados permitidos
        @NotBlank String idCliente, // No puede ser nulo ni vacío
        String idCupon, // Puede ser nulo
        @NotNull LocalDateTime fecha, // No puede ser nulo
        String codigoPasarela, // Puede ser nulo
        List<DetalleItemOrdenDTO> items, // Puede ser nulo
        @Min(value = 0) float total // Debe ser mayor o igual a 0
) {
}
