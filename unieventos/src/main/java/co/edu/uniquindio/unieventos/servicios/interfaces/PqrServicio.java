package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.Cupon.ItemCuponDTO;
import co.edu.uniquindio.unieventos.dto.Pqr.CambiarEstadoPqrDTO;
import co.edu.uniquindio.unieventos.dto.Pqr.CrearPqrDTO;
import co.edu.uniquindio.unieventos.dto.Pqr.InformacionPqrDTO;
import co.edu.uniquindio.unieventos.dto.Pqr.ResponderPqrDTO;

import java.util.List;

public interface PqrServicio {

    public String crearPqr(CrearPqrDTO crearPqrDTO) throws Exception;


    // Método para responder una PQR existente
    void responderPqr(ResponderPqrDTO responderPqrDTO) throws Exception;

    // Método para cambiar el estado de una PQR
    void cambiarEstadoPqr(CambiarEstadoPqrDTO cambiarEstadoPqrDTO) throws Exception;

    // Método para obtener la información detallada de una PQR
    InformacionPqrDTO obtenerInformacionPqr(String idPqr) throws Exception;

    // Método para eliminar una PQR existente
    void eliminarPqr(String idPqr) throws Exception;

    List<InformacionPqrDTO> listarPqr();
}
