package org.example.dao;
import org.example.model.Clientes;
import java.util.List;

public interface ClientesDAO {
    void crear(Clientes cliente);
    List<Clientes> listar();
    void eliminar(int id);
}