package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import dao.PlaylistDAO;
import model.Playlist;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class TelaPlaylists extends JFrame {

    private JTextField txtNomePlaylist, txtDono;
    private JButton btnCriar, btnVerMusicas;
    private JTable tabelaPlaylists;
    private DefaultTableModel modeloTabela;

    private final Color fundo = new Color(18, 18, 18);
    private final Color verde = new Color(30, 215, 96);
    private final Color branco = Color.WHITE;
    private final Color cinzaEscuro = new Color(40, 40, 40);

    public TelaPlaylists() {
        setTitle("Minhas Playlists");
        setSize(400, 380);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(fundo);

        JLabel lblNome = new JLabel("Nome Playlist:");
        lblNome.setBounds(20, 20, 100, 25);
        lblNome.setForeground(branco);
        add(lblNome);

        txtNomePlaylist = new JTextField();
        txtNomePlaylist.setBounds(130, 20, 200, 25);
        txtNomePlaylist.setBackground(cinzaEscuro);
        txtNomePlaylist.setForeground(branco);
        txtNomePlaylist.setCaretColor(branco);
        txtNomePlaylist.setBorder(BorderFactory.createLineBorder(verde));
        add(txtNomePlaylist);

        JLabel lblDono = new JLabel("Email Dono:");
        lblDono.setBounds(20, 60, 100, 25);
        lblDono.setForeground(branco);
        add(lblDono);

        txtDono = new JTextField();
        txtDono.setBounds(130, 60, 200, 25);
        txtDono.setEditable(false);
        txtDono.setBackground(cinzaEscuro);
        txtDono.setForeground(branco);
        txtDono.setBorder(BorderFactory.createLineBorder(verde));
        add(txtDono);

        btnCriar = new JButton("Criar Playlist");
        btnCriar.setBounds(130, 100, 150, 25);
        btnCriar.setBackground(verde);
        btnCriar.setForeground(branco);
        btnCriar.setFocusPainted(false);
        add(btnCriar);

        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Nome"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaPlaylists = new JTable(modeloTabela);
        tabelaPlaylists.setBackground(fundo);
        tabelaPlaylists.setForeground(branco);
        tabelaPlaylists.setSelectionBackground(verde);
        tabelaPlaylists.setSelectionForeground(branco);
        tabelaPlaylists.setGridColor(cinzaEscuro);
        tabelaPlaylists.setShowGrid(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setBackground(fundo);
        centerRenderer.setForeground(branco);
        tabelaPlaylists.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabelaPlaylists.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        JScrollPane scroll = new JScrollPane(tabelaPlaylists);
        scroll.setBounds(20, 140, 340, 150);
        scroll.getViewport().setBackground(fundo);
        add(scroll);

        btnVerMusicas = new JButton("Ver Músicas");
        btnVerMusicas.setBounds(130, 310, 150, 25);
        btnVerMusicas.setBackground(verde);
        btnVerMusicas.setForeground(branco);
        btnVerMusicas.setFocusPainted(false);
        add(btnVerMusicas);

        btnCriar.addActionListener((ActionEvent e) -> {
            Playlist p = new Playlist(txtNomePlaylist.getText(), txtDono.getText());
            if (PlaylistDAO.criar(p)) {
                JOptionPane.showMessageDialog(null, "Playlist criada!");
                atualizarLista();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao criar playlist.");
            }
        });

        btnVerMusicas.addActionListener(e -> {
            int linhaSelecionada = tabelaPlaylists.getSelectedRow();
            if (linhaSelecionada < 0) {
                JOptionPane.showMessageDialog(null, "Selecione uma playlist na tabela.");
                return;
            }
            int playlistId = Integer.parseInt(modeloTabela.getValueAt(linhaSelecionada, 0).toString());
            TelaVerPlaylist telaVer = new TelaVerPlaylist(playlistId);
            telaVer.setVisible(true);
        });

        txtDono.setText(util.Sessao.emailUsuario);

        atualizarLista();
    }

    private void atualizarLista() {
        String dono = util.Sessao.emailUsuario;
        if (dono != null && !dono.isEmpty()) {
            ArrayList<Playlist> playlists = PlaylistDAO.buscarPorUsuario(dono);
            modeloTabela.setRowCount(0);
            if (playlists != null) {
                for (Playlist p : playlists) {
                    modeloTabela.addRow(new Object[]{p.getId(), p.getNome()});
                }
            }
        }
    }
}
