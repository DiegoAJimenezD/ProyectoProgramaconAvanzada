package co.edu.uniquindio.unieventos.dto.Cuenta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EditarCuentaDTO(
        @NotBlank String id,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(max = 10) String telefono,
        @Length(max = 100) String direccion,
        @NotBlank @Length(max = 50) @Email String email,
        @NotBlank @Length(min = 7, max = 20) String password
) {
}
