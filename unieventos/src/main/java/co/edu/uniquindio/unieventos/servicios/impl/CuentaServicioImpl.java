package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.config.JWTUtils;
import co.edu.uniquindio.unieventos.dto.Cuenta.*;
import co.edu.uniquindio.unieventos.dto.Email.EmailDTO;
import co.edu.uniquindio.unieventos.dto.TokenDTO;
import co.edu.uniquindio.unieventos.modelo.*;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.modelo.enums.Rol;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.repositorios.UsuarioRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.EmailServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class CuentaServicioImpl implements CuentaServicio {

    private final CuentaRepo cuentaRepo;
    private final UsuarioRepo usuarioRepo;
    private final EmailServicio emailServicio;
    private final JWTUtils jwtUtils;

    @Override
    public String crearCuenta(CrearCuentaDTO cuenta) throws Exception {

        if (existeEmail(cuenta.email())) {
            throw new Exception("El correo " + cuenta.email() + " ya está en uso");
        }
        if (existeCedula(cuenta.cedula())) {
            throw new Exception("La cédula " + cuenta.cedula() + " ya se encuentra registrada");
        }
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setEmail(cuenta.email());
        nuevaCuenta.setRol(Rol.CLIENTE);
        nuevaCuenta.setFechaRegistro(LocalDateTime.now());
        nuevaCuenta.setUsuario(new Usuario(
                cuenta.cedula(),
                cuenta.nombre(),
                cuenta.telefono(),
                cuenta.direccion()
        ));
        nuevaCuenta.setEstado(EstadoCuenta.INACTIVO);

        // Generar un código de activación de 6 dígitos
        String codigoActivacion = String.format("%06d", (int) (Math.random() * 1000000));

        // Asignar el código de recuperación a la cuenta y establecer una expiración de 15 minutos
        nuevaCuenta.setCodigoValidacionRegistro(new CodigoValidacion(
                LocalDateTime.now(),
                codigoActivacion
        ));

        //Guardamos la cuenta del usuario en la base de datos
        Cuenta cuentaCreada = cuentaRepo.save(nuevaCuenta);

        // Enviar el correo con el código de activación
        String contenidoCorreo = "¡Bienvenido a Unieventos!\n\n" +
                "Gracias por registrarte. Para activar tu cuenta, por favor utiliza el siguiente código de activación:\n\n" +
                "Código de activación: " + codigoActivacion + "\n\n" +
                "Este código es válido por 15 minutos.\n" +
                "Si no solicitaste esta cuenta, puedes ignorar este mensaje.";

        emailServicio.enviarCorreo(
                new EmailDTO("Activación de cuenta - Unieventos", contenidoCorreo, cuentaCreada.getEmail())
        );

        return cuentaCreada.getId();
    }

    @Override
    public void editarCuenta(EditarCuentaDTO cuenta) throws Exception {

        //Buscamos la cuenta del usuario que se quiere actualizar
        Optional<Cuenta> optionalCuenta = cuentaRepo.findById(cuenta.id());

        //Si no se encontró la cuenta del usuario, lanzamos una excepción
        if (optionalCuenta.isEmpty()) {
            throw new Exception("No se encontró el usuario con el id " + cuenta.id());
        }

        //Obtenemos la cuenta del usuario a modificar y actualizamos sus datos
        Cuenta cuentaModificada = optionalCuenta.get();
        cuentaModificada.getUsuario().setNombre(cuenta.nombre());
        cuentaModificada.getUsuario().setTelefono(cuenta.telefono());
        cuentaModificada.getUsuario().setDireccion(cuenta.direccion());
        cuentaModificada.setPassword(encriptarPassword(cuenta.password()));

        //Como el objeto cuenta ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        cuentaRepo.save(cuentaModificada);
    }

    public void eliminarCuenta(String id) throws Exception {

        //Buscamos la cuenta del usuario que se quiere eliminar
        Optional<Cuenta> optionalCuenta = cuentaRepo.findById(id);

        //Si no se encontró la cuenta, lanzamos una excepción
        if (optionalCuenta.isEmpty()) {
            throw new Exception("No se encontró el usuario con el id " + id);
        } else if (optionalCuenta.get().getEstado().equals(EstadoCuenta.ELIMINADO)) {
            throw new Exception("La cuenta de usuario se encuentra eliminada");
        }

        //Obtenemos la cuenta del usuario que se quiere eliminar y le asignamos el estado eliminado
        Cuenta cuenta = optionalCuenta.get();

        cuenta.setEstado(EstadoCuenta.ELIMINADO);

        //Como el objeto cuenta ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        cuentaRepo.save(cuenta);
    }

    @Override
    public InformacionCuentaDTO obtenerInformacionCuenta(String id) throws Exception {

        //Buscamos la cuenta del usuario que se quiere obtener
        Optional<Cuenta> optionalCuenta = cuentaRepo.findById(id);

        //Si no se encontró la cuenta, lanzamos una excepción
        if (optionalCuenta.isEmpty()) {
            throw new Exception("No se encontró el usuario con el id  " + id);
        }

        //Obtenemos la cuenta del usuario
        Cuenta cuenta = optionalCuenta.get();

        //Si la cuenta esta eliminad, lanzamos una excepcion
        if (cuenta.getEstado().equals(EstadoCuenta.ELIMINADO)) {
            throw new Exception("La cuenta de usuario se encuentra eliminada");
        }

        //Retornamos la información de la cuenta del usuario
        return new InformacionCuentaDTO(
                cuenta.getId(),
                cuenta.getUsuario().getCedula(),
                cuenta.getUsuario().getNombre(),
                cuenta.getUsuario().getTelefono(),
                cuenta.getUsuario().getDireccion(),
                cuenta.getEmail()
        );
    }

    @Override
    public List<ItemCuentaDTO> listarCuentas() {

        //Obtenemos todas las cuentas de los usuarios de la base de datos
        List<Cuenta> cuentas = cuentaRepo.findAll();

        //Creamos una lista de DTOs
        List<ItemCuentaDTO> items = new ArrayList<>();

        //Recorremos la lista de cuentas y por cada uno creamos un DTO y lo agregamos a la lista
        for (Cuenta cuenta : cuentas) {
            items.add(new ItemCuentaDTO(
                    cuenta.getId(),
                    cuenta.getUsuario().getNombre(),
                    cuenta.getEmail(),
                    cuenta.getUsuario().getTelefono()
            ));
        }

        return items;
    }

    @Override
    public void enviarCodigoRecuperacionPassword(String correo) throws Exception {
        // Verificar que el correo exista
        Optional<Cuenta> cuentaOptional = cuentaRepo.findByEmail(correo);

        if (cuentaOptional.isEmpty()) {
            throw new Exception("El correo no está registrado en el sistema.");
        }

        // Generar un código aleatorio de 6 dígitos
        String codigoRecuperacion = String.format("%06d", (int) (Math.random() * 1000000));

        // Obtener la cuenta del usuario
        Cuenta cuentaModificada = cuentaOptional.get();

        // Asignar el código de recuperación a la cuenta y establecer una expiración de 15 minutos
        cuentaModificada.setCodigoValidacionPassword(new CodigoValidacion(
                LocalDateTime.now(),
                codigoRecuperacion
        ));

        // Guardar la cuenta actualizada en la base de datos
        cuentaRepo.save(cuentaModificada);

        // Modificar el contenido del correo para el restablecimiento de contraseña
        String contenido = "Hemos recibido una solicitud para restablecer tu contraseña en Unieventos.\n\n" +
                "Tu código de verificación es: " + codigoRecuperacion + "\n" +
                "Este código es válido por 15 minutos. Si no solicitaste este cambio, puedes ignorar este mensaje.";

        // Enviar el correo
        emailServicio.enviarCorreo(
                new EmailDTO("Restablecimiento de contraseña - Unieventos", contenido, correo)
        );
    }

    @Override
    public void cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception {
        Optional<Cuenta> optionalCuenta = cuentaRepo.findByEmail(cambiarPasswordDTO.email());
        if (optionalCuenta.isEmpty()) {
            throw new Exception("La cuenta no existe");
        }
        Cuenta cuentaModificada = optionalCuenta.get();
        cuentaModificada.setPassword(encriptarPassword(cambiarPasswordDTO.passwordNueva()));

        cuentaRepo.save(cuentaModificada);
    }

    @Override
    public TokenDTO iniciarSesion(LoginDTO loginDTO) throws Exception {
        Optional<Cuenta> optionalCuenta = cuentaRepo.findByEmail(loginDTO.email());
        if (optionalCuenta.isEmpty()) {
            throw new Exception("La cuenta no existe");
        }
        Cuenta cuenta = optionalCuenta.get();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if( !passwordEncoder.matches(loginDTO.password(), cuenta.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }

        Map<String, Object> map = construirClaims(cuenta);
        return new TokenDTO( jwtUtils.generarToken(cuenta.getEmail(), map) );
    }

    private boolean existeEmail(String email) {
        return cuentaRepo.findByEmail(email).isPresent();
    }

    private boolean existeCedula(String cedula) {
        return usuarioRepo.findByCedula(cedula).isPresent();
    }

    private String encriptarPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode( password );
    }

    private Map<String, Object> construirClaims(Cuenta cuenta) {
        return Map.of(
                "rol", cuenta.getRol(),
                "nombre", cuenta.getUsuario().getNombre(),
                "id", cuenta.getId()
        );
    }
}


