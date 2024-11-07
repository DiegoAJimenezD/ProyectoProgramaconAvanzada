package co.edu.uniquindio.unieventos.controladores.cliente;

import co.edu.uniquindio.unieventos.dto.Evento.*;
import co.edu.uniquindio.unieventos.dto.MensajeDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.ImagenServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente/evento")
@RequiredArgsConstructor
public class EventoClienteControlador {

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

    @PostMapping("/filtrar")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> filtrarEventos(@Valid @RequestBody FiltroEventoDTO filtroEventoDTO) {
        List<ItemEventoDTO> listaFiltrada = eventoServicio.filtrarEventos(filtroEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, listaFiltrada));
    }

}