package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.modelo.Carrito;
import co.edu.uniquindio.unieventos.modelo.Orden;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdenRepo extends MongoRepository<Orden, String> {
    Optional<Orden> findById(String id);
}
