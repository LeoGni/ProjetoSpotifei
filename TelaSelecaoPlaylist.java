package view;

import javax.swing.*;
import dao.PlaylistDAO;
import dao.PlaylistMusicaDAO;
import model.Playlist;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class TelaSelecaoPlaylist extends JFrame {

    private JComboBox<String> comboPlaylists;
    private JButton btnAdicionar;
    private int musicaId;
    private ArrayList<Playlist> playlists;
    private String emailUsuario;

    private final Color fundo = new Color(18, 18, 18);
    private final Color verde = new Color(30, 215, 96);
    private final Color branco = Color.WHITE;
    private final Color cinzaEscuro = new Color(40, 40, 40);

    public TelaSelecaoPlaylist(int musicaId, String emailUsuario) {
        this.musicaId = musicaId;
        this.emailUsuario = emailUsuario;

        setTitle("Selecionar Playlist");
        setSize(300, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(fundo);

        JLabel lbl = new JLabel("Escolha a playlist:");
        lbl.setBounds(20, 20, 120, 25);
        lbl.setForeground(branco);
        add(lbl);

        comboPlaylists = new JComboBox<>();
        comboPlaylists.setBounds(20, 50, 240, 25);
        comboPlaylists.setBackground(cinzaEscuro);
        comboPlaylists.setForeground(branco);
        add(comboPlaylists);

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(80, 90, 120, 25);
        btnAdicionar.setBackground(verde);
        btnAdicionar.setForeground(branco);
        btnAdicionar.setFocusPainted(false);
        add(btnAdicionar);

        carregarPlaylists();

        btnAdicionar.addActionListener((ActionEvent e) -> {
            if (comboPlaylists.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "Selecione uma playlist.");
                return;
            }
            Playlist selecionada = playlists.get(comboPlaylists.getSelectedIndex());
            boolean sucesso = PlaylistMusicaDAO.adicionarMusicaNaPlaylist(selecionada.getId(), musicaId);
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Música adicionada à playlist!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar música.");
            }
        });
    }

    private void carregarPlaylists() {
        playlists = PlaylistDAO.buscarPorUsuario(emailUsuario);
        if (playlists != null) {
            for (Playlist p : playlists) {
                comboPlaylists.addItem(p.getNome());
            }
        }
    }
}
