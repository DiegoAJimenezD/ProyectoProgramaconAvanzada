package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.Evento.*;
import co.edu.uniquindio.unieventos.modelo.Evento;
import co.edu.uniquindio.unieventos.modelo.Localidad;

import java.util.List;

public interface EventoServicio {

    String crearEvento(CrearEventoDTO crearEventoDTO) throws Exception;

    void editarEvento(EditarEventoDTO editarEventoDTO) throws Exception;

    void eliminarEvento(String id) throws Exception;

    InformacionEventoDTO obtenerInformacionEvento(String id) throws Exception;

    List<ItemEventoDTO> listarEventos();

    List<ItemEventoDTO> filtrarEventos(FiltroEventoDTO filtroEventoDTO);


}
