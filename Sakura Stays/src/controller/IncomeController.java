package controller;

import model.SqlModel;
import view.AdminHomeView;
import view.IncomeView;

public class IncomeController {
    private IncomeView view;
    private SqlModel model;

    public IncomeController(SqlModel model, IncomeView view){
        this.view = view;
        this.model = model;

        model.loadIncomeTable(view);

        view.roomTypeCombo.addActionListener(e -> {
            String selectedRoomType = view.roomTypeCombo.getSelectedItem().toString();
            if(selectedRoomType.isEmpty()){
                model.loadIncomeTable(view);
            }
            else{
                model.updateIncomeTable(view, selectedRoomType);
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
