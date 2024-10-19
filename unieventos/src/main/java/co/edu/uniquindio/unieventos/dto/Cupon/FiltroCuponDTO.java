package co.edu.uniquindio.unieventos.dto.Cupon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FiltroCuponDTO(
        String codigo, // No puede ser nulo ni vacío
        String estadoCupon, // No puede ser nulo
        String nombre // No puede ser nulo ni vacío
) {
}
