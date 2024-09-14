package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.modelo.Carrito;
import co.edu.uniquindio.unieventos.modelo.DetalleCarrito;
import co.edu.uniquindio.unieventos.repositorios.CarritoRepo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CarritoTest {
    @Autowired
    private CarritoRepo carritoRepo;

    @Test
    public void registrarTest(){
        //Creamos el evento con sus propiedades
        Carrito carrito = Carrito.builder()
                .fecha(LocalDateTime.parse("06-12-2024"))
                .items(
                        Arrays.asList(
                                        DetalleCarrito.builder()
                                                .cantidad(2)
                                                .nombreLocalidad("VIP")
                                                .idEvento(new ObjectId("XXXX"))
                                                .build(),
                                        DetalleCarrito.builder()
                                                .cantidad(5)
                                                .nombreLocalidad("General")
                                                .idEvento(new ObjectId("XXXXX"))
                                                .build()
                                )
                ).idUsuario(new ObjectId("XXXXXXXXXX")).build();

        //Guardamos el carrito en la base de datos
        Carrito carritoCreado = carritoRepo.save(carrito);

        //Verificamos que se haya guardado validando que no sea null
        assertNotNull(carritoCreado);
    }

    @Test
    public void actualizarTest(){
        //Obtenemos el carrito con el id XXXXXXX
        Carrito carrito = carritoRepo.findById("XXXXXXX").orElseThrow();
        //Modificar el enombre del carrito
        carrito.setIdUsuario(new ObjectId("Ppo"));

        //Guardamos el carrito
        carritoRepo.save( carrito );

        //Obtenemos el carrito con el id XXXXXXX nuevamente
        Carrito carritoActualizado = carritoRepo.findById("XXXXXXX").orElseThrow();

        //Verificamos que el nombre se haya actualizado
        assertEquals("Ppo", carritoActualizado.getIdUsuario());
    }

    @Test
    public void listarTodosTest(){
        //Obtenemos la lista de todos los carritos (por ahora solo tenemos 1)
        List<Carrito> lista = carritoRepo.findAll();


        //Imprimimos los carritos, se hace uso de una función lambda
        lista.forEach(System.out::println);


        //Verificamos que solo exista un carrito
        assertEquals(1, lista.size());
    }

    @Test
    public void eliminarTest(){
        //Borramos el carrito con el id XXXXXXX
        carritoRepo.deleteById("XXXXXXX");


        //Obtenemos el carrito con el id XXXXXXX
        Carrito carrito = carritoRepo.findById("XXXXXXX").orElse(null);


        //Verificamos que la cuenta no exista (sea null) ya que fue eliminado
        assertNull(carrito);
    }
}
