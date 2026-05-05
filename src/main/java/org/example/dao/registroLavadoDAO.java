package org.example.dao;
import org.example.model.registroLavado;
import java.util.List;

public interface registroLavadoDAO {
    void registrar(registroLavado r);
    List<registroLavado> listarTodo();
}