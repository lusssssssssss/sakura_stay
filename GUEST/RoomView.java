import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class RoomView extends JFrame{
    public JLabel room_number, room_feature, room_type, room_capacity, room_size, bed_type, room_price;
    public JComboBox roomTypeList;
    public CustomButton back_button;
    String email, photopath;
    Model model;
    Connection con;
    PanelWithBackground photo;
    public RoomView(Model model, Connection con, String email, String photopath) throws SQLException {
        this.model = model;
        this.con = con;
        this.email = email;
        this.photopath = photopath;
        this.setTitle("Service Scroll");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(255,244,241));

        ImageIcon bgImage = new ImageIcon("roomTitle.jpg");
        JLabel title = new JLabel(new ImageIcon(bgImage.getImage().getScaledInstance(1536, 250, Image.SCALE_SMOOTH)));
        title.setBounds(0, 0, 1536, 250);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255,244,241));
        mainPanel.setLayout(null);
        mainPanel.setBounds(20, 270, 1485, 500);

        JPanel filter_panel = new JPanel(new GridLayout(1,7));
        filter_panel.setBackground(new Color(255,244,241));
        filter_panel.setBounds(0,0,1485,40);
        mainPanel.add(filter_panel);

        JLabel roomTypeLabel = new JLabel("Room Type: ");
        roomTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        roomTypeLabel.setForeground(new Color(204,51,0));
        filter_panel.add(roomTypeLabel);

        String[] roomTypes = {"Kapsul","Standard","Deluxe","VIP"};
        roomTypeList = new JComboBox(roomTypes);
        roomTypeList.setFont(new Font("Tahoma", Font.BOLD, 20));
        roomTypeList.setFocusable(false);
        roomTypeList.setMaximumRowCount(2);
        roomTypeList.setForeground(new Color(204,51,0));
        roomTypeList.setBackground(new Color(255,185,182));
        filter_panel.add(roomTypeList);
        filter_panel.add(new JLabel());
        filter_panel.add(new JLabel());
        filter_panel.add(new JLabel());
        filter_panel.add(new JLabel());
        filter_panel.add(new JLabel());

        JPanel data_panel = new JPanel();
        data_panel.setLayout(new BoxLayout(data_panel, BoxLayout.Y_AXIS));
        data_panel.setAlignmentY(TOP_ALIGNMENT);
        data_panel.setBackground(new Color(255,118,118));
        data_panel.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BorderLayout());
        descriptionPanel.setBackground(new Color(255,244,241));
        descriptionPanel.add(data_panel, BorderLayout.NORTH);

        JPanel photoPanel = new JPanel(null);
        photoPanel.setOpaque(false);
        photoPanel.setBounds(0, 70, 500, 370);
        photoPanel.setBackground(new Color(255,244,241));
        photoPanel.setBorder(BorderFactory.createLineBorder(new Color(255,118,118),25));

        photo = new PanelWithBackground("");
        photo.setOpaque(false);
        photo.setBounds(0, 0, 500, 370);
        photoPanel.add(photo);

        mainPanel.add(photoPanel);

        JScrollPane pane = new JScrollPane(descriptionPanel);
        pane.setBounds(550,70,935,370);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setBorder(null);

        photo.setOpaque(true);
        photoPanel.setOpaque(true);
        photoPanel.removeAll();
        photo = new PanelWithBackground("kapsul.jpg");
        photo.setBorder(BorderFactory.createLineBorder(new Color(255,118,118),25));
        mainPanel.add(photo);
        loadrooms(room_number, room_feature, room_type, room_capacity, room_size, bed_type, room_price, data_panel, pane, roomTypeList.getSelectedItem().toString());
        photo.setBounds(0, 0, 500, 370);
        photoPanel.add(photo);
        photoPanel.revalidate();
        photoPanel.repaint();

        roomTypeList.addActionListener(e->{
            try {
                photo.setOpaque(true);
                photoPanel.setOpaque(true);
                photoPanel.removeAll();
                String selected_room_type = roomTypeList.getSelectedItem().toString();
                if(selected_room_type.equals("Kapsul")){
                    photo = new PanelWithBackground("kapsul.jpg");
                    photo.setBorder(BorderFactory.createLineBorder(new Color(255,118,118),25));
                    mainPanel.add(photo);
                }
                else if(selected_room_type.equals("Standard")){
                    photo = new PanelWithBackground("standard.jpg");
                    photo.setBorder(BorderFactory.createLineBorder(new Color(255,118,118),25));
                    mainPanel.add(photo);
                }
                else if(selected_room_type.equals("VIP")){
                    photo = new PanelWithBackground("vip.jpg");
                    photo.setBorder(BorderFactory.createLineBorder(new Color(255,118,118),25));
                    mainPanel.add(photo);
                }
                else if(selected_room_type.equals("Deluxe")){
                    photo = new PanelWithBackground("deluxe.jpg");
                    photo.setBorder(BorderFactory.createLineBorder(new Color(255,118,118),25));
                    mainPanel.add(photo);
                }
                photo.setBounds(0, 0, 500, 370);
                photoPanel.add(photo);
                photoPanel.revalidate();
                photoPanel.repaint();

                loadrooms(room_number, room_feature, room_type, room_capacity, room_size, bed_type, room_price, data_panel, pane, selected_room_type);
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        mainPanel.add(pane);

        back_button = new CustomButton("BACK",30);
        back_button.setFont(new Font("Times New Roman", Font.BOLD, 24));
        back_button.setBackground(new Color(255,118,118));
        back_button.setForeground(Color.WHITE);
        back_button.setBounds(0,460,200,40);
        back_button.addActionListener(e->{
            RoomView.this.dispose();
            GuestHomeView home = new GuestHomeView(model, con, email, photopath);
            home.setVisible(true);
        });
        mainPanel.add(back_button);

        this.add(title);
        this.add(mainPanel);
    }

    private void loadrooms(JLabel room_number, JLabel room_feature, JLabel room_type,
                           JLabel room_capacity, JLabel room_size,
                           JLabel bed_type, JLabel room_price, JPanel data_panel, JScrollPane pane, String selectedtype) throws SQLException {
        ArrayList<String> rooms = model.roomdisplay(con, selectedtype);

        data_panel.removeAll();

        data_panel.setLayout(new BoxLayout(data_panel, BoxLayout.Y_AXIS));

        for (int i = 0; i < rooms.size(); i += 8) {
            String roomnumber = rooms.get(i);
            String bedname = rooms.get(i + 1);
            String roomtype = rooms.get(i + 2);
            String roomsize = rooms.get(i + 3);
            String smoking = rooms.get(i + 4);
            String quiet = rooms.get(i + 5);
            String capacity = rooms.get(i + 6);
            String price = rooms.get(i + 7);

            JPanel roomPanel = new JPanel(new GridLayout(0, 1));
            roomPanel.setBackground(new Color(255, 118, 118));
            roomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            JLabel lblNumber = new JLabel("Room Number: " + roomnumber);
            JLabel lblBed = new JLabel("Bed Type: " + bedname);
            JLabel lblType = new JLabel("Room Type: " + roomtype);
            JLabel lblSize = new JLabel("Room Size: " + roomsize + " m²");
            JLabel lblCapacity = new JLabel("Room Capacity: " + capacity);

            String features = "-";
            if (smoking.equals("1")) features = "Smoking";
            if (quiet.equals("1")) features = "Quiet";
            JLabel lblFeature = new JLabel("Room Feature: " + features);

            JLabel lblPrice = new JLabel("Room Price: Rp" + price + "/hari");
            lblPrice.setFont(new Font("Tahoma", Font.BOLD, 28));

            JLabel[] labels = {lblNumber, lblBed, lblType, lblSize, lblCapacity, lblFeature, lblPrice};
            for (JLabel label : labels) {
                label.setFont(new Font("Tahoma", Font.PLAIN, 25));
                label.setForeground(Color.WHITE);
                roomPanel.add(label);
            }
            data_panel.add(roomPanel);
            data_panel.add(Box.createVerticalStrut(30));
        }
        data_panel.revalidate();
        data_panel.repaint();
    }

}