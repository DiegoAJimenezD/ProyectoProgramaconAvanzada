package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.dto.Cupon.*;
import co.edu.uniquindio.unieventos.modelo.Cupon;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.modelo.enums.TipoCupon;
import co.edu.uniquindio.unieventos.repositorios.CuponRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CuponServicioImpl implements CuponServicio {

    private final CuponRepo cuponRepo;

    @Override
    public String crearCupon(CrearCuponDTO crearCuponDTO) throws Exception {
        Cupon nuevoCupon = new Cupon();
        nuevoCupon.setCodigo(crearCuponDTO.codigo());
        nuevoCupon.setFechaVencimiento(crearCuponDTO.fechaVencimiento());
        nuevoCupon.setDescuento(crearCuponDTO.descuento());
        nuevoCupon.setNombre(crearCuponDTO.nombre());
        nuevoCupon.setEstado(EstadoCupon.valueOf(crearCuponDTO.estadoCupon()));
        nuevoCupon.setTipo(TipoCupon.valueOf(crearCuponDTO.tipoCupon()));

        // Guardamos el nuevo cupón en la base de datos
        Cupon cuponGuardado = cuponRepo.save(nuevoCupon);
        return cuponGuardado.getId();
    }

    @Override
    public void editarCupon(EditarCuponDTO editarCuponDTO) throws Exception {
        Optional<Cupon> optionalCupon = cuponRepo.findById(editarCuponDTO.id());

        if (optionalCupon.isPresent()) {
            Cupon cuponModificado = optionalCupon.get();
            cuponModificado.setCodigo(editarCuponDTO.codigo());
            cuponModificado.setFechaVencimiento(editarCuponDTO.fechaVencimiento());
            cuponModificado.setDescuento(editarCuponDTO.descuento());
            cuponModificado.setNombre(editarCuponDTO.nombre());
            cuponModificado.setEstado(EstadoCupon.valueOf(editarCuponDTO.estadoCupon()));
            cuponModificado.setTipo(TipoCupon.valueOf(editarCuponDTO.tipoCupon()));

            // Guardamos los cambios en el cupón
            cuponRepo.save(cuponModificado);
        } else {
            throw new Exception("Cupon no encontrado");
        }
    }

    @Override
    public void eliminarCupon(String idCupon) throws Exception {

        //Buscamos el cupon que se quiere eliminar
        Optional<Cupon> optionalCupon = cuponRepo.findById(idCupon);

        //Si no se encontró la cuenta, lanzamos una excepción
        if (optionalCupon.isEmpty()) {
            throw new Exception("No se encontró el cupon con el id " + idCupon);
        }

        //Obtenemos la cuenta del usuario que se quiere eliminar y le asignamos el estado eliminado
        Cupon cuponModificado = optionalCupon.get();

        if (cuponModificado.getEstado() == EstadoCupon.NO_DISPONIBLE) {
            throw new Exception("No se encontró el cupon con el id " + idCupon);
        }

        cuponModificado.setEstado(EstadoCupon.NO_DISPONIBLE);

        //Como el objeto cuenta ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        cuponRepo.save(cuponModificado);
    }

    @Override
    public InformacionCuponDTO obtenerInformacionCupon(String idCupon) throws Exception {
        Optional<Cupon> optionalCupon = cuponRepo.findById(idCupon);

        if (optionalCupon.isPresent()) {
            Cupon cupon = optionalCupon.get();
            return new InformacionCuponDTO(
                    cupon.getId(),
                    cupon.getDescuento(),
                    cupon.getFechaVencimiento(),
                    cupon.getCodigo(),
                    cupon.getEstado().toString(),
                    cupon.getTipo().toString(),
                    cupon.getNombre()
            );
        } else {
            throw new Exception("Cupon no encontrado");
        }
    }

    @Override
    public List<ItemCuponDTO> listarCupones() {
        List<Cupon> cupones = cuponRepo.findAll();

        //Creamos una lista de DTOs
        List<ItemCuponDTO> items = new ArrayList<>();

        //Recorremos la lista de cuentas y por cada uno creamos un DTO y lo agregamos a la lista
        for (Cupon cupon : cupones) {
            items.add(new ItemCuponDTO(
                    cupon.getDescuento(),
                    cupon.getFechaVencimiento(),
                    cupon.getCodigo(),
                    cupon.getEstado().toString(),
                    cupon.getTipo().toString(),
                    cupon.getNombre()
            ));
        }
        return items;
    }

    @Override
    public void aplicarCupon(String idCupon, String idCarrito) throws Exception {
//        Optional<Cupon> cuponOpt = cuponRepo.findById(idCupon);
//        Optional<Carrito> carritoOpt = carritoRepo.findById(idCarrito);
//
//        if (cuponOpt.isPresent() && carritoOpt.isPresent()) {
//            Cupon cupon = cuponOpt.get();
//            Carrito carrito = carritoOpt.get();
//
//            // Asumimos que aplicamos un descuento porcentual sobre el total del carrito
//            double descuento = cupon.getDescuento();
//            double totalOriginal = carrito.getTotal();
//            double totalConDescuento = totalOriginal - (totalOriginal * (descuento / 100));
//
//            carrito.setTotal(totalConDescuento);
//            carritoRepo.save(carrito);
//        } else {
//            throw new Exception("Cupon o Carrito no encontrado");
//        }
    }

    @Override
    public List<ItemCuponDTO> filtrarCupones(FiltroCuponDTO filtroCuponDTO) {
        List<Cupon> cupones = cuponRepo.findByCodigoOrNombre(filtroCuponDTO.codigo(), filtroCuponDTO.nombre());

        //Creamos una lista de DTOs
        List<ItemCuponDTO> items = new ArrayList<>();

        //Recorremos la lista de cuentas y por cada uno creamos un DTO y lo agregamos a la lista
        for (Cupon cupon : cupones) {
            items.add(new ItemCuponDTO(
                    cupon.getDescuento(),
                    cupon.getFechaVencimiento(),
                    cupon.getCodigo(),
                    cupon.getEstado().toString(),
                    cupon.getTipo().toString(),
                    cupon.getNombre()
            ));
        }
        return items;
    }
}


