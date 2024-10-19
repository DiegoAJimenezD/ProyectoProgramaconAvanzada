package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.dto.Carrito.AgregarItemCarritoDTO;
import co.edu.uniquindio.unieventos.dto.Carrito.CrearCarritoDTO;
import co.edu.uniquindio.unieventos.dto.Carrito.EditarItemCarritoDTO;
import co.edu.uniquindio.unieventos.dto.Carrito.InformacionCarritoDTO;
import co.edu.uniquindio.unieventos.modelo.Carrito;
import co.edu.uniquindio.unieventos.modelo.DetalleCarrito;
import co.edu.uniquindio.unieventos.repositorios.CarritoRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CarritoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
            DetalleCarrito nuevoItem = new DetalleCarrito();
            nuevoItem.setIdEvento(itemCarritoDTO.idEvento());
            nuevoItem.setCantidad(itemCarritoDTO.cantidad());
            nuevoItem.setNombreLocalidad(itemCarritoDTO.localidad());

            List<DetalleCarrito> items = carritoModificado.getItems();
            items.add(nuevoItem);
            carritoModificado.setItems(items);

            carritoRepo.save(carritoModificado);
        } else {
            if (optionalCarrito.isEmpty()){
                throw new Exception("Ningún item seleccionado");
            }
            CrearCarritoDTO crearCarritoDTO = new CrearCarritoDTO(itemCarritoDTO.id());
            crearCarrito(crearCarritoDTO);
            agregarItemCarrito(itemCarritoDTO);
        }
    }

    @Override
    public void editarItemCarrito(EditarItemCarritoDTO itemCarritoDTO) throws Exception {

        Optional<Carrito> optionalCarrito = carritoRepo.findById(itemCarritoDTO.idCarrito());

        if (optionalCarrito.isPresent()) {
            Carrito carritoModificado = optionalCarrito.get();
            DetalleCarrito itemModificado = carritoModificado.getItems().get(0);
            itemModificado.setIdEvento(itemCarritoDTO.idEvento());
            itemModificado.setCantidad(itemCarritoDTO.cantidad());
            itemModificado.setNombreLocalidad(itemCarritoDTO.localidad());

            List<DetalleCarrito> items = carritoModificado.getItems();
            items.add(itemModificado);
            carritoModificado.setItems(items);

            carritoRepo.save(carritoModificado);
        } else {
            throw new Exception("Carrito no encontrado");
        }
    }

    @Override
    public void eliminarItemCarrito(String idCarrito) throws Exception {

        Optional<Carrito> optionalCarrito = carritoRepo.findById(idCarrito);

        if (optionalCarrito.isPresent()) {
            Carrito carritoModificado = optionalCarrito.get();
            List<DetalleCarrito> items = carritoModificado.getItems();
            items.remove(0);
            carritoModificado.setItems(items);

            carritoRepo.save(carritoModificado);
        } else {
            throw new Exception("Carrito no encontrado");
        }
    }

    @Override
    public void vaciarCarrito(String idCarrito) throws Exception {

        Optional<Carrito> optionalCarrito = carritoRepo.findById(idCarrito);

        if (optionalCarrito.isPresent()) {
            Carrito carritoModificado = optionalCarrito.get();
            carritoRepo.delete(carritoModificado);
        } else {
            throw new Exception("Carrito no encontrado");
        }
    }

    @Override
    public InformacionCarritoDTO obtenerInformacionCarrito(String idCarrito) throws Exception {
        Optional<Carrito> optionalCarrito = carritoRepo.findById(idCarrito);

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

}


