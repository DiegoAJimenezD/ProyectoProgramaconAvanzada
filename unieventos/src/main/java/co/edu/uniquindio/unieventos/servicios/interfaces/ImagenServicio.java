package co.edu.uniquindio.unieventos.servicios.interfaces;

import org.springframework.web.multipart.MultipartFile;


public interface ImagenServicio {

    String subirImagen(MultipartFile imagen) throws Exception;

    void eliminarImagen(String nombreImagen) throws Exception;
}

