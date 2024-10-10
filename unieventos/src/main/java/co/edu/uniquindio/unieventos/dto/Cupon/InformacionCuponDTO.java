package co.edu.uniquindio.unieventos.dto.Cupon;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record InformacionCuponDTO(
        @NotBlank String id, // No puede ser nulo ni vacío
        @NotNull @DecimalMin(value = "0.0", inclusive = false) float descuento, // Debe ser mayor que 0
        @NotNull LocalDateTime fechaVencimiento, // No puede ser nulo
        @NotBlank String codigo, // No puede ser nulo ni vacío
        @NotBlank @Pattern(regexp = "^(DISPONIBLE|NO_DISPONIBLE)$") String estadoCupon, // Debe ser un valor válido
        @NotBlank @Pattern(regexp = "^(MULTIPLE|UNICO)$") String tipoCupon, // Debe ser un valor válido
        @NotBlank String nombre // No puede ser nulo ni vacío
) {

}
