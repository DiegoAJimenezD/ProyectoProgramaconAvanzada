package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.Evento.*;

import java.util.List;
import java.util.Map;

public interface EventoServicio {

    String crearEvento(CrearEventoDTO crearEventoDTO) throws Exception;

    void editarEvento(EditarEventoDTO editarEventoDTO) throws Exception;

    void eliminarEvento(String id) throws Exception;

    InformacionEventoDTO obtenerInformacionEvento(String id) throws Exception;

    List<ItemEventoDTO> listarEventos();

    List<Map<String, String>> listarTipos();

    List<Map<String, String>> listarCiudades();

    List<ItemEventoDTO> filtrarEventos(FiltroEventoDTO filtroEventoDTO);

    void subirImagenEvento(SubirImagenEventoDTO subirImagenEventoDTO, String enlace) throws Exception;
}
