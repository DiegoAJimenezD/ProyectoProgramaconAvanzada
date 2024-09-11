package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.modelo.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CuentaRepo extends MongoRepository<Cuenta, String> {
}
