package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.modelo.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepo  extends MongoRepository<Usuario, String> {
}
