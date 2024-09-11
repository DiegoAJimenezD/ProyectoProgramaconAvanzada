package co.edu.uniquindio.unieventos.modelo;

import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Pago {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String moneda;
    private String tipoPago;
    private String detalleEstado;
    private String codigoAutorizacion;
    private LocalDateTime fecha;
    private float valorTransaccion;
    private String estado;
}
