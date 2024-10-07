package co.edu.uniquindio.unieventos.servicios.interfaces;

import java.util.Map;
import com.mercadopago.resources.preference.Preference;


public interface OrdenServicio {


    //TODO Todos los demás métodos que se van a implementar…


    Preference realizarPago(String idOrden) throws Exception;
    void recibirNotificacionMercadoPago(Map<String, Object> request);
}

