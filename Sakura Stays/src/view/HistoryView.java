package view;

import component.CustomButton;
import component.PanelWithBackground;

import javax.swing.*;
import java.awt.*;

public class HistoryView extends JFrame {
    public JPanel dataPanel,mainPanel;
    public CustomButton backButton;

    public HistoryView() {
        this.setTitle("Admin - Booking History");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);

        PanelWithBackground bgPanel = new PanelWithBackground("picture/historyBackground.png");
        bgPanel.setLayout(null);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBounds(30,190,1465,511);
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
        bgPanel.add(mainPanel);

        dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        dataPanel.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(dataPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        backButton = new CustomButton("BACK",30);
        backButton.setBounds(30,725,200,40);
        backButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        backButton.setBackground(new Color(255, 118, 118));
        backButton.setForeground(Color.WHITE);
        bgPanel.add(backButton);

        this.add(bgPanel);
    }
}