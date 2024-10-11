package co.edu.uniquindio.unieventos.dto.Cuenta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CambiarPasswordDTO(
        @NotBlank @Email String email,
        @NotBlank @Length(min = 6) String codigoVerificacion,
        @NotBlank @Length(min = 7, max = 20) String passwordNueva
) {
}
