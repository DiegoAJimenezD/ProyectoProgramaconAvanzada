package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.dto.Evento.*;
import co.edu.uniquindio.unieventos.modelo.Evento;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoEvento;
import co.edu.uniquindio.unieventos.modelo.enums.TipoEvento;
import co.edu.uniquindio.unieventos.repositorios.EventoRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventoServicioImpl implements EventoServicio {

    private final EventoRepo eventoRepo;

    @Override
    public String crearEvento(CrearEventoDTO crearEventoDTO) throws Exception {
        Evento nuevoEvento = new Evento();
        nuevoEvento.setNombre(crearEventoDTO.nombre());
        nuevoEvento.setLocalidades(
                crearEventoDTO.localidades()
        );
        nuevoEvento.setDireccion(crearEventoDTO.direccion());
        nuevoEvento.setCiudad(crearEventoDTO.ciudad());
        nuevoEvento.setFecha(crearEventoDTO.fecha());
        nuevoEvento.setTipo(TipoEvento.valueOf(crearEventoDTO.tipoEvento()));

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
            eventoModificado.setCiudad(editarEventoDTO.ciudad());
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
                    evento.getTipo().toString(),
                    evento.getCiudad(),
                    evento.getFecha(),
                    evento.getDireccion(),
                    evento.getImagenPortada()

            );
        } else {
            throw new Exception("Evento no encontrado");
        }
    }

    @Override
    public List<ItemEventoDTO> listarEventos() {
        List<Evento> eventos = eventoRepo.findAll();
        List<ItemEventoDTO> items = new ArrayList<>();

        for (Evento evento : eventos) {
            items.add(new ItemEventoDTO(
                    evento.getId(),
                    evento.getNombre(),
                    evento.getTipo().toString(),
                    evento.getCiudad(),
                    evento.getFecha(),
                    evento.getDireccion(),
                    evento.getImagenPortada()
            ));
        }
        return items;
    }

    @Override
    public List<ItemEventoDTO> filtrarEventos(FiltroEventoDTO filtroEventoDTO) {
        // Supongamos que ya existe una consulta en el repositorio para filtrar eventos
        List<Evento> eventosFiltrados = eventoRepo.findByNombreOrCiudadOrTipo(
                filtroEventoDTO.nombre(),
                filtroEventoDTO.ciudad(),
                filtroEventoDTO.tipo());
        List<ItemEventoDTO> items = new ArrayList<>();

        for (Evento evento : eventosFiltrados) {
            items.add(new ItemEventoDTO(
                    evento.getId(),
                    evento.getNombre(),
                    evento.getTipo().toString(),
                    evento.getCiudad(),
                    evento.getFecha(),
                    evento.getDireccion(),
                    evento.getImagenPortada()
            ));
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


