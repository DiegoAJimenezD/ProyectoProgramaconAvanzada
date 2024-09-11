package co.edu.uniquindio.unieventos.modelo;

import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

public class Evento {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String imagenPortada;
    private EstadoEvento estado;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String imagenLocalidades;
    private TipoEvento tipo;
    private LocalDateTime fecha;
    private String ciudad;
    private List<Localidad> localidades;
}
