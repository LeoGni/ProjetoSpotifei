/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MusicaCurtidaDAO {

    public static boolean curtirMusica(String usuarioEmail, int musicaId) {
        String sql = "INSERT INTO musica_curtida (usuario_email, musica_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuarioEmail);
            stmt.setInt(2, musicaId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean descurtirMusica(String usuarioEmail, int musicaId) {
        String sql = "DELETE FROM musica_curtida WHERE usuario_email = ? AND musica_id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuarioEmail);
            stmt.setInt(2, musicaId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isMusicaCurtida(String usuarioEmail, int musicaId) {
        String sql = "SELECT 1 FROM musica_curtida WHERE usuario_email = ? AND musica_id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuarioEmail);
            stmt.setInt(2, musicaId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
