package co.edu.uniquindio.unieventos.dto.Carrito;

public record AgregarItemCarritoDTO(
        String idCarrito,
        String idEvento,
        String localidad,
        int cantidad
) {
}
