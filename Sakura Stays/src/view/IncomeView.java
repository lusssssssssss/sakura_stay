package view;

import component.CustomButton;
import component.PanelWithBackground;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class IncomeView extends JFrame {
    public JComboBox roomTypeCombo;
    public JTable incomeTable;
    public DefaultTableModel tableModel;
    public JLabel totalLabel;
    public CustomButton backButton;

    public IncomeView() {
        this.setTitle("Admin - Income");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);

        PanelWithBackground mainPanel = new PanelWithBackground("picture/incomeBackground.png");
        mainPanel.setLayout(null);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBounds(30,215,1465,500);
        tablePanel.setOpaque(false);
        mainPanel.add(tablePanel);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        filterPanel.setOpaque(false);
        filterPanel.setBorder(new EmptyBorder(0,0,20,0));
        tablePanel.add(filterPanel, BorderLayout.NORTH);

        JLabel roomTypeLabel = new JLabel("Room Type:");
        roomTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        roomTypeLabel.setForeground(new Color(188,0,45));
        filterPanel.add(roomTypeLabel);
        filterPanel.add(Box.createHorizontalStrut(20));

        String[] roomTypes = {"","Kapsul","Standard","Deluxe","VIP"};
        roomTypeCombo = new JComboBox(roomTypes);
        styleComboBox(roomTypeCombo);
        filterPanel.add(roomTypeCombo);

        String[] columnNames = {"Type", "Room Number", "Guest ID", "Income"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        incomeTable = new JTable(tableModel);
        styleTable();
        JScrollPane scrollPane = new JScrollPane(incomeTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        totalLabel = new JLabel("Total Penghasilan: ", SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        totalLabel.setForeground(new Color(188,0,45));
        totalLabel.setBorder(new EmptyBorder(20,0,0,0));
        tablePanel.add(totalLabel, BorderLayout.SOUTH);

        backButton = new CustomButton("BACK",30);
        backButton.setBounds(30,725,200,40);
        backButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        backButton.setBackground(new Color(255, 118, 118));
        backButton.setForeground(Color.WHITE);
        mainPanel.add(backButton);

        this.add(mainPanel);
    }

    private void styleComboBox(JComboBox<String> combo) {
        combo.setFont(new Font("Tahoma", Font.PLAIN, 25));
        combo.setPreferredSize(new Dimension(220, 50));
        combo.setFocusable(false);
        combo.setMaximumRowCount(2);
        combo.setForeground(new Color(188,0,45));
        combo.setBackground(new Color(255, 232, 250));
    }

    private void styleTable() {
        incomeTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        incomeTable.setRowHeight(50);
        incomeTable.setShowGrid(true);
        incomeTable.setGridColor(new Color(220, 20, 60));
        incomeTable.setSelectionBackground(new Color(255, 182, 193, 100));
        incomeTable.getTableHeader().setReorderingAllowed(false);

        JTableHeader header = incomeTable.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 25));
        header.setBackground(new Color(255, 182, 193));
        header.setForeground(Color.BLACK);
        header.setPreferredSize(new Dimension(0, 60));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setBackground(Color.WHITE);

        for (int i = 0; i < incomeTable.getColumnCount(); i++) {
            incomeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        incomeTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        incomeTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        incomeTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        incomeTable.getColumnModel().getColumn(3).setPreferredWidth(250);
    }
}