import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class GalleryView extends JFrame {
    JLabel picture1, picture2, picture3;
    JButton previousButton, nextButton;
    CustomButton backButton;
    String email, photopath;
    Model model;
    Connection con;
    public GalleryView(Model model, Connection con, String email, String photopath) {
        this.model = model;
        this.con = con;
        this.email = email;
        this.photopath = photopath;
        this.setTitle("Gallery");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(255, 209, 229));

        ImageIcon bgImage = new ImageIcon("galleryTitle.png");
        JLabel title = new JLabel(new ImageIcon(bgImage.getImage().getScaledInstance(1536, 250, Image.SCALE_SMOOTH)));
        title.setBounds(0, 0, 1536, 250);
        this.add(title);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(20, 270, 1536-40, 500);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(0, 0, 1536-40, 500);
        layeredPane.add(mainPanel,JLayeredPane.DEFAULT_LAYER);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBounds(40, 290, 1536-80, 350);
        this.add(contentPanel);

        JPanel picturePanel = new JPanel(new GridLayout(1,3));
        picturePanel.setBackground(Color.WHITE);
        picturePanel.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
        contentPanel.add(picturePanel, BorderLayout.CENTER);

        ImageIcon gallery1Image = new ImageIcon("gallery1.png");
        picture1 = new JLabel(new ImageIcon(gallery1Image.getImage().getScaledInstance(400, 280, Image.SCALE_SMOOTH)));
        picturePanel.add(picture1);

        ImageIcon gallery2Image = new ImageIcon("gallery2.png");
        picture2 = new JLabel(new ImageIcon(gallery2Image.getImage().getScaledInstance(400, 280, Image.SCALE_SMOOTH)));
        picturePanel.add(picture2);

        ImageIcon gallery3Image = new ImageIcon("gallery3.png");
        picture3 = new JLabel(new ImageIcon(gallery3Image.getImage().getScaledInstance(400, 280, Image.SCALE_SMOOTH)));
        picturePanel.add(picture3);

        JPanel buttonPanel = new JPanel(new GridLayout(1,6,40,0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        previousButton = new JButton("<");
        previousButton.setFocusable(false);
        previousButton.setFont(new Font("Tahoma", Font.BOLD, 25));
        previousButton.setForeground(new Color(204,51,0));
        previousButton.setBackground(Color.WHITE);
        previousButton.setContentAreaFilled(false);
        previousButton.setVisible(false);
        buttonPanel.add(previousButton);

        nextButton = new JButton(">");
        nextButton.setFocusable(false);
        nextButton.setFont(new Font("Tahoma", Font.BOLD, 25));
        nextButton.setForeground(new Color(204,51,0));
        nextButton.setBackground(Color.WHITE);
        nextButton.setContentAreaFilled(false);
        nextButton.setVisible(false);
        buttonPanel.add(nextButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());

        JLabel enjoyLabel = new JLabel("Enjoy The Vibes");
        enjoyLabel.setBounds(630, 650, 400, 50);
        enjoyLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        enjoyLabel.setForeground(new Color(204,51,0));
        this.add(enjoyLabel);

        backButton = new CustomButton("BACK",30);
        backButton.setBounds(40, 715, 200, 40);
        backButton.setFocusable(false);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 25));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(255, 118, 118));
        backButton.addActionListener(e->{
            GalleryView.this.dispose();
            GuestHomeView guestHomeView = new GuestHomeView(model, con, email, photopath);
            guestHomeView.setVisible(true);
        });
        this.add(backButton);

        this.add(layeredPane);
    }
}
