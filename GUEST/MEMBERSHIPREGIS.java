import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.time.LocalDate;

public class MEMBERSHIPREGIS extends JFrame {
    JTextField nameField, emailField, phoneField, addressField, birthdayField;
    JPasswordField passwordField, confirmPasswordField;
    JComboBox membershipComboBox;
    JButton registerButton, uploadButton, editButton, removeButton;
    ImageIcon pictureicon;
    JPanel deleditpanel;
    String iconpath, memberemail, membername, address, memberphone, birth, pass, type;
    int memberprice;
    LocalDate birthday = LocalDate.now();
    BACKBUTTON backbutton;
    Model model;
    Connection con;
    String email, photopath;
    MEMBERSHIPREGIS(Model model, Connection con, String email, String photopath) {
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

        ImageIcon awanicon1 = new ImageIcon("awanapdev2.png");
        Image awamimage1 = awanicon1.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon asset1 = new ImageIcon(awamimage1);

        ImageIcon awanicon2 = new ImageIcon("awanapdev.png");
        Image awamimage2 = awanicon2.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
        ImageIcon asset2 = new ImageIcon(awamimage2);

        ImageIcon icon = new ImageIcon("background.png");
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon background = new ImageIcon(image);
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, width, height);

        JPanel contentPanel = new JPanel(null);
        contentPanel.setOpaque(false);
        contentPanel.setBounds(0, 0, width, height);

//        isi konten panel yes
       JPanel fieldpanel = new JPanel(new BorderLayout());
       fieldpanel.setBounds(256,100,1000,700);
       fieldpanel.setBackground(new Color(255,255,255,200));
       contentPanel.add(fieldpanel);

       JLabel labelatas = new JLabel("REGISTER MEMBERSHIP");
       labelatas.setFont(new Font("Calibri", Font.BOLD, 30));
       labelatas.setForeground(new Color(200,0,0));
       labelatas.setHorizontalAlignment(SwingConstants.CENTER);
       fieldpanel.add(labelatas, BorderLayout.NORTH);

       JPanel labeltengah = new JPanel(new GridLayout(1,2));
       labeltengah.setOpaque(false);

       JPanel datapanelholder = new JPanel(null);
       datapanelholder.setOpaque(false);
       JPanel datapanel = new JPanel(new GridLayout(7,2,0,10));
       datapanel.setOpaque(false);
       datapanel.setBounds(40,90,420,380);
       datapanelholder.add(datapanel);

       nameField = new JTextField(5);
       nameField.setBackground(new Color(255, 235, 238));
       nameField.setFont(new Font("Calibri", Font.BOLD, 18));
       nameField.setForeground(new Color(200,0,0));
       nameField.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
       JLabel nameLabel = new JLabel("Name");
       nameLabel.setFont(new Font("Calibri", Font.BOLD, 18));
       nameLabel.setForeground(new Color(200,0,0));
       datapanel.add(nameLabel);
       datapanel.add(nameField);

       emailField = new JTextField(5);
       emailField.setBackground(new Color(255, 235, 238));
       emailField.setFont(new Font("Calibri", Font.BOLD, 18));
       emailField.setForeground(new Color(200,0,0));
       emailField.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
       JLabel emailLabel = new JLabel("Email");
       emailLabel.setFont(new Font("Calibri", Font.BOLD, 18));
       emailLabel.setForeground(new Color(200,0,0));
       datapanel.add(emailLabel);
       datapanel.add(emailField);

       phoneField = new JTextField(5);
       phoneField.setBackground(new Color(255, 235, 238));
       phoneField.setFont(new Font("Calibri", Font.BOLD, 18));
       phoneField.setForeground(new Color(200,0,0));
       phoneField.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
       JLabel phoneLabel = new JLabel("Phone");
       phoneLabel.setFont(new Font("Calibri", Font.BOLD, 18));
       phoneLabel.setForeground(new Color(200,0,0));
       datapanel.add(phoneLabel);
       datapanel.add(phoneField);

       addressField = new JTextField(5);
       addressField.setBackground(new Color(255, 235, 238));
       addressField.setFont(new Font("Calibri", Font.BOLD, 18));
       addressField.setForeground(new Color(200,0,0));
       addressField.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
       JLabel addressLabel = new JLabel("Address");
       addressLabel.setFont(new Font("Calibri", Font.BOLD, 18));
       addressLabel.setForeground(new Color(200,0,0));
       datapanel.add(addressLabel);
       datapanel.add(addressField);

       birthdayField = new JTextField(5);
       birthdayField.setBackground(new Color(255, 235, 238));
       birthdayField.setFont(new Font("Calibri", Font.BOLD, 18));
       birthdayField.setForeground(new Color(200,0,0));
       birthdayField.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
       birthdayField.setText(String.valueOf(birthday));
       JLabel birthdayLabel = new JLabel("Birthday (yyyy-MM-dd)");
       birthdayLabel.setFont(new Font("Calibri", Font.BOLD, 15));
       birthdayLabel.setForeground(new Color(200,0,0));
       datapanel.add(birthdayLabel);
       datapanel.add(birthdayField);

       passwordField = new JPasswordField(5);
       passwordField.setBackground(new Color(255, 235, 238));
       passwordField.setFont(new Font("Calibri", Font.BOLD, 18));
       passwordField.setForeground(new Color(200,0,0));
       passwordField.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
       JLabel passwordLabel = new JLabel("Password");
       passwordLabel.setFont(new Font("Calibri", Font.BOLD, 18));
       passwordLabel.setForeground(new Color(200,0,0));
       datapanel.add(passwordLabel);
       datapanel.add(passwordField);

       confirmPasswordField = new JPasswordField(5);
       confirmPasswordField.setBackground(new Color(255, 235, 238));
       confirmPasswordField.setFont(new Font("Calibri", Font.BOLD, 18));
       confirmPasswordField.setForeground(new Color(200,0,0));
       confirmPasswordField.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
       JLabel confirmPasswordLabel = new JLabel("Confirm Password");
       confirmPasswordLabel.setFont(new Font("Calibri", Font.BOLD, 18));
       confirmPasswordLabel.setForeground(new Color(200,0,0));
       datapanel.add(confirmPasswordLabel);
       datapanel.add(confirmPasswordField);

       labeltengah.add(datapanelholder);

       JPanel photoandmembershippanelholder = new JPanel(null);
       photoandmembershippanelholder.setOpaque(false);
       JPanel photoandmembershippanel = new JPanel(new BorderLayout(10,10));
       photoandmembershippanel.setOpaque(false);
       photoandmembershippanel.setBounds(40,90,420,380);
       photoandmembershippanelholder.add(photoandmembershippanel);
       labeltengah.add(photoandmembershippanelholder);

       JPanel typepanel = new JPanel();
       typepanel.setOpaque(false);
       JLabel typelabel = new JLabel("Type");
       typelabel.setFont(new Font("Calibri", Font.BOLD, 18));
       typelabel.setForeground(new Color(200,0,0));
       typelabel.setHorizontalAlignment(SwingConstants.CENTER);
       typepanel.add(typelabel);

       String[]type = {"Bronze","Silver","Gold"};
       membershipComboBox = new JComboBox(type);
       membershipComboBox.setBackground(new Color(255, 235, 238));
       membershipComboBox.setForeground(new Color(200, 0, 0));
       membershipComboBox.setFont(new Font("Calibri", Font.BOLD, 18));
       membershipComboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0)));
       membershipComboBox.setPreferredSize(new Dimension(200,30));
       typepanel.add(membershipComboBox);

       photoandmembershippanel.add(typepanel, BorderLayout.NORTH);

       JPanel photopanel = new JPanel();
       photopanel.setLayout(new BorderLayout(10,10));
       photopanel.setOpaque(false);
       JLabel upyourphotolabel = new JLabel("Upload Your Photo");
       upyourphotolabel.setFont(new Font("Calibri", Font.BOLD, 18));
       upyourphotolabel.setForeground(new Color(200,0,0));
       upyourphotolabel.setHorizontalAlignment(SwingConstants.CENTER);
       photopanel.add(upyourphotolabel, BorderLayout.NORTH);

       JPanel upbuttonpanel = new JPanel();
       upbuttonpanel.setOpaque(false);
       iconpath = "upload.png";
       ImageIcon cloud = new ImageIcon(iconpath);
       Image cloudimage = cloud.getImage().getScaledInstance(250,250,Image.SCALE_SMOOTH);
       pictureicon = new ImageIcon(cloudimage);
       pictureicon.setDescription(iconpath);
       uploadButton = new JButton(pictureicon);
       uploadButton.setBorderPainted(false);
       uploadButton.setContentAreaFilled(false);
       uploadButton.setFocusPainted(false);
       uploadButton.setOpaque(false);
       uploadButton.setPreferredSize(new Dimension(250,250));
       uploadButton.addActionListener(new REGISCONTROL(this));
       upbuttonpanel.add(uploadButton);
       photopanel.add(upbuttonpanel, BorderLayout.CENTER);

       photoandmembershippanel.add(photopanel, BorderLayout.CENTER);

       deleditpanel = new JPanel();
       deleditpanel.setOpaque(false);
       editButton = new JButton("Edit Photo");
       editButton.setBackground(new Color(200, 0, 0));
       editButton.setFont(new Font("Calibri", Font.BOLD, 12));
       editButton.setForeground(new Color(255,255,255));
       editButton.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
       editButton.setOpaque(true);
       editButton.setFocusPainted(false);
       editButton.setBorderPainted(false);
       editButton.setPreferredSize(new Dimension(100,20));
       editButton.addActionListener(new REGISCONTROL(this));
       deleditpanel.add(editButton);

       removeButton = new JButton("Remove Photo");
       removeButton.setBackground(new Color(200,0,0));
       removeButton.setFont(new Font("Calibri", Font.BOLD, 12));
       removeButton.setForeground(new Color(255,255,255));
       removeButton.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
       removeButton.setOpaque(true);
       removeButton.setFocusPainted(false);
       removeButton.setBorderPainted(false);
       removeButton.setPreferredSize(new Dimension(100,20));
       removeButton.addActionListener(new REGISCONTROL(this));
       deleditpanel.add(removeButton);
       removeButton.setEnabled(false);
       editButton.setEnabled(false);

       photoandmembershippanel.add(deleditpanel, BorderLayout.SOUTH);

       fieldpanel.add(labeltengah, BorderLayout.CENTER);

       registerButton = new JButton("REGISTER");
       registerButton.setBackground(new Color(200, 0, 0));
       registerButton.setOpaque(true);
       registerButton.setBorderPainted(false);
       registerButton.setFocusPainted(false);
       registerButton.setPreferredSize(new Dimension(250,50));
       registerButton.setForeground(Color.WHITE);
       registerButton.setFont(new Font("Calibri", Font.BOLD, 18));
       registerButton.addActionListener(new REGISCONTROL(this));
       JPanel buttonpanel = new JPanel();
       buttonpanel.setOpaque(false);
       buttonpanel.setPreferredSize(new Dimension(0,100));
       buttonpanel.add(registerButton);
       fieldpanel.add(buttonpanel, BorderLayout.SOUTH);

       JLabel assetlabel1 = new JLabel(asset1);
       assetlabel1.setBounds(570,130,200,150);
       JLabel assetlabel2 = new JLabel(asset2);
       assetlabel2.setBounds(460,570,150,100);


//         content panel sama background taro di layerpane (tumpukkan)
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        layeredPane.add(contentPanel, Integer.valueOf(1));
        layeredPane.add(assetlabel1, Integer.valueOf(2));
        layeredPane.add(assetlabel2, Integer.valueOf(3));
        backbutton = new BACKBUTTON(30,820);
        backbutton.addActionListener(new REGISCONTROL(this));
        layeredPane.add(backbutton, Integer.valueOf(4));

        this.setContentPane(layeredPane);
        this.setVisible(true);
    }

   public MEMBERSHIPREGIS(Model model, Connection con, String memberemail, String membername, String address, String memberphone, String birth, String pass, String type, int memberprice, String photopath) {
       this.model = model;
       this.con = con;
       this.memberemail = memberemail;
       this.membername = membername;
       this.address = address;
       this.memberphone = memberphone;
       this.birth = birth;
       this.pass = pass;
       this.type = type;
       this.memberprice = memberprice;
       this.photopath = photopath;
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int width = screenSize.width;
      int height = screenSize.height;

      this.setExtendedState(JFrame.MAXIMIZED_BOTH);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);

      ImageIcon awanicon1 = new ImageIcon("awanapdev2.png");
      Image awamimage1 = awanicon1.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
      ImageIcon asset1 = new ImageIcon(awamimage1);

      ImageIcon awanicon2 = new ImageIcon("awanapdev.png");
      Image awamimage2 = awanicon2.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
      ImageIcon asset2 = new ImageIcon(awamimage2);

      ImageIcon icon = new ImageIcon("background.png");
      Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
      ImageIcon background = new ImageIcon(image);
      JLabel backgroundLabel = new JLabel(background);
      backgroundLabel.setBounds(0, 0, width, height);

      JPanel contentPanel = new JPanel(null);
      contentPanel.setOpaque(false);
      contentPanel.setBounds(0, 0, width, height);

//        isi konten panel yes
      JPanel fieldpanel = new JPanel(new BorderLayout());
      fieldpanel.setBounds(256,100,1000,700);
      fieldpanel.setBackground(new Color(255,255,255,200));
      contentPanel.add(fieldpanel);

      JLabel labelatas = new JLabel("REGISTER MEMBERSHIP");
      labelatas.setFont(new Font("Calibri", Font.BOLD, 30));
      labelatas.setForeground(new Color(200,0,0));
      labelatas.setHorizontalAlignment(SwingConstants.CENTER);
      fieldpanel.add(labelatas, BorderLayout.NORTH);

      JPanel labeltengah = new JPanel(new GridLayout(1,2));
      labeltengah.setOpaque(false);

      JPanel datapanelholder = new JPanel(null);
      datapanelholder.setOpaque(false);
      JPanel datapanel = new JPanel(new GridLayout(7,2,0,10));
      datapanel.setOpaque(false);
      datapanel.setBounds(40,90,420,380);
      datapanelholder.add(datapanel);

      nameField = new JTextField(5);
      nameField.setBackground(new Color(255, 235, 238));
      nameField.setFont(new Font("Calibri", Font.BOLD, 18));
      nameField.setForeground(new Color(200,0,0));
      nameField.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
      JLabel nameLabel = new JLabel("Name");
      nameLabel.setFont(new Font("Calibri", Font.BOLD, 18));
      nameLabel.setForeground(new Color(200,0,0));
      datapanel.add(nameLabel);
      datapanel.add(nameField);

      emailField = new JTextField(5);
      emailField.setBackground(new Color(255, 235, 238));
      emailField.setFont(new Font("Calibri", Font.BOLD, 18));
      emailField.setForeground(new Color(200,0,0));
      emailField.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
      JLabel emailLabel = new JLabel("Email");
      emailLabel.setFont(new Font("Calibri", Font.BOLD, 18));
      emailLabel.setForeground(new Color(200,0,0));
      datapanel.add(emailLabel);
      datapanel.add(emailField);

      phoneField = new JTextField(5);
      phoneField.setBackground(new Color(255, 235, 238));
      phoneField.setFont(new Font("Calibri", Font.BOLD, 18));
      phoneField.setForeground(new Color(200,0,0));
      phoneField.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
      JLabel phoneLabel = new JLabel("Phone");
      phoneLabel.setFont(new Font("Calibri", Font.BOLD, 18));
      phoneLabel.setForeground(new Color(200,0,0));
      datapanel.add(phoneLabel);
      datapanel.add(phoneField);

      addressField = new JTextField(5);
      addressField.setBackground(new Color(255, 235, 238));
      addressField.setFont(new Font("Calibri", Font.BOLD, 18));
      addressField.setForeground(new Color(200,0,0));
      addressField.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
      JLabel addressLabel = new JLabel("Address");
      addressLabel.setFont(new Font("Calibri", Font.BOLD, 18));
      addressLabel.setForeground(new Color(200,0,0));
      datapanel.add(addressLabel);
      datapanel.add(addressField);

      birthdayField = new JTextField(5);
      birthdayField.setBackground(new Color(255, 235, 238));
      birthdayField.setFont(new Font("Calibri", Font.BOLD, 18));
      birthdayField.setForeground(new Color(200,0,0));
      birthdayField.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
      birthdayField.setText(String.valueOf(birthday));
      JLabel birthdayLabel = new JLabel("Birthday (yyyy-MM-dd)");
      birthdayLabel.setFont(new Font("Calibri", Font.BOLD, 15));
      birthdayLabel.setForeground(new Color(200,0,0));
      datapanel.add(birthdayLabel);
      datapanel.add(birthdayField);

      passwordField = new JPasswordField(5);
      passwordField.setBackground(new Color(255, 235, 238));
      passwordField.setFont(new Font("Calibri", Font.BOLD, 18));
      passwordField.setForeground(new Color(200,0,0));
      passwordField.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
      JLabel passwordLabel = new JLabel("Password");
      passwordLabel.setFont(new Font("Calibri", Font.BOLD, 18));
      passwordLabel.setForeground(new Color(200,0,0));
      datapanel.add(passwordLabel);
      datapanel.add(passwordField);

      confirmPasswordField = new JPasswordField(5);
      confirmPasswordField.setBackground(new Color(255, 235, 238));
      confirmPasswordField.setFont(new Font("Calibri", Font.BOLD, 18));
      confirmPasswordField.setForeground(new Color(200,0,0));
      confirmPasswordField.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
      JLabel confirmPasswordLabel = new JLabel("Confirm Password");
      confirmPasswordLabel.setFont(new Font("Calibri", Font.BOLD, 18));
      confirmPasswordLabel.setForeground(new Color(200,0,0));
      datapanel.add(confirmPasswordLabel);
      datapanel.add(confirmPasswordField);

      labeltengah.add(datapanelholder);

      JPanel photoandmembershippanelholder = new JPanel(null);
      photoandmembershippanelholder.setOpaque(false);
      JPanel photoandmembershippanel = new JPanel(new BorderLayout(10,10));
      photoandmembershippanel.setOpaque(false);
      photoandmembershippanel.setBounds(40,90,420,380);
      photoandmembershippanelholder.add(photoandmembershippanel);
      labeltengah.add(photoandmembershippanelholder);

      JPanel typepanel = new JPanel();
      typepanel.setOpaque(false);
      JLabel typelabel = new JLabel("Type");
      typelabel.setFont(new Font("Calibri", Font.BOLD, 18));
      typelabel.setForeground(new Color(200,0,0));
      typelabel.setHorizontalAlignment(SwingConstants.CENTER);
      typepanel.add(typelabel);

      String[] types = {"Bronze","Silver","Gold"};
      membershipComboBox = new JComboBox(types);
      membershipComboBox.setBackground(new Color(255, 235, 238));
      membershipComboBox.setForeground(new Color(200, 0, 0));
      membershipComboBox.setFont(new Font("Calibri", Font.BOLD, 18));
      membershipComboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0)));
      membershipComboBox.setPreferredSize(new Dimension(200,30));
      typepanel.add(membershipComboBox);

      photoandmembershippanel.add(typepanel, BorderLayout.NORTH);

      JPanel photopanel = new JPanel();
      photopanel.setLayout(new BorderLayout(10,10));
      photopanel.setOpaque(false);
      JLabel upyourphotolabel = new JLabel("Upload Your Photo");
      upyourphotolabel.setFont(new Font("Calibri", Font.BOLD, 18));
      upyourphotolabel.setForeground(new Color(200,0,0));
      upyourphotolabel.setHorizontalAlignment(SwingConstants.CENTER);
      photopanel.add(upyourphotolabel, BorderLayout.NORTH);

      JPanel upbuttonpanel = new JPanel();
      upbuttonpanel.setOpaque(false);
      iconpath = "upload.png";
      ImageIcon cloud = new ImageIcon(iconpath);
      Image cloudimage = cloud.getImage().getScaledInstance(250,250,Image.SCALE_SMOOTH);
      pictureicon = new ImageIcon(cloudimage);
      pictureicon.setDescription(iconpath);
      uploadButton = new JButton(pictureicon);
      uploadButton.setBorderPainted(false);
      uploadButton.setContentAreaFilled(false);
      uploadButton.setFocusPainted(false);
      uploadButton.setOpaque(false);
      uploadButton.setPreferredSize(new Dimension(250,250));
      uploadButton.addActionListener(new REGISCONTROL(this));
      upbuttonpanel.add(uploadButton);
      photopanel.add(upbuttonpanel, BorderLayout.CENTER);

      photoandmembershippanel.add(photopanel, BorderLayout.CENTER);

      deleditpanel = new JPanel();
      deleditpanel.setOpaque(false);
      editButton = new JButton("Edit Photo");
      editButton.setBackground(new Color(200, 0, 0));
      editButton.setFont(new Font("Calibri", Font.BOLD, 12));
      editButton.setForeground(new Color(255,255,255));
      editButton.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
      editButton.setOpaque(true);
      editButton.setFocusPainted(false);
      editButton.setBorderPainted(false);
      editButton.setPreferredSize(new Dimension(100,20));
      editButton.addActionListener(new REGISCONTROL(this));
      deleditpanel.add(editButton);

      removeButton = new JButton("Remove Photo");
      removeButton.setBackground(new Color(200,0,0));
      removeButton.setFont(new Font("Calibri", Font.BOLD, 12));
      removeButton.setForeground(new Color(255,255,255));
      removeButton.setBorder(BorderFactory.createLineBorder(new Color(200,0,0)));
      removeButton.setOpaque(true);
      removeButton.setFocusPainted(false);
      removeButton.setBorderPainted(false);
      removeButton.setPreferredSize(new Dimension(100,20));
      removeButton.addActionListener(new REGISCONTROL(this));
      deleditpanel.add(removeButton);
      removeButton.setEnabled(false);
      editButton.setEnabled(false);

      photoandmembershippanel.add(deleditpanel, BorderLayout.SOUTH);

      fieldpanel.add(labeltengah, BorderLayout.CENTER);

      registerButton = new JButton("REGISTER");
      registerButton.setBackground(new Color(200, 0, 0));
      registerButton.setOpaque(true);
      registerButton.setBorderPainted(false);
      registerButton.setFocusPainted(false);
      registerButton.setPreferredSize(new Dimension(250,50));
      registerButton.setForeground(Color.WHITE);
      registerButton.setFont(new Font("Calibri", Font.BOLD, 18));
      registerButton.addActionListener(new REGISCONTROL(this));
      JPanel buttonpanel = new JPanel();
      buttonpanel.setOpaque(false);
      buttonpanel.setPreferredSize(new Dimension(0,100));
      buttonpanel.add(registerButton);
      fieldpanel.add(buttonpanel, BorderLayout.SOUTH);

      JLabel assetlabel1 = new JLabel(asset1);
      assetlabel1.setBounds(570,130,200,150);
      JLabel assetlabel2 = new JLabel(asset2);
      assetlabel2.setBounds(460,570,150,100);


//         content panel sama background taro di layerpane (tumpukkan)
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.add(backgroundLabel, Integer.valueOf(0));
      layeredPane.add(contentPanel, Integer.valueOf(1));
      layeredPane.add(assetlabel1, Integer.valueOf(2));
      layeredPane.add(assetlabel2, Integer.valueOf(3));
      backbutton = new BACKBUTTON(30,820);
      backbutton.addActionListener(new REGISCONTROL(this));
      layeredPane.add(backbutton, Integer.valueOf(4));

      this.setContentPane(layeredPane);
      this.setVisible(true);
      loadprevdata(memberemail, membername,address,memberphone,birth,pass,type,photopath);
   }

   private void loadprevdata(String memberemail, String membername, String address, String memberphone, String birth, String pass, String type, String photopath) {
       this.emailField.setText(memberemail);
       this.nameField.setText(membername);
       this.addressField.setText(address);
       this.phoneField.setText(memberphone);
       this.birthdayField.setText(birth);
       this.passwordField.setText(pass);
       this.confirmPasswordField.setText(pass);
       this.membershipComboBox.setSelectedItem(type);
       ImageIcon up = new ImageIcon(photopath);
       Image upimage = up.getImage().getScaledInstance(250,250,Image.SCALE_SMOOTH);
       ImageIcon imagebefore = new ImageIcon(upimage);
       this.uploadButton.setEnabled(false);
       this.uploadButton.setDisabledIcon(imagebefore);
       this.editButton.setEnabled(true);
       this.removeButton.setEnabled(true);
   }
}
