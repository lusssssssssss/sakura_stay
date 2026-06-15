package view;

import component.CustomButton;
import component.CustomPanel;
import component.PanelWithBackground;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class EditServiceView extends JFrame {
    public JComboBox serviceTypeList;
    public CustomButton backButton, submitButton;
    public JRadioButton disableRB, enableRB;
    public ButtonGroup group;

    public EditServiceView() {
        this.setTitle("Admin - Edit Service");
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

        JLabel title = new JLabel("EDIT SERVICE");
        title.setFont(new Font("Tahoma", Font.BOLD, 50));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(179, 60, 0));
        formPanel.add(title, BorderLayout.NORTH);

        JPanel dataPanel = new JPanel(new BorderLayout());
        dataPanel.setOpaque(false);
        formPanel.add(dataPanel, BorderLayout.CENTER);

        JPanel informationPanel = new JPanel(new GridLayout(2,2,-100,120));
        informationPanel.setBorder(BorderFactory.createEmptyBorder(90,90,120,90));
        informationPanel.setOpaque(false);
        JLabel serviceTypeLabel = new JLabel("Service Type: ");
        serviceTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
        serviceTypeLabel.setForeground(new Color(179, 60, 0));
        informationPanel.add(serviceTypeLabel);
        String[] services = {"","Breakfast","Extra Bed","Baby Cot"};
        serviceTypeList = new JComboBox(services);
        serviceTypeList.setFont(new Font("Tahoma", Font.PLAIN, 40));
        serviceTypeList.setForeground(new Color(179, 60, 0));
        serviceTypeList.setBackground(Color.WHITE);
        serviceTypeList.setFocusable(false);
        serviceTypeList.setBorder(BorderFactory.createLineBorder(new Color(1,1,1),1));
        informationPanel.add(serviceTypeList);
        JLabel statusLabel = new JLabel("Status: ");
        statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
        statusLabel.setForeground(new Color(179, 60, 0));
        informationPanel.add(statusLabel);
        JPanel radioButtonPanel = new JPanel(new GridLayout(1,2,20,0));
        radioButtonPanel.setBackground(Color.WHITE);
        radioButtonPanel.setBorder(BorderFactory.createLineBorder(new Color(1,1,1),1));
        disableRB = new JRadioButton("Disable");
        disableRB.setFont(new Font("Tahoma", Font.PLAIN, 40));
        disableRB.setForeground(new Color(179, 60, 0));
        disableRB.setFocusable(false);
        disableRB.setOpaque(false);
        disableRB.setIconTextGap(15);
        disableRB.setHorizontalAlignment(SwingConstants.CENTER);
        enableRB = new JRadioButton("Enable");
        enableRB.setFont(new Font("Tahoma", Font.PLAIN, 40));
        enableRB.setForeground(new Color(179, 60, 0));
        enableRB.setFocusable(false);
        enableRB.setOpaque(false);
        enableRB.setIconTextGap(15);
        enableRB.setHorizontalAlignment(SwingConstants.CENTER);
        group = new ButtonGroup();
        group.add(disableRB);
        group.add(enableRB);
        radioButtonPanel.add(disableRB);
        radioButtonPanel.add(enableRB);
        informationPanel.add(radioButtonPanel);
        dataPanel.add(informationPanel, BorderLayout.CENTER);

        JPanel button_panel = new JPanel(new GridLayout(1,4,30,0));
        button_panel.setOpaque(false);
        formPanel.add(button_panel, BorderLayout.SOUTH);
        button_panel.add(new JLabel());

        backButton = new CustomButton("BACK",30);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 24));
        backButton.setBackground(new Color(255, 118, 118));
        backButton.setForeground(Color.WHITE);
        button_panel.add(backButton);

        submitButton = new CustomButton("SUBMIT",30);
        submitButton.setFont(new Font("Tahoma", Font.BOLD, 24));
        submitButton.setBackground(new Color(255, 118, 118));
        submitButton.setForeground(Color.WHITE);
        button_panel.add(submitButton);
        button_panel.add(new JLabel());

        this.add(mainPanel, BorderLayout.CENTER);
    }
}