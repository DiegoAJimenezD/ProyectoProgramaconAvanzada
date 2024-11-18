package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.dto.Cupon.ItemCuponDTO;
import co.edu.uniquindio.unieventos.dto.Email.EmailDTO;
import co.edu.uniquindio.unieventos.dto.Pqr.CambiarEstadoPqrDTO;
import co.edu.uniquindio.unieventos.dto.Pqr.CrearPqrDTO;
import co.edu.uniquindio.unieventos.dto.Pqr.InformacionPqrDTO;
import co.edu.uniquindio.unieventos.dto.Pqr.ResponderPqrDTO;
import co.edu.uniquindio.unieventos.modelo.Cuenta;
import co.edu.uniquindio.unieventos.modelo.Cupon;
import co.edu.uniquindio.unieventos.modelo.Pqr;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoPqr;
import co.edu.uniquindio.unieventos.modelo.enums.TipoPqr;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.repositorios.PqrRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.PqrServicio;
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
public class PqrServicioImpl implements PqrServicio {

    private final PqrRepo pqrRepo;
    private final CuentaRepo cuentaRepo;
    private final EmailServicio emailServicio;


    @Override
    public String crearPqr(CrearPqrDTO pqrDTO) throws Exception {
        Pqr nuevaPqr = new Pqr();
        nuevaPqr.setTipo(TipoPqr.valueOf(pqrDTO.tipo()));
        nuevaPqr.setDescripcion(pqrDTO.descripcion());
        nuevaPqr.setEstado(EstadoPqr.EN_PROCESO);
        nuevaPqr.setFechaCreacion(LocalDateTime.now());
        nuevaPqr.setIdCuenta(pqrDTO.idCuenta());

        // Guardamos el PQRS en la base de datos
        Pqr pqrCreada = pqrRepo.save(nuevaPqr);

        // Modificar el contenido del correo para el restablecimiento de contraseña
        String contenido = "Nuestro equipo está revisando su solicitud y se pondrá en contacto con usted a la brevedad para brindarle una respuesta.\n\n "
                + "Si tiene alguna pregunta o necesita asistencia adicional, no dude en responder a este correo electrónico.";

        Optional<Cuenta> optionalCuenta = cuentaRepo.findById(pqrCreada.getIdCuenta());

        //Si no se encontró la cuenta del usuario, lanzamos una excepción
        if (optionalCuenta.isEmpty()) {
            throw new Exception("No se encontró el usuario con el id " + pqrCreada.getIdCuenta());
        }

        //Obtenemos la cuenta del usuario a modificar y actualizamos sus datos
        Cuenta cuenta = optionalCuenta.get();

        // Enviar el correo
        emailServicio.enviarCorreo(
                new EmailDTO("PQR - Unieventos", contenido, cuenta.getEmail())
        );

        return pqrCreada.getId();
    }

    @Override
    public void responderPqr(ResponderPqrDTO responderPqrDTO) throws Exception {
        Optional<Pqr> optionalPqr = pqrRepo.findById(responderPqrDTO.id());

        if (optionalPqr.isPresent()) {
            Pqr pqr = optionalPqr.get();
            pqr.setRespuesta(responderPqrDTO.respuesta());
            pqr.setFechaRespuesta(LocalDateTime.now());

            // Guardar la actualización del PQRS
            pqrRepo.save(pqr);
        } else {
            throw new Exception("PQR no encontrada");
        }
    }

    @Override
    public void cambiarEstadoPqr(CambiarEstadoPqrDTO estadoPqrDTO) throws Exception {
        Optional<Pqr> optionalPqr = pqrRepo.findById(estadoPqrDTO.id());

        if (optionalPqr.isPresent()) {
            Pqr pqr = optionalPqr.get();
            pqr.setEstado(EstadoPqr.valueOf(estadoPqrDTO.nuevoEstado()));

            // Guardar la actualización del estado
            pqrRepo.save(pqr);
        } else {
            throw new Exception("PQR no encontrada");
        }
    }

    @Override
    public InformacionPqrDTO obtenerInformacionPqr(String idPqr) throws Exception {
        Optional<Pqr> optionalPqr = pqrRepo.findById(idPqr);

        if (optionalPqr.isPresent()) {
            Pqr pqr = optionalPqr.get();

            // Retornamos la información del PQRS
            return new InformacionPqrDTO(
                    pqr.getId(),
                    pqr.getTipo().toString(),
                    pqr.getDescripcion(),
                    pqr.getEstado().toString(),
                    pqr.getFechaCreacion(),
                    pqr.getFechaRespuesta(),
                    pqr.getRespuesta(),
                    pqr.getIdCuenta()
            );
        } else {
            throw new Exception("PQR no encontrada");
        }
    }

    @Override
    public void eliminarPqr(String idPqr) throws Exception {
        Optional<Pqr> optionalPqr = pqrRepo.findById(idPqr);

        if (optionalPqr.isPresent()) {
            pqrRepo.delete(optionalPqr.get());
        } else {
            throw new Exception("PQR no encontrada");
        }
    }

    @Override
    public List<InformacionPqrDTO> listarPqr() {
        List<Pqr> pqr = pqrRepo.findAll();

        //Creamos una lista de DTOs
        List<InformacionPqrDTO> items = new ArrayList<>();

        //Recorremos la lista de cuentas y por cada uno creamos un DTO y lo agregamos a la lista
        for (Pqr pqr1 : pqr) {
            items.add(new InformacionPqrDTO(
                    pqr1.getId(),
                    pqr1.getTipo().toString(),
                    pqr1.getDescripcion(),
                    pqr1.getEstado().toString(),
                    pqr1.getFechaCreacion(),
                    pqr1.getFechaRespuesta(),
                    pqr1.getRespuesta(),
                    pqr1.getIdCuenta()
            ));
        }
        return items;
    }
}
