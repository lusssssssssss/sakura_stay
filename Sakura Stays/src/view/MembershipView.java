package view;

import component.CustomButton;
import component.PanelWithBackground;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class MembershipView extends JFrame {
    public JTable membershipTable;
    public DefaultTableModel tableModel;
    public CustomButton backButton;

    public MembershipView() {
        this.setTitle("Admin - Membership");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);

        PanelWithBackground mainPanel = new PanelWithBackground("picture/membershipBackground.png");
        mainPanel.setLayout(null);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBounds(30,205,1465,500);
        tablePanel.setOpaque(false);
        mainPanel.add(tablePanel);

        String[] columnNames = {"ID","Name","Address","Phone","Birthdate","Email","Password","Type"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        membershipTable = new JTable(tableModel);
        styleTable();
        JScrollPane scrollPane = new JScrollPane(membershipTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        backButton = new CustomButton("BACK",30);
        backButton.setBounds(30,725,200,40);
        backButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        backButton.setBackground(new Color(255, 118, 118));
        backButton.setForeground(Color.WHITE);
        mainPanel.add(backButton);

        this.add(mainPanel);
    }

    private void styleTable() {
        membershipTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        membershipTable.setRowHeight(50);
        membershipTable.setShowGrid(true);
        membershipTable.setGridColor(new Color(220, 20, 60));
        membershipTable.setSelectionBackground(new Color(255, 182, 193, 100));
        membershipTable.getTableHeader().setReorderingAllowed(false);

        JTableHeader header = membershipTable.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 25));
        header.setBackground(new Color(255, 182, 193));
        header.setForeground(Color.BLACK);
        header.setPreferredSize(new Dimension(0, 60));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setBackground(Color.WHITE);

        for (int i = 0; i < membershipTable.getColumnCount(); i++) {
            membershipTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        membershipTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        membershipTable.getColumnModel().getColumn(1).setPreferredWidth(183);
        membershipTable.getColumnModel().getColumn(2).setPreferredWidth(341);
        membershipTable.getColumnModel().getColumn(3).setPreferredWidth(133);
        membershipTable.getColumnModel().getColumn(4).setPreferredWidth(163);
        membershipTable.getColumnModel().getColumn(5).setPreferredWidth(291);
        membershipTable.getColumnModel().getColumn(6).setPreferredWidth(153);
        membershipTable.getColumnModel().getColumn(7).setPreferredWidth(100);
    }
}