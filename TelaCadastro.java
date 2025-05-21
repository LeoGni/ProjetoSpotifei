package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.UsuarioDAO;
import model.Usuario;

public class TelaCadastro extends JFrame {
    private JTextField txtNome, txtEmail;
    private JPasswordField txtSenha;
    private JButton btnCadastrar;

    private final Color fundo = new Color(18, 18, 18);
    private final Color verde = new Color(30, 215, 96);
    private final Color branco = Color.WHITE;
    private final Color cinzaEscuro = new Color(40, 40, 40);

    public TelaCadastro() {
        setTitle("Cadastro - Spotifei");
        setSize(300, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(fundo);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 80, 25);
        lblNome.setForeground(branco);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 20, 150, 25);
        txtNome.setBackground(cinzaEscuro);
        txtNome.setForeground(branco);
        txtNome.setCaretColor(branco);
        txtNome.setBorder(BorderFactory.createLineBorder(verde));
        add(txtNome);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 60, 80, 25);
        lblEmail.setForeground(branco);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(100, 60, 150, 25);
        txtEmail.setBackground(cinzaEscuro);
        txtEmail.setForeground(branco);
        txtEmail.setCaretColor(branco);
        txtEmail.setBorder(BorderFactory.createLineBorder(verde));
        add(txtEmail);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(20, 100, 80, 25);
        lblSenha.setForeground(branco);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(100, 100, 150, 25);
        txtSenha.setBackground(cinzaEscuro);
        txtSenha.setForeground(branco);
        txtSenha.setCaretColor(branco);
        txtSenha.setBorder(BorderFactory.createLineBorder(verde));
        add(txtSenha);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(100, 140, 120, 25);
        btnCadastrar.setBackground(verde);
        btnCadastrar.setForeground(branco);
        btnCadastrar.setFocusPainted(false);
        add(btnCadastrar);

        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Usuario u = new Usuario(txtNome.getText(), txtEmail.getText(), new String(txtSenha.getPassword()));
                if (UsuarioDAO.cadastrar(u)) {
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário.");
                }
            }
        });
    }
}
