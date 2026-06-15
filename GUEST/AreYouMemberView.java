import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class AreYouMemberView extends JFrame {
    public JLabel registerLabel, guest_label;
    public JTextField emailField;
    public JPasswordField passField;
    public CustomButton loginButton;
    Model model;
    Connection con;

    public AreYouMemberView(Model model, Connection con) {
        this.model = model;
        this.con = con;
        this.setTitle("Guest Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1536, 864));

        ImageIcon bgImage = new ImageIcon("Design ALP APDEVXDATABASE.jpg");
        JLabel background = new JLabel(new ImageIcon(bgImage.getImage().getScaledInstance(1536, 864, Image.SCALE_SMOOTH)));
        background.setBounds(0, 0, 1536, 864);
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);

        JLabel titleLabel = new JLabel("ARE YOU A MEMBER?");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        titleLabel.setForeground(Color.RED);
        titleLabel.setBounds(530,200,500,60);
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
        loginButton.setBounds(637,560,220,60);
        loginButton.addActionListener(new AreYouMemberControl(this));
        this.add(loginButton);

        registerLabel = new JLabel("Don't have an account?");
        registerLabel.setForeground(Color.RED);
        registerLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        registerLabel.setBounds(660,610,220,60);
        this.add(registerLabel);

        guest_label = new JLabel("Continue as Guest");
        guest_label.setForeground(Color.RED);
        guest_label.setFont(new Font("Tahoma", Font.BOLD, 15));
        guest_label.setBounds(675,635,220,60);
        guest_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AreYouMemberView.this.dispose();
                GuestHomeView guestHomeView = new GuestHomeView(model, con, "","");
                guestHomeView.setVisible(true);
            }
        });
        this.add(guest_label);

        this.add(layeredPane);
    }
}