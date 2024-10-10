package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.Carrito.AgregarItemCarritoDTO;
import co.edu.uniquindio.unieventos.dto.Carrito.CrearCarritoDTO;
import co.edu.uniquindio.unieventos.dto.Carrito.EditarItemCarritoDTO;
import co.edu.uniquindio.unieventos.dto.Carrito.InformacionCarritoDTO;

public interface CarritoServicio {

    // Metodo para crear un nuevo carrito
    String crearCarrito(CrearCarritoDTO crearCarritoDTO) throws Exception;

    // Metodo para agregar un producto al carrito
    void agregarItemCarrito(AgregarItemCarritoDTO agregarItemCarritoDTO) throws Exception;

    // Metodo para editar un producto ya existente en el carrito
    void editarItemCarrito(EditarItemCarritoDTO editarItemCarritoDTO) throws Exception;

    // Metodo para eliminar un producto del carrito
    void eliminarItemCarrito(String idCarrito) throws Exception;

    // Metodo para vaciar completamente el carrito
    void vaciarCarrito(String idCarrito) throws Exception;

    // Metodo para obtener la información detallada del carrito
    InformacionCarritoDTO obtenerInformacionCarrito(String idCarrito) throws Exception;

}
