package view;

import javax.swing.*;
import java.awt.*;
import dao.UsuarioDAO;
import model.Usuario;

public class TelaLogin extends JFrame {
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JButton btnLogin, btnCadastro;

    private final Color fundo = new Color(18, 18, 18);
    private final Color verde = new Color(30, 215, 96);
    private final Color branco = Color.WHITE;
    private final Color cinzaEscuro = new Color(40, 40, 40);

    public TelaLogin() {
        setTitle("Login - Spotifei");
        setSize(300, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(fundo);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 20, 80, 25);
        lblEmail.setForeground(branco);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(100, 20, 150, 25);
        txtEmail.setBackground(cinzaEscuro);
        txtEmail.setForeground(branco);
        txtEmail.setCaretColor(branco);
        txtEmail.setBorder(BorderFactory.createLineBorder(verde));
        add(txtEmail);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(20, 60, 80, 25);
        lblSenha.setForeground(branco);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(100, 60, 150, 25);
        txtSenha.setBackground(cinzaEscuro);
        txtSenha.setForeground(branco);
        txtSenha.setCaretColor(branco);
        txtSenha.setBorder(BorderFactory.createLineBorder(verde));
        add(txtSenha);

        btnLogin = new JButton("Entrar");
        btnLogin.setBounds(100, 100, 80, 25);
        btnLogin.setBackground(verde);
        btnLogin.setForeground(branco);
        btnLogin.setFocusPainted(false);
        add(btnLogin);

        btnCadastro = new JButton("Cadastrar");
        btnCadastro.setBounds(100, 130, 120, 25);
        btnCadastro.setBackground(verde);
        btnCadastro.setForeground(branco);
        btnCadastro.setFocusPainted(false);
        add(btnCadastro);

        btnLogin.addActionListener(e -> {
            Usuario u = UsuarioDAO.autenticar(txtEmail.getText(), new String(txtSenha.getPassword()));
            if (u != null) {
                util.Sessao.emailUsuario = u.getEmail();  // guarda o email do usuário logado
                JOptionPane.showMessageDialog(null, "Bem-vindo, " + u.getNome());
                new TelaMenuInicial().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Credenciais inválidas.");
            }
        });

        btnCadastro.addActionListener(e -> {
            new TelaCadastro().setVisible(true);
            dispose();
        });
    }
}
