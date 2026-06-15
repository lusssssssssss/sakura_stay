import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BookRoomControl implements ActionListener {
    private BookRoomView view;
    public BookRoomControl(BookRoomView view) {
        this.view = view;
    }
    public void actionPerformed(ActionEvent e) {
        String selectedroom = "";
        String selectedrtype = "";
        String selectedrsize = "";
        String selectedbedtype = "";
        String selectedrsmoking = "";
        String selectedquiet = "";
        String selectedcap = "";
        String selectedprice = "0";
        Date datein = null;
        Date dateout = null;
        Date mindate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);

        if(e.getSource() == view.back_button){
            view.dispose();
            GuestHomeView guestHomeView = new GuestHomeView(view.model, view.con, view.email, view.photopath);
            guestHomeView.setVisible(true);
        }
        else if(e.getSource() == view.room_type_list){
            String type = view.room_type_list.getSelectedItem().toString();
            view.tanggalin = view.checkin_datePicker.getJFormattedTextField().getText();
            view.tanggalout = view.checkout_datePicker.getJFormattedTextField().getText();
            try {
                datein = formatter.parse(view.tanggalin);
                dateout = formatter.parse(view.tanggalout);
                mindate = formatter.parse("2025-05-28");
            }
            catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            if(datein.before(mindate)){
                JOptionPane.showMessageDialog(view, "Tidak boleh sebelum tanggal 28 Mei 2025");
                return;
            }
            else if(dateout.before(mindate)){
                JOptionPane.showMessageDialog(view, "Tidak boleh sebelum tanggal 28 Mei 2025");
                return;
            }
            else if(dateout.equals(datein)){
                JOptionPane.showMessageDialog(view, "Tanggal Check-in dan Check-out tidak boleh sama");
                return;
            }
            else if(datein.after(dateout)){
                JOptionPane.showMessageDialog(view,"Tanggal Check-out tidak boleh sebelum Tanggal Check-in");
                return;
            }
            try {
                view.loadlabel(view.room_number, view.room_feature, view.room_type,
                        view.room_capacity, view.room_size,
                        view.bed_type, view.room_price, view.data_panel, type, view.tanggalin, view.tanggalout);
                ImageIcon iconkamar;
                JLabel kamarlabel = new JLabel();
                kamarlabel.setOpaque(false);
                view.photo_panel.removeAll();
                if(type.equalsIgnoreCase("Kapsul")) {
                    iconkamar = new ImageIcon("kapsul.jpg");
                    Image imagekamar = iconkamar.getImage().getScaledInstance(400,270,Image.SCALE_SMOOTH);
                    ImageIcon kamar = new ImageIcon(imagekamar);
                    kamarlabel.setIcon(kamar);
                    view.photo_panel.add(kamarlabel);
                }
                if(type.equalsIgnoreCase("Standard")) {
                    iconkamar = new ImageIcon("standard.jpg");
                    Image imagekamar = iconkamar.getImage().getScaledInstance(400,270,Image.SCALE_SMOOTH);
                    ImageIcon kamar = new ImageIcon(imagekamar);
                    kamarlabel.setIcon(kamar);
                    view.photo_panel.add(kamarlabel);
                }
                if(type.equalsIgnoreCase("VIP")) {
                    iconkamar = new ImageIcon("vip.jpg");
                    Image imagekamar = iconkamar.getImage().getScaledInstance(400,270,Image.SCALE_SMOOTH);
                    ImageIcon kamar = new ImageIcon(imagekamar);
                    kamarlabel.setIcon(kamar);
                    view.photo_panel.add(kamarlabel);
                }
                if(type.equalsIgnoreCase("Deluxe")) {
                    iconkamar = new ImageIcon("deluxe.jpg");
                    Image imagekamar = iconkamar.getImage().getScaledInstance(400,270,Image.SCALE_SMOOTH);
                    ImageIcon kamar = new ImageIcon(imagekamar);
                    kamarlabel.setIcon(kamar);
                    view.photo_panel.add(kamarlabel);
                }
                view.photo_panel.setBorder(new LineBorder(new Color(255, 232, 250), 20));
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.BOTH;
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.weightx = 0.55;
                gbc.weighty = 1.0;
                view.room_panel.add(view.photo_panel, gbc);
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource() == view.next_button){
            if(view.room_number.getText().toString().isEmpty()){
                JOptionPane.showMessageDialog(view, "Select Date and Room Type First!");
                return;
            }
            String getroomnow = view.room_number.getText().toString();
            String roomnow = getroomnow.substring(getroomnow.indexOf(": ")+2);
            String lastroom = view.readyroom.get(view.readyroom.size()-8);
            if(roomnow.equalsIgnoreCase(lastroom)){
                return;
            }
            int index = view.readyroom.indexOf(roomnow);
            int nextindex = index+8;
            selectedroom = view.readyroom.get(nextindex);
            selectedrtype = view.readyroom.get(nextindex+1);
            selectedrsize = view.readyroom.get(nextindex+2);
            selectedbedtype = view.readyroom.get(nextindex+3);
            selectedrsmoking = view.readyroom.get(nextindex+4);
            selectedquiet = view.readyroom.get(nextindex+5);
            selectedcap = view.readyroom.get(nextindex+6);
            selectedprice = view.readyroom.get(nextindex+7);

            view.room_number.setText("Room Number: "+ selectedroom);
            view.room_type.setText("Room Type: "+ selectedrtype);
            view.room_size.setText("Room Size: "+ selectedrsize);
            view.bed_type.setText("Bed Type: "+ selectedbedtype);
            view.room_feature.setText("Room Feature: -");
            if(selectedrsmoking.equals("1")){
                view.room_feature.setText("Room Feature: Smoking");
            }
            else if(selectedquiet.equals("1")){
                view.room_feature.setText("Room Feature: Quiet Room");
            }
            view.room_capacity.setText("Room Capacity: "+ selectedcap);
            view.room_price.setText("Room Price: "+ selectedprice);
        }
        else if(e.getSource() == view.previous_button){
            if(view.room_number.getText().toString().isEmpty()){
                JOptionPane.showMessageDialog(view, "Select Date and Room Type First!");
                return;
            }
            String getroomnow = view.room_number.getText().toString();
            String roomnow = getroomnow.substring(getroomnow.indexOf(": ")+2);
            String first = view.readyroom.getFirst();
            if(roomnow.equalsIgnoreCase(first)){
                return;
            }
            int index = view.readyroom.indexOf(roomnow);
            int previndex = index-8;
            selectedroom = view.readyroom.get(previndex);
            selectedrtype = view.readyroom.get(previndex+1);
            selectedrsize = view.readyroom.get(previndex+2);
            selectedbedtype = view.readyroom.get(previndex+3);
            selectedrsmoking = view.readyroom.get(previndex+4);
            selectedquiet = view.readyroom.get(previndex+5);
            selectedcap = view.readyroom.get(previndex+6);
            selectedprice = view.readyroom.get(previndex+7);

            view.room_number.setText("Room Number: "+ selectedroom);
            view.room_type.setText("Room Type: "+ selectedrtype);
            view.room_size.setText("Room Size: "+ selectedrsize);
            view.bed_type.setText("Bed Type: "+ selectedbedtype);
            view.room_feature.setText("Room Feature: -");
            if(selectedrsmoking.equals("1")){
                view.room_feature.setText("Room Feature: Smoking");
            }
            else if(selectedquiet.equals("1")){
                view.room_feature.setText("Room Feature: Quiet Room");
            }
            view.room_capacity.setText("Room Capacity: "+ selectedcap);
            view.room_price.setText("Room Price: "+ selectedprice);
        }

        else if(e.getSource() == view.continue_button){
            if(view.room_number.getText().toString().isEmpty()){
                JOptionPane.showMessageDialog(view, "No Date and Room Selected!");
                return;
            }
            selectedroom = view.room_number.getText().toString().substring(view.room_number.getText().indexOf(": ")+2);
            selectedrtype = view.room_type.getText().toString().substring(view.room_type.getText().indexOf(": ")+2);
            selectedrsize = view.room_size.getText().toString().substring(view.room_size.getText().indexOf(": ")+2);
            selectedbedtype = view.bed_type.getText().toString().substring(view.bed_type.getText().indexOf(": ")+2);
            selectedrsmoking = view.room_feature.getText().toString().substring(view.room_feature.getText().indexOf(": ")+2);
            selectedquiet = view.room_feature.getText().toString().substring(view.room_feature.getText().indexOf(": ")+2);
            selectedcap = view.room_capacity.getText().toString().substring(view.room_capacity.getText().indexOf(": ")+2);
            selectedprice = view.room_price.getText().toString().substring(view.room_price.getText().indexOf(": ")+2);
            String longdate = view.tanggalin.concat(" - ").concat(view.tanggalout);
            try {
                datein = formatter.parse(view.tanggalin);
                dateout = formatter.parse(view.tanggalout);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

            long diffMillis = dateout.getTime() - datein.getTime();
            long daysLong = TimeUnit.MILLISECONDS.toDays(diffMillis);
            int selisihHari = (int) daysLong;
            String[]data = {selectedroom,selectedrtype,selectedrsize, selectedbedtype, selectedrsmoking, selectedquiet,selectedcap, selectedprice,longdate,String.valueOf(selisihHari)};
            view.dispose();
            BookServiceView bookServiceView = new BookServiceView(view.model, view.con, view.email, view.photopath, data);
            bookServiceView.setVisible(true);
        }
    }
}
