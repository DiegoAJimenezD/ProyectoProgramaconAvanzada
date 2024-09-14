package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.modelo.DetalleOrden;
import co.edu.uniquindio.unieventos.modelo.Orden;
import co.edu.uniquindio.unieventos.modelo.Pago;
import co.edu.uniquindio.unieventos.repositorios.OrdenRepo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrdenTest {
    @Autowired
    private OrdenRepo ordenRepo;
    
    @Test
    public void registrarTest(){
        //Creamos la orden con sus propiedades
        Orden orden = Orden.builder()
                .idCliente(new ObjectId("XXXXXX"))
                .fecha(LocalDateTime.now())
                .codigoPasarela("A1B23d##")
                .items(
                        Arrays.asList(
                                DetalleOrden.builder()
                                        .cantidad(2)
                                        .nombreLocalidad("VIP")
                                        .idEvento(new ObjectId("XXXX"))
                                        .build(),
                                DetalleOrden.builder()
                                        .cantidad(5)
                                        .nombreLocalidad("General")
                                        .idEvento(new ObjectId("XXXXX"))
                                        .build()
                        )
                )
                .pago(
                        Pago.builder()
                                .estado("Activo")
                                .codigoAutorizacion("KASNQWEK")
                                .moneda("COP")
                                .detalleEstado("ACTIVO")
                                .tipoPago("Tarjeta de cre")
                                .valorTransaccion(254000)
                                .fecha(LocalDateTime.now())
                                .build()
                )
                .build();

        //Guardamos la orden del usuario en la base de datos
        Orden ordenCreada = ordenRepo.save(orden);

        //Verificamos que se haya guardado validando que no sea null
        assertNotNull(ordenCreada);
    }

    @Test
    public void actualizarTest(){
        //Obtenemos la orden con el id XXXXXXX
        Orden orden = ordenRepo.findById("XXXXXXX").orElseThrow();
        //Modificar el email de la orden
        orden.setCodigoPasarela("nuevoCodigooasa");

        //Guardamos la orden del usuario
        ordenRepo.save( orden );

        //Obtenemos la orden del usuario con el id XXXXXXX nuevamente
        Orden ordenActualizada = ordenRepo.findById("XXXXXXX").orElseThrow();

        //Verificamos que el email se haya actualizado
        assertEquals("nuevoCodigooasa", ordenActualizada.getCodigoPasarela());
    }

    @Test
    public void listarTodosTest(){
        //Obtenemos la lista de todas las ordens de los usuarios (por ahora solo tenemos 1)
        List<Orden> lista = ordenRepo.findAll();


        //Imprimimos las ordens, se hace uso de una función lambda
        lista.forEach(System.out::println);


        //Verificamos que solo exista una orden
        assertEquals(1, lista.size());
    }

    @Test
    public void eliminarTest(){
        //Borramos la orden del usuario con el id XXXXXXX
        ordenRepo.deleteById("XXXXXXX");


        //Obtenemos la orden del usuario con el id XXXXXXX
        Orden orden = ordenRepo.findById("XXXXXXX").orElse(null);


        //Verificamos que la orden no exista (sea null) ya que fue eliminado
        assertNull(orden);
    }
}
