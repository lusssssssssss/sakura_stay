package view;

import component.CustomButton;

import javax.swing.*;
import java.awt.*;

public class AdminLoginView extends JFrame {
    public JTextField emailField;
    public JPasswordField passField;
    public CustomButton loginButton;

    public AdminLoginView() {
        this.setTitle("Admin - Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1536, 864));

        ImageIcon bgImage = new ImageIcon("picture/adminLoginBackground.jpg");
        JLabel background = new JLabel(new ImageIcon(bgImage.getImage().getScaledInstance(1536, 864, Image.SCALE_SMOOTH)));
        background.setBounds(0, 0, 1536, 864);
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);

        JLabel titleLabel = new JLabel("ADMIN LOGIN");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
        titleLabel.setForeground(Color.RED);
        titleLabel.setBounds(560,200,400,60);
        this.add(titleLabel);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new GridLayout(2,2,-100,60));
        formPanel.setBounds(520,340,450,130);
        this.add(formPanel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.RED);
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        formPanel.add(emailLabel);

        emailField = new JTextField(15);
        emailField.setBorder(BorderFactory.createLineBorder(Color.RED));
        emailField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        formPanel.add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.RED);
        passLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        formPanel.add(passLabel);

        passField = new JPasswordField(15);
        passField.setBorder(BorderFactory.createLineBorder(Color.RED));
        passField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        formPanel.add(passField);

        loginButton = new CustomButton("LOGIN",30);
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 25));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.RED);
        loginButton.setFocusable(false);
        loginButton.setBounds(637,580,220,60);
        this.add(loginButton);

        this.add(layeredPane);
    }
}