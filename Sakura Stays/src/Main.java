import controller.*;
import model.SqlModel;
import view.*;


import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
//        try {
//            AdminLoginView view = new AdminLoginView();
//            SqlModel model = new SqlModel();
//            new AdminLoginController(model, view);
//            view.setVisible(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sakura_stay", "root", "louisanthony");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



}