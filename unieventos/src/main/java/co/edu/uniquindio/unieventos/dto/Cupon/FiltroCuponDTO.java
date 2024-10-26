package co.edu.uniquindio.unieventos.dto.Cupon;

public record FiltroCuponDTO(
        String codigo, // No puede ser nulo ni vacío
        String estado, // No puede ser nulo
        String nombre // No puede ser nulo ni vacío
) {
}
