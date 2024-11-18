package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.dto.Orden.InformacionOrdenDTO;
import co.edu.uniquindio.unieventos.modelo.DetalleOrden;
import co.edu.uniquindio.unieventos.modelo.Orden;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.repositorios.EventoRepo;
import co.edu.uniquindio.unieventos.repositorios.OrdenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final OrdenRepo orderRepository;
    private final EventoRepo eventRepository;
    private final CuentaRepo accountRepository;

    public List<InformacionOrdenDTO> generateSalesReportByLocation() {
        // Obtener todas las órdenes
        List<Orden> ordenes = orderRepository.findAll();

        // Agrupar ventas por localidad y sumar las ganancias
        Map<String, Double> salesByLocation = new HashMap<>();
        for (Orden orden : ordenes) {
            for (DetalleOrden detalleOrden : orden.getItems()) {
                String location = String.valueOf(detalleOrden.getNombreLocalidad());
                double totalSales = detalleOrden.getPrecio() * detalleOrden.getCantidad();
                salesByLocation.put(location, salesByLocation.getOrDefault(location, 0.0) + totalSales);
            }
        }

        // Convertir la información en un DTO para retornar
        /**List<InformacionOrdenDTO> reportData = new ArrayList<>();
        salesByLocation.forEach((localidad, precio) -> {
            reportData.add(new InformacionOrdenDTO(localidad, precio.toString()));
        });**/

        return null;
    }

    /** Mètodo para generar el PDF
     *
     * @return
     */

}