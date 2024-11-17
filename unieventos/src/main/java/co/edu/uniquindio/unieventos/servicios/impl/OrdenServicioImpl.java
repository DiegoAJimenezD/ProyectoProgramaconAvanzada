package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.dto.Orden.*;
import co.edu.uniquindio.unieventos.modelo.*;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoOrden;
import co.edu.uniquindio.unieventos.repositorios.EventoRepo;
import co.edu.uniquindio.unieventos.repositorios.OrdenRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.OrdenServicio;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdenServicioImpl implements OrdenServicio {

    private final OrdenRepo ordenRepo;
    private final EventoRepo eventoRepo;
    private final EventoServicio eventoServicio;
    private final String servidor = "https://df7b-189-50-209-152.ngrok-free.app";

    @Override
    public String crearOrden(CrearOrdenDTO crearOrdenDTO) throws Exception {
        Optional<Evento> eventoOptional;

        List<DetalleOrden> items = crearOrdenDTO.items();
        for (DetalleOrden item : items) {
            eventoOptional = eventoRepo.findById(item.getIdEvento());

            if (eventoOptional.isEmpty()) {
                throw new Exception("El evento no existe.");
            }

            boolean existeLocalidad = true;
            Evento eventoModificado = eventoOptional.get();
            List<Localidad> localidades = eventoModificado.getLocalidades();
            for (Localidad localidad : localidades) {
                if (localidad.getNombre().equals(item.getNombreLocalidad())) {
                    existeLocalidad = false;
                    if (item.getCantidad() <= (localidad.getCapacidadMaxima() - localidad.getEntradasVendidas())) {
                        localidad.setEntradasVendidas(localidad.getEntradasVendidas() + item.getCantidad());
                        eventoModificado.setLocalidades(localidades);
                    } else {
                        throw new Exception("La localidad " + localidad.getNombre() + " tiene disponibilidad de "
                                + (localidad.getCapacidadMaxima() - localidad.getEntradasVendidas()) + " entradas y las entradas " +
                                "a comprar son "
                                + item.getCantidad());
                    }
                }
            }
            if (existeLocalidad) {
                throw new Exception("La localidad no existe.");
            }
            eventoRepo.save(eventoModificado);
        }
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
    public DetalleOrdenDTO obtenerInformacionOrden(String idOrden) throws Exception {
        Optional<Orden> optionalOrden = ordenRepo.findById(idOrden);

        if (optionalOrden.isPresent()) {
            Orden orden = optionalOrden.get();
            // Convertir la información de la orden a un DTO
            List<DetalleItemOrdenDTO> listaItems = new ArrayList<>();
            for (DetalleOrden item : orden.getItems()) {
                listaItems.add(new DetalleItemOrdenDTO(
                        eventoRepo.findById(item.getIdEvento()).get(),
                        item.getNombreLocalidad(),
                        item.getPrecio(),
                        item.getCantidad()
                ));
            }
            return new DetalleOrdenDTO(
                    orden.getId(),
                    orden.getEstado().toString(),
                    orden.getIdCliente(),
                    orden.getIdCupon(),
                    orden.getFecha(),
                    orden.getCodigoPasarela(),
                    listaItems,
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

            Optional<Evento> eventoOptional;

            List<DetalleOrden> items = orden.getItems();
            for (DetalleOrden item : items) {
                eventoOptional = eventoRepo.findById(item.getIdEvento());

                if (eventoOptional.isEmpty()) {
                    throw new Exception("El evento no existe.");
                }
                boolean existeLocalidad = true;
                Evento eventoModificado = eventoOptional.get();
                List<Localidad> localidades = eventoModificado.getLocalidades();
                for (Localidad localidad : localidades) {
                    if (localidad.getNombre().equals(item.getNombreLocalidad())) {
                        existeLocalidad = false;
                        localidad.setEntradasVendidas(localidad.getEntradasVendidas() - item.getCantidad());
                        eventoModificado.setLocalidades(localidades);
                    }
                }
                if (existeLocalidad) {
                    throw new Exception("La localidad no existe.");
                }
                eventoRepo.save(eventoModificado);
            }


            ordenRepo.save(orden);
        } else {
            throw new Exception("Orden no encontrada");
        }
    }

    @Override
    public Preference realizarPago(String idOrden) throws Exception {

        Optional<Orden> ordenOptional = ordenRepo.findById(idOrden);
        List<PreferenceItemRequest> itemsPasarela = new ArrayList<>();

        if (ordenOptional.isEmpty()) {
            throw new Exception("La orden no existe.");
        }

        // Obtener la cuenta del usuario
        Orden ordenGuardada = ordenOptional.get();

        List<DetalleOrden> items = ordenGuardada.getItems();

        if (items == null) {
            throw new Exception(ordenGuardada.toString());
        }

        // Recorrer los items de la orden y crea los ítems de la pasarela
        for (DetalleOrden item : items) {
            // Obtener el evento y la localidad del ítem
            Optional<Evento> eventoOptional = eventoRepo.findById(item.getIdEvento());
            if (eventoOptional.isEmpty()) {
                throw new Exception("El evento no existe.");
            }

            Evento eventoGuardado = eventoOptional.get();
            //Localidad localidad = eventoGuardado.getLocalidades().get(item.getNombreLocalidad());
            Localidad localidad = eventoGuardado.getLocalidades().get(0);

            // Crear el item de la pasarela
            PreferenceItemRequest itemRequest =
                    PreferenceItemRequest.builder()
                            .id(eventoGuardado.getId())
                            .title(eventoGuardado.getNombre())
                            //   .pictureUrl(eventoGuardado.getImagenPortada())
                            .pictureUrl("https://www.mercadopago.com/org-img/MP3/home/logomp3.gif")
                            .categoryId(eventoGuardado.getTipo().name())
                            .quantity(item.getCantidad())
                            .currencyId("COP")
                            .unitPrice(BigDecimal.valueOf(localidad.getPrecio()))
                            .build();

            itemsPasarela.add(itemRequest);
        }
        // Configurar las credenciales de MercadoPago
        MercadoPagoConfig.setAccessToken("APP_USR-6852244274066081-102317-38f02c71de3f1dbf1913f9a6695c069e-1136948209");

        if (MercadoPagoConfig.getAccessToken() == null) {
            throw new Exception("Acces token invalido");
        }

        // Configurar las urls de retorno de la pasarela (Frontend)
        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success(servidor + "/api/cliente/orden/success")
                .failure(servidor + "/api/cliente/orden/failure")
                .pending(servidor + "/api/cliente/orden/pending")
                .build();

        if (ordenGuardada.getId() == null) {
            throw new Exception("El código de la orden es nulo.");
        }

        // Construir la preferencia de la pasarela con los ítems, metadatos y urls de retorno
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .backUrls(backUrls)
                .items(itemsPasarela)
                .metadata(Map.of("id_orden", ordenGuardada.getId()))
                .notificationUrl(servidor + "/api/cliente/orden/notificacion-pago")
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

        // Obtener el tipo de notificación
        Object tipo = request.get("type");

        // Si la notificación es de un pago entonces obtener el pago y la orden asociada
        if ("payment".equals(tipo)) {

            // Capturamos el JSON que viene en el request y lo convertimos a un String
            String input = request.get("data").toString();

            // Extraemos los números de la cadena, es decir, el id del pago
            String idPago = input.replaceAll("\\D+", "");

            // Se crea el cliente de MercadoPago y se obtiene el pago con el id
            PaymentClient client = new PaymentClient();
            Payment payment = null;
            try {
                payment = client.get(Long.parseLong(idPago));
            } catch (MPException e) {
                throw new RuntimeException(e);
            } catch (MPApiException e) {
                throw new RuntimeException(e);
            }

            // Obtener el id de la orden asociada al pago que viene en los metadatos
            String idOrden = payment.getMetadata().get("id_orden").toString();

            // Se obtiene la orden guardada en la base de datos y se le asigna el pago
            Orden orden = ordenRepo.findById(idOrden).get();
            Pago pago = crearPago(payment);
            orden.setPago(pago);
            ordenRepo.save(orden);
        }

    }

    private Pago crearPago(Payment payment) {
        Pago pago = new Pago();
        pago.setCodigo(payment.getId().toString());
        pago.setFecha(payment.getDateCreated().toLocalDateTime());
        pago.setEstado(payment.getStatus());
        pago.setDetalleEstado(payment.getStatusDetail());
        pago.setTipoPago(payment.getPaymentTypeId());
        pago.setMoneda(payment.getCurrencyId());
        pago.setCodigoAutorizacion(payment.getAuthorizationCode());
        pago.setValorTransaccion(payment.getTransactionAmount().floatValue());
        return pago;
    }

    @Override
    public List<InformacionOrdenDTO> listarOrdenesPorCliente(String idCliente) throws Exception {
        // Buscar todas las órdenes del cliente
        List<Orden> ordenes = ordenRepo.findByIdCliente(idCliente);

        // Verificar si se encontraron órdenes
        if (ordenes.isEmpty()) {
            throw new Exception("No se encontraron órdenes para el cliente con ID: " + idCliente);
        }

        // Convertir cada orden a un DTO
        List<InformacionOrdenDTO> ordenesDTO = new ArrayList<>();
        for (Orden orden : ordenes) {
            ordenesDTO.add(new InformacionOrdenDTO(
                    orden.getId(),
                    orden.getEstado().toString(),
                    orden.getIdCliente(),
                    orden.getIdCupon(),
                    orden.getFecha(),
                    orden.getCodigoPasarela(),
                    orden.getItems(),
                    orden.getTotal()
            ));
        }

        return ordenesDTO;
    }

}
