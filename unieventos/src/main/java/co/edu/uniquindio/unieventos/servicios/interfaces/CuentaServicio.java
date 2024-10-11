package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.Cuenta.*;
import co.edu.uniquindio.unieventos.dto.TokenDTO;

import java.util.List;

public interface CuentaServicio {

    String crearCuenta(CrearCuentaDTO cuenta) throws Exception;

    void editarCuenta(EditarCuentaDTO cuenta) throws Exception;

    void eliminarCuenta(String id) throws Exception;

    InformacionCuentaDTO obtenerInformacionCuenta(String id) throws Exception;

    List<ItemCuentaDTO> listarCuentas();

    void enviarCodigoRecuperacionPassword(String correo) throws Exception;

    void cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception;

    TokenDTO iniciarSesion(LoginDTO loginDTO) throws Exception;

}