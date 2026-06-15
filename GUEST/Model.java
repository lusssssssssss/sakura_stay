import java.sql.*;
import java.util.ArrayList;

public class Model {
    public Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/sakura_stay";
            String user = "root";
            String password = "root";

            conn = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    public ArrayList<String> EmailPassMember (Connection con) throws SQLException {
        ArrayList<String> emailpass = new ArrayList<>();
        String qry = "SELECT m.Membership_Email, m.Membership_Password, m.Membership_Photo FROM Membership m;";
        Statement ps = con.createStatement();
        ResultSet rs = ps.executeQuery(qry);
        while (rs.next()) {
            emailpass.add(rs.getString("Membership_Email"));
            emailpass.add(rs.getString("Membership_Password"));
            emailpass.add(rs.getString("Membership_Photo"));
        }
        return emailpass;
    }
    public void InsertMember (Connection con, String name, String email,
                              String phone, String address, String birthday, String pass,
                              String type, int price,String photopath) throws SQLException {
        String qry = "INSERT INTO Membership (Membership_Email, Membership_Guest_Name, Membership_Address, " +
                "Membership_Guest_Phone, Membership_Birth, Membership_Password, Membership_Type, " +
                "Membership_Price, Membership_Photo, Membership_Delete)\n" +
                "    VALUES(?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement ps = con.prepareStatement(qry);
        switch (type) {
            case "Bronze" -> {
                type = "B";
            }
            case "Silver" -> {
                type = "S";
            }
            case "Gold" -> {
                type = "G";
            }
        }
        ps.setString(1, email);
        ps.setString(2, name);
        ps.setString(3, address);
        ps.setString(4, phone);
        ps.setString(5, birthday);
        ps.setString(6, pass);
        ps.setString(7, type);
        ps.setString(8, String.valueOf(price));
        ps.setString(9, photopath);
        ps.setString(10, "0");
        ps.executeUpdate();
    }

    public boolean isout (Connection con, String servicesid)throws SQLException {
        boolean isout = false;
        int jumlah = 0;
        String qry ="SELECT s.Service_Jumlah\n" +
                "    FROM Service s\n" +
                "    WHERE s.Service_ID = '"+servicesid+"';";
        PreparedStatement ps = con.prepareStatement(qry);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            jumlah = rs.getInt("Service_Jumlah");
        }
        if(jumlah == 0){
            isout = true;
        }
        return isout;
    }
    public boolean isdisabled(Connection con, String servicesid)throws SQLException {
        boolean isdisabled = false;
        String del = "0";
        String qry = "SELECT s.Service_Delete\n" +
                "    FROM Service s\n" +
                "    WHERE s.Service_ID = '"+servicesid+"';";
        PreparedStatement ps = con.prepareStatement(qry);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            del = rs.getString("Service_Delete");
        }
        if(del.equals("1")){
            isdisabled = true;
        }
        return isdisabled;
    }

    public ArrayList<String> roomdisplay (Connection con, String roomtype) throws SQLException {
        ArrayList<String> roomdisplay = new ArrayList<>();
        String qry = "SELECT r.Room_number, b.Bed_Name , r.Room_Type, r.Room_Size,\n" +
                "    r.Room_Smoking, r.Room_QuietRoom, r.Room_Capacity, (t.RoomType_Price+b.Bed_Price) AS Price\n" +
                "    FROM Room r\n" +
                "    LEFT JOIN Bed b ON b.Bed_Type = r.Bed_Type\n" +
                "    LEFT JOIN RoomType t On r.RoomType_ID = t.RoomType_ID\n" +
                "    WHERE r.Room_Type = '"+roomtype+"';";
        PreparedStatement ps = con.prepareStatement(qry);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            roomdisplay.add(rs.getString("Room_Number"));
            roomdisplay.add(rs.getString("Bed_Name"));
            roomdisplay.add(rs.getString("Room_Type"));
            roomdisplay.add(rs.getString("Room_Size"));
            roomdisplay.add(rs.getString("Room_Smoking"));
            roomdisplay.add(rs.getString("Room_QuietRoom"));
            roomdisplay.add(rs.getString("Room_Capacity"));
            roomdisplay.add(rs.getString("Price"));
        }
        return roomdisplay;
    }

    public ArrayList<String> availableroom (Connection con, String roomtype, String checkin, String checkout) throws SQLException {
        ArrayList<String> availableroom = new ArrayList<>();
        String qry = "SELECT r.Room_number, r.Room_Type, r.Room_Size, b.Bed_Name, r.Room_Smoking, r.Room_QuietRoom, r.Room_Capacity, (t.RoomType_Price+b.Bed_Price) AS Price\n" +
                "    FROM Room r\n" +
                "    LEFT JOIN Invoice i ON r.Room_number = i.Room_Number AND i.Invoice_CheckinDate < ? AND i.Invoice_CheckoutDate > ?\n" +
                "    LEFT JOIN Bed b ON b.Bed_Type = r.Bed_Type\n" +
                "    LEFT JOIN RoomType t On r.RoomType_ID = t.RoomType_ID\n" +
                "    WHERE i.Room_Number IS NULL AND r.Room_status != 'D' AND r.Room_Type = ?\n" +
                "    ORDER BY 1;";
        PreparedStatement ps = con.prepareStatement(qry);
        ps.setString(1, checkout);
        ps.setString(2, checkin);
        ps.setString(3, roomtype);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            availableroom.add(rs.getString("Room_Number"));
            availableroom.add(rs.getString("Room_Type"));
            availableroom.add(rs.getString("Room_Size"));
            availableroom.add(rs.getString("Bed_Name"));
            availableroom.add(rs.getString("Room_Smoking"));
            availableroom.add(rs.getString("Room_QuietRoom"));
            availableroom.add(rs.getString("Room_Capacity"));
            availableroom.add(rs.getString("Price"));
        }
        return availableroom;
    }
    public ArrayList<String> getVouchers (Connection con, String email) throws SQLException {
        ArrayList<String> vouchers = new ArrayList<>();
        String sql = "SELECT t.Voucher_ID\n" +
                "    FROM Tracking_Voucher t\n" +
                "    JOIN Membership m ON t.Membership_Email = m.Membership_Email\n" +
                "    WHERE t.Membership_Email = '"+email+"' AND t.Tracking_Voucher_Delete != '1';\n";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            vouchers.add(rs.getString("Voucher_ID"));
        }
        return vouchers;
    }
    public ArrayList<String> getdatauser (Connection con, String email) throws SQLException {
        ArrayList<String> datauser = new ArrayList<>();
        String qry = "SELECT g.Guest_NIK, g.Guest_Email, g.Guest_Name, g.Guest_Phone\n" +
                "    FROM Membership m\n" +
                "    JOIN Guest g ON g.Guest_Email = m.Membership_Email\n" +
                "    WHERE g.Guest_Membership = '1' AND g.Guest_Email = '"+email+"';";
        PreparedStatement ps = con.prepareStatement(qry);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            datauser.add(rs.getString("Guest_NIK"));
            datauser.add(rs.getString("Guest_Email"));
            datauser.add(rs.getString("Guest_Name"));
            datauser.add(rs.getString("Guest_Phone"));
        }
        return datauser;
    }
    public String getmembertype (Connection con, String email) throws SQLException {
        String membertype = "";
        String qry = "SELECT m.Membership_Type\n" +
                "    FROM Tracking_Voucher t\n" +
                "    JOIN Membership m ON t.Membership_Email = m.Membership_Email\n" +
                "    WHERE t.Membership_Email = '"+email+"';";
        PreparedStatement ps = con.prepareStatement(qry);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            membertype = rs.getString("Membership_Type");
        }
        return membertype;
    }
    public ArrayList<String> getguests (Connection con) throws SQLException {
        ArrayList<String> guests = new ArrayList<>();
        String qry = "SELECT Guest.Guest_Email\n" +
                "    FROM Guest;";
        PreparedStatement ps = con.prepareStatement(qry);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            guests.add(rs.getString("Guest_Email"));
        }
        return guests;
    }
    public void insertguest (Connection con, String nik, String guestID, String email, String name, String phone,
                             String membership) throws SQLException {
        String qry = "INSERT INTO Guest(Guest_NIK, Guest_ID, Guest_Email, Guest_Name, Guest_Phone, Guest_Membership, Admin_Email, Guest_Delete)\n" +
                "VALUES (?,?,?,?,?,?,'admin@gmail.com','0');";
        PreparedStatement ps = con.prepareStatement(qry);
        ps.setString(1, nik);
        ps.setString(2, guestID);
        ps.setString(3, email);
        ps.setString(4, name);
        ps.setString(5, phone);
        ps.setString(6, membership);
        ps.executeUpdate();
    }
    public ArrayList<String> guestids (Connection con) throws SQLException {
        ArrayList<String> guests = new ArrayList<>();
        String qry = "SELECT Guest_ID\n" +
                "    FROM Guest;";
        PreparedStatement ps = con.prepareStatement(qry);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            guests.add(rs.getString("Guest_ID"));
        }
        return guests;
    }
    public ArrayList<String> invoiceid (Connection con) throws SQLException {
        ArrayList<String> invoiceids = new ArrayList<>();
        String qry = "SELECT i.Invoice_ID FROM Invoice i\n" +
                "    ORDER BY 1;";
        PreparedStatement ps = con.prepareStatement(qry);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            invoiceids.add(rs.getString("Invoice_ID"));
        }
        return invoiceids;
    }
    public void insertinvoice (Connection con, String Invoiceid, String nik, String guestid, String email, String roomnumber, String paymentmethodid,String checkin,
                               String checkout, String jumlahmalam, String usefas, String total) throws SQLException {
        String qry = "INSERT INTO Invoice (Invoice_ID, Guest_NIK, Guest_ID, Guest_Email, Room_Number, PaymentMethod_ID, Invoice_CheckinDate, Invoice_CheckoutDate, " +
                "Invoice_JumlahMalam, Invoice_PaymentDate, Invoice_UseFas, Invoice_Total, Invoice_PaymentStatus, Invoice_Delete)\n" +
                "VALUES (?,?,?,?,?,?,?,?,?,'2025-05-28',?,?,'1','0');";
        PreparedStatement ps = con.prepareStatement(qry);
        ps.setString(1, Invoiceid);
        ps.setString(2, nik);
        ps.setString(3, guestid);
        ps.setString(4, email);
        ps.setString(5, roomnumber);
        ps.setString(6, paymentmethodid);
        ps.setString(7, checkin);
        ps.setString(8, checkout);
        ps.setString(9, jumlahmalam);
        ps.setString(10, usefas);
        ps.setString(11, total);
        ps.executeUpdate();
    }
    public String getid (Connection con, String email) throws SQLException {
        String id = "";
        String qry = "SELECT Guest_ID\n" +
                "    FROM Guest\n" +
                "    WHERE Guest_Email = '"+email+"';";
        PreparedStatement ps = con.prepareStatement(qry);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            id = rs.getString("Guest_ID");

        }
        return id;
    }
    public void updateservice (Connection con, String serviceid, int jumlah) throws SQLException {
        String qry = "UPDATE Service s SET s.Service_Jumlah = s.Service_Jumlah-? WHERE Service_ID = ?;";
        PreparedStatement ps = con.prepareStatement(qry);
        ps.setInt(1, jumlah);
        ps.setString(2, serviceid);
        ps.executeUpdate();
    }
    public int getservisjumlahphoto (Connection con) throws SQLException {
        int jumlah = 0;
        String qry = "SELECT s.Service_Jumlah\n" +
                "    FROM Service s\n" +
                "    WHERE Service_ID = 'SERVE-P';";
        PreparedStatement ps = con.prepareStatement(qry);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            jumlah = rs.getInt("Service_Jumlah");
        }
        return jumlah;
    }
    public int getservisjumlahyukata (Connection con) throws SQLException {
        int jumlah = 0;
        String qry = "SELECT s.Service_Jumlah\n" +
                "    FROM Service s\n" +
                "    WHERE Service_ID = 'SERVE-Y';";
        PreparedStatement ps = con.prepareStatement(qry);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            jumlah = rs.getInt("Service_Jumlah");
        }
        return jumlah;
    }
    public void voucherdipakek (Connection con, String email, String voucherid) throws SQLException {
        String qry = "UPDATE Tracking_Voucher t \n" +
                "SET t.Tracking_Voucher_Delete = '1' \n" +
                "WHERE Membership_Email = ? AND t.Voucher_ID = ?;";
        PreparedStatement ps = con.prepareStatement(qry);
        ps.setString(1, email);
        ps.setString(2, voucherid);
        ps.executeUpdate();
    }
}