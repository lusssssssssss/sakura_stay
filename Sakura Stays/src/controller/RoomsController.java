package controller;

import component.CustomToggleButton;
import model.SqlModel;
import view.AdminHomeView;
import view.RoomsView;

import javax.swing.*;
import java.awt.*;

public class RoomsController {
    private RoomsView view;
    private SqlModel model;

    public RoomsController(SqlModel model, RoomsView view){
        this.model = model;
        this.view = view;

        for(int i = 4; i > 0; i--){
            for(int j = 1; j < 6; j++){
                String room_number = view.roomButtons[4-i][j-1].getText();
                model.updateRoomColor(room_number,view,i,j);
            }
        }

        for(int i = 0; i < view.roomButtons.length; i++){
            for(int j = 0; j < view.roomButtons[i].length; j++){
                CustomToggleButton button = view.roomButtons[i][j];
                if (button != null) {
                    int roomNumber = Integer.parseInt(button.getText());
                    button.addActionListener(e -> {
                        model.updateRoomDetailsPanel(roomNumber,view);
                    });
                }
            }
        }

        view.outOfServiceButton.addActionListener(e -> {
            boolean run_confirmation = true;
            boolean break_confirmation = false;
            for (int i = 0; i < view.roomButtons.length; i++) {
                for (int j = 0; j < view.roomButtons[i].length; j++) {
                    CustomToggleButton button = view.roomButtons[i][j];
                    if(button.getBackground().equals(new Color(230,230,230)) && button.isSelected()){
                        JOptionPane.showMessageDialog(view,"You cannot change the room status\nwith the same status","Input Error",JOptionPane.ERROR_MESSAGE);
                        run_confirmation = false;
                        break_confirmation = true;
                        break;
                    }
                }
                if(break_confirmation){
                    break;
                }
            }
            if(run_confirmation){
                int confirmation = JOptionPane.showConfirmDialog(view,"Are you sure you want to change\nthe room status to Out of Service?","Room Status Change Confirmation",JOptionPane.YES_NO_OPTION);
                if(confirmation == JOptionPane.YES_OPTION){
                    model.updateRoomStatus(new Color(230,230,230),"D","",view);
                    JOptionPane.showMessageDialog(view,"Successfully change the room status to\nOut of Service","Room Status Change Successful",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        view.occupiedButton.addActionListener(e -> {
            boolean run_confirmation = true;
            boolean break_confirmation = false;
            for (int i = 0; i < view.roomButtons.length; i++) {
                for (int j = 0; j < view.roomButtons[i].length; j++) {
                    CustomToggleButton button = view.roomButtons[i][j];
                    if(button.getBackground().equals(new Color(241,34,0)) && button.isSelected()){
                        JOptionPane.showMessageDialog(view,"You cannot change the room status\nwith the same status","Input Error",JOptionPane.ERROR_MESSAGE);
                        run_confirmation = false;
                        break_confirmation = true;
                        break;
                    }
                }
                if(break_confirmation){
                    break;
                }
            }
            if(run_confirmation){
                int confirmation = JOptionPane.showConfirmDialog(view,"Are you sure you want to change\nthe room status to Occupied?","Room Status Change Confirmation",JOptionPane.YES_NO_OPTION);
                if(confirmation == JOptionPane.YES_OPTION){
                    JComboBox comboBox = new JComboBox();
                    model.updateOptionPaneCB(comboBox);
                    int result = JOptionPane.showConfirmDialog(view,comboBox,"Select Guest ID",JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        String selectedGuestID = comboBox.getSelectedItem().toString();
                        model.updateRoomStatus(new Color(241,34,0),"O",selectedGuestID,view);
                        JOptionPane.showMessageDialog(view,"Successfully change the room status to Occupied","Room Status Change Successful",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        view.vacantButton.addActionListener(e -> {
            boolean run_confirmation = true;
            boolean break_confirmation = false;
            for (int i = 0; i < view.roomButtons.length; i++) {
                for (int j = 0; j < view.roomButtons[i].length; j++) {
                    CustomToggleButton button = view.roomButtons[i][j];
                    if(button.getBackground().equals(new Color(102,153,51)) && button.isSelected()){
                        JOptionPane.showMessageDialog(view,"You cannot change the room status\nwith the same status","Input Error",JOptionPane.ERROR_MESSAGE);
                        run_confirmation = false;
                        break_confirmation = true;
                        break;
                    }
                }
                if(break_confirmation){
                    break;
                }
            }
            if(run_confirmation){
                int confirmation = JOptionPane.showConfirmDialog(view,"Are you sure you want to change\nthe room status to Vacant?","Room Status Change Confirmation",JOptionPane.YES_NO_OPTION);
                if(confirmation == JOptionPane.YES_OPTION){
                    model.updateRoomStatus(new Color(102,153,51),"V","",view);
                    JOptionPane.showMessageDialog(view,"Successfully change the room status to Vacant","Room Status Change Successful",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        view.backButton.addActionListener(e -> {
            AdminHomeView adminHomeView = new AdminHomeView();
            new AdminHomeController(model, adminHomeView);
            adminHomeView.setVisible(true);
            view.dispose();
        });
    }
}
