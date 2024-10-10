package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.dto.Orden.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.dto.Orden.EditarOrdenDTO;
import co.edu.uniquindio.unieventos.dto.Orden.InformacionOrdenDTO;
import co.edu.uniquindio.unieventos.modelo.EstadoOrden;
import co.edu.uniquindio.unieventos.modelo.Orden;
import co.edu.uniquindio.unieventos.repositorios.OrdenRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.OrdenServicio;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdenServicioImpl implements OrdenServicio {

    private final OrdenRepo ordenRepo;

    @Override
    public Preference realizarPago(String idOrden) throws Exception {

        // Obtener la orden guardada en la base de datos y los ítems de la orden
        Orden ordenGuardada = obtenerOrden(idOrden);
        List<PreferenceItemRequest> itemsPasarela = new ArrayList<>();

        // Recorrer los items de la orden y crea los ítems de la pasarela
        for (DetalleOrden item : ordenGuardada.getDetalle()) {

            // Obtener el evento y la localidad del ítem
            Evento evento = eventoServicio.obtenerEvento(item.getCodigoEvento().toString());
            Localidad localidad = evento.obtenerLocalidad(item.getNombreLocalidad());

            // Crear el item de la pasarela
            PreferenceItemRequest itemRequest =
                    PreferenceItemRequest.builder()
                            .id(evento.getCodigo())
                            .title(evento.getNombre())
                            .pictureUrl(evento.getImagenPortada())
                            .categoryId(evento.getTipo().name())
                            .quantity(item.getCantidad())
                            .currencyId("COP")
                            .unitPrice(BigDecimal.valueOf(localidad.getPrecio()))
                            .build();

            itemsPasarela.add(itemRequest);
        }

        // Configurar las credenciales de MercadoPago
        MercadoPagoConfig.setAccessToken("ACCESS_TOKEN");

        // Configurar las urls de retorno de la pasarela (Frontend)
        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("URL PAGO EXITOSO")
                .failure("URL PAGO FALLIDO")
                .pending("URL PAGO PENDIENTE")
                .build();

        // Construir la preferencia de la pasarela con los ítems, metadatos y urls de retorno
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .backUrls(backUrls)
                .items(itemsPasarela)
                .metadata(Map.of("id_orden", ordenGuardada.getCodigo()))
                .notificationUrl("URL NOTIFICACION")
                .build();

        // Crear la preferencia en la pasarela de MercadoPago
        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);

        // Guardar el código de la pasarela en la orden
        ordenGuardada.setCodigoPasarela(preference.getId());
        ordenRepo.save(ordenGuardada);

        return preference;
    }

    @Override
    public void recibirNotificacionMercadoPago(Map<String, Object> request) {

    }

    @Override
    public String crearOrden(CrearOrdenDTO crearOrdenDTO) throws Exception {
        // Crear una nueva orden con los datos del DTO
        Orden nuevaOrden = new Orden();
        nuevaOrden.setEstado(EstadoOrden.ACTIVO);
        nuevaOrden.setIdCliente(crearOrdenDTO.idCliente());
        nuevaOrden.setIdCupon(crearOrdenDTO.idCupon());
        nuevaOrden.setFecha(crearOrdenDTO.fecha());
        nuevaOrden.setCodigoPasarela(crearOrdenDTO.codigoPasarela());
        nuevaOrden.setItems(crearOrdenDTO.items());
        nuevaOrden.setTotal(crearOrdenDTO.total());

        Orden ordenGuardada = ordenRepo.save(nuevaOrden);
        return ordenGuardada.getId();
    }

    @Override
    public InformacionOrdenDTO obtenerInformacionOrden(String idOrden) throws Exception {
        Optional<Orden> optionalOrden = ordenRepo.findById(idOrden);

        if (optionalOrden.isPresent()) {
            Orden orden = optionalOrden.get();
            // Convertir la información de la orden a un DTO
            return new InformacionOrdenDTO(
                    orden.getId(),
                    orden.getEstado().toString(),
                    orden.getIdCliente(),
                    orden.getIdCupon(),
                    orden.getFecha(),
                    orden.getCodigoPasarela(),
                    orden.getItems(),
                    orden.getTotal()
            );
        } else {
            throw new Exception("Orden no encontrada");
        }
    }

    @Override
    public void editarOrden(EditarOrdenDTO editarOrdenDTO) throws Exception {
        Optional<Orden> optionalOrden = ordenRepo.findById(editarOrdenDTO.id());

        if (optionalOrden.isPresent()) {
            Orden ordenModificada = optionalOrden.get();
            ordenModificada.setCodigoPasarela(editarOrdenDTO.codigoPasarela());
            ordenModificada.setEstado(EstadoOrden.valueOf(editarOrdenDTO.estadoOrden()));
            // Pendiente pago

            ordenRepo.save(ordenModificada);
        } else {
            throw new Exception("Orden no encontrada");
        }
    }

    @Override
    public void cancelarOrden(String idOrden) throws Exception {
        Optional<Orden> optionalOrden = ordenRepo.findById(idOrden);

        if (optionalOrden.isPresent()) {
            Orden orden = optionalOrden.get();
            orden.setEstado(EstadoOrden.CANCELADO);
            ordenRepo.save(orden);
        } else {
            throw new Exception("Orden no encontrada");
        }
    }
}
