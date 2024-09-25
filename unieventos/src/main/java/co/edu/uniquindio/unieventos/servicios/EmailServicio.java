package co.edu.uniquindio.unieventos.servicios;

import co.edu.uniquindio.unieventos.dto.Email.EmailDTO;

public interface EmailServicio {
    void enviarCorreo (EmailDTO emailDTO) throws  Exception;
}
