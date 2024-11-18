package co.edu.uniquindio.unieventos.dto.Orden;

import co.edu.uniquindio.unieventos.modelo.DetalleOrden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record RealizarPagoDTO(
        @NotNull String idOrden// Puede ser nulo
) {
}
