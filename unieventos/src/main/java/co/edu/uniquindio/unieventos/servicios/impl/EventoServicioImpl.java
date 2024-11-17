package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.dto.Evento.*;
import co.edu.uniquindio.unieventos.modelo.Evento;
import co.edu.uniquindio.unieventos.modelo.enums.Ciudad;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoEvento;
import co.edu.uniquindio.unieventos.modelo.enums.TipoEvento;
import co.edu.uniquindio.unieventos.repositorios.EventoRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.ImagenServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class EventoServicioImpl implements EventoServicio {

    private final EventoRepo eventoRepo;
    private final ImagenServicio imagenServicio;

    @Override
    public String crearEvento(CrearEventoDTO crearEventoDTO) throws Exception {
        Evento nuevoEvento = new Evento();
        nuevoEvento.setNombre(crearEventoDTO.nombre());
        nuevoEvento.setLocalidades(
                crearEventoDTO.localidades()
        );
        nuevoEvento.setDireccion(crearEventoDTO.direccion());
        nuevoEvento.setCiudad(Ciudad.valueOf(crearEventoDTO.ciudad()));
        nuevoEvento.setFecha(crearEventoDTO.fecha());
        nuevoEvento.setTipo(TipoEvento.valueOf(crearEventoDTO.tipoEvento()));
        nuevoEvento.setEstado(EstadoEvento.ACTIVO);

        //nuevoEvento.setImagenPortada(imagenServicio.subirImagen(crearEventoDTO.multipartFile()));
        // Guardamos el nuevo evento en la base de datos
        Evento eventoGuardado = eventoRepo.save(nuevoEvento);
        return eventoGuardado.getId();
    }

    @Override
    public void editarEvento(EditarEventoDTO editarEventoDTO) throws Exception {
        Optional<Evento> optionalEvento = eventoRepo.findById(editarEventoDTO.id());

        if (optionalEvento.isPresent()) {
            Evento eventoModificado = optionalEvento.get();
            eventoModificado.setNombre(editarEventoDTO.nombre());
            eventoModificado.setLocalidades(editarEventoDTO.localidades());
            eventoModificado.setCiudad(Ciudad.valueOf(editarEventoDTO.ciudad()));
            eventoModificado.setFecha(editarEventoDTO.fecha());
            eventoModificado.setDireccion(editarEventoDTO.direccion());
            eventoModificado.setTipo(TipoEvento.valueOf(editarEventoDTO.tipoEvento()));

            // Guardamos los cambios en el evento
            eventoRepo.save(eventoModificado);
        } else {
            throw new Exception("Evento no encontrado");
        }
    }

    @Override
    public void eliminarEvento(String id) throws Exception {
        Optional<Evento> optionalEvento = eventoRepo.findById(id);

        //Si no se encontró la cuenta, lanzamos una excepción
        if (optionalEvento.isEmpty()) {
            throw new Exception("No se encontró el evento con el id " + id);
        }

        Evento evento = optionalEvento.get();
        evento.setEstado(EstadoEvento.INACTIVO);

        eventoRepo.save(evento);
    }

    @Override
    public InformacionEventoDTO obtenerInformacionEvento(String id) throws Exception {
        Optional<Evento> optionalEvento = eventoRepo.findById(id);

        if (optionalEvento.isPresent()) {
            Evento evento = optionalEvento.get();
            return new InformacionEventoDTO(
                    evento.getId(),
                    evento.getNombre(),
                    evento.getDescripcion(),
                    evento.getTipo().getNombreTipoEvento(),
                    evento.getCiudad().getNombreCiudad(),
                    evento.getFecha(),
                    evento.getDireccion(),
                    evento.getImagenPortada(),
                    evento.getImagenLocalidades(),
                    evento.getLocalidades()
            );
        } else {
            throw new Exception("Evento no encontrado");
        }
    }

    @Override
    public List<ItemEventoDTO> listarEventos() {
        List<Evento> eventos = eventoRepo.findByEstado("ACTIVO");
        List<ItemEventoDTO> items = new ArrayList<>();

        for (Evento evento : eventos) {
            //if (evento.getEstado().equals(EstadoEvento.ACTIVO)) {
            items.add(new ItemEventoDTO(
                    evento.getId(),
                    evento.getNombre(),
                    evento.getTipo().getNombreTipoEvento(),
                    evento.getCiudad().getNombreCiudad(),
                    evento.getFecha(),
                    evento.getDireccion(),
                    evento.getImagenPortada()
            ));
            //}
        }
        return items;
    }

    @Override
    public List<Map<String, String>> listarTipos() {
        List<Map<String, String>> tipos = new ArrayList<>();
        for (TipoEvento tipo : TipoEvento.values()) {
            Map<String, String> tipoMap = new HashMap<>();
            tipoMap.put("value", tipo.name());
            tipoMap.put("nombre", tipo.getNombreTipoEvento());
            tipos.add(tipoMap);
        }
        return tipos;
    }

    @Override
    public List<Map<String, String>> listarCiudades() {
        List<Map<String, String>> ciudades = new ArrayList<>();
        for (Ciudad ciudad : Ciudad.values()) {
            Map<String, String> ciudadMap = new HashMap<>();
            ciudadMap.put("value", ciudad.name());
            ciudadMap.put("nombre", ciudad.getNombreCiudad());
            ciudades.add(ciudadMap);
        }
        return ciudades;
    }

    @Override
    public List<ItemEventoDTO> filtrarEventos(FiltroEventoDTO filtroEventoDTO) {
        // Supongamos que ya existe una consulta en el repositorio para filtrar eventos
        List<Evento> eventosFiltrados;

        eventosFiltrados = eventoRepo.findByNombreIgnoreCaseLikeAndCiudadLikeAndTipoLikeAndEstado(
                filtroEventoDTO.nombre(),
                filtroEventoDTO.ciudad(),
                filtroEventoDTO.tipo(),
                "ACTIVO"
        );

        List<ItemEventoDTO> items = new ArrayList<>();

        for (Evento evento : eventosFiltrados) {
            if (evento.getEstado().equals(EstadoEvento.ACTIVO)) {
                items.add(new ItemEventoDTO(
                        evento.getId(),
                        evento.getNombre(),
                        evento.getTipo().getNombreTipoEvento(),
                        evento.getCiudad().getNombreCiudad(),
                        evento.getFecha(),
                        evento.getDireccion(),
                        evento.getImagenPortada()
                ));
            }

        }
        return items;
    }

    @Override
    public void subirImagenEvento(SubirImagenEventoDTO subirImagenEventoDTO, String enlace) throws Exception {
        Optional<Evento> optionalEvento = eventoRepo.findById(subirImagenEventoDTO.id());

        if (optionalEvento.isPresent()) {
            Evento eventoModificado = optionalEvento.get();
            eventoModificado.setNombre(enlace);

            // Guardamos los cambios en el evento
            eventoRepo.save(eventoModificado);
        } else {
            throw new Exception("Evento no encontrado");
        }
    }

}


