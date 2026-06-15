package controller;

import model.SqlModel;
import view.ReturnServiceView;
import view.ServiceView;

import javax.swing.*;

public class ReturnServiceController{
    private SqlModel model;
    private ReturnServiceView view;

    public ReturnServiceController(SqlModel model, ReturnServiceView view){
        this.model = model;
        this.view = view;

        view.submitButton.addActionListener(e -> {
            if(view.serviceTypeList.getSelectedItem().toString().isEmpty()){
                JOptionPane.showMessageDialog(view, "Please fill all the required fields!", "Input Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            else{
                String selectedService = view.serviceTypeList.getSelectedItem().toString();
                int returnAmount = Integer.parseInt(view.serviceAmount.getValue().toString());
                int total = model.checkAmount(selectedService, returnAmount);
                if(total > 10){
                    JOptionPane.showMessageDialog(view, "The number of service items\ncannot be more than 10!", "Input Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int confirmation = JOptionPane.showConfirmDialog(view,"Are you sure you want to add\nitems to this service?","Service Return Confirmation",JOptionPane.YES_NO_OPTION);
                    if(confirmation == JOptionPane.YES_OPTION){
                        model.updateServiceAmount(total, selectedService);
                        JOptionPane.showMessageDialog(view, "Service items added successfully!", "Return Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        view.serviceTypeList.setSelectedItem("");
                        view.serviceAmount.setValue(1);
                    }
                }
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
