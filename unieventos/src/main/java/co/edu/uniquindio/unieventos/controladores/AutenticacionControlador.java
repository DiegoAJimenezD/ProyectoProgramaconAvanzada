package co.edu.uniquindio.unieventos.controladores;

import co.edu.uniquindio.unieventos.dto.Cuenta.CambiarPasswordDTO;
import co.edu.uniquindio.unieventos.dto.Cuenta.CrearCuentaDTO;
import co.edu.uniquindio.unieventos.dto.Cuenta.LoginDTO;
import co.edu.uniquindio.unieventos.dto.MensajeDTO;
import co.edu.uniquindio.unieventos.dto.TokenDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AutenticacionControlador {

    private final CuentaServicio cuentaServicio;

    @PostMapping("/crear-cuenta")
    public ResponseEntity<MensajeDTO<String>> crearCuenta(@Valid @RequestBody CrearCuentaDTO cuenta) throws Exception {
        cuentaServicio.crearCuenta(cuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta creada exitosamente.\nPor favor revise el correo electronico diligenciado en el fomrulatio para activar la cuenta."));
    }

    @PostMapping("/activar-cuenta")
    public ResponseEntity<MensajeDTO<String>> activarCuenta(@Valid @RequestBody CambiarPasswordDTO cambiarPasswordDTO) throws Exception {
        cuentaServicio.cambiarPassword(cambiarPasswordDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta activada exitosamente"));
    }

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesion(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        TokenDTO token = cuentaServicio.iniciarSesion(loginDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, token));
    }

    @PostMapping("/refresh")
    public ResponseEntity<MensajeDTO<TokenDTO>> refreshToken(@RequestParam String token) throws Exception {
        TokenDTO tokenDTO = cuentaServicio.refreshToken(token);
        return ResponseEntity.ok(new MensajeDTO<>(false, tokenDTO));
    }

    @PostMapping("/recuperar-contrasena")
    public ResponseEntity<MensajeDTO<String>> recuperarContrasena(@RequestParam String email) throws Exception {
        cuentaServicio.enviarCodigoRecuperacionPassword(email);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Se ha enviado un código de recuperación a tu correo"));
    }

    @PostMapping("/cambiar-contrasena")
    public ResponseEntity<MensajeDTO<String>> cambiarContrasena(@Valid @RequestBody CambiarPasswordDTO cambiarPasswordDTO) throws Exception {
        cuentaServicio.cambiarPassword(cambiarPasswordDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Contraseña cambiada exitosamente"));
    }


}
    