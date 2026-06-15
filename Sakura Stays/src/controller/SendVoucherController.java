package controller;

import model.SqlModel;
import view.AdminHomeView;
import view.SendVoucherView;
import view.TrackingVoucherView;

import javax.swing.*;

public class SendVoucherController {
    private SendVoucherView view;
    private SqlModel model;

    public SendVoucherController(SqlModel model, SendVoucherView view) {
        this.view = view;
        this.model = model;

        model.addSendVoucherData(view);

        view.sendButton.addActionListener(e -> {
            if(view.emailList.getSelectedItem().equals("") || view.voucherList.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(view, "Please fill all the required fields!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                int confirmation = JOptionPane.showConfirmDialog(view,"Are you sure you want to send the voucher?","Send Voucher Confirmation",JOptionPane.YES_NO_OPTION);
                if(confirmation == JOptionPane.YES_OPTION){
                    String selectedEmail = view.emailList.getSelectedItem().toString();
                    String selectedVoucherID = view.voucherList.getSelectedItem().toString();
                    model.sendVoucher(selectedEmail,selectedVoucherID,view);
                    JOptionPane.showMessageDialog(view,"Voucher successfully sent!","Send Voucher Success",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        view.backButton.addActionListener(e -> {
            AdminHomeView adminHomeView = new AdminHomeView();
            new AdminHomeController(model, adminHomeView);
            adminHomeView.setVisible(true);
            view.dispose();
        });

        view.trackingVoucherButton.addActionListener(e -> {
            TrackingVoucherView trackingVoucherView = new TrackingVoucherView();
            new TrackingVoucherController(model, trackingVoucherView);
            trackingVoucherView.setVisible(true);
            view.setVisible(false);
        });
    }
}
