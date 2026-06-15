package view;

import component.CustomButton;
import component.PanelWithBackground;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class TrackingVoucherView extends JFrame {
    public JComboBox voucherList;
    public JTable voucherTable;
    public DefaultTableModel tableModel;
    public CustomButton backButton;

    public TrackingVoucherView() {
        this.setTitle("Admin - Tracking Voucher");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);

        PanelWithBackground mainPanel = new PanelWithBackground("picture/trackingVoucherBackground.png");
        mainPanel.setLayout(null);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBounds(30,215,1465,500);
        tablePanel.setOpaque(false);
        mainPanel.add(tablePanel);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        filterPanel.setOpaque(false);
        filterPanel.setBorder(new EmptyBorder(0,0,20,0));
        tablePanel.add(filterPanel, BorderLayout.NORTH);

        JLabel voucherLabel = new JLabel("Voucher Type:");
        voucherLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        voucherLabel.setForeground(new Color(188,0,45));
        filterPanel.add(voucherLabel);
        filterPanel.add(Box.createHorizontalStrut(20));

        String[] voucherTypes = {"","Birthday","Stay"};
        voucherList = new JComboBox(voucherTypes);
        styleComboBox(voucherList);
        filterPanel.add(voucherList);

        String[] columnNames = {"Membership Email", "Voucher ID", "Membership Type","Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        voucherTable = new JTable(tableModel);
        styleTable();
        JScrollPane scrollPane = new JScrollPane(voucherTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

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
        voucherTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        voucherTable.setRowHeight(50);
        voucherTable.setShowGrid(true);
        voucherTable.setGridColor(new Color(220, 20, 60));
        voucherTable.setSelectionBackground(new Color(255, 182, 193, 100));
        voucherTable.getTableHeader().setReorderingAllowed(false);

        JTableHeader header = voucherTable.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 25));
        header.setBackground(new Color(255, 182, 193));
        header.setForeground(Color.BLACK);
        header.setPreferredSize(new Dimension(0, 60));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setBackground(Color.WHITE);

        for (int i = 0; i < voucherTable.getColumnCount(); i++) {
            voucherTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        voucherTable.getColumnModel().getColumn(0).setPreferredWidth(366);
        voucherTable.getColumnModel().getColumn(1).setPreferredWidth(366);
        voucherTable.getColumnModel().getColumn(2).setPreferredWidth(366);
        voucherTable.getColumnModel().getColumn(3).setPreferredWidth(366);
    }
}