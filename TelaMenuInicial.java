package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaMenuInicial extends JFrame {

    private JButton btnBuscarMusica;
    private JButton btnPlaylists;
    private JButton btnHistorico;
    private JButton btnSair;

    private final Color fundo = new Color(18, 18, 18);
    private final Color verde = new Color(30, 215, 96);
    private final Color branco = Color.WHITE;
    private final Color cinzaEscuro = new Color(40, 40, 40);

    public TelaMenuInicial() {
        setTitle("Spotifei - Menu Principal");
        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(fundo);

        btnBuscarMusica = new JButton("🎵 Buscar Música");
        btnBuscarMusica.setBounds(80, 20, 180, 30);
        estiloBotao(btnBuscarMusica);
        add(btnBuscarMusica);

        btnPlaylists = new JButton("📁 Minhas Playlists");
        btnPlaylists.setBounds(80, 60, 180, 30);
        estiloBotao(btnPlaylists);
        add(btnPlaylists);

        btnHistorico = new JButton("🕓 Ver Histórico");
        btnHistorico.setBounds(80, 100, 180, 30);
        estiloBotao(btnHistorico);
        add(btnHistorico);

        btnSair = new JButton("🚪 Sair");
        btnSair.setBounds(80, 140, 180, 30);
        estiloBotao(btnSair);
        add(btnSair);

        btnBuscarMusica.addActionListener((ActionEvent e) -> {
            new TelaBuscarMusica().setVisible(true);
        });

        btnPlaylists.addActionListener((ActionEvent e) -> {
            new TelaPlaylists().setVisible(true);
        });

        btnHistorico.addActionListener((ActionEvent e) -> {
            new TelaHistorico().setVisible(true);
        });

        btnSair.addActionListener((ActionEvent e) -> {
            dispose();
        });
    }

    private void estiloBotao(JButton botao) {
        botao.setBackground(verde);
        botao.setForeground(branco);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
    }
}
