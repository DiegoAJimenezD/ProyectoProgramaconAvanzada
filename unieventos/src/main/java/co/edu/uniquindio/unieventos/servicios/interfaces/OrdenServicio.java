package co.edu.uniquindio.unieventos.servicios.interfaces;

import java.util.Map;

import co.edu.uniquindio.unieventos.dto.Orden.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.dto.Orden.EditarOrdenDTO;
import co.edu.uniquindio.unieventos.dto.Orden.InformacionOrdenDTO;
import com.mercadopago.resources.preference.Preference;


public interface OrdenServicio {

    // Metodo para realizar un pago basado en el ID de la orden
    Preference realizarPago(String idOrden) throws Exception;

    // Metodo para recibir notificaciones de MercadoPago
    void recibirNotificacionMercadoPago(Map<String, Object> request);

    // Metodo para crear una nueva orden
    String crearOrden(CrearOrdenDTO crearOrdenDTO) throws Exception;

    // Metodo para obtener una orden por su ID
    InformacionOrdenDTO obtenerInformacionOrden(String idOrden) throws Exception;

    // Metodo para actualizar el estado de una orden
    void editarOrden(EditarOrdenDTO editarOrdenDTO) throws Exception;

    // Metodo para cancelar una orden
    void cancelarOrden(String idOrden) throws Exception;
}

