package controller;

import view.AdminHomeView;
import view.AdminLoginView;
import model.SqlModel;

import javax.swing.*;

public class AdminLoginController {
    private AdminLoginView view;
    private SqlModel model;

    public AdminLoginController(SqlModel model, AdminLoginView view) {
        this.model = model;
        this.view = view;

        view.loginButton.addActionListener(e -> {
            if(view.emailField.getText().isEmpty() || view.passField.getText().isEmpty()){
                JOptionPane.showMessageDialog(view, "Please fill all the required fields!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                if(!model.confirmation(view.emailField.getText(), view.passField.getText())){
                    JOptionPane.showMessageDialog(view, "Invalid email or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    view.emailField.setText("");
                    view.passField.setText("");
                }
                else{
                    JOptionPane.showMessageDialog(view,"Login successful! Welcome, Admin.","Login Success", JOptionPane.INFORMATION_MESSAGE);
                    AdminHomeView adminHomeView = new AdminHomeView();
                    new AdminHomeController(model, adminHomeView);
                    adminHomeView.setVisible(true);
                    view.dispose();
                }
            }
        });
    }
}
