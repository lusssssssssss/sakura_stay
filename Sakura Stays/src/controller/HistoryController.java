package controller;

import model.SqlModel;
import view.AdminHomeView;
import view.HistoryView;

public class HistoryController {
    private HistoryView view;
    private SqlModel model;

    public HistoryController(SqlModel model, HistoryView view) {
        this.model = model;
        this.view = view;

        model.addHistory(view);

        view.backButton.addActionListener(e -> {
            AdminHomeView adminHomeView = new AdminHomeView();
            new AdminHomeController(model, adminHomeView);
            adminHomeView.setVisible(true);
            view.dispose();
        });
    }
}
