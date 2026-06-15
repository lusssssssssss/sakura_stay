import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class REGISCONTROL implements ActionListener {
    private MEMBERSHIPREGIS frame;
    REGISCONTROL(MEMBERSHIPREGIS frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = frame.nameField.getText();
        String email = frame.emailField.getText();
        String address = frame.addressField.getText();
        String phone = frame.phoneField.getText();
        String birth = frame.birthdayField.getText();
        String password = frame.passwordField.getText();
        String confirmPassword = frame.confirmPasswordField.getText();
        String type = frame.membershipComboBox.getSelectedItem().toString();
        int price = 0;

        if(e.getSource() == frame.registerButton){
            if(name.isEmpty()||email.isEmpty()||address.isEmpty()||phone.isEmpty()||password.isEmpty()||confirmPassword.isEmpty() || birth.isEmpty()){
                JOptionPane.showMessageDialog(frame, "Please fill all the required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!password.equals(confirmPassword)){
                JOptionPane.showMessageDialog(frame, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            switch (type) {
                case "Gold" -> {
                    price = 1000000;
                }
                case "Silver" -> {
                    price = 500000;
                }
                case "Bronze" -> {
                    price = 250000;
                }
            }
            if(!email.contains("@gmail.com")){
                JOptionPane.showMessageDialog(frame, "Please enter a valid email!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(phone.length() <= 9){
                JOptionPane.showMessageDialog(frame, "Please enter a valid phone number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            char[]numbers = phone.toCharArray();
            for(int i = 0; i < numbers.length; i++){
                if(!Character.isDigit(numbers[i])){
                    JOptionPane.showMessageDialog(frame, "Please enter a valid phone number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if(password.length() <= 6){
                JOptionPane.showMessageDialog(frame, "Password must be atleast 7 characters", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try{
                LocalDate birthDate = LocalDate.parse(birth, formatter);
                frame.birthday = birthDate;
            }
            catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid date! Use yyyy-MM-dd (e.g. 1990-02-28)", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if(password.contains(" ")){
                JOptionPane.showMessageDialog(frame, "Password must not contain spaces", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            MEMBERPAYMENT memberpayment = new MEMBERPAYMENT(frame.model, frame.con, name, email, phone, address, birth, password, type, price,frame.pictureicon.getDescription());
            memberpayment.setVisible(true);
        }
        else if(e.getSource() == frame.uploadButton){
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Choose a Photo");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Image Files (PNG, JPG, JPEG)", "png", "jpg", "jpeg"
            );
            chooser.setFileFilter(filter);
            int response = chooser.showOpenDialog(frame);
            if(response == JFileChooser.APPROVE_OPTION){
                File file = chooser.getSelectedFile();
                String picturePath = file.getAbsolutePath();
                if(picturePath.toLowerCase().endsWith(".png") || picturePath.toLowerCase().endsWith(".jpg") || picturePath.toLowerCase().endsWith(".jpeg")) {
                    ImageIcon memberpictureicon = new ImageIcon(picturePath);
                    Image memberpictureimage = memberpictureicon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                    ImageIcon memberpicture = new ImageIcon(memberpictureimage);
                    frame.uploadButton.setIcon(memberpicture);
                    frame.pictureicon.setDescription(picturePath);
                    frame.uploadButton.setEnabled(false);
                    frame.uploadButton.setDisabledIcon(frame.uploadButton.getIcon());
                    frame.removeButton.setEnabled(true);
                    frame.editButton.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a valid image file (PNG, JPG, JPEG).", "Invalid File", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if(e.getSource() == frame.removeButton){
            frame.uploadButton.setEnabled(true);
            frame.pictureicon.setDescription("upload.png");
            ImageIcon reseticon = new ImageIcon("upload.png");
            Image resetimage = reseticon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            ImageIcon reset = new ImageIcon(resetimage);
            frame.uploadButton.setIcon(reset);
            frame.removeButton.setEnabled(false);
            frame.editButton.setEnabled(false);
        }
        else if(e.getSource() == frame.editButton){
            frame.uploadButton.setEnabled(true);
            frame.uploadButton.doClick();
            frame.uploadButton.setEnabled(false);
        }
        else if(e.getSource() == frame.backbutton){
            frame.dispose();
            GuestHomeView guestHomeView = new GuestHomeView(frame.model, frame.con, "", "");
            guestHomeView.setVisible(true);
        }
    }
}
