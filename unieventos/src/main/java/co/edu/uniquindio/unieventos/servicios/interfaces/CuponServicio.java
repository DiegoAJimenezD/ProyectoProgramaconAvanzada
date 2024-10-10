package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.Cuenta.*;
import co.edu.uniquindio.unieventos.dto.Cupon.*;

import java.util.List;

public interface CuponServicio {

    // Metodo para crear un nuevo cupon
    String crearCupon(CrearCuponDTO crearCuponDTO) throws Exception;

    // Metodo para editar un cupon existente
    void editarCupon(EditarCuponDTO editarCuponDTO) throws Exception;

    // Metodo para eliminar un cupon por su ID
    void eliminarCupon(String idCupon) throws Exception;

    // Metodo para obtener la informacion de un cupon por su ID
    InformacionCuponDTO obtenerInformacionCupon(String idCupon) throws Exception;

    // Metodo para listar todos los cupones disponibles
    List<ItemCuponDTO> listarCupones();

    // Metodo para aplicar un cupon a un carrito o pedido
    void aplicarCupon(String idCupon, String idCarrito) throws Exception;

    // Metodo para filtrar cupones segun criterios
    List<ItemCuponDTO> filtrarCupones(FiltroCuponDTO filtroCuponDTO);

}