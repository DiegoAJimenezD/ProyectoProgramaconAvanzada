package co.edu.uniquindio.unieventos.controladores;



import co.edu.uniquindio.unieventos.dto.MensajeDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.ImagenServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/imagenes")
public class ImagenControlador {


    private final ImagenServicio imagenServicio;


    @PostMapping("/subir")
    public ResponseEntity<MensajeDTO<String>> subir(@RequestParam("imagen") MultipartFile imagen) throws Exception{
        String respuesta = imagenServicio.subirImagen(imagen);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, respuesta));
    }


    @DeleteMapping("/eliminar")
    public ResponseEntity<MensajeDTO<String>> eliminar(@RequestParam("idImagen") String idImagen)  throws Exception{
        imagenServicio.eliminarImagen( idImagen );
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "La imagen fue eliminada correctamente"));
    }


}
