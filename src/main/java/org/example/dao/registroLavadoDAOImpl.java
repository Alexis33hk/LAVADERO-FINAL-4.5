package org.example.dao;

import org.example.model.registroLavado;
import org.example.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class registroLavadoDAOImpl implements registroLavadoDAO {

    @Override
    public void registrar(registroLavado r) {
        String sql = "INSERT INTO registroLavado (registroID, vehiculoID, servicioID, fecha) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, r.getRegistroID());
            ps.setInt(2, r.getVehiculoID());
            ps.setInt(3, r.getServicioID());
            ps.setString(4, r.getFecha());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<registroLavado> listarTodo() {
        List<registroLavado> lista = new ArrayList<>();
        String sql = "SELECT * FROM registroLavado";

        try (Connection con = ConexionBD.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                registroLavado r = new registroLavado(
                        rs.getInt("registroID"),
                        rs.getInt("vehiculoID"),
                        rs.getInt("servicioID"),
                        rs.getString("fecha")
                );
                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
