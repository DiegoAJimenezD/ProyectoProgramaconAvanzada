package co.edu.uniquindio.unieventos.controladores;

import co.edu.uniquindio.unieventos.dto.Evento.*;
import co.edu.uniquindio.unieventos.dto.MensajeDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evento")
@RequiredArgsConstructor
public class EventoControlador {

    private final EventoServicio eventoServicio;

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO<String>> crearEvento(@Valid @RequestBody CrearEventoDTO crearEventoDTO) throws Exception {
        String id = eventoServicio.crearEvento(crearEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Evento creado exitosamente con ID: " + id));
    }

    @PutMapping("/editar")
    public ResponseEntity<MensajeDTO<String>> editarEvento(@Valid @RequestBody EditarEventoDTO editarEventoDTO) throws Exception {
        eventoServicio.editarEvento(editarEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Evento editado exitosamente"));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarEvento(@PathVariable String id) throws Exception {
        eventoServicio.eliminarEvento(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Evento eliminado exitosamente"));
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<MensajeDTO<InformacionEventoDTO>> obtenerInformacionEvento(@PathVariable String id) throws Exception {
        InformacionEventoDTO info = eventoServicio.obtenerInformacionEvento(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, info));
    }

    @GetMapping("/listar-todo")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> listarEventos() {
        List<ItemEventoDTO> lista = eventoServicio.listarEventos();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    @PostMapping("/filtrar")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventos(@Valid @RequestBody FiltroEventoDTO filtroEventoDTO) {
        List<ItemEventoDTO> listaFiltrada = eventoServicio.filtrarEventos(filtroEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, listaFiltrada));
    }
}