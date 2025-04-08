package com.davisory.davisory_bd.dao;

import com.davisory.davisory_bd.model.Administrativo;
import com.davisory.davisory_bd.model.Funcionario;
import com.davisory.davisory_bd.model.Operacional;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FuncionarioRepositorio {

    public List<Administrativo> listarAdministrativos() {
        List<Administrativo> lista = new ArrayList<>();
        String sql = """
            SELECT f.*, a.cargoFuncionarioAdministrativo 
            FROM Funcionario f 
            JOIN Administrativo a ON f.idFuncionario = a.fk_Funcionario_idFuncionario
        """;

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Administrativo adm = new Administrativo();
                adm.setIdFuncionario(rs.getInt("idFuncionario"));
                adm.setNomeFuncionario(rs.getString("nomeFuncionario"));
                adm.setSalarioFuncionario(rs.getDouble("salarioFuncionario"));
                adm.setDataContratacaoFuncionario(rs.getDate("dataContratacaoFuncionario"));
                adm.setChefeFuncionario(rs.getInt("chefeFuncionario"));
                adm.setCargoFuncionarioAdministrativo(rs.getString("cargoFuncionarioAdministrativo"));
                adm.setEmpregado(rs.getBoolean("empregado"));
                lista.add(adm);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Operacional> listarOperacionais() {
        List<Operacional> lista = new ArrayList<>();
        String sql = """
            SELECT f.* 
            FROM Funcionario f 
            JOIN Operacional o ON f.idFuncionario = o.fk_Funcionario_idFuncionario
        """;

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Operacional op = new Operacional();
                op.setIdFuncionario(rs.getInt("idFuncionario"));
                op.setNomeFuncionario(rs.getString("nomeFuncionario"));
                op.setSalarioFuncionario(rs.getDouble("salarioFuncionario"));
                op.setDataContratacaoFuncionario(rs.getDate("dataContratacaoFuncionario"));
                op.setChefeFuncionario(rs.getInt("chefeFuncionario"));
                op.setEmpregado(rs.getBoolean("empregado"));
                lista.add(op);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Funcionario buscarPorId(int id) {
        String sql = "SELECT * FROM Funcionario WHERE idFuncionario = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Funcionario f = new Funcionario();
                    f.setIdFuncionario(rs.getInt("idFuncionario"));
                    f.setNomeFuncionario(rs.getString("nomeFuncionario"));
                    f.setSalarioFuncionario(rs.getDouble("salarioFuncionario"));
                    f.setDataContratacaoFuncionario(rs.getDate("dataContratacaoFuncionario"));
                    f.setChefeFuncionario(rs.getInt("chefeFuncionario"));
                    f.setEmpregado(rs.getBoolean("empregado"));
                    return f;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizarEmpregado(int id, boolean empregado) {
        String sql = "UPDATE Funcionario SET empregado = ? WHERE idFuncionario = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, empregado);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Funcionario> listarFuncionariosComChefes() {
        List<Funcionario> lista = new ArrayList<>();
        String sql = """
            SELECT f.idFuncionario, f.nomeFuncionario, f.salarioFuncionario, 
                   f.dataContratacaoFuncionario, f.chefeFuncionario, f.empregado,
                   c.nomeFuncionario AS nomeChefe
            FROM Funcionario f
            LEFT JOIN Funcionario c ON f.chefeFuncionario = c.idFuncionario
        """;

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setIdFuncionario(rs.getInt("idFuncionario"));
                f.setNomeFuncionario(rs.getString("nomeFuncionario"));
                f.setSalarioFuncionario(rs.getDouble("salarioFuncionario"));
                f.setDataContratacaoFuncionario(rs.getDate("dataContratacaoFuncionario"));
                f.setChefeFuncionario(rs.getObject("chefeFuncionario", Integer.class));
                f.setEmpregado(rs.getBoolean("empregado"));
                lista.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}