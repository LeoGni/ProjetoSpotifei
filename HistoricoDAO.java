package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Historico;

public class HistoricoDAO {

    public static boolean registrarAcao(String usuario, String tipo, String descricao) {
        String sql = "INSERT INTO historico(usuario, tipo, descricao) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, tipo);
            stmt.setString(3, descricao);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<String> buscarHistorico(String usuario, String tipo, int limite) {
        ArrayList<String> historico = new ArrayList<>();
        String sql = "SELECT descricao FROM historico WHERE usuario = ? AND tipo = ? ORDER BY id DESC LIMIT ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, tipo);
            stmt.setInt(3, limite);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                historico.add(rs.getString("descricao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historico;
    }

    public static ArrayList<Historico> listarPorUsuario(String usuario) {
        ArrayList<Historico> lista = new ArrayList<>();
        String sql = "SELECT id, usuario, tipo, descricao FROM historico WHERE usuario = ? ORDER BY id DESC";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Historico h = new Historico();
                h.setId(rs.getInt("id"));
                h.setUsuario(rs.getString("usuario"));
                h.setTipo(rs.getString("tipo"));
                h.setDescricao(rs.getString("descricao"));
                lista.add(h);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
