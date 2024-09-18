package co.edu.uniquindio.unieventos.dto.Cuenta;

public record CambiarPasswordDTO(
        String codigoVerificacion,
        String passwordNueva
) {
}
