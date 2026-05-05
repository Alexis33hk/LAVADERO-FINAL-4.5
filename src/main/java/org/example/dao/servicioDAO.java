package org.example.dao;
import org.example.model.servicio;
import java.util.List;

public interface servicioDAO {
    void registrar(servicio servicio);
    List<servicio> listar();
}