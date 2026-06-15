package controller;

import model.SqlModel;
import view.AdminHomeView;
import view.MembershipView;

public class MembershipController {
    private MembershipView view;
    private SqlModel model;

    public MembershipController(SqlModel model, MembershipView view) {
        this.model = model;
        this.view = view;

        model.updateMembershipTable(view);

        view.backButton.addActionListener(e -> {
            AdminHomeView adminHomeView = new AdminHomeView();
            new AdminHomeController(model, adminHomeView);
            adminHomeView.setVisible(true);
            view.dispose();
        });
    }
}