import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class MEMBERSHIPINFOFRAME extends JFrame {
    JButton toRegisframeButton;
    BACKBUTTON backbutton;
    Model model;
    Connection con;
    String email, photopath;
    MEMBERSHIPINFOFRAME(Model model, Connection con, String email, String photopath) {
        this.model = model;
        this.con = con;
        this.email = email;
        this.photopath = photopath;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(width, height);

        ImageIcon bgicon = new ImageIcon("background2.png");
        Image bgimage = bgicon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon background = new ImageIcon(bgimage);
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, width, height);

        ImageIcon asset1icon = new ImageIcon("1749388250202.png");
        Image asset1img = asset1icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon asset1 = new ImageIcon(asset1img);
        JLabel asset1label = new JLabel(asset1);
        asset1label.setBounds(1150, 150, 300, 300);

        ImageIcon asset2icon = new ImageIcon("1749388250202.png");
        Image asset2img = asset2icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon asset2 = new ImageIcon(asset2img);
        JLabel asset2label = new JLabel(asset2);
        asset2label.setBounds(10, 400, 300, 300);

        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBounds(0,50,width,200);
        JLabel titleLabel = new JLabel("MEMBERSHIP");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 100));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        JLabel penjelasantitle = new JLabel("Join Our Sakura Stay Membership");
        penjelasantitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        penjelasantitle.setFont(new Font("Arial", Font.BOLD, 60));
        penjelasantitle.setForeground(Color.WHITE);
        headerPanel.add(penjelasantitle);

        JPanel pinkpanel = new JPanel();
        pinkpanel.setLayout(new BoxLayout(pinkpanel, BoxLayout.Y_AXIS));
        pinkpanel.setBackground(new Color(255, 118, 118));
        pinkpanel.setBounds(0,350,width,180);

        pinkpanel.add(Box.createVerticalStrut(30));
        JLabel labelpanjang1 = new JLabel("Enjoy Exclusive Benefits! Become a part of the Sakura Stay Membership and unlock a world of comfort and rewards. As a");
        labelpanjang1.setForeground(Color.WHITE);
        labelpanjang1.setFont(new Font("Arial", Font.BOLD, 20));
        labelpanjang1.setAlignmentX(Component.CENTER_ALIGNMENT);
        pinkpanel.add(labelpanjang1);
        JLabel labelpanjang2 = new JLabel("valued member, you'll enjoy exclusive vouchers, special promotions, and priority access to our best deals. Whether you're");
        labelpanjang2.setForeground(Color.WHITE);
        labelpanjang2.setFont(new Font("Arial", Font.BOLD, 20));
        labelpanjang2.setAlignmentX(Component.CENTER_ALIGNMENT);
        pinkpanel.add(labelpanjang2);
        JLabel labelpanjang3 = new JLabel("planning a short getaway or an extended stay, our membership is designed to make every visit more rewarding.");
        labelpanjang3.setForeground(Color.WHITE);
        labelpanjang3.setFont(new Font("Arial", Font.BOLD, 20));
        labelpanjang3.setAlignmentX(Component.CENTER_ALIGNMENT);
        pinkpanel.add(labelpanjang3);
        JLabel labelpanjang4 = new JLabel("✨ Get a free voucher upon joining – your next stay could be on us!");
        labelpanjang4.setForeground(Color.WHITE);
        labelpanjang4.setFont(new Font("Arial", Font.BOLD, 20));
        labelpanjang4.setAlignmentX(Component.CENTER_ALIGNMENT);
        pinkpanel.add(labelpanjang4);
        JLabel labelpanjang5 = new JLabel("Sign up today and experience hospitality the Sakura Stay way.");
        labelpanjang5.setForeground(Color.WHITE);
        labelpanjang5.setFont(new Font("Arial", Font.BOLD, 20));
        labelpanjang5.setAlignmentX(Component.CENTER_ALIGNMENT);
        pinkpanel.add(labelpanjang5);

        JPanel typepanel = new JPanel(new GridLayout(1,3,50,10));
        typepanel.setOpaque(false);
        typepanel.setBounds(150,550,1212,200);

        JPanel gold = new JPanel(new BorderLayout());
        gold.setBackground(new Color(255, 183, 197));
        JLabel goldlabel = new JLabel("Gold", JLabel.CENTER);
        goldlabel.setForeground(Color.WHITE);
        goldlabel.setFont(new Font("Arial", Font.BOLD, 100));
        gold.add(goldlabel, BorderLayout.CENTER);

        JLabel goldprice = new JLabel("Rp1.000.000,00", JLabel.CENTER);
        goldprice.setForeground(Color.WHITE);
        goldprice.setFont(new Font("Arial", Font.BOLD, 30));
        goldprice.setVisible(false);
        gold.add(goldprice, BorderLayout.SOUTH);
        typepanel.add(gold);

        JPanel silver = new JPanel(new BorderLayout());
        silver.setBackground(new Color(255, 183, 197));
        JLabel silverlabel = new JLabel("Silver", JLabel.CENTER);
        silverlabel.setForeground(Color.WHITE);
        silverlabel.setFont(new Font("Arial", Font.BOLD, 100));
        silver.add(silverlabel, BorderLayout.CENTER);

        JLabel silverprice = new JLabel("Rp500.000,00", JLabel.CENTER);
        silverprice.setForeground(Color.WHITE);
        silverprice.setFont(new Font("Arial", Font.BOLD, 30));
        silverprice.setVisible(false);
        silver.add(silverprice, BorderLayout.SOUTH);
        typepanel.add(silver);

        JPanel bronze = new JPanel(new BorderLayout());
        bronze.setBackground(new Color(255, 183, 197));
        JLabel bronzelabel = new JLabel("Bronze", JLabel.CENTER);
        bronzelabel.setForeground(Color.WHITE);
        bronzelabel.setFont(new Font("Arial", Font.BOLD, 100));
        bronze.add(bronzelabel, BorderLayout.CENTER);

        JLabel bronzeprice = new JLabel("Rp250.000,00", JLabel.CENTER);
        bronzeprice.setForeground(Color.WHITE);
        bronzeprice.setFont(new Font("Arial", Font.BOLD, 30));
        bronzeprice.setVisible(false);
        bronze.add(bronzeprice, BorderLayout.SOUTH);
        typepanel.add(bronze);

        gold.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                gold.setBackground(new Color(255, 215, 0));
                goldprice.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                gold.setBackground(new Color(255, 183, 197));
                goldprice.setVisible(false);
            }
        });

        silver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                silver.setBackground(new Color(192, 192, 192));
                silverprice.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                silver.setBackground(new Color(255, 183, 197));
                silverprice.setVisible(false);
            }
        });

        bronzelabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bronze.setBackground(new Color(205, 127, 50));
                bronzeprice.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bronze.setBackground(new Color(255, 183, 197));
                bronzeprice.setVisible(false);
            }
        });

        JPanel buttonpanel = new JPanel();
        buttonpanel.setOpaque(false);
        buttonpanel.setBounds(0,800,width,50);
        toRegisframeButton = new JButton("Register Now!");
        toRegisframeButton.setForeground(Color.WHITE);
        toRegisframeButton.setFont(new Font("Arial", Font.BOLD, 20));
        toRegisframeButton.setBackground(new Color(204, 51, 0));
        toRegisframeButton.setOpaque(true);
        toRegisframeButton.setFocusPainted(false);
        toRegisframeButton.setBorderPainted(false);
        toRegisframeButton.addActionListener(new MEMBERSHIPINFOFRAMECONTROL(this));
        buttonpanel.add(toRegisframeButton);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(backgroundLabel,Integer.valueOf(0));
        layeredPane.add(headerPanel,Integer.valueOf(1));
        layeredPane.add(pinkpanel,Integer.valueOf(2));
        layeredPane.add(typepanel,Integer.valueOf(3));
        layeredPane.add(buttonpanel,Integer.valueOf(4));
        layeredPane.add(asset1label,Integer.valueOf(5));
        layeredPane.add(asset2label,Integer.valueOf(6));
        backbutton = new BACKBUTTON(30,820);
        backbutton.addActionListener(new MEMBERSHIPINFOFRAMECONTROL(this));
        layeredPane.add(backbutton,Integer.valueOf(7));

        this.setContentPane(layeredPane);
    }
}
