package org.example.dao;

import org.example.model.vehiculos;
import org.example.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class vehiculosDAOImpl implements vehiculosDAO {
    @Override
    public void registrar(vehiculos v) {
        String sql = "INSERT INTO vehiculos (vehiculoID, ClienteID, marca, modelo, placa, color, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, v.getVehiculoID());
            ps.setInt(2, v.getClienteID());
            ps.setString(3, v.getMarca());
            ps.setString(4, v.getModelo());
            ps.setString(5, v.getPlaca());
            ps.setString(6, v.getColor());
            ps.setString(7, v.getTipo());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public List<vehiculos> listarPorCliente(int clienteId) {
        List<vehiculos> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos WHERE ClienteID = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new vehiculos(rs.getInt("vehiculoID"), rs.getInt("ClienteID"), rs.getString("marca"),
                        rs.getString("modelo"), rs.getString("placa"), rs.getString("color"), rs.getString("tipo")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public List<vehiculos> listarTodos() {
        List<vehiculos> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";
        try (Connection con = ConexionBD.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new vehiculos(rs.getInt("vehiculoID"), rs.getInt("ClienteID"), rs.getString("marca"),
                        rs.getString("modelo"), rs.getString("placa"), rs.getString("color"), rs.getString("tipo")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public void eliminar(int vehiculoID) {
        String sql = "DELETE FROM vehiculos WHERE vehiculoID = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, vehiculoID);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}