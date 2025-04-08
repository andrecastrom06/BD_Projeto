package com.davisory.davisory_bd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.davisory.davisory_bd.model.Administrativo;
import com.davisory.davisory_bd.model.Funcionario;
import com.davisory.davisory_bd.model.Operacional;

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
                adm.setChefeFuncionario(rs.getInt("chefeFuncionario"));
                adm.setCargoFuncionarioAdministrativo(rs.getString("cargoFuncionarioAdministrativo"));
                adm.setEmpregado(rs.getBoolean("empregado"));
                adm.setCargo(rs.getString("cargoFuncionarioAdministrativo"));
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
                    f.setChefeFuncionario(rs.getObject("chefeFuncionario", Integer.class));
                    f.setEmpregado(rs.getBoolean("empregado"));

                    String sqlCargo = "SELECT cargoFuncionarioAdministrativo FROM Administrativo WHERE fk_Funcionario_idFuncionario = ?";
                    try (PreparedStatement stmt2 = conn.prepareStatement(sqlCargo)) {
                        stmt2.setInt(1, id);
                        try (ResultSet rs2 = stmt2.executeQuery()) {
                            if (rs2.next()) {
                                f.setCargo(rs2.getString("cargoFuncionarioAdministrativo"));
                            }
                        }
                    }

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
                f.setChefeFuncionario(rs.getObject("chefeFuncionario", Integer.class));
                f.setEmpregado(rs.getBoolean("empregado"));
                lista.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizarFuncionarioCompleto(Funcionario f) {
        String sqlFuncionario = """
            UPDATE Funcionario SET 
                nomeFuncionario = ?, 
                salarioFuncionario = ?, 
                chefeFuncionario = ?, 
                empregado = ?
            WHERE idFuncionario = ?
        """;

        String sqlCargo = """
            UPDATE Administrativo 
            SET cargoFuncionarioAdministrativo = ?
            WHERE fk_Funcionario_idFuncionario = ?
        """;

        try (Connection conn = ConexaoBD.conectar()) {
            try (PreparedStatement stmt = conn.prepareStatement(sqlFuncionario)) {
                stmt.setString(1, f.getNomeFuncionario());
                stmt.setDouble(2, f.getSalarioFuncionario());
                if (f.getChefeFuncionario() != null) {
                    stmt.setInt(3, f.getChefeFuncionario());
                } else {
                    stmt.setNull(3, java.sql.Types.INTEGER);
                }
                stmt.setBoolean(4, f.isEmpregado());
                stmt.setInt(5, f.getIdFuncionario());
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt2 = conn.prepareStatement(sqlCargo)) {
                stmt2.setString(1, f.getCargo());
                stmt2.setInt(2, f.getIdFuncionario());
                stmt2.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirAdministrativo(int idFuncionario, String cargo) {
        try (Connection conn = ConexaoBD.conectar()) {
            String sql = "INSERT INTO Administrativo (fk_Funcionario_idFuncionario, cargoFuncionarioAdministrativo) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idFuncionario);
            stmt.setString(2, cargo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int inserir(Funcionario f) {
        int novoId = -1;
        try (Connection conn = ConexaoBD.conectar()) {
            String sql = "INSERT INTO Funcionario (nomeFuncionario, salarioFuncionario, dataContratacaoFuncionario, chefeFuncionario, empregado) VALUES (?, ?, CURDATE(), ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, f.getNomeFuncionario());
            stmt.setDouble(2, f.getSalarioFuncionario());
            if (f.getChefeFuncionario() != null) {
                stmt.setInt(3, f.getChefeFuncionario());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            stmt.setBoolean(4, f.isEmpregado());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                novoId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return novoId;
    }

    public List<Funcionario> listarTodosFuncionarios() {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Funcionario";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setIdFuncionario(rs.getInt("idFuncionario"));
                f.setNomeFuncionario(rs.getString("nomeFuncionario"));
                f.setSalarioFuncionario(rs.getDouble("salarioFuncionario"));
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