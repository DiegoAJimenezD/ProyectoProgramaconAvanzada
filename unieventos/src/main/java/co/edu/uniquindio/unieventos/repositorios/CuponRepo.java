package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.modelo.Cupon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuponRepo extends MongoRepository<Cupon, String> {
    List<Cupon> findByCodigoOrEstadoCuponOrNombre(String codigo, String estadoCupon, String nombre);
}
