package co.edu.uniquindio.unieventos.controladores;

import co.edu.uniquindio.unieventos.dto.MensajeDTO;
import co.edu.uniquindio.unieventos.dto.Orden.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.dto.Orden.EditarOrdenDTO;
import co.edu.uniquindio.unieventos.dto.Orden.InformacionOrdenDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.OrdenServicio;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orden")
public class OrdenControlador {

    private final OrdenServicio ordenServicio;

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO<String>> crearOrden(@RequestBody CrearOrdenDTO crearOrdenDTO) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ordenServicio.crearOrden(crearOrdenDTO)));
    }

    @GetMapping("/informacion/{id}")
    public ResponseEntity<MensajeDTO<InformacionOrdenDTO>> obtenerInformacionOrden(@PathVariable("id") String idOrden) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ordenServicio.obtenerInformacionOrden(idOrden)));
    }

    @GetMapping("/listar-ordenes/{id}")
    public ResponseEntity<MensajeDTO<List<InformacionOrdenDTO>>> listarOrdenesByIdCuenta(@PathVariable("id") String idOrden) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ordenServicio.listarOrdenesPorCliente(idOrden)));
    }

    @PutMapping("/editar")
    public ResponseEntity<MensajeDTO<String>> editarOrden(@RequestBody EditarOrdenDTO editarOrdenDTO) throws Exception {
        ordenServicio.editarOrden(editarOrdenDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Orden editada correctamente"));
    }

    @DeleteMapping("/cancelar/{id}")
    public ResponseEntity<MensajeDTO<String>> cancelarOrden(@PathVariable("id") String idOrden) throws Exception {
        ordenServicio.cancelarOrden(idOrden);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Orden cancelada correctamente"));
    }

    @PostMapping("/realizar-pago")
    public ResponseEntity<MensajeDTO<Preference>> realizarPago(@RequestParam("idOrden") String idOrden) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ordenServicio.realizarPago(idOrden)));
    }

    @PostMapping("/notificacion-pago")
    public void recibirNotificacionMercadoPago(@RequestBody Map<String, Object> requestBody) {
        ordenServicio.recibirNotificacionMercadoPago(requestBody);
    }
}
