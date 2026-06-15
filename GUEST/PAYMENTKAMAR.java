import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PAYMENTKAMAR extends JFrame {
    JButton payment;
    JLabel selectedpayment, roomtype, longdate, price, name, email, phone, NIK; //roomtype, price, sama longdate set text sesuai databse
    JComboBox vouchers = new JComboBox();
    JTextArea addons; //bisa diubah ini
    Model model;
    Connection con;
    String guestemail, photopath;
    String[]data,extras,guestdata;
    String paymentmethod;
    int totalprice;
    PAYMENTKAMAR(Model model, Connection con, String guestemail, String photopath, String[] data, String[] extras, String[] guestdata, int totalprice) throws SQLException {
        this.model = model;
        this.con = con;
        this.guestemail = guestemail;
        this.photopath = photopath;
        this.data = data;
        this.extras = extras;
        this.guestdata = guestdata;
        this.totalprice = totalprice;
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
        name = new JLabel("");
        name.setFont(new Font("Tahoma", Font.PLAIN, 20));
        name.setForeground(Color.BLACK);

        JLabel emaillabel = new JLabel("Email");
        emaillabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        emaillabel.setForeground(new Color(188,0,45));
        email = new JLabel("");
        email.setFont(new Font("Tahoma", Font.PLAIN, 20));
        email.setForeground(Color.BLACK);

        JLabel phonelabel = new JLabel("Phone");
        phonelabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        phonelabel.setForeground(new Color(188,0,45));
        phone = new JLabel("");
        phone.setFont(new Font("Tahoma", Font.PLAIN, 20));
        phone.setForeground(Color.BLACK);

        JLabel niklabel = new JLabel("NIK");
        niklabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        niklabel.setForeground(new Color(188,0,45));
        NIK = new JLabel("");
        NIK.setFont(new Font("Tahoma", Font.PLAIN, 20));
        NIK.setForeground(Color.BLACK);

        JLabel type = new JLabel("Type");
        type.setFont(new Font("Tahoma", Font.PLAIN, 20));
        type.setForeground(new Color(188, 0, 45));
        roomtype = new JLabel("");
        roomtype.setFont(new Font("Tahoma", Font.PLAIN, 20));
        roomtype.setForeground(Color.BLACK);

        JLabel date = new JLabel("Date");
        date.setFont(new Font("Tahoma", Font.PLAIN, 20));
        date.setForeground(new Color(188, 0, 45));
        longdate = new JLabel("");
        longdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
        longdate.setForeground(Color.BLACK);

        JLabel add = new JLabel("Extra");
        add.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add.setForeground(new Color(188, 0, 45));
        addons = new JTextArea("");
        addons.setFont(new Font("Tahoma", Font.PLAIN, 20));
        addons.setForeground(new Color(255, 118, 118));
        addons.setEditable(false);
        addons.setOpaque(false);
        addons.setLineWrap(true);
        addons.setWrapStyleWord(true);
        namelabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        name.setAlignmentX(Component.LEFT_ALIGNMENT);
        emaillabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        email.setAlignmentX(Component.LEFT_ALIGNMENT);
        phonelabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        phone.setAlignmentX(Component.LEFT_ALIGNMENT);
        niklabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        NIK.setAlignmentX(Component.LEFT_ALIGNMENT);
        type.setAlignmentX(Component.LEFT_ALIGNMENT);
        roomtype.setAlignmentX(Component.LEFT_ALIGNMENT);
        date.setAlignmentX(Component.LEFT_ALIGNMENT);
        longdate.setAlignmentX(Component.LEFT_ALIGNMENT);
        add.setAlignmentX(Component.LEFT_ALIGNMENT);
        addons.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftinvoice.add(namelabel);
        leftinvoice.add(name);
        leftinvoice.add(Box.createVerticalStrut(10));
        leftinvoice.add(emaillabel);
        leftinvoice.add(email);
        leftinvoice.add(Box.createVerticalStrut(10));
        leftinvoice.add(phonelabel);
        leftinvoice.add(phone);
        leftinvoice.add(Box.createVerticalStrut(10));
        leftinvoice.add(niklabel);
        leftinvoice.add(NIK);
        leftinvoice.add(Box.createVerticalStrut(10));
        leftinvoice.add(type);
        leftinvoice.add(roomtype);
        leftinvoice.add(Box.createVerticalStrut(10));
        leftinvoice.add(date);
        leftinvoice.add(longdate);
        leftinvoice.add(Box.createVerticalStrut(10));
        leftinvoice.add(add);
        leftinvoice.add(addons);

        JScrollPane leftinvoicescrollpane = new JScrollPane(leftinvoice);
        invoicepaneltengah.add(leftinvoicescrollpane);
        leftinvoicescrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        leftinvoicescrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel rightinvoice = new JPanel();
        rightinvoice.setOpaque(false);
        rightinvoice.setLayout(new BoxLayout(rightinvoice, BoxLayout.Y_AXIS));
        JLabel voucherlabel = new JLabel("Voucher");
        voucherlabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        voucherlabel.setForeground(new Color(188, 0, 45));
        updatevoucher();

        JPanel selectedpaymentmethod = new JPanel(new BorderLayout());
        selectedpaymentmethod.setOpaque(false);
        JLabel selectedpaymentmethodlabel = new JLabel("Payment Method");
        selectedpaymentmethodlabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        selectedpaymentmethodlabel.setForeground(new Color(188, 0, 45));
        selectedpaymentmethodlabel.setHorizontalAlignment(SwingConstants.LEFT);
        selectedpayment = new JLabel();
        selectedpaymentmethod.add(selectedpaymentmethodlabel, BorderLayout.NORTH);
        selectedpaymentmethod.add(selectedpayment, BorderLayout.CENTER);

        price = new JLabel("RP. 0");
        price.setFont(new Font("Tahoma", Font.BOLD, 20));
        price.setForeground(Color.BLACK);
        voucherlabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        vouchers.setAlignmentX(Component.LEFT_ALIGNMENT);
        vouchers.addActionListener(new PAYMENTKAMARCONTROL(this));
        selectedpaymentmethod.setAlignmentX(Component.LEFT_ALIGNMENT);
        price.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightinvoice.add(voucherlabel);
        rightinvoice.add(vouchers);
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
                paymentmethod = "CRD";
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
                paymentmethod = "DBT";
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
                paymentmethod = "GPY";
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
                paymentmethod = "DAN";
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
                paymentmethod = "SPY";
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
                paymentmethod = "OVO";
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
        payment.addActionListener(new PAYMENTKAMARCONTROL(this));
        buttonPanel.add(payment);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        layeredPane.add(header, Integer.valueOf(1));
        layeredPane.add(paneltengah, Integer.valueOf(2));
        layeredPane.add(buttonPanel, Integer.valueOf(3));
        layeredPane.add(assetLabel, Integer.valueOf(4));

        this.setContentPane(layeredPane);
        updateAllLabels(roomtype, longdate, price, name, email, phone, NIK, addons);
    }

    public void updateAllLabels(JLabel roomtype, JLabel longdate, JLabel price, JLabel name, JLabel email, JLabel phone, JLabel nik, JTextArea addons) {
        //            data kamar
        String setroomnumber = data[0];
        String setroomtype = data[1];
        String setroomsize = data[2];
        String setbedtype = data[3];
        String setsmoking = data[4];
        String setquiet = data[5];
        String setcapacity = data[6];
        String setprice = data[7];
        String setdate = data[8];
//            data extra
        String setbreakfast = extras[0];
        String setbabycot = extras[1];
        String setextrabed = extras[2];
        String setyukata = extras[3];
        String setphoto =extras[4];
//            data guest
        String setnik = guestdata[0];
        String setname = guestdata[1];
        String setemail = guestdata[2];
        String setphone = guestdata[3];

        roomtype.setText(setroomtype);
        longdate.setText(setdate);
        Locale indo = new Locale("in", "ID");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(indo);
        String hasil = formatter.format(totalprice);
        price.setText(hasil);
        email.setText(setemail);
        nik.setText(setnik);
        name.setText(setname);
        phone.setText(setphone);

        StringBuilder listextra = new StringBuilder();
        if(setsmoking.equals("Smoking")){
            listextra.append("Smoking Room\n");
        }
        if(setquiet.equals("Quiet Room")){
            listextra.append("Quiet Room\n");
        }
        for(int i = 0; i < extras.length; i++){
            if(!extras[i].isEmpty()){
                listextra.append(extras[i]+"\n");
            }
        }
        addons.setText(listextra.toString());
    }

    private void updatevoucher() throws SQLException {
        if(!photopath.isEmpty() || !guestemail.isEmpty()){
            ArrayList<String> voucherlist = model.getVouchers(con, guestemail);
            vouchers = new JComboBox();
            vouchers.setFont(new Font("Tahoma", Font.PLAIN, 15));
            vouchers.setForeground(new Color(188, 0, 45));
            vouchers.addItem("");
            for(String voucher : voucherlist){
                vouchers.addItem(voucher);
            }
        }
    }
}
