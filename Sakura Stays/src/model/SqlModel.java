package model;

import component.CustomToggleButton;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class SqlModel {
    private Connection conn;

    public SqlModel() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakura_stay", "root", "louisanthony");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean confirmation(String email, String password) {
        String sql = "select * from admin";
        try(Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(sql)) {
            while(resultSet.next()){
                String Admin_Email = resultSet.getString("Admin_Email");
                String Admin_Password = resultSet.getString("Admin_Password");
                if(email.equals(Admin_Email) && password.equals(Admin_Password)){
                    return true;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void updateMembershipTable(MembershipView view){
        String sql = "select g.Guest_ID, m.Membership_Guest_Name, m.Membership_Address, m.Membership_Guest_Phone, m.Membership_Birth, m.Membership_Email, m.Membership_Password, m.Membership_Type\n" +
                "from membership m\n" +
                "join guest g on m.Membership_Email = g.Guest_Email\n" +
                "order by 1";
        try(Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(sql)) {
            while(resultSet.next()){
                String Guest_ID = resultSet.getString("Guest_ID");
                String Membership_Guest_Name = resultSet.getString("Membership_Guest_Name");
                String Membership_Address = resultSet.getString("Membership_Address");
                String Membership_Guest_Phone = resultSet.getString("Membership_Guest_Phone");
                String Membership_Birth = resultSet.getString("Membership_Birth");
                String Membership_Email = resultSet.getString("Membership_Email");
                String Membership_Password = resultSet.getString("Membership_Password");
                String Membership_Type = resultSet.getString("Membership_Type");
                view.tableModel.addRow(new Object[]{Guest_ID,Membership_Guest_Name,Membership_Address,Membership_Guest_Phone,Membership_Birth,Membership_Email,Membership_Password,Membership_Type});
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadIncomeTable(IncomeView view){
        int counter = 0;
        view.tableModel.setRowCount(0);
        String sql = "select r.Room_Type, i.Room_Number, i.Guest_ID, i.Invoice_Total\n" +
                "from invoice i\n" +
                "join room r on r.Room_number = i.Room_Number";
        try(Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(sql)) {
            while(resultSet.next()){
                String Room_Type = resultSet.getString("Room_Type");
                String Room_Number = resultSet.getString("Room_Number");
                String Guest_ID = resultSet.getString("Guest_ID");
                String Invoice_Total = resultSet.getString("Invoice_Total");
                counter += Integer.parseInt(Invoice_Total);
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setGroupingSeparator('.');
                symbols.setDecimalSeparator(',');
                DecimalFormat formatter = new DecimalFormat("Rp#,###", symbols);
                String formatted_Invoice_Total = formatter.format(Integer.parseInt(Invoice_Total));
                view.tableModel.addRow(new Object[]{Room_Type,Room_Number,Guest_ID,formatted_Invoice_Total});
                view.totalLabel.setText("Total Penghasilan: " + formatter.format(counter));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateIncomeTable(IncomeView view, String selectedRoomType){
        int counter = 0;
        view.tableModel.setRowCount(0);
        String sql = "select r.Room_Type, i.Room_Number, i.Guest_ID, i.Invoice_Total\n" +
                "from invoice i\n" +
                "join room r on r.Room_number = i.Room_Number\n" +
                "where r.Room_Type = ?";
        try(PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, selectedRoomType);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String Room_Type = resultSet.getString("Room_Type");
                String Room_Number = resultSet.getString("Room_Number");
                String Guest_ID = resultSet.getString("Guest_ID");
                String Invoice_Total = resultSet.getString("Invoice_Total");
                counter += Integer.parseInt(Invoice_Total);
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setGroupingSeparator('.');
                symbols.setDecimalSeparator(',');
                DecimalFormat formatter = new DecimalFormat("Rp#,###", symbols);
                String formatted_Invoice_Total = formatter.format(Integer.parseInt(Invoice_Total));
                view.tableModel.addRow(new Object[]{Room_Type,Room_Number,Guest_ID,formatted_Invoice_Total});
                view.totalLabel.setText("Total Penghasilan: " + formatter.format(counter));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateRoomColor(String room_number, RoomsView view, int i, int j){
        String sql = "select r.Room_number, r.Room_status\n" +
                "from room r";
        try(Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(sql)) {
            while(resultSet.next()){
                String Room_Number = resultSet.getString("Room_Number");
                String Room_Status = resultSet.getString("Room_Status");
                if(Room_Number.equals(room_number)){
                    if(Room_Status.equals("D")){
                        view.roomButtons[4-i][j-1].setBackground(new Color(230,230,230));
                    }
                    else if(Room_Status.equals("O")){
                        view.roomButtons[4-i][j-1].setBackground(new Color(241,34,0));
                    }
                    else{
                        view.roomButtons[4-i][j-1].setBackground(new Color(102,153,51));
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRoomDetailsPanel(int room_number, RoomsView view){
        view.roomDetailPanel.setVisible(true);
        view.informationPanel.setVisible(true);
        view.buttonPanel.setVisible(true);
        String sql = "select r.Room_number, r.Admin_Email, ifnull(r.Guest_ID,'None') as Guest_ID, r.Room_Type, b.Bed_Name, r.Room_Size, r.Room_Capacity, r.Room_Smoking, r.Room_QuietRoom, if(r.Room_status = 'D','Out of Service', if(r.Room_status = 'O','Occupied','Vacant')) as Room_status, rt.RoomType_Price, b.Bed_Price\n" +
                "from room r\n" +
                "join bed b on b.Bed_Type = r.Bed_Type\n" +
                "join roomtype rt on r.RoomType_ID = rt.RoomType_ID\n" +
                "where r.Room_number = ?\n" +
                "order by 1";
        try(PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, room_number);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String Room_Number = resultSet.getString("Room_Number");
                String Admin_Email = resultSet.getString("Admin_Email");
                String Guest_ID = resultSet.getString("Guest_ID");
                String Room_Type = resultSet.getString("Room_Type");
                String Bed_Name = resultSet.getString("Bed_Name");
                String Room_Size = resultSet.getString("Room_Size");
                String Room_Capacity = resultSet.getString("Room_Capacity");
                String Room_Smoking = resultSet.getString("Room_Smoking");
                String Room_QuietRoom = resultSet.getString("Room_QuietRoom");
                String Room_Feature = "";
                if(Room_Smoking.equals("1") && Room_QuietRoom.equals("0")){
                    Room_Feature = "Smoking";
                }
                else if(Room_Smoking.equals("0") && Room_QuietRoom.equals("1")){
                    Room_Feature = "Quiet";
                }
                else if(Room_Smoking.equals("0") && Room_QuietRoom.equals("0")){
                    Room_Feature = "-";
                }
                String Room_Status = resultSet.getString("Room_Status");
                int RoomType_Price = resultSet.getInt("RoomType_Price");
                int Bed_Price = resultSet.getInt("Bed_Price");
                int total = RoomType_Price + Bed_Price;
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setGroupingSeparator('.');
                symbols.setDecimalSeparator(',');
                DecimalFormat formatter = new DecimalFormat("Rp#,###", symbols);
                String formatted_Total = formatter.format(total);
                view.room_number.setText("Room Number: " + Room_Number);
                view.email_admin.setText("Admin Email: " + Admin_Email);
                view.guest_id.setText("Guest ID: " + Guest_ID);
                view.room_type.setText("Room Type: " + Room_Type);
                view.bed_type.setText("Bed Type: " + Bed_Name);
                view.room_feature.setText("Room Feature: " + Room_Feature);
                view.room_capacity.setText("Room Capacity: " + Room_Capacity);
                view.room_size.setText("Room Size: " + Room_Size + " m2");
                view.room_status.setText("Room Status: " + Room_Status);
                view.room_price.setText("Room Price: " + formatted_Total + "/day");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRoomStatus(Color color, String roomStatus, String guest_id, RoomsView view){
        for (int i = 0; i < view.roomButtons.length; i++) {
            for (int j = 0; j < view.roomButtons[i].length; j++) {
                CustomToggleButton button = view.roomButtons[i][j];
                if (button != null && button.isSelected()) {
                    int roomNumber = Integer.parseInt(button.getText());
                    button.setBackground(color);
                    String sql;
                    if(roomStatus.equals("D")){
                        view.guest_id.setText("Guest ID: None");
                        view.room_status.setText("Room Status: Out of Service");
                        sql = "update room set Room_status = ?, Guest_ID = null where Room_number = ?";
                        try(PreparedStatement statement = conn.prepareStatement(sql)) {
                            statement.setString(1, roomStatus);
                            statement.setInt(2, roomNumber);
                            statement.executeUpdate();
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        return;
                    }
                    else if(roomStatus.equals("V")){
                        view.guest_id.setText("Guest ID: None");
                        view.room_status.setText("Room Status: Vacant");
                        sql = "update room set Room_status = ?, Guest_ID = null where Room_number = ?";
                        try(PreparedStatement statement = conn.prepareStatement(sql)) {
                            statement.setString(1, roomStatus);
                            statement.setInt(2, roomNumber);
                            statement.executeUpdate();
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        return;
                    }
                    else{
                        view.guest_id.setText("Guest ID: " + guest_id);
                        view.room_status.setText("Room Status: Occupied");
                        sql = "update room set Room_status = ?, Guest_ID = ? where Room_number = ?";
                        try(PreparedStatement statement = conn.prepareStatement(sql)) {
                            statement.setString(1, roomStatus);
                            statement.setString(2, guest_id);
                            statement.setInt(3, roomNumber);
                            statement.executeUpdate();
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        return;
                    }
                }
            }
        }
    }

    public void updateOptionPaneCB(JComboBox comboBox){
        String sql = "select Guest_ID from guest";
        try(Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(sql)) {
            while(resultSet.next()){
                String guest_id = resultSet.getString("Guest_ID");
                comboBox.addItem(guest_id);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSendVoucherData(SendVoucherView view){
        String sql = "select Voucher_ID, Voucher_Delete from voucher";
        String sql2 = "select Membership_Email from membership";
        try(Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(sql)) {
            while(resultSet.next()){
                String voucher_ID = resultSet.getString("Voucher_ID");
                String voucher_delete = resultSet.getString("Voucher_Delete");
                if(voucher_delete.equals("0")){
                    view.voucherList.addItem(voucher_ID);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try(Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(sql2)) {
            while(resultSet.next()){
                String Membership_Email = resultSet.getString("Membership_Email");
                view.emailList.addItem(Membership_Email);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendVoucher(String membership_email, String voucher_id, SendVoucherView view){
        String sql = "update voucher set Voucher_Delete = '1' where Voucher_ID = ?";
        String sql2 = "insert into tracking_voucher (Membership_Email, Voucher_ID, Membership_Type) value(?,?,?)";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, voucher_id);
            statement.executeUpdate();
            view.voucherList.removeItem(voucher_id);
            view.emailList.setSelectedItem("");
            view.voucherList.setSelectedItem("");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try(PreparedStatement statement = conn.prepareStatement(sql2)){
            statement.setString(1, membership_email);
            statement.setString(2, voucher_id);
            statement.setString(3, retrieveMembershipType(membership_email));
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public String retrieveMembershipType(String membership_email){
        String sql = "select Membership_Type, Membership_Email\n" +
                "from membership";
        try(Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(sql)) {
            while(resultSet.next()){
                String Membership_Type = resultSet.getString("Membership_Type");
                String Membership_Email = resultSet.getString("Membership_Email");
                if(Membership_Email.equals(membership_email)){
                    return Membership_Type;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateTrackingVoucherTable(TrackingVoucherView view){
        view.tableModel.setRowCount(0);
        String sql = "select Membership_Email, Voucher_ID, if(Membership_Type = 'B','Bronze',if(Membership_Type = 'S','Silver','Gold')) as Membership_Type, if(Tracking_Voucher_Delete = '1','Used','Not Used') as Tracking_Voucher_Delete\n" +
                "from tracking_voucher";
        try(Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(sql)){
            while(resultSet.next()){
                String Membership_Email = resultSet.getString("Membership_Email");
                String Voucher_ID = resultSet.getString("Voucher_ID");
                String Membership_Type = resultSet.getString("Membership_Type");
                String Tracking_Voucher_Delete = resultSet.getString("Tracking_Voucher_Delete");
                view.tableModel.addRow(new Object[]{Membership_Email,Voucher_ID,Membership_Type,Tracking_Voucher_Delete});
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void filterTrackingVoucher(String selectedVoucher, TrackingVoucherView view){
        view.tableModel.setRowCount(0);
        String sql = "select Membership_Email, Voucher_ID, if(Membership_Type = 'B','Bronze',if(Membership_Type = 'S','Silver','Gold')) as Membership_Type, if(Tracking_Voucher_Delete = '1','Used','Not Used') as Tracking_Voucher_Delete\n" +
                "from tracking_voucher\n" +
                "where Voucher_ID like ?";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, "%" + selectedVoucher + "%");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String Membership_Email = resultSet.getString("Membership_Email");
                String Voucher_ID = resultSet.getString("Voucher_ID");
                String Membership_Type = resultSet.getString("Membership_Type");
                String Tracking_Voucher_Delete = resultSet.getString("Tracking_Voucher_Delete");
                view.tableModel.addRow(new Object[]{Membership_Email,Voucher_ID,Membership_Type,Tracking_Voucher_Delete});
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addHistory(HistoryView view){
        String sql = "select h.*, ifnull(m.Membership_Photo,'None') as Membership_Photo\n" +
                "from history h\n" +
                "left join guest g on g.Guest_ID = h.Guest_ID\n" +
                "left join membership m on m.Membership_Guest_Name = g.Guest_Name\n" +
                "order by 1";
        try(Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(sql)){
            while(resultSet.next()){
                String Invoice_ID = resultSet.getString("Invoice_ID");
                String Admin_Email = resultSet.getString("Admin_Email");
                String Guest_ID = resultSet.getString("Guest_ID");
                String Room_Number = resultSet.getString("Room_Number");
                String Invoice_CheckinDate = resultSet.getString("Invoice_CheckinDate");
                String Invoice_CheckoutDate = resultSet.getString("Invoice_CheckoutDate");
                String Membership_Photo = resultSet.getString("Membership_Photo");

                JPanel userDataPanel = new JPanel();
                userDataPanel.setLayout(new BoxLayout(userDataPanel, BoxLayout.X_AXIS));
                userDataPanel.setBackground(new Color(255,156,154));
                userDataPanel.setBorder(BorderFactory.createEmptyBorder(0,13,0,0));
                userDataPanel.setPreferredSize(new Dimension(1465,220));
                view.dataPanel.add(userDataPanel);

                String userPhoto;
                if(!Membership_Photo.equals("None")){
                    userPhoto = "picture/"+Membership_Photo;
                }
                else{
                    userPhoto = "picture/anonymous.png";
                }
                ImageIcon photoProfile = new ImageIcon(userPhoto);
                JLabel photo = new JLabel(new ImageIcon(photoProfile.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                userDataPanel.add(photo);
                userDataPanel.add(Box.createHorizontalStrut(13));

                JPanel infoPanel = new JPanel(new GridLayout(2,3,20,-40));
                infoPanel.setBackground(new Color(255,156,154));
                userDataPanel.add(infoPanel);

                JLabel invoiceIDLabel = new JLabel("Invoice ID: " + Invoice_ID);
                invoiceIDLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
                invoiceIDLabel.setForeground(Color.WHITE);
                infoPanel.add(invoiceIDLabel);

                JLabel adminEmailLabel = new JLabel("Admin Email: " + Admin_Email);
                adminEmailLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
                adminEmailLabel.setForeground(Color.WHITE);
                infoPanel.add(adminEmailLabel);

                JLabel guestIDLabel = new JLabel("Guest ID: " + Guest_ID);
                guestIDLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
                guestIDLabel.setForeground(Color.WHITE);
                infoPanel.add(guestIDLabel);

                JLabel roomNumberLabel = new JLabel("Room Number: " + Room_Number);
                roomNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
                roomNumberLabel.setForeground(Color.WHITE);
                infoPanel.add(roomNumberLabel);

                JLabel checkinLabel = new JLabel("Check In: " + Invoice_CheckinDate);
                checkinLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
                checkinLabel.setForeground(Color.WHITE);
                infoPanel.add(checkinLabel);

                JLabel checkoutLabel = new JLabel("Check Out: " + Invoice_CheckoutDate);
                checkoutLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
                checkoutLabel.setForeground(Color.WHITE);
                infoPanel.add(checkoutLabel);
                view.dataPanel.add(Box.createVerticalStrut(13));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addServiceHistoryData(ServiceHistoryView view){
        int counter = 0;
        view.tableModel.setRowCount(0);
        String sql = "select Guest_ID, if(Service_ID = 'SERVE-C','Baby Cot',if(Service_ID = 'SERVE-B','Breakfast',if(Service_ID = 'SERVE-P','Photo Session',if(Service_ID = 'SERVE-X','Extra Bed','Yukata')))) as Service_Name, UseServ_Jumlah\n" +
                "from useserv";
        try(Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)){
            while(resultSet.next()){
                String Guest_ID = resultSet.getString("Guest_ID");
                String Service_Name = resultSet.getString("Service_Name");
                String UseServ_Jumlah = resultSet.getString("UseServ_Jumlah");
                view.tableModel.addRow(new Object[]{Guest_ID,Service_Name,UseServ_Jumlah});
                counter++;
            }
            view.totalLabel.setText("Total Penggunaan: " + counter);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateServiceHistoryData(String selected_service, ServiceHistoryView view){
        int counter = 0;
        view.tableModel.setRowCount(0);
        String sql = "select Guest_ID, if(Service_ID = 'SERVE-C','Baby Cot',if(Service_ID = 'SERVE-B','Breakfast',if(Service_ID = 'SERVE-P','Photo Session',if(Service_ID = 'SERVE-X','Extra Bed','Yukata')))) as Service_Name, UseServ_Jumlah\n" +
                "from useserv\n" +
                "where if(Service_ID = 'SERVE-C','Baby Cot',if(Service_ID = 'SERVE-B','Breakfast',if(Service_ID = 'SERVE-P','Photo Session',if(Service_ID = 'SERVE-X','Extra Bed','Yukata')))) = ?";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, selected_service);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String Guest_ID = resultSet.getString("Guest_ID");
                String Service_Name = resultSet.getString("Service_Name");
                String UseServ_Jumlah = resultSet.getString("UseServ_Jumlah");
                view.tableModel.addRow(new Object[]{Guest_ID,Service_Name,UseServ_Jumlah});
                counter++;
            }
            view.totalLabel.setText("Total Penggunaan: " + counter);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public String checkServiceStatus(String selected_service){
        String sql = "select Service_Name, Service_Delete from service";
        try(Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)){
            while(resultSet.next()){
                String Service_Name = resultSet.getString("Service_Name");
                if(selected_service.equals(Service_Name)){
                    String Service_Delete = resultSet.getString("Service_Delete");
                    return Service_Delete;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void updateServiceStatus(String selected_service, String status){
        String sql = "update service set Service_Delete = ? where Service_Name =?";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, status);
            statement.setString(2, selected_service);
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public int checkAmount(String selectedService, int returnAmount){
        String sql = "select Service_Name, Service_Jumlah from service";
        try(Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)){
            while(resultSet.next()){
                String Service_Name = resultSet.getString("Service_Name");
                if(selectedService.equals(Service_Name)){
                    int Service_Jumlah = resultSet.getInt("Service_Jumlah");
                    return returnAmount + Service_Jumlah;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public void updateServiceAmount(int total, String selectedService){
        String sql = "update service set Service_Jumlah = ? where Service_Name = ?";
        try(PreparedStatement statement = conn.prepareStatement(sql);){
            statement.setInt(1, total);
            statement.setString(2, selectedService);
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}