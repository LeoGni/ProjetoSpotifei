package dao;

import model.Musica;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicaDAO {

    public static boolean cadastrar(Musica m) {
        String sql = "INSERT INTO musica(nome, artista, genero) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getArtista());
            stmt.setString(3, m.getGenero());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static ArrayList<Musica> buscarPorNome(String nome) {
        ArrayList<Musica> lista = new ArrayList<>();
        String sql = "SELECT * FROM musica WHERE nome ILIKE ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Musica m = new Musica(rs.getString("nome"), rs.getString("artista"), rs.getString("genero"));
                m.setId(rs.getInt("id"));
                lista.add(m);
            }
        } catch (SQLException e) {
            return null;
        }
        return lista;
    }

    public static ArrayList<Musica> buscarMusicasDaPlaylist(int playlistId) {
        ArrayList<Musica> musicas = new ArrayList<>();
        String sql = "SELECT m.id, m.nome, m.artista, m.genero " +
                     "FROM musica m " +
                     "JOIN playlist_musica pm ON m.id = pm.musica_id " +
                     "WHERE pm.playlist_id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, playlistId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Musica m = new Musica(rs.getString("nome"), rs.getString("artista"), rs.getString("genero"));
                m.setId(rs.getInt("id"));
                musicas.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return musicas;
    }

    public static ArrayList<Musica> buscarMusicasCurtidas(String usuarioEmail) {
        ArrayList<Musica> musicas = new ArrayList<>();
        String sql = "SELECT m.id, m.nome, m.artista, m.genero " +
                     "FROM musica m " +
                     "JOIN musica_curtida mc ON m.id = mc.musica_id " +
                     "WHERE mc.usuario_email = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuarioEmail);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Musica m = new Musica(rs.getString("nome"), rs.getString("artista"), rs.getString("genero"));
                m.setId(rs.getInt("id"));
                musicas.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicas;
    }

    public static ArrayList<Musica> buscarMusicasPorIds(List<Integer> ids) {
        ArrayList<Musica> musicas = new ArrayList<>();
        if (ids.isEmpty()) return musicas;

        StringBuilder sql = new StringBuilder("SELECT * FROM musica WHERE id IN (");
        for (int i = 0; i < ids.size(); i++) {
            sql.append("?");
            if (i < ids.size() - 1) sql.append(",");
        }
        sql.append(")");

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < ids.size(); i++) {
                stmt.setInt(i + 1, ids.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Musica m = new Musica(rs.getString("nome"), rs.getString("artista"), rs.getString("genero"));
                m.setId(rs.getInt("id"));
                musicas.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicas;
    }

}
