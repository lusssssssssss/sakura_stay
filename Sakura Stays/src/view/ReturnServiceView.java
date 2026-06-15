package view;

import component.CustomButton;
import component.CustomPanel;
import component.PanelWithBackground;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class ReturnServiceView extends JFrame {
    public JComboBox serviceTypeList;
    public CustomButton backButton, submitButton;
    public JSpinner serviceAmount;

    public ReturnServiceView() {
        this.setTitle("Admin - Return Service");
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

        JLabel title = new JLabel("RETURN SERVICE");
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
        String[] services = {"","Yukata","Photobooth"};
        serviceTypeList = new JComboBox(services);
        serviceTypeList.setFont(new Font("Tahoma", Font.PLAIN, 40));
        serviceTypeList.setForeground(new Color(179, 60, 0));
        serviceTypeList.setBackground(Color.WHITE);
        serviceTypeList.setFocusable(false);
        serviceTypeList.setBorder(BorderFactory.createLineBorder(new Color(1,1,1),1));
        informationPanel.add(serviceTypeList);
        JLabel amountLabel = new JLabel("Amount: ");
        amountLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
        amountLabel.setForeground(new Color(179, 60, 0));
        informationPanel.add(amountLabel);
        serviceAmount = new JSpinner();
        serviceAmount.setModel(new SpinnerNumberModel(1,1,10,1));
        serviceAmount.setFont(new Font("Tahoma", Font.PLAIN, 40));
        serviceAmount.setForeground(new Color(179, 60, 0));
        serviceAmount.setBackground(Color.WHITE);
        serviceAmount.setFocusable(false);
        serviceAmount.setBorder(BorderFactory.createLineBorder(new Color(1,1,1),1));
        JComponent editor = serviceAmount.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
            JTextField textField = spinnerEditor.getTextField();
            textField.setHorizontalAlignment(JTextField.LEFT);
            textField.setEditable(false);
            textField.setBackground(Color.WHITE);
        }
        informationPanel.add(serviceAmount);
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