package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.dto.Cuenta.*;
import co.edu.uniquindio.unieventos.modelo.Cuenta;
import co.edu.uniquindio.unieventos.modelo.EstadoCuenta;
import co.edu.uniquindio.unieventos.modelo.Rol;
import co.edu.uniquindio.unieventos.modelo.Usuario;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class CuentaServicioImpl implements CuentaServicio {

    private final CuentaRepo cuentaRepo;

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
        nuevaCuenta.setPassword(cuenta.password());
        nuevaCuenta.setRol(Rol.CLIENTE);
        nuevaCuenta.setFechaRegistro(LocalDateTime.now());
        nuevaCuenta.setUsuario(new Usuario(
                cuenta.cedula(),
                cuenta.nombre(),
                cuenta.telefono(),
                cuenta.direccion()
        ));
        nuevaCuenta.setEstado(EstadoCuenta.INACTIVO);


        //Guardamos la cuenta del usuario en la base de datos
        Cuenta cuentaCreada = cuentaRepo.save(nuevaCuenta);
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
        cuentaModificada.setPassword(cuenta.password());

        //Como el objeto cuenta ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        cuentaRepo.save(cuentaModificada);
    }

    public void eliminarCuenta(String id) throws Exception {

        //Buscamos la cuenta del usuario que se quiere eliminar
        Optional<Cuenta> optionalCuenta = cuentaRepo.findById(id);

        //Si no se encontró la cuenta, lanzamos una excepción
        if (optionalCuenta.isEmpty()) {
            throw new Exception("No se encontró el usuario con el id " + id);
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

    }

    @Override
    public void cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception {

    }

    @Override
    public String iniciarSesion(LoginDTO loginDTO) throws Exception {
        return "";
    }


    private boolean existeEmail(String email) {
        return cuentaRepo.findByEmail(email).isPresent();
    }

    private boolean existeCedula(String cedula) {
        return cuentaRepo.findByCedula(cedula).isPresent();
    }

}


