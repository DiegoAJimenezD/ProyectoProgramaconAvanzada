package co.edu.uniquindio.unieventos.dto.Carrito;

public record EditarItemCarritoDTO(
        String idCarrito,
        String idEvento,
        String localidad,
        int cantidad
) {
}
