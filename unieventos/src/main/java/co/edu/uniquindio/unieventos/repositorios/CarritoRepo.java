package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.modelo.Carrito;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoRepo extends MongoRepository<Carrito, String> {
    Optional<Carrito> findById(String id);

    Optional<Carrito> findByIdCuenta(String idCuenta);

    @Query("{ $and: [ { 'idCuenta' : ?0 }, { 'items' : { $elemMatch: { 'idEvento' : ?1, 'nombreLocalidad' : ?2 } } } ] }")
    Optional<Carrito> findByIdCuentaAndIdEventoAndLocalidad(String idCuenta, String idEvento, String localidad);
}
