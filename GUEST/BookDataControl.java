import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookDataControl implements ActionListener {
    private  BookDataView view;
    public BookDataControl(BookDataView view) {
        this.view = view;
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.back_button){
            view.dispose();
            BookServiceView bookServiceView = new BookServiceView(view.model, view.con, view.email, view.photopath, view.data);
            bookServiceView.setVisible(true);
        }
        else if(e.getSource() == view.continue_button){
            String nik = view.nik_field.getText();
            String name = view.name_field.getText();
            String email = view.email_field.getText();
            String phone = view.phone_field.getText();
            if(nik.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty()){
                JOptionPane.showMessageDialog(view, "Please fill all the required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!email.contains("@gmail.com")){
                JOptionPane.showMessageDialog(view, "Please enter a valid email address!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(phone.length() <= 9 || phone.length() >= 14){
                JOptionPane.showMessageDialog(view, "Please enter a valid phone number! (10-13 numbers)", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            char[]numbers = phone.toCharArray();
            for(int i = 0; i < numbers.length; i++){
                if(!Character.isDigit(numbers[i])){
                    JOptionPane.showMessageDialog(view, "Please enter a valid phone number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if(nik.length() != 16){
                JOptionPane.showMessageDialog(view, "Please enter a valid NIK! (16 characters)", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            char[] niks = nik.toCharArray();
            for(int i = 0; i < niks.length; i++){
                if(!Character.isDigit(niks[i])){
                    JOptionPane.showMessageDialog(view, "Please enter a valid phone number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            String[]guestdata = {nik, name, email, phone};
            view.dispose();
            try {
                PAYMENTKAMAR paymentkamar = new PAYMENTKAMAR(view.model, view.con, view.email, view.photopath, view.data, view.extras, guestdata, view.totalprice);
                paymentkamar.setVisible(true);
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
