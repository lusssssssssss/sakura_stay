package controller;

import model.SqlModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class AdminHomeController{
    private SqlModel model;
    private AdminHomeView view;

    public AdminHomeController(SqlModel model, AdminHomeView view){
        this.model = model;
        this.view = view;

        JButton[] buttons = {
                view.roomsButton, view.serviceButton, view.historyButton, view.incomeButton,
                view.membershipButton, view.sendVoucherButton, view.logOutButton
        };
        for (JButton btn : buttons) {
            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 51, 0)));
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0))); // transparan kembali
                }
            });
        }

        view.roomsButton.addActionListener(e -> {
            RoomsView roomsView = new RoomsView();
            new RoomsController(model,roomsView);
            roomsView.setVisible(true);
            view.dispose();
        });

        view.serviceButton.addActionListener(e -> {
            ServiceView serviceView = new ServiceView();
            new ServiceController(model,serviceView);
            serviceView.setVisible(true);
            view.dispose();
        });

        view.historyButton.addActionListener(e -> {
            HistoryView historyView = new HistoryView();
            new HistoryController(model,historyView);
            historyView.setVisible(true);
            view.dispose();
        });

        view.incomeButton.addActionListener(e -> {
            IncomeView incomeView = new IncomeView();
            new IncomeController(model,incomeView);
            incomeView.setVisible(true);
            view.dispose();
        });

        view.membershipButton.addActionListener(e -> {
            MembershipView membershipView = new MembershipView();
            new MembershipController(model,membershipView);
            membershipView.setVisible(true);
            view.dispose();
        });

        view.sendVoucherButton.addActionListener(e -> {
            SendVoucherView sendVoucherView = new SendVoucherView();
            new SendVoucherController(model,sendVoucherView);
            sendVoucherView.setVisible(true);
            view.dispose();
        });

        view.logOutButton.addActionListener(e -> {
            int confirmation = JOptionPane.showConfirmDialog(view,"Are you sure you want to logout?","Logout Cofirmation",JOptionPane.YES_NO_OPTION);
            if(confirmation == JOptionPane.YES_OPTION){
                AdminLoginView adminLoginView = new AdminLoginView();
                new AdminLoginController(model, adminLoginView);
                adminLoginView.setVisible(true);
                view.dispose();
            }
        });
    }
}
