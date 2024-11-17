package co.edu.uniquindio.unieventos.controladores.admin;

import co.edu.uniquindio.unieventos.dto.Cupon.*;
import co.edu.uniquindio.unieventos.dto.MensajeDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/administrador/cupon")
@RequiredArgsConstructor
public class CuponControlador {

    private final CuponServicio cuponServicio;

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO<String>> crearCupon(@Valid @RequestBody CrearCuponDTO crearCuponDTO) throws Exception {
        String id = cuponServicio.crearCupon(crearCuponDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cupón creado exitosamente con ID: " + id));
    }

    @PutMapping("/editar")
    public ResponseEntity<MensajeDTO<String>> editarCupon(@Valid @RequestBody EditarCuponDTO editarCuponDTO) throws Exception {
        cuponServicio.editarCupon(editarCuponDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cupón editado exitosamente"));
    }

    @DeleteMapping("/eliminar/{idCupon}")
    public ResponseEntity<MensajeDTO<String>> eliminarCupon(@PathVariable String idCupon) throws Exception {
        cuponServicio.eliminarCupon(idCupon);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cupón eliminado exitosamente"));
    }

    @GetMapping("/obtener/{idCupon}")
    public ResponseEntity<MensajeDTO<InformacionCuponDTO>> obtenerInformacionCupon(@PathVariable String idCupon) throws Exception {
        InformacionCuponDTO info = cuponServicio.obtenerInformacionCupon(idCupon);
        return ResponseEntity.ok(new MensajeDTO<>(false, info));
    }

    @GetMapping("/listar-todo")
    public ResponseEntity<MensajeDTO<List<ItemCuponDTO>>> listarCupones() {
        List<ItemCuponDTO> lista = cuponServicio.listarCupones();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    // Si deseas agregar la funcionalidad de filtrar cupones
    @PostMapping("/filtrar")
    public ResponseEntity<MensajeDTO<List<ItemCuponDTO>>> filtrarCupones(@Valid @RequestBody FiltroCuponDTO filtroCuponDTO) {
        List<ItemCuponDTO> listaFiltrada = cuponServicio.filtrarCupones(filtroCuponDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, listaFiltrada));
    }

    @GetMapping("/obtener-tipos")
    public ResponseEntity<MensajeDTO<List<Map<String, String>>>> showTiposCupon() {
        List<Map<String, String>> listaTipos = cuponServicio.listarTipos();
        return ResponseEntity.ok(new MensajeDTO<>(false, listaTipos));
    }
}
