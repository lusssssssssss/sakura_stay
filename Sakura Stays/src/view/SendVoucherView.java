package view;

import component.CustomButton;
import component.CustomPanel;
import component.PanelWithBackground;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class SendVoucherView extends JFrame {
    public JComboBox emailList, voucherList;
    public CustomButton backButton, sendButton, trackingVoucherButton;

    public SendVoucherView() {
        this.setTitle("Admin - Send Voucher");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);

        PanelWithBackground mainPanel = new PanelWithBackground("picture/serviceEditReturnSendBackground.png");
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(100,350,100,350));

        CustomPanel formPanel = new CustomPanel();
        formPanel.setBackground(new Color(255,255,255,180));
        formPanel.setBorder(new EmptyBorder(20,0,40,0));
        formPanel.setLayout(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.CENTER);

        JLabel title = new JLabel("SEND VOUCHER");
        title.setFont(new Font("Tahoma", Font.BOLD, 50));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(179, 60, 0));
        formPanel.add(title, BorderLayout.NORTH);

        JPanel dataPanel = new JPanel(new BorderLayout());
        dataPanel.setOpaque(false);
        dataPanel.setBorder(new EmptyBorder(20,0,20,0));
        formPanel.add(dataPanel, BorderLayout.CENTER);

        JPanel informationPanel = new JPanel(new GridLayout(2,2,-170,100));
        informationPanel.setBorder(BorderFactory.createEmptyBorder(60,90,110,90));
        informationPanel.setOpaque(false);
        JLabel emailLabel = new JLabel("Guest Email: ");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        emailLabel.setForeground(new Color(179, 60, 0));
        informationPanel.add(emailLabel);
        emailList = new JComboBox();
        emailList.addItem("");
        emailList.setMaximumRowCount(5);
        emailList.setFont(new Font("Tahoma", Font.PLAIN, 30));
        emailList.setBackground(Color.WHITE);
        emailList.setFocusable(false);
        informationPanel.add(emailList);
        JLabel voucherLabel = new JLabel("Voucher ID: ");
        voucherLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        voucherLabel.setForeground(new Color(179, 60, 0));
        informationPanel.add(voucherLabel);
        voucherList = new JComboBox();
        voucherList.addItem("");
        voucherList.setMaximumRowCount(5);
        voucherList.setFont(new Font("Tahoma", Font.PLAIN, 30));
        voucherList.setBackground(Color.WHITE);
        voucherList.setFocusable(false);
        informationPanel.add(voucherList);
        dataPanel.add(informationPanel, BorderLayout.CENTER);

        JPanel trackingPanel = new JPanel();
        trackingPanel.setOpaque(false);
        trackingVoucherButton = new CustomButton("Tracking Voucher",30);
        trackingVoucherButton.setFont(new Font("Tahoma", Font.BOLD, 24));
        trackingVoucherButton.setBackground(Color.WHITE);
        trackingVoucherButton.setForeground(new Color(255, 118, 118));
        trackingPanel.add(trackingVoucherButton);
        dataPanel.add(trackingPanel, BorderLayout.SOUTH);

        JPanel button_panel = new JPanel(new GridLayout(1,4,30,0));
        button_panel.setOpaque(false);
        formPanel.add(button_panel, BorderLayout.SOUTH);
        button_panel.add(new JLabel());

        backButton = new CustomButton("BACK",30);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 24));
        backButton.setBackground(new Color(255, 118, 118));
        backButton.setForeground(Color.WHITE);
        button_panel.add(backButton);

        sendButton = new CustomButton("SEND",30);
        sendButton.setFont(new Font("Tahoma", Font.BOLD, 24));
        sendButton.setBackground(new Color(255, 118, 118));
        sendButton.setForeground(Color.WHITE);
        button_panel.add(sendButton);
        button_panel.add(new JLabel());

        this.add(mainPanel, BorderLayout.CENTER);
    }
}