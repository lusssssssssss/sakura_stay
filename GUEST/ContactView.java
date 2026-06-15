import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class ContactView extends JFrame {
    public CustomButton backButton;
    String email, photopath;
    Model model;
    Connection con;
    public ContactView(Model model, Connection con, String email, String photopath) {
        this.model = model;
        this.con = con;
        this.email = email;
        this.photopath = photopath;
        this.setTitle("Contact");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(255, 209, 229));

        ImageIcon bgImage = new ImageIcon("contactTitle.jpg");
        JLabel title = new JLabel(new ImageIcon(bgImage.getImage().getScaledInstance(1536, 250, Image.SCALE_SMOOTH)));
        title.setBounds(0, 0, 1536, 250);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.setBounds(20, 270, 1485, 500);

        JPanel info_panel = new JPanel(new GridLayout(1,2,60,0));
        info_panel.setBackground(Color.WHITE);
        mainPanel.add(info_panel, BorderLayout.CENTER);

        JPanel map_panel = new JPanel(new BorderLayout());
        map_panel.setBackground(Color.WHITE);
        info_panel.add(map_panel);

        JPanel location_panel = new JPanel();
        location_panel.setBackground(Color.WHITE);
        JLabel location_label = new JLabel("Our Location");
        location_label.setFont(new Font("Arial", Font.BOLD, 30));
        location_label.setForeground(new Color(188,0,45));
        location_panel.add(location_label);
        map_panel.add(location_panel, BorderLayout.NORTH);

        ImageIcon mapImage = new ImageIcon("map.jpg");
        JLabel map = new JLabel(new ImageIcon(mapImage.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH)));
        map_panel.add(map, BorderLayout.CENTER);

        JPanel contact_panel = new JPanel(new GridLayout(3,1,0,10));
        contact_panel.setBackground(Color.WHITE);
        contact_panel.setBorder(BorderFactory.createEmptyBorder(45,0,0,0));
        info_panel.add(contact_panel);

        ImageIcon instagramImage = new ImageIcon("instagram.png");
        JLabel instagram = new JLabel(new ImageIcon(instagramImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        instagram.setText("@sakura_stay");
        instagram.setFont(new Font("Arial", Font.BOLD, 40));
        instagram.setForeground(new Color(188,0,45));
        instagram.setHorizontalTextPosition(SwingConstants.RIGHT);
        instagram.setVerticalTextPosition(SwingConstants.CENTER);
        instagram.setHorizontalAlignment(SwingConstants.LEFT);
        instagram.setIconTextGap(30);
        contact_panel.add(instagram);

        ImageIcon phoneImage = new ImageIcon("phone.png");
        JLabel phone = new JLabel(new ImageIcon(phoneImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        phone.setText("089282726281");
        phone.setFont(new Font("Arial", Font.BOLD, 40));
        phone.setForeground(new Color(188,0,45));
        phone.setHorizontalTextPosition(SwingConstants.RIGHT);
        phone.setVerticalTextPosition(SwingConstants.CENTER);
        phone.setHorizontalAlignment(SwingConstants.LEFT);
        phone.setIconTextGap(30);
        contact_panel.add(phone);

        ImageIcon webImage = new ImageIcon("web.png");
        JLabel web = new JLabel(new ImageIcon(webImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        web.setText("https//sakurastay.com");
        web.setFont(new Font("Arial", Font.BOLD, 40));
        web.setForeground(new Color(188,0,45));
        web.setHorizontalTextPosition(SwingConstants.RIGHT);
        web.setVerticalTextPosition(SwingConstants.CENTER);
        web.setHorizontalAlignment(SwingConstants.LEFT);
        web.setIconTextGap(30);
        contact_panel.add(web);

        JPanel buttonPanel = new JPanel(new GridLayout(1,6,40,0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        buttonPanel.setBackground(Color.WHITE);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        backButton = new CustomButton("BACK",30);
        backButton.setFocusable(false);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 25));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(255, 118, 118));
        buttonPanel.add(backButton);
        backButton.addActionListener(e -> {
            ContactView.this.dispose();
            GuestHomeView guestHomeView = new GuestHomeView(model, con, email, photopath);
            guestHomeView.setVisible(true);
        });
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());

        this.add(title);
        this.add(mainPanel);
    }
}
