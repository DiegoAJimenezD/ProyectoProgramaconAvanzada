package co.edu.uniquindio.unieventos.dto.Evento;

import co.edu.uniquindio.unieventos.modelo.Localidad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public record SubirImagenEventoDTO(
        @NotBlank String id, // No puede ser nulo ni vacío
        @NotEmpty MultipartFile imagen) {
}
