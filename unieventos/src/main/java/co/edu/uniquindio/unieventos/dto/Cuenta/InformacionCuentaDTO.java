package co.edu.uniquindio.unieventos.dto.Cuenta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record InformacionCuentaDTO(
        @NotBlank String id,
        @NotBlank @Length(min = 6, max =12) String cedula,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(max = 10) String telefono,
        @Length(max = 100) String direccion,
        @NotBlank @Length(max = 50) @Email String email
) {
}
