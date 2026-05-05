package org.example.dao;

import org.example.model.servicio;
import org.example.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class servicioDAOImpl implements servicioDAO {

    @Override
    public void registrar(servicio s) {
        String sql = "INSERT INTO servicios (servicioID, nombreServicio, precio) VALUES (?, ?, ?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, s.getServicioID());
            ps.setString(2, s.getNombreServicio());
            ps.setDouble(3, s.getPrecio());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<servicio> listar() {
        List<servicio> lista = new ArrayList<>();
        String sql = "SELECT * FROM servicios";

        try (Connection con = ConexionBD.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                servicio s = new servicio(
                        rs.getInt("servicioID"),
                        rs.getString("nombreServicio"),
                        rs.getDouble("precio")
                );
                lista.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
