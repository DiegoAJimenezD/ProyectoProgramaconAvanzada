package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.modelo.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepo extends MongoRepository<Evento, String> {
    Optional<Evento> findById(String id);

    List<Evento> findByEstado(String estado);

    List<Evento> findByNombreIgnoreCaseLikeAndCiudadLikeAndTipoLikeAndEstado(String nombre, String ciudad, String tipo, String estado);
}
