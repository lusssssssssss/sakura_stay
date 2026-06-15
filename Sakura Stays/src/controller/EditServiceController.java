package controller;

import model.SqlModel;
import view.EditServiceView;
import view.ServiceView;

import javax.swing.*;

public class EditServiceController {
    private EditServiceView view;
    private SqlModel model;

    public EditServiceController(SqlModel model, EditServiceView view){
        this.model = model;
        this.view = view;

        view.submitButton.addActionListener(e -> {
            boolean RBconfirmation = false;
            if(view.enableRB.isSelected() || view.disableRB.isSelected()){
                RBconfirmation = true;
            }
            if(view.serviceTypeList.getSelectedItem().toString().isEmpty()){
                JOptionPane.showMessageDialog(view, "Please fill all the required fields!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(!RBconfirmation){
                JOptionPane.showMessageDialog(view, "Please fill all the required fields!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                if(view.enableRB.isSelected()){
                    String selectedService = view.serviceTypeList.getSelectedItem().toString();
                    String check = model.checkServiceStatus(selectedService);
                    if(check.equals("0")){
                        JOptionPane.showMessageDialog(view,"This service has been activated","Input Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        int enable_confirmation = JOptionPane.showConfirmDialog(view,"Are you sure you want to\nenable the service?","Enable Confirmation",JOptionPane.YES_NO_OPTION);
                        if(enable_confirmation == JOptionPane.YES_OPTION){
                            model.updateServiceStatus(selectedService,"0");
                            JOptionPane.showMessageDialog(view,"The service has been successfully activated","Status Change Success",JOptionPane.INFORMATION_MESSAGE);
                            view.group.clearSelection();
                            view.serviceTypeList.setSelectedItem("");
                        }
                    }
                }
                else{
                    String selectedService = view.serviceTypeList.getSelectedItem().toString();
                    String check = model.checkServiceStatus(selectedService);
                    if(check.equals("1")){
                        JOptionPane.showMessageDialog(view,"This service has been deactivated","Input Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        int disable_confirmation = JOptionPane.showConfirmDialog(view,"Are you sure you want to\ndisable the service?","Enable Confirmation",JOptionPane.YES_NO_OPTION);
                        if(disable_confirmation == JOptionPane.YES_OPTION){
                            model.updateServiceStatus(selectedService,"1");
                            JOptionPane.showMessageDialog(view,"The service has been successfully deactivated","Status Change Success",JOptionPane.INFORMATION_MESSAGE);
                            view.group.clearSelection();
                            view.serviceTypeList.setSelectedItem("");
                        }
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
