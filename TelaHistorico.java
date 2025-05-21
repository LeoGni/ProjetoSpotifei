package view;

import javax.swing.*;
import dao.HistoricoDAO;
import model.Historico;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class TelaHistorico extends JFrame {
    private JTextField txtEmail;
    private JButton btnBuscar;
    private JTextArea areaHistorico;

    private final Color fundo = new Color(18, 18, 18);
    private final Color verde = new Color(30, 215, 96);
    private final Color branco = Color.WHITE;
    private final Color cinzaEscuro = new Color(40, 40, 40);

    public TelaHistorico() {
        setTitle("Histórico de Ações");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(fundo);

        JLabel lblEmail = new JLabel("Email Usuário:");
        lblEmail.setBounds(20, 20, 100, 25);
        lblEmail.setForeground(branco);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(130, 20, 200, 25);
        txtEmail.setBackground(cinzaEscuro);
        txtEmail.setForeground(branco);
        txtEmail.setCaretColor(branco);
        txtEmail.setBorder(BorderFactory.createLineBorder(verde));
        add(txtEmail);

        btnBuscar = new JButton("Buscar Histórico");
        btnBuscar.setBounds(130, 60, 150, 25);
        btnBuscar.setBackground(verde);
        btnBuscar.setForeground(branco);
        btnBuscar.setFocusPainted(false);
        add(btnBuscar);

        areaHistorico = new JTextArea();
        areaHistorico.setBackground(cinzaEscuro);
        areaHistorico.setForeground(branco);
        areaHistorico.setCaretColor(branco);
        areaHistorico.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaHistorico);
        scroll.setBounds(20, 100, 340, 140);
        scroll.getViewport().setBackground(cinzaEscuro);
        add(scroll);

        btnBuscar.addActionListener((ActionEvent e) -> {
            String email = txtEmail.getText();
            ArrayList<Historico> lista = HistoricoDAO.listarPorUsuario(email);
            areaHistorico.setText("");
            for (Historico h : lista) {
                areaHistorico.append("[" + h.getTipo() + "] " + h.getDescricao() + "\n");
            }
        });
    }
}
