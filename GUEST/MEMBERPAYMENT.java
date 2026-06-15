import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MEMBERPAYMENT extends JFrame {
    JButton payment;
    JLabel selectedpayment, price, name, email, phone, membertype; //roomtype, price, sama longdate set text sesuai databse
    BACKBUTTON backbutton;
    String membername, memberemail, memberphone, type, address, birth, pass, photopath;
    int memberprice;
    Model model;
    Connection con;
    String getphotopath;

    MEMBERPAYMENT(Model model, Connection con, String membername, String memberemail, String memberphone, String address,
                  String birth, String pass, String type, int memberprice, String photopath) {
        this.model = model;
        this.con = con;
        this.address = address;
        this.birth = birth;
        this.pass = pass;
        this.membername = membername;
        this.memberemail = memberemail;
        this.memberphone = memberphone;
        this.type = type;
        this.memberprice = memberprice;
        this.photopath = photopath;
        getphotopath = photopath;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(width, height);

        ImageIcon icon = new ImageIcon("backgroundPayment.png");
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon background = new ImageIcon(image);
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, width, height);

        ImageIcon asset1 = new ImageIcon("1749457491921.png");
        Image image1 = asset1.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        ImageIcon asset = new ImageIcon(image1);
        JLabel assetLabel = new JLabel(asset);
        assetLabel.setBounds(0, 400, 400, 400);

        JPanel header = new JPanel(new BorderLayout());
        header.setBounds(0,70,width,200);
        header.setOpaque(false);
        JLabel title = new JLabel("PAYMENT");
        title.setFont(new Font("Tahoma", Font.BOLD, 150));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        header.add(title, BorderLayout.CENTER);

        JPanel paneltengah = new JPanel(new GridLayout(1,2,50,10));
        paneltengah.setOpaque(false);
        paneltengah.setBounds(50,350,1412,400);

        JPanel paymentpanel = new JPanel(new GridLayout(2,3));
        paymentpanel.setBackground(new Color(255, 209, 229));
        paymentpanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

        JLabel cc = new JLabel("Credit Card");
        cc.setFont(new Font("Tahoma", Font.PLAIN, 20));
        cc.setForeground(Color.WHITE);
        cc.setHorizontalTextPosition(SwingConstants.CENTER);
        cc.setVerticalTextPosition(SwingConstants.BOTTOM);
        ImageIcon ccicon = new ImageIcon("logo-cc.png");
        Image ccimg = ccicon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon cclogo = new ImageIcon(ccimg);
        cc.setIcon(cclogo);
        paymentpanel.add(cc);

        JLabel dc = new JLabel("Debit Card");
        dc.setFont(new Font("Tahoma", Font.PLAIN, 20));
        dc.setForeground(Color.WHITE);
        dc.setHorizontalTextPosition(SwingConstants.CENTER);
        dc.setVerticalTextPosition(SwingConstants.BOTTOM);
        ImageIcon dcicon = new ImageIcon("logo-dc.png");
        Image dcimg = dcicon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon dclogo = new ImageIcon(dcimg);
        dc.setIcon(dclogo);
        paymentpanel.add(dc);

        JLabel gopay = new JLabel("Gopay");
        gopay.setFont(new Font("Tahoma", Font.PLAIN, 20));
        gopay.setForeground(Color.WHITE);
        gopay.setHorizontalTextPosition(SwingConstants.CENTER);
        gopay.setVerticalTextPosition(SwingConstants.BOTTOM);
        ImageIcon gpicon = new ImageIcon("logo-gopay.png");
        Image gpimg = gpicon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon gplogo = new ImageIcon(gpimg);
        gopay.setIcon(gplogo);
        paymentpanel.add(gopay);

        JLabel dana = new JLabel("Dana");
        dana.setFont(new Font("Tahoma", Font.PLAIN, 20));
        dana.setForeground(Color.WHITE);
        dana.setHorizontalTextPosition(SwingConstants.CENTER);
        dana.setVerticalTextPosition(SwingConstants.BOTTOM);
        ImageIcon danaicon = new ImageIcon("logo-dana.png");
        Image danaimg = danaicon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon danalogo = new ImageIcon(danaimg);
        dana.setIcon(danalogo);
        paymentpanel.add(dana);

        JLabel spay = new JLabel("Shopee Pay");
        spay.setFont(new Font("Tahoma", Font.PLAIN, 20));
        spay.setForeground(Color.WHITE);
        spay.setHorizontalTextPosition(SwingConstants.CENTER);
        spay.setVerticalTextPosition(SwingConstants.BOTTOM);
        ImageIcon spayicon = new ImageIcon("logo-spay.png");
        Image spayimg = spayicon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon spaylogo = new ImageIcon(spayimg);
        spay.setIcon(spaylogo);
        paymentpanel.add(spay);

        JLabel ovo = new JLabel("Ovo");
        ovo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ovo.setForeground(Color.WHITE);
        ovo.setHorizontalTextPosition(SwingConstants.CENTER);
        ovo.setVerticalTextPosition(SwingConstants.BOTTOM);
        ImageIcon ovoicon = new ImageIcon("logo-ovo.png");
        Image ovoimg = ovoicon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon ovologo = new ImageIcon(ovoimg);
        ovo.setIcon(ovologo);
        paymentpanel.add(ovo);
        paneltengah.add(paymentpanel);

        JPanel invoicePanel = new JPanel(new BorderLayout());
        invoicePanel.setBackground(new Color(255, 209, 229));
        JLabel invoiceLabel = new JLabel("Invoice");
        invoiceLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        invoiceLabel.setForeground(Color.BLACK);
        invoiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        invoiceLabel.setVerticalAlignment(SwingConstants.CENTER);
        invoicePanel.add(invoiceLabel, BorderLayout.NORTH);

        JPanel invoicepaneltengah = new JPanel(new GridLayout(1,2,30,10));
        invoicepaneltengah.setOpaque(false);
        invoicepaneltengah.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JPanel leftinvoice = new JPanel();
        leftinvoice.setLayout(new BoxLayout(leftinvoice, BoxLayout.Y_AXIS));
        leftinvoice.setBackground(Color.WHITE);

        JLabel namelabel = new JLabel("Name");
        namelabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        namelabel.setForeground(new Color(188,0,45));
        name = new JLabel(membername);
        name.setFont(new Font("Tahoma", Font.PLAIN, 20));
        name.setForeground(Color.BLACK);

        JLabel emaillabel = new JLabel("Email");
        emaillabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        emaillabel.setForeground(new Color(188,0,45));
        email = new JLabel(memberemail);
        email.setFont(new Font("Tahoma", Font.PLAIN, 20));
        email.setForeground(Color.BLACK);

        JLabel phonelabel = new JLabel("Phone");
        phonelabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        phonelabel.setForeground(new Color(188,0,45));
        phone = new JLabel(memberphone);
        phone.setFont(new Font("Tahoma", Font.PLAIN, 20));
        phone.setForeground(Color.BLACK);

        JLabel membertypelabel = new JLabel("Type");
        membertypelabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        membertypelabel.setForeground(new Color(188,0,45));
        membertype = new JLabel(type);
        membertype.setFont(new Font("Tahoma", Font.PLAIN, 20));
        membertype.setForeground(Color.BLACK);

        namelabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        name.setAlignmentX(Component.LEFT_ALIGNMENT);
        emaillabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        email.setAlignmentX(Component.LEFT_ALIGNMENT);
        phonelabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        phone.setAlignmentX(Component.LEFT_ALIGNMENT);
        membertypelabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        membertype.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftinvoice.add(namelabel);
        leftinvoice.add(name);
        leftinvoice.add(Box.createVerticalStrut(10));
        leftinvoice.add(emaillabel);
        leftinvoice.add(email);
        leftinvoice.add(Box.createVerticalStrut(10));
        leftinvoice.add(phonelabel);
        leftinvoice.add(phone);
        leftinvoice.add(Box.createVerticalStrut(10));
        leftinvoice.add(membertypelabel);
        leftinvoice.add(membertype);
        leftinvoice.add(Box.createVerticalStrut(10));

        JScrollPane leftinvoicescrollpane = new JScrollPane(leftinvoice);
        invoicepaneltengah.add(leftinvoicescrollpane);
        leftinvoicescrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        leftinvoicescrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel rightinvoice = new JPanel();
        rightinvoice.setOpaque(false);
        rightinvoice.setLayout(new BoxLayout(rightinvoice, BoxLayout.Y_AXIS));

        JPanel selectedpaymentmethod = new JPanel(new BorderLayout());
        selectedpaymentmethod.setOpaque(false);
        JLabel selectedpaymentmethodlabel = new JLabel("Payment Method");
        selectedpaymentmethodlabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        selectedpaymentmethodlabel.setForeground(new Color(188, 0, 45));
        selectedpaymentmethodlabel.setHorizontalAlignment(SwingConstants.LEFT);
        selectedpayment = new JLabel();
        selectedpaymentmethod.add(selectedpaymentmethodlabel, BorderLayout.NORTH);
        selectedpaymentmethod.add(selectedpayment, BorderLayout.CENTER);

        Locale indo = new Locale("in", "ID");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(indo);
        String hasil = formatter.format(memberprice);
        price = new JLabel(hasil);
        price.setFont(new Font("Tahoma", Font.BOLD, 20));
        price.setForeground(Color.BLACK);
        selectedpaymentmethod.setAlignmentX(Component.LEFT_ALIGNMENT);
        price.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightinvoice.add(Box.createVerticalStrut(10));
        rightinvoice.add(selectedpaymentmethod);
        rightinvoice.add(Box.createVerticalStrut(10));
        rightinvoice.add(price);

        invoicepaneltengah.add(rightinvoice);

        cc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cc.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cc.setForeground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                selectedpayment.setIcon(cc.getIcon());
            }
        });
        dc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                dc.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                dc.setForeground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                selectedpayment.setIcon(dc.getIcon());
            }
        });
        gopay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                gopay.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                gopay.setForeground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                selectedpayment.setIcon(gopay.getIcon());
            }
        });
        dana.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                dana.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                dana.setForeground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                selectedpayment.setIcon(dana.getIcon());
            }
        });
        spay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                spay.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                spay.setForeground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                selectedpayment.setIcon(spay.getIcon());
            }
        });
        ovo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ovo.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ovo.setForeground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                selectedpayment.setIcon(ovo.getIcon());
            }
        });

        invoicePanel.add(invoicepaneltengah, BorderLayout.CENTER);
        paneltengah.add(invoicePanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0,800,width,50);
        payment = new JButton("PAY");
        payment.setBackground(new Color(204, 51, 0));
        payment.setOpaque(true);
        payment.setFocusPainted(false);
        payment.setBorderPainted(false);
        payment.setForeground(Color.WHITE);
        payment.setFont(new Font("Tahoma", Font.BOLD, 25));
        payment.setPreferredSize(new Dimension(100, 50));
        buttonPanel.add(payment);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        layeredPane.add(header, Integer.valueOf(1));
        layeredPane.add(paneltengah, Integer.valueOf(2));
        layeredPane.add(buttonPanel, Integer.valueOf(3));
        layeredPane.add(assetLabel, Integer.valueOf(4));
        backbutton = new BACKBUTTON(30,820);
        backbutton.addActionListener(e->{
            MEMBERSHIPREGIS membershipregis = new MEMBERSHIPREGIS(model, con, memberemail, membername, address, memberphone,birth, pass, type, memberprice, photopath);
            membershipregis.setVisible(true);
        });
        layeredPane.add(backbutton, Integer.valueOf(5));

        payment.addActionListener(e->{
            if(selectedpayment.getIcon() != null) {
                if (getphotopath.equals("upload.png")) {
                    switch (type) {
                        case "Gold" -> {
                            getphotopath = "goldmember.png";
                        }
                        case "Silver" -> {
                            getphotopath = "silvermember.png";
                        }
                        case "Bronze" -> {
                            getphotopath = "bronzemember.png";
                        }
                    }
                }
                MEMBERPAYMENT.this.dispose();
                try {
                    model.InsertMember(con, membername, memberemail, memberphone, address, birth, pass, type, memberprice, getphotopath);
                    MemberSuccessView memberSuccessView = new MemberSuccessView(model, con);
                    memberSuccessView.setVisible(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(MEMBERPAYMENT.this, "Oops! Something Went Wrong!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(MEMBERPAYMENT.this, "Please Select a Payment Method!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.setContentPane(layeredPane);
    }
}
