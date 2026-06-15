package view;

import component.CustomButton;
import component.PanelWithBackground;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ServiceView extends JFrame {
    public CustomButton backButton, editButton, returnButton, historyButton;
    public JComboBox serviceList;
    public JLabel photo, description;

    public ServiceView() {
        this.setTitle("Admin - Service");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);

        PanelWithBackground backgroundPanel = new PanelWithBackground("picture/serviceBackground.png");
        backgroundPanel.setLayout(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBounds(30,215,1465,480);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        mainPanel.setOpaque(false);
        backgroundPanel.add(mainPanel);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        filterPanel.setOpaque(false);
        filterPanel.setBorder(new EmptyBorder(0,0,20,0));
        mainPanel.add(filterPanel, BorderLayout.NORTH);

        JLabel serviceLabel = new JLabel("Available Service:");
        serviceLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        serviceLabel.setForeground(new Color(188,0,45));
        filterPanel.add(serviceLabel);
        filterPanel.add(Box.createHorizontalStrut(20));

        String[] services = {"Breakfast","Baby Cot","Photobooth","Extra Bed","Yukata"};
        serviceList = new JComboBox(services);
        styleComboBox(serviceList);
        filterPanel.add(serviceList);

        JPanel photoPanel = new JPanel(new BorderLayout());
        photoPanel.setOpaque(false);
        mainPanel.add(photoPanel, BorderLayout.CENTER);
        ImageIcon image = new ImageIcon("picture/japanese breakfast.png");
        photo = new JLabel(new ImageIcon(image.getImage().getScaledInstance(850, 300, Image.SCALE_SMOOTH)));
        photoPanel.add(photo, BorderLayout.CENTER);

        description = new JLabel();
        description.setFont(new Font("Tahoma", Font.PLAIN, 30));
        description.setForeground(new Color(188,0,45));
        description.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(description, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1,5,20,0));
        buttonPanel.setBounds(30,725,1465,40);
        buttonPanel.setOpaque(false);
        backgroundPanel.add(buttonPanel);
        backButton = new CustomButton("BACK",30);
        backButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        backButton.setBackground(new Color(255, 118, 118));
        backButton.setForeground(Color.WHITE);
        buttonPanel.add(backButton);
        buttonPanel.add(new JLabel());
        editButton = new CustomButton("EDIT",40);
        editButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        editButton.setBackground(new Color(255, 118, 118));
        editButton.setForeground(Color.WHITE);
        buttonPanel.add(editButton);
        returnButton = new CustomButton("RETURN SERVICE",40);
        returnButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        returnButton.setBackground(new Color(255, 118, 118));
        returnButton.setForeground(Color.WHITE);
        buttonPanel.add(returnButton);
        historyButton = new CustomButton("SEE HISTORY",40);
        historyButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        historyButton.setBackground(new Color(255, 118, 118));
        historyButton.setForeground(Color.WHITE);
        buttonPanel.add(historyButton);

        this.add(backgroundPanel);
    }

    private void styleComboBox(JComboBox<String> combo) {
        combo.setFont(new Font("Tahoma", Font.PLAIN, 25));
        combo.setPreferredSize(new Dimension(220, 50));
        combo.setFocusable(false);
        combo.setMaximumRowCount(2);
        combo.setForeground(new Color(188,0,45));
        combo.setBackground(new Color(255, 232, 250));
    }
}
