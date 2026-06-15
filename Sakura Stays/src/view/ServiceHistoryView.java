package view;

import component.CustomButton;
import component.PanelWithBackground;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ServiceHistoryView extends JFrame {
    public JComboBox serviceList;
    public JTable serviceTable;
    public DefaultTableModel tableModel;
    public CustomButton backButton;
    public JLabel totalLabel;

    public ServiceHistoryView() {
        this.setTitle("Admin - Service History");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);

        PanelWithBackground mainPanel = new PanelWithBackground("picture/serviceHistoryBackground.png");
        mainPanel.setLayout(null);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBounds(30,215,1465,500);
        tablePanel.setOpaque(false);
        mainPanel.add(tablePanel);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        filterPanel.setOpaque(false);
        filterPanel.setBorder(new EmptyBorder(0,0,20,0));
        tablePanel.add(filterPanel, BorderLayout.NORTH);

        JLabel serviceLabel = new JLabel("Available Service:");
        serviceLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        serviceLabel.setForeground(new Color(188,0,45));
        filterPanel.add(serviceLabel);
        filterPanel.add(Box.createHorizontalStrut(20));

        String[] services = {"","Breakfast","Yukata","Photo Session","Extra Bed","Baby Cot"};
        serviceList = new JComboBox(services);
        styleComboBox(serviceList);
        filterPanel.add(serviceList);

        String[] columnNames = {"Guest ID", "Service Name", "Amount"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        serviceTable = new JTable(tableModel);
        styleTable();
        JScrollPane scrollPane = new JScrollPane(serviceTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        totalLabel = new JLabel("Total Penggunaan: ", SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        totalLabel.setForeground(new Color(188,0,45));
        totalLabel.setBorder(new EmptyBorder(20,0,0,0));
        tablePanel.add(totalLabel, BorderLayout.SOUTH);

        backButton = new CustomButton("BACK",30);
        backButton.setBounds(30,730,200,40);
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
        serviceTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        serviceTable.setRowHeight(50);
        serviceTable.setShowGrid(true);
        serviceTable.setGridColor(new Color(220, 20, 60));
        serviceTable.setSelectionBackground(new Color(255, 182, 193, 100));
        serviceTable.getTableHeader().setReorderingAllowed(false);

        JTableHeader header = serviceTable.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 25));
        header.setBackground(new Color(255, 182, 193));
        header.setForeground(Color.BLACK);
        header.setPreferredSize(new Dimension(0, 60));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setBackground(Color.WHITE);

        for (int i = 0; i < serviceTable.getColumnCount(); i++) {
            serviceTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        serviceTable.getColumnModel().getColumn(0).setPreferredWidth(488);
        serviceTable.getColumnModel().getColumn(1).setPreferredWidth(488);
        serviceTable.getColumnModel().getColumn(2).setPreferredWidth(488);
    }
}