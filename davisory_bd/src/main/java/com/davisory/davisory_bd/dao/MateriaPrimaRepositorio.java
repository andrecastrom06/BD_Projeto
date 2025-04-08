package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.model.MateriaPrima;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriaPrimaRepositorio {

    public List<MateriaPrima> listar() {
        List<MateriaPrima> lista = new ArrayList<>();
        String sql = "SELECT * FROM MateriaPrima";
    
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                MateriaPrima m = new MateriaPrima();
                m.setIdMateriaPrima(rs.getInt("idMateriaPrima"));
                m.setNomeMateriaPrima(rs.getString("nomeMateriaPrima"));
                m.setValorMateriaPrima(rs.getDouble("valorMateriaPrima"));
                m.setCodigoEntregaMateriaPrima(rs.getString("codigoEntregaMateriaPrima"));
                m.setDataEstimadaEntregaMateriaPrima(rs.getDate("dataEstimadaEntregaMateriaPrima"));
                lista.add(m);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return lista;
    }
    
    public MateriaPrima buscarPorId(int id) {
        String sql = "SELECT * FROM MateriaPrima WHERE idMateriaPrima = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                MateriaPrima m = new MateriaPrima();
                m.setIdMateriaPrima(rs.getInt("idMateriaPrima"));
                m.setNomeMateriaPrima(rs.getString("nomeMateriaPrima"));
                m.setValorMateriaPrima(rs.getDouble("valorMateriaPrima"));
                m.setCodigoEntregaMateriaPrima(rs.getString("codigoEntregaMateriaPrima"));
                m.setDataEstimadaEntregaMateriaPrima(rs.getDate("dataEstimadaEntregaMateriaPrima"));
                return m;
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return null;
    }    

}