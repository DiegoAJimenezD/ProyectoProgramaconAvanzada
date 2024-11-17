package co.edu.uniquindio.unieventos.dto.Orden;

import co.edu.uniquindio.unieventos.modelo.Evento;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record DetalleItemOrdenDTO(
        @NotBlank @Length(max = 100) Evento evento, // No puede ser nulo ni vacío; longitud máxima de 100 caracteres
        @NotBlank String nombreLocalidad, // No puede ser nulo ni vacío
        @Min(value = 0) float precio, // Debe ser mayor o igual a 0
        @Min(value = 1) int cantidad // Debe ser mayor o igual a 1
) {
}
