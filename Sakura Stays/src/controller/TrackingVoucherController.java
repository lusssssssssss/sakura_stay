package controller;

import model.SqlModel;
import view.SendVoucherView;
import view.TrackingVoucherView;

public class TrackingVoucherController {
    private TrackingVoucherView view;
    private SqlModel model;

    public TrackingVoucherController(SqlModel model, TrackingVoucherView view) {
        this.view = view;
        this.model = model;

        model.updateTrackingVoucherTable(view);

        view.voucherList.addActionListener(e -> {
            String selectedVoucher = view.voucherList.getSelectedItem().toString();
            if(selectedVoucher.isEmpty()){
                model.updateTrackingVoucherTable(view);
            }
            else{
                String voucherCode = selectedVoucher.substring(0,1);
                model.filterTrackingVoucher(voucherCode,view);
            }
        });

        view.backButton.addActionListener(e -> {
            SendVoucherView sendVoucherView = new SendVoucherView();
            new SendVoucherController(model,sendVoucherView);
            sendVoucherView.setVisible(true);
            view.dispose();
        });
    }
}
