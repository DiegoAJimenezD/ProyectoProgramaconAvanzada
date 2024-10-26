package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.dto.Carrito.*;
import co.edu.uniquindio.unieventos.modelo.Carrito;
import co.edu.uniquindio.unieventos.modelo.DetalleCarrito;
import co.edu.uniquindio.unieventos.repositorios.CarritoRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CarritoServicio;
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
public class CarritoServicioImpl implements CarritoServicio {

    private final CarritoRepo carritoRepo;

    @Override
    public String crearCarrito(CrearCarritoDTO carritoDTO) throws Exception {
        Carrito nuevoCarrito = new Carrito();
        nuevoCarrito.setIdCuenta(carritoDTO.idUsuario());
        nuevoCarrito.setFecha(LocalDateTime.now());

        //Guardamos el carrito del usuario en la base de datos
        Carrito carritoCredo = carritoRepo.save(nuevoCarrito);
        return carritoCredo.getId();
    }

    @Override
    public void agregarItemCarrito(AgregarItemCarritoDTO itemCarritoDTO) throws Exception {
        Optional<Carrito> optionalCarrito = carritoRepo.findByIdCuenta(itemCarritoDTO.id());

        if (optionalCarrito.isPresent()) {
            Carrito carritoModificado = optionalCarrito.get();
            if (existeElemento(itemCarritoDTO.id(), itemCarritoDTO.idEvento(), itemCarritoDTO.localidad())) {
                throw new Exception("El item ya existe");
            }
            DetalleCarrito nuevoItem = new DetalleCarrito();
            nuevoItem.setIdEvento(itemCarritoDTO.idEvento());
            nuevoItem.setCantidad(itemCarritoDTO.cantidad());
            nuevoItem.setNombreLocalidad(itemCarritoDTO.localidad());

            List<DetalleCarrito> items = new ArrayList<>();
            if (carritoModificado.getItems() == null) {
                items.add(nuevoItem);
            } else {
                items = carritoModificado.getItems();
                items.add(nuevoItem);
            }
            carritoModificado.setItems(items);
            carritoRepo.save(carritoModificado);
        } else {
            CrearCarritoDTO crearCarritoDTO = new CrearCarritoDTO(itemCarritoDTO.id());
            crearCarrito(crearCarritoDTO);
            agregarItemCarrito(itemCarritoDTO);
        }
    }

    @Override
    public void editarItemCarrito(EditarItemCarritoDTO itemCarritoDTO) throws Exception {

        Optional<Carrito> optionalCarrito = carritoRepo.findByIdCuenta(itemCarritoDTO.idCuenta());

        if (optionalCarrito.isPresent()) {
            Carrito carritoModificado = optionalCarrito.get();

            List<DetalleCarrito> items = carritoModificado.getItems();
            for (DetalleCarrito item : items) {
                if (item.getIdEvento().equals(itemCarritoDTO.idEvento()) && item.getNombreLocalidad().equals(itemCarritoDTO.localidad())) {
                    item.setCantidad(itemCarritoDTO.cantidad());
                    item.setNombreLocalidad(itemCarritoDTO.localidad());

                    // Guardamos los cambios en el carrito
                    carritoModificado.setItems(items);
                    carritoRepo.save(carritoModificado);
                    return; // Sale del bucle una vez que se ha encontrado y modificado el item
                }
            }
            throw new Exception("Item no encontrado");

        } else {
            throw new Exception("Carrito no encontrado");
        }
    }

    @Override
    public void eliminarItemCarrito(EliminarItemCarritoDTO itemCarritoDTO) throws Exception {

        Optional<Carrito> optionalCarrito = carritoRepo.findByIdCuenta(itemCarritoDTO.idCuenta());

        if (optionalCarrito.isPresent()) {
            Carrito carritoModificado = optionalCarrito.get();
            List<DetalleCarrito> items = carritoModificado.getItems();

            for (DetalleCarrito item : items) {
                if (item.getIdEvento().equals(itemCarritoDTO.idEvento()) && item.getNombreLocalidad().equals(itemCarritoDTO.localidad())) {
                    items.remove(item);
                    // Guardamos los cambios en el carrito
                    carritoModificado.setItems(items);
                    carritoRepo.save(carritoModificado);
                    return; // Sale del bucle una vez que se ha encontrado y modificado el item
                }
            }

            throw new Exception("Item no encontrado");

        } else {
            throw new Exception("Carrito no encontrado");
        }
    }

    @Override
    public void vaciarCarrito(String idCarrito) throws Exception {

        Optional<Carrito> optionalCarrito = carritoRepo.findByIdCuenta(idCarrito);

        if (optionalCarrito.isPresent()) {
            Carrito carritoModificado = optionalCarrito.get();
            carritoModificado.setItems(new ArrayList<>());
            carritoRepo.save(carritoModificado);
        } else {
            throw new Exception("Carrito no encontrado");
        }
    }

    @Override
    public InformacionCarritoDTO obtenerInformacionCarrito(String idCuenta) throws Exception {
        Optional<Carrito> optionalCarrito = carritoRepo.findByIdCuenta(idCuenta);

        if (optionalCarrito.isPresent()) {
            Carrito carrito = optionalCarrito.get();
            //Retornamos la información de lel carrito del usuario
            return new InformacionCarritoDTO(
                    carrito.getFecha(),
                    carrito.getIdCuenta(),
                    carrito.getItems()
            );
        } else {
            throw new Exception("Carrito no encontrado");
        }

    }

    private boolean existeElemento(String idUsuario, String idEvento, String localidad) {
        return carritoRepo.findByIdCuentaAndIdEventoAndLocalidad(idUsuario, idEvento, localidad).isPresent();
    }
}


