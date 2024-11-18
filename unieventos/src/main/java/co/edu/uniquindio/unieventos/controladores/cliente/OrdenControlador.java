package co.edu.uniquindio.unieventos.controladores.cliente;

import co.edu.uniquindio.unieventos.dto.MensajeDTO;
import co.edu.uniquindio.unieventos.dto.Orden.*;
import co.edu.uniquindio.unieventos.servicios.interfaces.OrdenServicio;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cliente/orden")
public class OrdenControlador {

    private final OrdenServicio ordenServicio;

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO<String>> crearOrden(@RequestBody CrearOrdenDTO crearOrdenDTO) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ordenServicio.crearOrden(crearOrdenDTO)));
    }

    @GetMapping("/informacion/{id}")
    public ResponseEntity<MensajeDTO<DetalleOrdenDTO>> obtenerInformacionOrden(@PathVariable("id") String idOrden) throws Exception {
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
    public ResponseEntity<MensajeDTO<Preference>> realizarPago(@RequestBody RealizarPagoDTO realizarPagoDTO) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ordenServicio.realizarPago(realizarPagoDTO)));
    }

    @PostMapping("/notificacion-pago")
    public void recibirNotificacionMercadoPago(@RequestBody Map<String, Object> requestBody) {
        ordenServicio.recibirNotificacionMercadoPago(requestBody);
    }

    @GetMapping(value = "/qr/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCode(@PathVariable("barcode") String id) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        DetalleOrdenDTO orden = ordenServicio.obtenerInformacionOrden(id);
        BitMatrix bitMatrix = barcodeWriter.encode(orden.toString(), BarcodeFormat.QR_CODE, 500, 500);

        // Convertir la imagen a un array de bytes
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageBytes = baos.toByteArray();

        // Crear respuesta con la imagen
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return ResponseEntity.ok()
                .headers(headers)
                .body(imageBytes);
    }
}
