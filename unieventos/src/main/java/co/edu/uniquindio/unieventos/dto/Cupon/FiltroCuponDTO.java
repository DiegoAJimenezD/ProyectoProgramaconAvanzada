package co.edu.uniquindio.unieventos.dto.Cupon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FiltroCuponDTO(
        @NotBlank String codigo, // No puede ser nulo ni vacío
        @NotNull String estadoCupon, // No puede ser nulo
        @NotBlank String nombre // No puede ser nulo ni vacío
) {
}
