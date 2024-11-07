package co.edu.uniquindio.unieventos.servicios.impl;

import co.edu.uniquindio.unieventos.servicios.interfaces.ImagenServicio;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@Service
public class ImagenServicioImpl implements ImagenServicio {


    @Override
    public String subirImagen(MultipartFile multipartFile) throws Exception {
        Bucket bucket = StorageClient.getInstance().bucket();

        String fileName = String.format("%s-%s", UUID.randomUUID().toString(), multipartFile.getOriginalFilename());

        Blob blob = bucket.create(fileName, multipartFile.getInputStream(), multipartFile.getContentType());

        return String.format(
                "https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                bucket.getName(),
                blob.getName()
        );
    }

    @Override
    public void eliminarImagen(String nombreImagen) throws Exception {
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.get(nombreImagen);
        blob.delete();
    }

    @Override
    public String editarImagen(String nombreImagenExistente, MultipartFile nuevaImagen) throws Exception {
        // Primero, eliminamos la imagen existente.
        eliminarImagen(nombreImagenExistente);

        // Luego, subimos la nueva imagen y obtenemos su URL.
        return subirImagen(nuevaImagen);
    }


}