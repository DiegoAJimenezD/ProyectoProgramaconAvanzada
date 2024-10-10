package co.edu.uniquindio.unieventos.dto.Carrito;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record CrearCarritoDTO(
    @NotBlank String idUsuario,
    LocalDateTime fecha
){

}
