import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class GuestHomeView extends JFrame {
    public JButton roomsButton, serviceButton, galleryButton, bookButton, contactButton, loginButton;
    public JLabel profile;
    public CustomButton membershipButton;
    Model model;
    Connection con;
    String email, photopath;
    public GuestHomeView(Model model, Connection con, String email, String photopath) {
        this.model = model;
        this.con = con;
        this.email = email;
        this.photopath = photopath;
        this.setTitle("User Homepage");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);

        PanelWithBackground bgPanel = new PanelWithBackground("guestHomeBackground.png");
        bgPanel.setLayout(null);

        ImageIcon homeImage = new ImageIcon("Home.png");
        JLabel home = new JLabel(new ImageIcon(homeImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        home.setBounds(70,15,50,50);
        bgPanel.add(home);

        JPanel menuBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        menuBarPanel.setBounds(170,28,1200,30);
        menuBarPanel.setOpaque(false);
        bgPanel.add(menuBarPanel);

        roomsButton = new JButton("ROOMS");
        roomsButton.setFocusable(false);
        roomsButton.setFont(new Font("Tahoma", Font.BOLD, 19));
        roomsButton.setForeground(new Color(204,51,0));
        roomsButton.setContentAreaFilled(false);
        roomsButton.setFocusPainted(false);
        roomsButton.setBorderPainted(true);
        roomsButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0)));
        menuBarPanel.add(roomsButton);
        roomsButton.addActionListener(new GuestHomeControl(this));
        menuBarPanel.add(Box.createHorizontalStrut(130));

        serviceButton = new JButton("SERVICE");
        serviceButton.setFocusable(false);
        serviceButton.setFont(new Font("Tahoma", Font.BOLD, 19));
        serviceButton.setForeground(new Color(204,51,0));
        serviceButton.setContentAreaFilled(false);
        serviceButton.setFocusPainted(false);
        serviceButton.setBorderPainted(true);
        serviceButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0)));
        serviceButton.addActionListener(new GuestHomeControl(this));
        menuBarPanel.add(serviceButton);
        menuBarPanel.add(Box.createHorizontalStrut(130));

        galleryButton = new JButton("GALLERY");
        galleryButton.setFocusable(false);
        galleryButton.setFont(new Font("Tahoma", Font.BOLD, 19));
        galleryButton.setForeground(new Color(204,51,0));
        galleryButton.setContentAreaFilled(false);
        galleryButton.setFocusPainted(false);
        galleryButton.setBorderPainted(true);
        galleryButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0)));
        menuBarPanel.add(galleryButton);
        galleryButton.addActionListener(new GuestHomeControl(this));
        menuBarPanel.add(Box.createHorizontalStrut(130));

        bookButton = new JButton("BOOK NOW");
        bookButton.setFocusable(false);
        bookButton.setFont(new Font("Tahoma", Font.BOLD, 19));
        bookButton.setForeground(new Color(204,51,0));
        bookButton.setContentAreaFilled(false);
        bookButton.setFocusPainted(false);
        bookButton.setBorderPainted(true);
        bookButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0)));
        menuBarPanel.add(bookButton);
        bookButton.addActionListener(new GuestHomeControl(this));
        menuBarPanel.add(Box.createHorizontalStrut(130));

        contactButton = new JButton("CONTACT");
        contactButton.setFocusable(false);
        contactButton.setFont(new Font("Tahoma", Font.BOLD, 19));
        contactButton.setForeground(new Color(204,51,0));
        contactButton.setContentAreaFilled(false);
        contactButton.setFocusPainted(false);
        contactButton.setBorderPainted(true);
        contactButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0)));
        menuBarPanel.add(contactButton);
        contactButton.addActionListener(new GuestHomeControl(this));
        menuBarPanel.add(Box.createHorizontalStrut(130));

        loginButton = new JButton("LOGIN");
        loginButton.setFocusable(false);
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 19));
        loginButton.setForeground(new Color(204,51,0));
        loginButton.setContentAreaFilled(false);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(true);
        loginButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0)));
        menuBarPanel.add(loginButton);
        loginButton.addActionListener(new GuestHomeControl(this));
        menuBarPanel.add(Box.createHorizontalStrut(130));

        ImageIcon profilePicture = new ImageIcon("guest.png");
        if(photopath == null){
            profilePicture = new ImageIcon("guest.png");
            this.photopath = "";
        }
        else if(!photopath.isEmpty() && photopath != null){
            profilePicture = new ImageIcon(photopath);
        }
        profile = new JLabel(new ImageIcon(profilePicture.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        profile.setOpaque(false);
        profile.setBounds(1400,15,50,50);
        bgPanel.add(profile);

        membershipButton = new CustomButton("Join Our Membership",40);
        membershipButton.setForeground(new Color(204,51,0));
        membershipButton.setFont(new Font("Tahoma", Font.BOLD, 25));
        membershipButton.setBackground(Color.WHITE);
        membershipButton.setBounds(1140,385,320,50);
        membershipButton.addActionListener(new GuestHomeControl(this));
        bgPanel.add(membershipButton);

        JPanel messagePanel = new JPanel(new GridLayout(3,1,0,-50));
        messagePanel.setBounds(265,120,1000,250);
        messagePanel.setBackground(new Color(1,1,1,100));
        bgPanel.add(messagePanel);

        JLabel title = new JLabel("Welcome to Sakura Stay's");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Times New Roman", Font.BOLD, 80));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        messagePanel.add(title);

        JLabel subtitle1 = new JLabel("Enjoy a comfortable stay and our");
        subtitle1.setForeground(Color.WHITE);
        subtitle1.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        subtitle1.setHorizontalAlignment(SwingConstants.CENTER);
        messagePanel.add(subtitle1);
        JLabel subtitle2 = new JLabel("best-in-class services.");
        subtitle2.setForeground(Color.WHITE);
        subtitle2.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        subtitle2.setHorizontalAlignment(SwingConstants.CENTER);
        messagePanel.add(subtitle2);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBounds(30,470,1463,310);
        bottomPanel.setOpaque(false);
        bgPanel.add(bottomPanel);

        JPanel picturePanel = new JPanel(new GridLayout(1,3));
        picturePanel.setOpaque(false);
        bottomPanel.add(picturePanel, BorderLayout.CENTER);

        ImageIcon home1Image = new ImageIcon("home1.jpg");
        JLabel home1 = new JLabel(new ImageIcon(home1Image.getImage().getScaledInstance(450, 300, Image.SCALE_SMOOTH)));
        picturePanel.add(home1);

        ImageIcon home2Image = new ImageIcon("home2.jpg");
        JLabel home2 = new JLabel(new ImageIcon(home2Image.getImage().getScaledInstance(450, 300, Image.SCALE_SMOOTH)));
        picturePanel.add(home2);

        ImageIcon home3Image = new ImageIcon("home3.jpg");
        JLabel home3 = new JLabel(new ImageIcon(home3Image.getImage().getScaledInstance(450, 300, Image.SCALE_SMOOTH)));
        picturePanel.add(home3);

        JLabel outHotelLabel = new JLabel("Our Hotel");
        outHotelLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        outHotelLabel.setForeground(new Color(204,51,0));
        outHotelLabel.setOpaque(false);
        outHotelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        outHotelLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        bottomPanel.add(outHotelLabel, BorderLayout.SOUTH);

        this.add(bgPanel);
        new GuestHomeController(this);
    }
}