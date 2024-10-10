package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.Carrito.*;

import java.util.List;

public interface CarritoServicio {

    // Metodo para crear un nuevo carrito
    String crearCarrito(CrearCarritoDTO crearCarritoDTO) throws Exception;

    // Metodo para agregar un producto al carrito
    void agregarItemCarrito(AgregarItemCarritoDTO agregarItemCarritoDTO) throws Exception;

    // Metodo para editar un producto ya existente en el carrito
    void editarItemCarrito(EditarItemCarritoDTO editarItemCarritoDTO) throws Exception;

    // Metodo para eliminar un producto del carrito
    void eliminarItemCarrito(String idItem) throws Exception;

    // Metodo para vaciar completamente el carrito
    void vaciarCarrito(String idCarrito) throws Exception;

    // Metodo para obtener la información detallada del carrito
    InformacionCarritoDTO obtenerInformacionCarrito(String idCarrito) throws Exception;

    // Metodo para listar los productos dentro de un carrito
    List<ItemCarritoDTO> listarProductos(String idCarrito);

}
