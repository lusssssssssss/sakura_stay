import javax.swing.*;
import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ServiceScrollView extends JFrame {
    CustomButton backButton;
    String email, photopath;
    Model model;
    Connection con;

    public ServiceScrollView(Model model, Connection con, String email, String photopath) throws SQLException {
        this.model = model;
        this.con = con;
        this.email = email;
        this.photopath = photopath;
        this.setTitle("Service");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(255, 209, 229));

        ImageIcon bgImage = new ImageIcon("serviceTitle.png");
        JLabel title = new JLabel(new ImageIcon(bgImage.getImage().getScaledInstance(1536, 250, Image.SCALE_SMOOTH)));
        title.setBounds(0, 0, 1536, 250);
        this.add(title);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(20, 270, 1536-40, 500);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(0, 0, 1536-40, 500);
        layeredPane.add(mainPanel,JLayeredPane.DEFAULT_LAYER);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBounds(40, 290, 1536-80, 410);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(Box.createVerticalStrut(15));

        boolean bdis = model.isdisabled(con, "SERVE-B");
        ImageIcon breakfastImage = new ImageIcon("japanese breakfast.png");
        JLabel japaneseBreakfast = new JLabel(new ImageIcon(breakfastImage.getImage().getScaledInstance(650, 230, Image.SCALE_SMOOTH)));
        japaneseBreakfast.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(japaneseBreakfast);
        contentPanel.add(Box.createVerticalStrut(15));

        JLabel breakfastLabel = new JLabel("Japanese-Style Breakfast");
        breakfastLabel.setForeground(new Color(188,0,45));
        breakfastLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        breakfastLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(breakfastLabel);
        contentPanel.add(Box.createVerticalStrut(5));

        JLabel breakfastIsi1 = new JLabel("Start your morning with a Japanese-style breakfast, featuring a beautifully arranged selection of steamed rice, grilled fish, miso soup,");
        breakfastIsi1.setAlignmentX(Component.CENTER_ALIGNMENT);
        breakfastIsi1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPanel.add(breakfastIsi1);
        JLabel breakfastIsi2 = new JLabel("pickled vegetables, and green tea — a nourishing and authentic culinary experience that reflects Japan’s rich heritage.");
        breakfastIsi2.setAlignmentX(Component.CENTER_ALIGNMENT);
        breakfastIsi2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPanel.add(breakfastIsi2);
        contentPanel.add(Box.createVerticalStrut(5));
        JLabel breakfastIsi3 = new JLabel("(+Rp.150000)");
        breakfastIsi3.setAlignmentX(Component.CENTER_ALIGNMENT);
        breakfastIsi3.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPanel.add(breakfastIsi3);
        contentPanel.add(Box.createVerticalStrut(80));

        boolean yout = model.isout(con, "SERVE-Y");
        boolean ydis = model.isdisabled(con, "SERVE-Y");
        ImageIcon yukataImage = new ImageIcon("yukata.png");
        JLabel yukata = new JLabel(new ImageIcon(yukataImage.getImage().getScaledInstance(650, 230, Image.SCALE_SMOOTH)));
        yukata.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(yukata);
        contentPanel.add(Box.createVerticalStrut(15));

        JLabel yukataLabel = new JLabel("Yukata Rental");
        yukataLabel.setForeground(new Color(188,0,45));
        yukataLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        yukataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(yukataLabel);
        contentPanel.add(Box.createVerticalStrut(5));

        JLabel yukataIsi1 =  new JLabel("Experience traditional Japanese charm by renting a yukata — a casual summer kimono perfect for relaxing or exploring. Choose from");
        yukataIsi1.setAlignmentX(Component.CENTER_ALIGNMENT);
        yukataIsi1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPanel.add(yukataIsi1);
        JLabel yukataIsi2 =  new JLabel("from elegant designs and enjoy a unique cultural touch during your stay. Rentals include matching accessories for a complete");
        yukataIsi2.setAlignmentX(Component.CENTER_ALIGNMENT);
        yukataIsi2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPanel.add(yukataIsi2);
        JLabel yukataIsi3 =  new JLabel("and memorable experience.");
        yukataIsi3.setAlignmentX(Component.CENTER_ALIGNMENT);
        yukataIsi3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPanel.add(yukataIsi3);
        contentPanel.add(Box.createVerticalStrut(5));
        JLabel yukataIsi4 =  new JLabel("(+Rp.50000/pcs)");
        yukataIsi4.setAlignmentX(Component.CENTER_ALIGNMENT);
        yukataIsi4.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPanel.add(yukataIsi4);
        contentPanel.add(Box.createVerticalStrut(80));

        boolean pout = model.isout(con, "SERVE-P");
        boolean pdis = model.isdisabled(con, "SERVE-P");
        ImageIcon photoShootImage = new ImageIcon("photo session.png");
        JLabel photoSession = new JLabel(new ImageIcon(photoShootImage.getImage().getScaledInstance(650, 230, Image.SCALE_SMOOTH)));
        photoSession.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(photoSession);
        contentPanel.add(Box.createVerticalStrut(15));

        JLabel photoSessionLabel = new JLabel("Photo session at the cherry blossom garden");
        photoSessionLabel.setForeground(new Color(188,0,45));
        photoSessionLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        photoSessionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(photoSessionLabel);
        contentPanel.add(Box.createVerticalStrut(5));

        JLabel photoSessionIsi1 = new JLabel("Capture the beauty of the moment with a photo session at the cherry blossom garden. Surrounded by blooming sakura trees,");
        photoSessionIsi1.setAlignmentX(Component.CENTER_ALIGNMENT);
        photoSessionIsi1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPanel.add(photoSessionIsi1);
        JLabel photoSessionIsi2 = new JLabel("this unforgettable experience lets you take home timeless memories in a truly magical setting.");
        photoSessionIsi2.setAlignmentX(Component.CENTER_ALIGNMENT);
        photoSessionIsi2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPanel.add(photoSessionIsi2);
        contentPanel.add(Box.createVerticalStrut(5));
        JLabel photoSessionIsi3 = new JLabel("(+Rp.200000/session)");
        photoSessionIsi3.setAlignmentX(Component.CENTER_ALIGNMENT);
        photoSessionIsi3.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPanel.add(photoSessionIsi3);
        contentPanel.add(Box.createVerticalStrut(80));

        boolean xdis = model.isdisabled(con, "SERVE-X");
        ImageIcon extraBedImage = new ImageIcon("extra bed.jpg");
        JLabel extraBed = new JLabel(new ImageIcon(extraBedImage.getImage().getScaledInstance(650, 230, Image.SCALE_SMOOTH)));
        extraBed.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(extraBed);
        contentPanel.add(Box.createVerticalStrut(15));

        JLabel extraBedLabel = new JLabel("Extra bed");
        extraBedLabel.setForeground(new Color(188,0,45));
        extraBedLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        extraBedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(extraBedLabel);
        contentPanel.add(Box.createVerticalStrut(5));

        JLabel extraBedIsi1 = new JLabel("Need more space for a restful night? Our Extra Bed Service offers a comfortable and convenient sleeping solution for");
        extraBedIsi1.setAlignmentX(Component.CENTER_ALIGNMENT);
        extraBedIsi1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPanel.add(extraBedIsi1);
        JLabel extraBedIsi2 = new JLabel("additional guests. Perfect for families or small groups, each bed is set up with fresh linens and hotel-quality bedding");
        extraBedIsi2.setAlignmentX(Component.CENTER_ALIGNMENT);
        extraBedIsi2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPanel.add(extraBedIsi2);
        JLabel extraBedIsi3 = new JLabel("to ensure a pleasant and relaxing stay.");
        extraBedIsi3.setAlignmentX(Component.CENTER_ALIGNMENT);
        extraBedIsi3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPanel.add(extraBedIsi3);
        contentPanel.add(Box.createVerticalStrut(5));
        JLabel extraBedIsi4 = new JLabel("(+Rp.150000)");
        extraBedIsi4.setAlignmentX(Component.CENTER_ALIGNMENT);
        extraBedIsi4.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPanel.add(extraBedIsi4);
        contentPanel.add(Box.createVerticalStrut(80));

        boolean cdis = model.isdisabled(con, "SERVE-C");
        ImageIcon babyCotImage = new ImageIcon("baby cot.jpg");
        JLabel babyCot = new JLabel(new ImageIcon(babyCotImage.getImage().getScaledInstance(650, 230, Image.SCALE_SMOOTH)));
        babyCot.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(babyCot);
        contentPanel.add(Box.createVerticalStrut(15));

        JLabel babyCotLabel = new JLabel("Baby Cot");
        babyCotLabel.setForeground(new Color(188,0,45));
        babyCotLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        babyCotLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(babyCotLabel);
        contentPanel.add(Box.createVerticalStrut(5));

        JLabel babyCotIsi1 = new JLabel("Traveling with a little one? Our Baby Cot Service provides a safe and cozy sleeping space for your baby, complete with soft");
        babyCotIsi1.setAlignmentX(Component.CENTER_ALIGNMENT);
        babyCotIsi1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPanel.add(babyCotIsi1);
        JLabel babyCotIsi2 = new JLabel("bedding and sturdy design. Enjoy peace of mind knowing your child can rest comfortably, allowing the whole family");
        babyCotIsi2.setAlignmentX(Component.CENTER_ALIGNMENT);
        babyCotIsi2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPanel.add(babyCotIsi2);
        JLabel babyCotIsi3 = new JLabel("to relax and enjoy the stay.");
        babyCotIsi3.setAlignmentX(Component.CENTER_ALIGNMENT);
        babyCotIsi3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPanel.add(babyCotIsi3);
        contentPanel.add(Box.createVerticalStrut(5));
        JLabel babyCotIsi4 = new JLabel("(+Rp.100000)");
        babyCotIsi4.setAlignmentX(Component.CENTER_ALIGNMENT);
        babyCotIsi4.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPanel.add(babyCotIsi4);
        contentPanel.add(Box.createVerticalStrut(11));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBounds(40, 290, 1536-80, 410);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.RED));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);

        updatepicture(bdis, japaneseBreakfast);

        updatepicture(yout, yukata);
        updatepicture(ydis, yukata);

        updatepicture(pout, photoSession);
        updatepicture(pdis, photoSession);

        updatepicture(cdis, babyCot);

        updatepicture(xdis, extraBed);

        backButton = new CustomButton("BACK",30);
        backButton.setBounds(40, 715, 200, 40);
        backButton.setFocusable(false);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 25));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(255, 118, 118));
        backButton.addActionListener(e->{
            ServiceScrollView.this.dispose();
            GuestHomeView guestHomeView = new GuestHomeView(model, con, email, photopath);
            guestHomeView.setVisible(true);
        });
        this.add(backButton);

        this.add(layeredPane);
    }

    private void updatepicture(boolean condition, JLabel label) {
        if(condition) {
            ImageIcon icon = (ImageIcon) label.getIcon();
            Image grayImage = Toolkit.getDefaultToolkit().createImage(
                    new FilteredImageSource(icon.getImage().getSource(),
                            new GrayFilter(true, 50))
            ).getScaledInstance(650, 230, Image.SCALE_SMOOTH);
            ImageIcon tidaktersedia = new ImageIcon(grayImage);
            label.setIcon(tidaktersedia);
            label.setText("NOT AVAILABLE");
            label.setHorizontalTextPosition(SwingConstants.CENTER);
            label.setVerticalTextPosition(SwingConstants.CENTER);
            label.setForeground(Color.RED);
            label.setFont(new Font("Tahoma", Font.PLAIN, 20));
        }
    }
}