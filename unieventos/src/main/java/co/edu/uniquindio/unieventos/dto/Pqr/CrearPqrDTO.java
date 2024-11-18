package co.edu.uniquindio.unieventos.dto.Pqr;

import jakarta.validation.constraints.NotBlank;


public record CrearPqrDTO(
        @NotBlank String tipo, // No puede ser nulo ni vacío
        @NotBlank String descripcion, // No puede ser nulo ni vacío
        @NotBlank String idCuenta // No puede ser nulo ni vacío
) {

}
