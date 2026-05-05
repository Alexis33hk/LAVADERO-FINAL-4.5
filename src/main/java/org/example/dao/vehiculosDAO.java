package org.example.dao;
import org.example.model.vehiculos;
import java.util.List;

public interface vehiculosDAO {
    void registrar(vehiculos vehiculo);
    List<vehiculos> listarPorCliente(int clienteId);
    List<vehiculos> listarTodos();
    void eliminar(int vehiculoID);
}