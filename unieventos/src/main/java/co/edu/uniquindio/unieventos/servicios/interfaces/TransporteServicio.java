package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.Transporte.CrearTransporteDTO;
import co.edu.uniquindio.unieventos.dto.Transporte.EditarTransporteDTO;
import co.edu.uniquindio.unieventos.dto.Transporte.InformacionTransporteDTO;
import co.edu.uniquindio.unieventos.dto.Transporte.ItemTransporteDTO;

import java.util.List;

public interface TransporteServicio {

    String crearTransporte(CrearTransporteDTO transporte) throws Exception;

    void editarTransporte(EditarTransporteDTO transporte) throws Exception;

    void eliminarTransporte(String id) throws Exception;

    InformacionTransporteDTO obtenerInformacionTransporte(String id) throws Exception;

    List<ItemTransporteDTO> listarTransporte();
}
