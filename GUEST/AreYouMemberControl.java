import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class AreYouMemberControl implements ActionListener {
    private AreYouMemberView view;
    public AreYouMemberControl(AreYouMemberView view) {
        this.view = view;
    }
    public void actionPerformed(ActionEvent e) {
        String email = view.emailField.getText();
        String pass = view.passField.getText();
        String existEmail = "";
        String existPass = "";
        String photo = "";
        if(e.getSource() == view.loginButton){
            try {
                ArrayList<String>emailpass = view.model.EmailPassMember(view.con);
                boolean exist = false;
                for(int i=0;i<emailpass.size();i+=3){
                    existEmail= emailpass.get(i);
                    existPass = emailpass.get(i+1);
                    photo = emailpass.get(i+2);
                    if(email.equals(existEmail) && pass.equals(existPass)){
                        exist = true;
                        break;
                    }
                }
                if(exist){
                    GuestHomeView guestHomeView = new GuestHomeView(view.model, view.con, email, photo);
                    guestHomeView.setVisible(true);
                    view.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(view, "Wrong email or password");
                }
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
