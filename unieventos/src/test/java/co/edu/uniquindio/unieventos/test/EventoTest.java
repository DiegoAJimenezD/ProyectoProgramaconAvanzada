package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.modelo.Evento;
import co.edu.uniquindio.unieventos.modelo.Localidad;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoEvento;
import co.edu.uniquindio.unieventos.modelo.enums.TipoEvento;
import co.edu.uniquindio.unieventos.repositorios.EventoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EventoTest {
    @Autowired
    private EventoRepo eventoRepo;

    @Test
    public void registrarTest() {
        //Creamos el evento con sus propiedades
        Evento evento = Evento.builder()
                .imagenPortada("/eventos/detalle/get-up-septiembre-2024/840101319799")
                .estado(EstadoEvento.ACTIVO)
                .nombre("Popular Monstour")
                .descripcion("Concierto de la banda Falling In Reverse")
                .direccion("Bosques de Pinares")
                .imagenLocalidades("eventos/detalle/get-up-septiembre-2024/840101319799")
                .tipo(TipoEvento.CONCIERTO)
                .fecha(LocalDateTime.parse("2030-12-03T10:15:30"))
                .localidades(
                        Arrays.asList(
                                Localidad.builder()
                                        .capacidadMaxima(100)
                                        .nombre("VIP")
                                        .precio(1800000)
                                        .build(),
                                Localidad.builder()
                                        .capacidadMaxima(500)
                                        .nombre("General")
                                        .precio(900000)
                                        .build()
                        )
                )
                .ciudad("Armenia").build();

        //Guardamos el evento en la base de datos
        Evento eventoCreado = eventoRepo.save(evento);

        //Verificamos que se haya guardado validando que no sea null
        assertNotNull(eventoCreado);
    }

    @Test
    public void actualizarTest() {
        //Obtenemos el evento con el idCarrito XXXXXXX
        Evento evento = eventoRepo.findById("670859fd99f20b4a16eb1d7a").orElseThrow();
        //Modificar el enombre del evento
        evento.setNombre("concierto");

        //Guardamos el evento
        eventoRepo.save(evento);

        //Obtenemos el evento con el idCarrito XXXXXXX nuevamente
        Evento eventoActualizado = eventoRepo.findById("670859fd99f20b4a16eb1d7a").orElseThrow();

        //Verificamos que el nombre se haya actualizado
        assertEquals("concierto", eventoActualizado.getNombre());
    }

    @Test
    public void listarTodosTest() {
        //Obtenemos la lista de todos los eventos (por ahora solo tenemos 1)
        List<Evento> lista = eventoRepo.findAll();


        //Imprimimos los eventos, se hace uso de una función lambda
        lista.forEach(System.out::println);


        //Verificamos que solo exista un evento
        assertEquals(1, lista.size());
    }

    @Test
    public void eliminarTest() {
        //Borramos el evento con el idCarrito XXXXXXX
        eventoRepo.deleteById("XXXXXXX");


        //Obtenemos el evento con el idCarrito XXXXXXX
        Evento evento = eventoRepo.findById("XXXXXXX").orElse(null);


        //Verificamos que lel evento no exista (sea null) ya que fue eliminado
        assertNull(evento);
    }
}
