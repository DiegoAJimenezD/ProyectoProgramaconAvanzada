package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.modelo.Cuenta;
import co.edu.uniquindio.unieventos.modelo.EstadoCuenta;
import co.edu.uniquindio.unieventos.modelo.Rol;
import co.edu.uniquindio.unieventos.modelo.Usuario;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class CuentaTest {

    @Autowired
    private CuentaRepo cuentaRepo;

    @Test
    public void registrarTest(){
        //Creamos la cuenta con sus propiedades
        Cuenta cuenta = Cuenta.builder()
                .email("juanito@email.com")
                .password("123456")
                .fechaRegistro(LocalDateTime.now())
                .estado(EstadoCuenta.ACTIVO)
                .usuario(
                        Usuario.builder()
                                .cedula("123")
                                .nombre("Juanito")
                                .direccion("Calle 123")
                                .telefono("121212").build()
                )
                .rol(Rol.CLIENTE).build();

        //Guardamos la cuenta del usuario en la base de datos
        Cuenta cuentaCreada = cuentaRepo.save(cuenta);

        //Verificamos que se haya guardado validando que no sea null
        assertNotNull(cuentaCreada);
    }

    @Test
    public void actualizarTest(){
        //Obtenemos la cuenta el usuario con el id XXXXXXX
        Cuenta cuenta = cuentaRepo.findById("XXXXXXX").orElseThrow();
        //Modificar el email de la cuenta
        cuenta.setEmail("nuevoemail@email.com");

        //Guardamos la cuenta del usuario
        cuentaRepo.save( cuenta );

        //Obtenemos la cuenta del usuario con el id XXXXXXX nuevamente
        Cuenta cuentaActualizada = cuentaRepo.findById("XXXXXXX").orElseThrow();

        //Verificamos que el email se haya actualizado
        assertEquals("nuevoemail@email.com", cuentaActualizada.getEmail());
    }

    @Test
    public void listarTodosTest(){
        //Obtenemos la lista de todas las cuentas de los usuarios (por ahora solo tenemos 1)
        List<Cuenta> lista = cuentaRepo.findAll();


        //Imprimimos las cuentas, se hace uso de una función lambda
        lista.forEach(System.out::println);


        //Verificamos que solo exista una cuenta
        assertEquals(1, lista.size());
    }

    @Test
    public void eliminarTest(){
        //Borramos la cuenta del usuario con el id XXXXXXX
        cuentaRepo.deleteById("XXXXXXX");


        //Obtenemos la cuenta del usuario con el id XXXXXXX
        Cuenta cuenta = cuentaRepo.findById("XXXXXXX").orElse(null);


        //Verificamos que la cuenta no exista (sea null) ya que fue eliminado
        assertNull(cuenta);
    }

}
