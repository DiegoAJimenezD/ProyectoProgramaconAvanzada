package co.edu.uniquindio.unieventos.controladores.admin;


import co.edu.uniquindio.unieventos.dto.MensajeDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.ImagenServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/administrador/imagen")
public class ImagenControlador {

    private final ImagenServicio imagenServicio;

    @PostMapping("/subir")
    public ResponseEntity<MensajeDTO<String>> subir(@RequestParam("imagen") MultipartFile imagen) throws Exception {
        String respuesta = imagenServicio.subirImagen(imagen);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, respuesta));
    }

    @DeleteMapping("/eliminar/{idImagen}")
    public ResponseEntity<MensajeDTO<String>> eliminar(@PathVariable String idImagen) throws Exception {
        imagenServicio.eliminarImagen(idImagen);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "La imagen fue eliminada correctamente"));
    }

    @PutMapping("/editar/{idImagenExistente}")
    public ResponseEntity<MensajeDTO<String>> editar(
            @PathVariable String idImagenExistente,
            @RequestParam("nuevaImagen") MultipartFile nuevaImagen) throws Exception {
        String nuevaUrl = imagenServicio.editarImagen(idImagenExistente, nuevaImagen);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Imagen editada correctamente: " + nuevaUrl));
    }
}
