package co.edu.uniquindio.unieventos.modelo;

import co.edu.uniquindio.unieventos.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.modelo.enums.Rol;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("cuentas")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cuenta {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private Usuario usuario;
    private String email;
    private EstadoCuenta estado;
    private Rol rol;
    private String password;
    private CodigoValidacion codigoValidacionRegistro;
    private CodigoValidacion codigoValidacionPassword;
    private LocalDateTime fechaRegistro;


}
