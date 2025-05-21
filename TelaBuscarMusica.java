package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import dao.MusicaDAO;
import dao.PlaylistMusicaDAO;
import dao.MusicaCurtidaDAO;
import dao.HistoricoDAO;
import model.Musica;

public class TelaBuscarMusica extends JFrame {

    private JTextField txtBusca;
    private JButton btnBuscar, btnAdicionarPlaylist, btnCurtirDescurtir;
    private JTable tabelaMusicas;
    private DefaultTableModel modeloTabela;

    private final Color fundo = new Color(18, 18, 18);
    private final Color verde = new Color(30, 215, 96);
    private final Color branco = Color.WHITE;
    private final Color cinzaEscuro = new Color(40, 40, 40);

    public TelaBuscarMusica() {
        setTitle("Buscar Música - Spotifei");
        setSize(500, 420);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(fundo);

        JLabel lblBusca = new JLabel("Buscar por nome:");
        lblBusca.setBounds(20, 20, 120, 25);
        lblBusca.setForeground(branco);
        add(lblBusca);

        txtBusca = new JTextField();
        txtBusca.setBounds(150, 20, 200, 25);
        txtBusca.setBackground(cinzaEscuro);
        txtBusca.setForeground(branco);
        txtBusca.setCaretColor(branco);
        txtBusca.setBorder(BorderFactory.createLineBorder(verde));
        add(txtBusca);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(370, 20, 90, 25);
        btnBuscar.setBackground(verde);
        btnBuscar.setForeground(branco);
        btnBuscar.setFocusPainted(false);
        add(btnBuscar);

        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Nome", "Artista", "Gênero"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaMusicas = new JTable(modeloTabela);
        tabelaMusicas.setBackground(fundo);
        tabelaMusicas.setForeground(branco);
        tabelaMusicas.setSelectionBackground(verde);
        tabelaMusicas.setSelectionForeground(branco);
        tabelaMusicas.setGridColor(cinzaEscuro);
        tabelaMusicas.setShowGrid(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setBackground(fundo);
        centerRenderer.setForeground(branco);
        for (int i = 0; i < modeloTabela.getColumnCount(); i++) {
            tabelaMusicas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scroll = new JScrollPane(tabelaMusicas);
        scroll.setBounds(20, 60, 440, 250);
        scroll.getViewport().setBackground(fundo);
        add(scroll);

        btnAdicionarPlaylist = new JButton("Adicionar à Playlist");
        btnAdicionarPlaylist.setBounds(150, 320, 200, 30);
        btnAdicionarPlaylist.setBackground(verde);
        btnAdicionarPlaylist.setForeground(branco);
        btnAdicionarPlaylist.setFocusPainted(false);
        add(btnAdicionarPlaylist);

        btnCurtirDescurtir = new JButton("Curtir");
        btnCurtirDescurtir.setBounds(150, 360, 200, 30);
        btnCurtirDescurtir.setBackground(verde);
        btnCurtirDescurtir.setForeground(branco);
        btnCurtirDescurtir.setFocusPainted(false);
        add(btnCurtirDescurtir);

        // Atualiza o texto do botão Curtir/Descurtir conforme seleção na tabela
        tabelaMusicas.getSelectionModel().addListSelectionListener(e -> {
            int linha = tabelaMusicas.getSelectedRow();
            if (linha >= 0) {
                int musicaId = Integer.parseInt(modeloTabela.getValueAt(linha, 0).toString());
                String email = util.Sessao.emailUsuario;
                boolean curtida = MusicaCurtidaDAO.isMusicaCurtida(email, musicaId);
                btnCurtirDescurtir.setText(curtida ? "Descurtir" : "Curtir");
            }
        });

        // Ação do botão Curtir/Descurtir
        btnCurtirDescurtir.addActionListener(e -> {
            int linha = tabelaMusicas.getSelectedRow();
            if (linha < 0) {
                JOptionPane.showMessageDialog(null, "Selecione uma música para curtir/descurtir.");
                return;
            }
            int musicaId = Integer.parseInt(modeloTabela.getValueAt(linha, 0).toString());
            String email = util.Sessao.emailUsuario;

            boolean curtida = MusicaCurtidaDAO.isMusicaCurtida(email, musicaId);
            boolean sucesso;

            if (curtida) {
                sucesso = MusicaCurtidaDAO.descurtirMusica(email, musicaId);
                if (sucesso) btnCurtirDescurtir.setText("Curtir");
            } else {
                sucesso = MusicaCurtidaDAO.curtirMusica(email, musicaId);
                if (sucesso) btnCurtirDescurtir.setText("Descurtir");
            }

            if (!sucesso) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar curtida.");
            }
        });

        // Ação do botão Buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtBusca.getText();

                // Registrar a busca no histórico
                HistoricoDAO.registrarAcao(util.Sessao.emailUsuario, "busca", nome);

                ArrayList<Musica> musicas = MusicaDAO.buscarPorNome(nome);
                modeloTabela.setRowCount(0);
                if (musicas != null && !musicas.isEmpty()) {
                    for (Musica m : musicas) {
                        modeloTabela.addRow(new Object[]{m.getId(), m.getNome(), m.getArtista(), m.getGenero()});
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhuma música encontrada.");
                }
            }
        });

        // Ação do botão Adicionar à Playlist
        btnAdicionarPlaylist.addActionListener((ActionEvent e) -> {
            int linhaSelecionada = tabelaMusicas.getSelectedRow();
            if (linhaSelecionada < 0) {
                JOptionPane.showMessageDialog(null, "Selecione uma música na tabela.");
                return;
            }
            int musicaId = Integer.parseInt(modeloTabela.getValueAt(linhaSelecionada, 0).toString());
            TelaSelecaoPlaylist telaSelecao = new TelaSelecaoPlaylist(musicaId, util.Sessao.emailUsuario);
            telaSelecao.setVisible(true);
        });
    }
}
