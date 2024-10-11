package co.edu.uniquindio.unieventos.dto.Transporte;

import co.edu.uniquindio.unieventos.modelo.enums.TipoViaje;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ItemTransporteDTO(
        @NotBlank String id,
        @NotBlank TipoViaje tipoViaje,
        @NotBlank int numParajeros,
        @NotBlank double precio,
        @NotBlank @Length(min = 10, max = 50) String descripcion
) {
}
