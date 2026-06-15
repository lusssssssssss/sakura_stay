package controller;

import model.SqlModel;
import view.ServiceHistoryView;
import view.ServiceView;

public class ServiceHistoryController {
    private ServiceHistoryView view;
    private SqlModel model;

    public ServiceHistoryController(SqlModel model, ServiceHistoryView view){
        this.model = model;
        this.view = view;

        model.addServiceHistoryData(view);

        view.serviceList.addActionListener(e -> {
            String selectedService = view.serviceList.getSelectedItem().toString();
            if(selectedService.isEmpty()){
                model.addServiceHistoryData(view);
            }
            else{
                model.updateServiceHistoryData(selectedService, view);
            }
        });

        view.backButton.addActionListener(e -> {
            ServiceView serviceView = new ServiceView();
            new ServiceController(model, serviceView);
            serviceView.setVisible(true);
            view.dispose();
        });
    }
}
