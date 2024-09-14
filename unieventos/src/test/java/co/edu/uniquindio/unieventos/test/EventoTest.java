package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.modelo.*;
import co.edu.uniquindio.unieventos.repositorios.EventoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EventoTest {
    @Autowired
    private EventoRepo eventoRepo;

    @Test
    public void registrarTest(){
        //Creamos el evento con sus propiedades
        Evento evento = Evento.builder()
                .imagenPortada("/eventos/detalle/get-up-septiembre-2024/840101319799")
                .estado(EstadoEvento.ACTIVO)
                .nombre("Popular Monstour")
                .descripcion("Concierto de la banda Falling In Reverse")
                .direccion("Bosques de Pinares")
                .imagenLocalidades("eventos/detalle/get-up-septiembre-2024/840101319799")
                .tipo(TipoEvento.CONCIERTO)
                .fecha(LocalDateTime.parse("06-12-2024"))
                .ciudad("Armenia").build();

        //Guardamos el evento en la base de datos
        Evento eventoCreado = eventoRepo.save(evento);

        //Verificamos que se haya guardado validando que no sea null
        assertNotNull(eventoCreado);
    }

    @Test
    public void actualizarTest(){
        //Obtenemos el evento con el id XXXXXXX
        Evento evento = eventoRepo.findById("XXXXXXX").orElseThrow();
        //Modificar el enombre del evento
        evento.setNombre("concierto");

        //Guardamos el evento
        eventoRepo.save( evento );

        //Obtenemos el evento con el id XXXXXXX nuevamente
        Evento eventoActualizado = eventoRepo.findById("XXXXXXX").orElseThrow();

        //Verificamos que el nombre se haya actualizado
        assertEquals("concierto", eventoActualizado.getNombre());
    }

}
