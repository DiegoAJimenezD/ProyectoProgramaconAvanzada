package co.edu.uniquindio.unieventos.controladores;

import co.edu.uniquindio.unieventos.dto.Evento.FiltroEventoDTO;
import co.edu.uniquindio.unieventos.dto.Evento.InformacionEventoDTO;
import co.edu.uniquindio.unieventos.dto.Evento.ItemEventoDTO;
import co.edu.uniquindio.unieventos.dto.MensajeDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.ImagenServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/evento")
@RequiredArgsConstructor
public class EventoPublicControlador {

    private final EventoServicio eventoServicio;
    private final ImagenServicio imagenServicio;

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

    @GetMapping("/obtener-tipos")
    public ResponseEntity<MensajeDTO<List<Map<String, String>>>> showTiposEvento() {
        List<Map<String, String>> listaTipos = eventoServicio.listarTipos();
        return ResponseEntity.ok(new MensajeDTO<>(false, listaTipos));
    }

    @GetMapping("/obtener-ciudades")
    public ResponseEntity<MensajeDTO<List<Map<String, String>>>> showCiudades() {
        List<Map<String, String>> listaCiudades = eventoServicio.listarCiudades();
        return ResponseEntity.ok(new MensajeDTO<>(false, listaCiudades));
    }

    @PostMapping("/filtrar")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventos(@Valid @RequestBody FiltroEventoDTO filtroEventoDTO) {
        List<ItemEventoDTO> listaFiltrada = eventoServicio.filtrarEventos(filtroEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, listaFiltrada));
    }

}