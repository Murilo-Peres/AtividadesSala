package AlyssonVeiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProdutoDAO {
    private static final String URL = "jdbc:mariadb://localhost:3306/produtos";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public void inserir(Veiculo veiculo) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO veiculo (numeroChassi, placa, modelo, nome, valor) VALUES (?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, veiculo.getNumeroChassi());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setString(4, veiculo.getNome());
            stmt.setDouble(5, veiculo.getValor());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    veiculo.setId(id);
                }
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Veiculo consultarID(int id) {
        Veiculo veiculo = null;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM veiculo WHERE id = ?")
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                veiculo = criarVeiculo(rs);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculo;
    }

    public Veiculo consultarPlaca(String placa) {
        Veiculo veiculo = null;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM veiculo WHERE placa = ?")
        ) {
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                veiculo = criarVeiculo(rs);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculo;
    }

    public void excluir(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM veiculo WHERE id = ?")
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Veiculo veiculo) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE veiculo SET numeroChassi = ?, placa = ?, modelo = ?, nome = ?, valor = ? WHERE id = ?")
        ) {
            stmt.setString(1, veiculo.getNumeroChassi());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setString(4, veiculo.getNome());
            stmt.setDouble(5, veiculo.getValor());
            stmt.setInt(6, veiculo.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Veiculo criarVeiculo(ResultSet rs) throws SQLException {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(rs.getInt("id"));
        veiculo.setNumeroChassi(rs.getString("numeroChassi"));
        veiculo.setPlaca(rs.getString("placa"));
        veiculo.setModelo(rs.getString("modelo"));
        veiculo.setNome(rs.getString("nome"));
        veiculo.setValor(rs.getDouble("valor"));
        return veiculo;
    }
}