package view;

import component.PanelWithBackground;

import javax.swing.*;
import java.awt.*;

public class AdminHomeView extends JFrame {
    public JButton roomsButton, serviceButton, historyButton, incomeButton, membershipButton, sendVoucherButton, logOutButton;

    public AdminHomeView() {
        this.setTitle("Admin - Home");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);

        PanelWithBackground bgPanel = new PanelWithBackground("picture/adminHomeBackground.png");
        bgPanel.setLayout(null);

        ImageIcon homeImage = new ImageIcon("picture/Home.png");
        JLabel home = new JLabel(new ImageIcon(homeImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        home.setBounds(70,15,50,50);
        bgPanel.add(home);

        JPanel menuBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        menuBarPanel.setBounds(170,30,1260,30);
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
        menuBarPanel.add(Box.createHorizontalStrut(90));

        serviceButton = new JButton("SERVICE");
        serviceButton.setFocusable(false);
        serviceButton.setFont(new Font("Tahoma", Font.BOLD, 19));
        serviceButton.setForeground(new Color(204,51,0));
        serviceButton.setContentAreaFilled(false);
        serviceButton.setFocusPainted(false);
        serviceButton.setBorderPainted(true);
        serviceButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0)));
        menuBarPanel.add(serviceButton);
        menuBarPanel.add(Box.createHorizontalStrut(90));

        historyButton = new JButton("HISTORY");
        historyButton.setFocusable(false);
        historyButton.setFont(new Font("Tahoma", Font.BOLD, 19));
        historyButton.setForeground(new Color(204,51,0));
        historyButton.setContentAreaFilled(false);
        historyButton.setFocusPainted(false);
        historyButton.setBorderPainted(true);
        historyButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0)));
        menuBarPanel.add(historyButton);
        menuBarPanel.add(Box.createHorizontalStrut(90));

        incomeButton = new JButton("INCOME");
        incomeButton.setFocusable(false);
        incomeButton.setFont(new Font("Tahoma", Font.BOLD, 19));
        incomeButton.setForeground(new Color(204,51,0));
        incomeButton.setContentAreaFilled(false);
        incomeButton.setFocusPainted(false);
        incomeButton.setBorderPainted(true);
        incomeButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0)));
        menuBarPanel.add(incomeButton);
        menuBarPanel.add(Box.createHorizontalStrut(90));

        membershipButton = new JButton("MEMBERSHIP");
        membershipButton.setFocusable(false);
        membershipButton.setFont(new Font("Tahoma", Font.BOLD, 19));
        membershipButton.setForeground(new Color(204,51,0));
        membershipButton.setContentAreaFilled(false);
        membershipButton.setFocusPainted(false);
        membershipButton.setBorderPainted(true);
        membershipButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0)));
        menuBarPanel.add(membershipButton);
        menuBarPanel.add(Box.createHorizontalStrut(90));

        sendVoucherButton = new JButton("SEND VOUCHER");
        sendVoucherButton.setFocusable(false);
        sendVoucherButton.setFont(new Font("Tahoma", Font.BOLD, 19));
        sendVoucherButton.setForeground(new Color(204,51,0));
        sendVoucherButton.setContentAreaFilled(false);
        sendVoucherButton.setFocusPainted(false);
        sendVoucherButton.setBorderPainted(true);
        sendVoucherButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0)));
        menuBarPanel.add(sendVoucherButton);
        menuBarPanel.add(Box.createHorizontalStrut(90));

        logOutButton = new JButton("LOGOUT");
        logOutButton.setFocusable(false);
        logOutButton.setFont(new Font("Tahoma", Font.BOLD, 19));
        logOutButton.setForeground(new Color(204,51,0));
        logOutButton.setContentAreaFilled(false);
        logOutButton.setFocusPainted(false);
        logOutButton.setBorderPainted(true);
        logOutButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0)));
        menuBarPanel.add(logOutButton);

        JPanel messagePanel = new JPanel(new GridLayout(2,1,0,-50));
        messagePanel.setBounds(265,120,1000,250);
        messagePanel.setBackground(new Color(1,1,1,100));
        bgPanel.add(messagePanel);

        JLabel title = new JLabel("Welcome to Sakura Stay's");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Times New Roman", Font.BOLD, 80));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        messagePanel.add(title);

        JLabel subtitle = new JLabel("Admin Dashboard");
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Times New Roman", Font.PLAIN, 70));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        messagePanel.add(subtitle);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBounds(30,470,1463,310);
        bottomPanel.setOpaque(false);
        bgPanel.add(bottomPanel);

        JPanel picturePanel = new JPanel(new GridLayout(1,3));
        picturePanel.setOpaque(false);
        bottomPanel.add(picturePanel, BorderLayout.CENTER);

        ImageIcon home1Image = new ImageIcon("picture/homePicture1.jpg");
        JLabel home1 = new JLabel(new ImageIcon(home1Image.getImage().getScaledInstance(450, 300, Image.SCALE_SMOOTH)));
        picturePanel.add(home1);

        ImageIcon home2Image = new ImageIcon("picture/homePicture2.jpg");
        JLabel home2 = new JLabel(new ImageIcon(home2Image.getImage().getScaledInstance(450, 300, Image.SCALE_SMOOTH)));
        picturePanel.add(home2);

        ImageIcon home3Image = new ImageIcon("picture/homePicture3.jpg");
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
    }
}
