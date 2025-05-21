package dao;

import model.Playlist;
import model.Musica;
import java.sql.*;
import java.util.ArrayList;

public class PlaylistDAO {

    public static boolean criar(Playlist p) {
        String sql = "INSERT INTO playlist(nome, dono) VALUES (?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDono());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static ArrayList<Playlist> buscarPorUsuario(String dono) {
        ArrayList<Playlist> listas = new ArrayList<>();
        String sql = "SELECT * FROM playlist WHERE dono = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dono);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Playlist p = new Playlist(rs.getString("nome"), dono);
                p.setId(rs.getInt("id"));
                listas.add(p);
            }
        } catch (SQLException e) {
            return null;
        }
        return listas;
    }
}
