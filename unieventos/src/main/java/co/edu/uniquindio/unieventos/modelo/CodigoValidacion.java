package co.edu.uniquindio.unieventos.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CodigoValidacion {
    private LocalDateTime fecha;
    private String codigo;
}
