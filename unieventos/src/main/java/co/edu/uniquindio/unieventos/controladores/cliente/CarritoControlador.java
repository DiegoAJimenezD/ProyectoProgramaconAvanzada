package co.edu.uniquindio.unieventos.controladores.cliente;

import co.edu.uniquindio.unieventos.dto.Carrito.*;
import co.edu.uniquindio.unieventos.dto.MensajeDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.CarritoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente/carrito")
@RequiredArgsConstructor
public class CarritoControlador {

    private final CarritoServicio carritoServicio;


    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO<String>> crearCarrito(@Valid @RequestBody CrearCarritoDTO carritoDTO) throws Exception {
        String idCarrito = carritoServicio.crearCarrito(carritoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Carrito creado exitosamente con ID: " + idCarrito));
    }

    @PostMapping("/agregar-item")
    public ResponseEntity<MensajeDTO<String>> agregarItemCarrito(@Valid @RequestBody AgregarItemCarritoDTO itemCarritoDTO) throws Exception {
        carritoServicio.agregarItemCarrito(itemCarritoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Item agregado al carrito exitosamente"));
    }

    @PutMapping("/editar-item")
    public ResponseEntity<MensajeDTO<String>> editarItemCarrito(@Valid @RequestBody EditarItemCarritoDTO itemCarritoDTO) throws Exception {
        carritoServicio.editarItemCarrito(itemCarritoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Item editado exitosamente"));
    }

    @DeleteMapping("/eliminar-item")
    public ResponseEntity<MensajeDTO<String>> eliminarItemCarrito(@Valid @RequestBody EliminarItemCarritoDTO itemCarritoDTO) throws Exception {
        carritoServicio.eliminarItemCarrito(itemCarritoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Item eliminado del carrito exitosamente"));
    }

    @DeleteMapping("/vaciar/{idCarrito}")
    public ResponseEntity<MensajeDTO<String>> vaciarCarrito(@PathVariable String idCarrito) throws Exception {
        carritoServicio.vaciarCarrito(idCarrito);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Carrito vaciado exitosamente"));
    }

    @GetMapping("/obtener/{idCuenta}")
    public ResponseEntity<MensajeDTO<InformacionCarritoDTO>> obtenerInformacionCarrito(@PathVariable String idCuenta) throws Exception {
        InformacionCarritoDTO infoCarrito = carritoServicio.obtenerInformacionCarrito(idCuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, infoCarrito));
    }
}