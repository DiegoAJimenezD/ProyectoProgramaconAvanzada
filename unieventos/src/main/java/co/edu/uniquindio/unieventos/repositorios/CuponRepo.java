package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.modelo.Cupon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuponRepo extends MongoRepository<Cupon, String> {
    List<Cupon> findByCodigoOrNombre(String codigo, String nombre);
}
