import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class BookRoomView extends JFrame {
    public JLabel room_number, room_feature, room_type, room_capacity, room_size, room_status, bed_type, room_price;
    public CustomButton previous_button, next_button, back_button, continue_button;
    public JComboBox room_type_list;
    public JFormattedTextField checkinTextField, checkoutTextField;
    public JPanel photo_panel;
    JDatePickerImpl checkin_datePicker, checkout_datePicker;
    JPanel room_panel;
    Model model;
    Connection con;
    String email, photopath, tanggalout, tanggalin;
    JPanel data_panel;
    ArrayList<String> readyroom;
    public BookRoomView(Model model, Connection con, String email, String photopath) throws SQLException {
        this.model = model;
        this.con = con;
        this.email = email;
        this.photopath = photopath;
        this.setTitle("Book Room");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(255, 209, 229));

        ImageIcon bgImage = new ImageIcon("BookBackground.jpg");
        JLabel title_label = new JLabel(new ImageIcon(bgImage.getImage().getScaledInstance(1536, 190, Image.SCALE_SMOOTH)));
        title_label.setBounds(0, 40, 1536, 190);
        this.add(title_label);

        JPanel main_panel = new JPanel(new BorderLayout());
        main_panel.setBorder(BorderFactory.createEmptyBorder(20, 140, 10, 140));
        main_panel.setBackground(Color.WHITE);
        main_panel.setBounds(25, 260, 1475, 500);
        this.add(main_panel);

        JPanel form_panel = new JPanel(new BorderLayout());
        main_panel.add(form_panel, BorderLayout.CENTER);

        JPanel date_and_filter_panel = new JPanel(new GridLayout(2,4,30,5));
        date_and_filter_panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        date_and_filter_panel.setBackground(Color.WHITE);
        JLabel checkin_label = new JLabel("Check in:");
        checkin_label.setFont(new Font( "Times New Roman", Font.BOLD, 28));
        date_and_filter_panel.add(checkin_label);
        JLabel checkout_label = new JLabel("Check out:");
        checkout_label.setFont(new Font( "Times New Roman", Font.BOLD, 28));
        date_and_filter_panel.add(checkout_label);
        JLabel room_type_label = new JLabel("Room Type:");
        room_type_label.setFont(new Font("Times New Roman", Font.BOLD, 28));
        date_and_filter_panel.add(room_type_label);
        date_and_filter_panel.add(new JLabel());

        Properties p = new Properties();
        p.put("text.today", " ");
        p.put("text.month", " ");
        p.put("text.year", " ");

        UtilDateModel checkin_model = new UtilDateModel();
        JDatePanelImpl checkin_datePanel = new JDatePanelImpl(checkin_model, p);
        checkin_datePicker = new JDatePickerImpl(checkin_datePanel, new DateLabelFormatter());
        checkin_datePicker.setBackground(Color.WHITE);
        checkin_datePicker.setFocusable(false);
        checkin_model.setDate(2025, 4, 28);
        checkin_model.setSelected(true);
        checkinTextField = checkin_datePicker.getJFormattedTextField();
        checkinTextField.setFont(new Font( "Times New Roman", Font.PLAIN, 25));
        checkinTextField.setBackground(new Color(255, 232, 250));
        date_and_filter_panel.add(checkin_datePicker);

        UtilDateModel checkout_model = new UtilDateModel();
        JDatePanelImpl checkout_datePanel = new JDatePanelImpl(checkout_model, p);
        checkout_datePicker = new JDatePickerImpl(checkout_datePanel, new DateLabelFormatter());
        checkout_datePicker.setBackground(Color.WHITE);
        checkout_datePicker.setFocusable(false);
        checkout_model.setDate(2025, 4, 29);
        checkout_model.setSelected(true);
        checkoutTextField = checkout_datePicker.getJFormattedTextField();
        checkoutTextField.setFont(new Font( "Times New Roman", Font.PLAIN, 25));
        checkoutTextField.setBackground(new Color(255, 232, 250));
        date_and_filter_panel.add(checkout_datePicker);


        String[] room_types = {"Kapsul","Standard","Deluxe","VIP"};
        room_type_list = new JComboBox(room_types);
        room_type_list.setFocusable(false);
        room_type_list.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        room_type_list.setMaximumRowCount(2);
        room_type_list.setBackground(new Color(255, 232, 250));
        date_and_filter_panel.add(room_type_list);
        date_and_filter_panel.add(new JLabel());
        form_panel.add(date_and_filter_panel, BorderLayout.NORTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        room_panel = new JPanel(new GridBagLayout());
        form_panel.add(room_panel, BorderLayout.CENTER);

        photo_panel = new JPanel();
        photo_panel.setBackground(new Color(255, 232, 250));
        photo_panel.setBorder(new LineBorder(new Color(255, 232, 250), 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.55;
        gbc.weighty = 1.0;
        room_panel.add(photo_panel, gbc);

        JPanel space_panel = new JPanel();
        space_panel.setBackground(Color.WHITE);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.05;
        gbc.weighty = 1.0;
        room_panel.add(space_panel, gbc);

        JPanel desc_panel = new JPanel(new BorderLayout());
        desc_panel.setBackground(new Color(255, 232, 250));

        data_panel = new JPanel(new GridLayout(4,2));
        data_panel.setBackground(new Color(255, 232, 250));
        data_panel.setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
        data_panel.setVisible(false);

        room_number = new JLabel("");
        room_number.setFont(new Font("Times New Roman", Font.BOLD, 20));
        room_feature = new JLabel("");
        room_feature.setFont(new Font("Times New Roman", Font.BOLD, 20));
        room_type = new JLabel("");
        room_type.setFont(new Font("Times New Roman", Font.BOLD, 20));
        room_capacity = new JLabel("");
        room_capacity.setFont(new Font("Times New Roman", Font.BOLD, 20));
        room_size = new JLabel("");
        room_size.setFont(new Font("Times New Roman", Font.BOLD, 20));
        room_status = new JLabel("");
        room_status.setFont(new Font("Times New Roman", Font.BOLD, 20));
        room_price = new JLabel("");
        room_price.setFont(new Font("Times New Roman", Font.BOLD, 20));
        bed_type = new JLabel("");
        bed_type.setFont(new Font("Times New Roman", Font.BOLD, 20));

        desc_panel.add(data_panel, BorderLayout.CENTER);

        previous_button = new CustomButton("<",0);
        previous_button.setFont(new Font("Times New Roman", Font.BOLD, 24));
        previous_button.setBackground(new Color(255, 232, 250));
        previous_button.addActionListener(new BookRoomControl(this));
        desc_panel.add(previous_button,BorderLayout.WEST);

        next_button = new CustomButton(">",0);
        next_button.setFont(new Font("Times New Roman", Font.BOLD, 24));
        next_button.setBackground(new Color(255, 232, 250));
        next_button.addActionListener(new BookRoomControl(this));
        desc_panel.add(next_button,BorderLayout.EAST);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        room_panel.add(desc_panel, gbc);

        JPanel button_panel = new JPanel(new GridLayout(1,4,40,0));
        button_panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        button_panel.setBackground(Color.WHITE);
        form_panel.add(button_panel, BorderLayout.SOUTH);
        button_panel.add(new JLabel());

        back_button = new CustomButton("BACK", 30);
        back_button.setFont(new Font("Times New Roman", Font.BOLD, 24));
        back_button.setBackground(new Color(255, 118, 118));
        back_button.setForeground(Color.WHITE);
        back_button.addActionListener(new BookRoomControl(this));
        button_panel.add(back_button);

        continue_button = new CustomButton("CONTINUE", 30);
        continue_button.setFont(new Font("Times New Roman", Font.BOLD, 24));
        continue_button.setBackground(new Color(255, 118, 118));
        continue_button.setForeground(Color.WHITE);
        continue_button.addActionListener(new BookRoomControl(this));
        button_panel.add(continue_button);
        button_panel.add(new JLabel());

        room_type_list.addActionListener(new BookRoomControl(this));
    }

    public void loadlabel(JLabel roomNumber, JLabel roomFeature, JLabel roomType, JLabel roomCapacity, JLabel roomSize, JLabel bedType, JLabel roomPrice, JPanel dataPanel, String selectedRoomType, String in, String out) throws SQLException {
        readyroom = model.availableroom(con, selectedRoomType, in, out);

        roomNumber.setText("Room Number: "+readyroom.get(0));
        roomType.setText("Room Type: "+readyroom.get(1));
        roomSize.setText("Room Size: "+readyroom.get(2));
        bedType.setText("Bed Type: "+readyroom.get(3));
        roomFeature.setText("Room Feature: -");
        if(readyroom.get(4).equals("1")){
            roomFeature.setText("Room Feature: Smoking");
        }
        else if(readyroom.get(5).equals("1")){
            roomFeature.setText("Room Feature: Quiet Room");
        }
        roomCapacity.setText("Room Capacity: "+readyroom.get(6));
        roomPrice.setText("Room Price: "+readyroom.get(7));

        dataPanel.setVisible(true);
        dataPanel.add(roomNumber);
        dataPanel.add(roomFeature);
        dataPanel.add(roomType);
        dataPanel.add(roomCapacity);
        dataPanel.add(roomSize);
        dataPanel.add(bedType);
        dataPanel.add(roomPrice);
    }
}