package co.edu.uniquindio.unieventos.dto.Cuenta;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EditarCuentaDTO(
        @NotBlank String id,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(max = 100) String cedula,
        @NotBlank @Length(max = 10) String telefono,
        @NotBlank@Length(max = 100) String direccion,
        @NotBlank@Length(max = 30) String email
) {
}
