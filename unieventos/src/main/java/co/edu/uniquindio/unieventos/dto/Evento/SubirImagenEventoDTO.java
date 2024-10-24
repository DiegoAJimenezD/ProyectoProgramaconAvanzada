package co.edu.uniquindio.unieventos.dto.Evento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public record SubirImagenEventoDTO(
        @NotBlank String id, // No puede ser nulo ni vacío
        @NotEmpty MultipartFile imagen) {
}
