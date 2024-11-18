package co.edu.uniquindio.unieventos.controladores.admin;

import co.edu.uniquindio.unieventos.dto.Cuenta.CrearCuentaDTO;
import co.edu.uniquindio.unieventos.dto.Cupon.ItemCuponDTO;
import co.edu.uniquindio.unieventos.dto.MensajeDTO;
import co.edu.uniquindio.unieventos.dto.Pqr.CambiarEstadoPqrDTO;
import co.edu.uniquindio.unieventos.dto.Pqr.CrearPqrDTO;
import co.edu.uniquindio.unieventos.dto.Pqr.InformacionPqrDTO;
import co.edu.uniquindio.unieventos.dto.Pqr.ResponderPqrDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.PqrServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrador/pqr")
@RequiredArgsConstructor
public class PqrControlador {

    private final PqrServicio pqrServicio;

    @PutMapping("/responder")
    public ResponseEntity<MensajeDTO<String>> responderPqr(@Valid @RequestBody ResponderPqrDTO responderPqrDTO) throws Exception {
        pqrServicio.responderPqr(responderPqrDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "PQRS respondido exitosamente"));
    }

    @PutMapping("/cambiar-estado")
    public ResponseEntity<MensajeDTO<String>> cambiarEstadoPqr(@Valid @RequestBody CambiarEstadoPqrDTO cambiarEstadoPqrDTO) throws Exception {
        pqrServicio.cambiarEstadoPqr(cambiarEstadoPqrDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Estado del PQRS actualizado exitosamente"));
    }

    @GetMapping("/detalle/{idPqr}")
    public ResponseEntity<MensajeDTO<InformacionPqrDTO>> obtenerDetallePqr(@PathVariable String idPqr) throws Exception {
        InformacionPqrDTO detallePqr = pqrServicio.obtenerInformacionPqr(idPqr);
        return ResponseEntity.ok(new MensajeDTO<>(false, detallePqr));
    }

    @GetMapping("/listar-todo")
    public ResponseEntity<MensajeDTO<List<InformacionPqrDTO>>> listarPqrs() {
        List<InformacionPqrDTO> lista = pqrServicio.listarPqr();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

}
