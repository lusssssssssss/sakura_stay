package view;

import component.CustomButton;
import component.CustomPanel;
import component.CustomToggleButton;
import component.PanelWithBackground;

import javax.swing.*;
import java.awt.*;

public class RoomsView extends JFrame {
    public CustomPanel roomDetailPanel;
    public JPanel informationPanel, buttonPanel;
    public CustomButton outOfServiceButton, occupiedButton, vacantButton, backButton;
    public JLabel room_number, email_admin, guest_id, room_type, bed_type, room_size, room_capacity, room_feature, room_status, room_price;
    public CustomToggleButton[][] roomButtons = new CustomToggleButton[4][5];

    public RoomsView() {
        this.setTitle("Admin - Rooms");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);

        PanelWithBackground bgPanel = new PanelWithBackground("picture/roomsBackground.png");
        bgPanel.setLayout(null);

        JPanel roomPanel = new JPanel(new BorderLayout());
        roomPanel.setBounds(30,215,1000,500);
        roomPanel.setOpaque(false);
        roomPanel.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        bgPanel.add(roomPanel);

        JPanel roomNumberPanel = new JPanel(new GridLayout(4,7,60,30));
        roomNumberPanel.setOpaque(false);
        updateRoom(roomNumberPanel);
        roomPanel.add(roomNumberPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2,1));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        bottomPanel.setOpaque(false);

        JPanel colorPanel = new JPanel(new GridLayout(1,3,120,0));
        colorPanel.setBorder(BorderFactory.createEmptyBorder(0,350,0,350));
        colorPanel.setOpaque(false);
        bottomPanel.add(colorPanel);

        CustomPanel greyPanel = new CustomPanel();
        greyPanel.setBackground(new Color(230,230,230));
        colorPanel.add(greyPanel);
        CustomPanel redPanel = new CustomPanel();
        redPanel.setBackground(new Color(241,34,0));
        colorPanel.add(redPanel);
        CustomPanel greenPanel = new CustomPanel();
        greenPanel.setBackground(new Color(102,153,51));
        colorPanel.add(greenPanel);

        JPanel textPanel = new JPanel(new GridLayout(1,3,-580,0));
        textPanel.setOpaque(false);
        bottomPanel.add(textPanel);

        JLabel outOfServiceLabel = new JLabel("Out of Service");
        outOfServiceLabel.setForeground(new Color(188,0,45));
        outOfServiceLabel.setFont(new Font("Tahoma",Font.BOLD,15));
        outOfServiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(outOfServiceLabel);
        JLabel occupiedLabel = new JLabel("Occupied");
        occupiedLabel.setForeground(new Color(188,0,45));
        occupiedLabel.setFont(new Font("Tahoma",Font.BOLD,15));
        occupiedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(occupiedLabel);
        JLabel vacantLabel = new JLabel("Vacant");
        vacantLabel.setForeground(new Color(188,0,45));
        vacantLabel.setFont(new Font("Tahoma",Font.BOLD,15));
        vacantLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(vacantLabel);
        roomPanel.add(bottomPanel, BorderLayout.SOUTH);

        roomDetailPanel = new CustomPanel();
        roomDetailPanel.setLayout(new BorderLayout());
        roomDetailPanel.setBounds(1020,215,475,500);
        roomDetailPanel.setBackground(new Color(255, 209, 229));
        roomDetailPanel.setBorder(BorderFactory.createEmptyBorder(20,30,20,30));
        roomDetailPanel.setVisible(false);
        bgPanel.add(roomDetailPanel);

        informationPanel = new JPanel(new GridLayout(12,1,0,10));
        informationPanel.setOpaque(false);
        informationPanel.setVisible(false);
        roomDetailPanel.add(informationPanel);

        JLabel title = new JLabel("ROOM DETAILS");
        title.setFont(new Font("Tahoma",Font.BOLD,30));
        title.setForeground(new Color(188,0,45));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        informationPanel.add(title);
        informationPanel.add(new JLabel());

        room_number = new JLabel("Room Number: ");
        room_number.setFont(new Font("Tahoma", Font.PLAIN, 18));
        room_number.setForeground(new Color(188,0,45));
        room_number.setAlignmentX(Component.LEFT_ALIGNMENT);
        informationPanel.add(room_number);

        email_admin = new JLabel("Admin Email: ");
        email_admin.setFont(new Font("Tahoma", Font.PLAIN, 18));
        email_admin.setForeground(new Color(188,0,45));
        informationPanel.add(email_admin);

        guest_id = new JLabel("Guest ID: ");
        guest_id.setFont(new Font("Tahoma", Font.PLAIN, 18));
        guest_id.setForeground(new Color(188,0,45));
        informationPanel.add(guest_id);

        room_type = new JLabel("Room Type: ");
        room_type.setFont(new Font("Tahoma", Font.PLAIN, 18));
        room_type.setForeground(new Color(188,0,45));
        informationPanel.add(room_type);

        bed_type = new JLabel("Bed Type: ");
        bed_type.setFont(new Font("Tahoma", Font.PLAIN, 18));
        bed_type.setForeground(new Color(188,0,45));
        informationPanel.add(bed_type);

        room_feature = new JLabel("Room Feature: ");
        room_feature.setFont(new Font("Tahoma", Font.PLAIN, 18));
        room_feature.setForeground(new Color(188,0,45));
        informationPanel.add(room_feature);

        room_capacity = new JLabel("Room Capacity: ");
        room_capacity.setFont(new Font("Tahoma", Font.PLAIN, 18));
        room_capacity.setForeground(new Color(188,0,45));
        informationPanel.add(room_capacity);

        room_size = new JLabel("Room Size: ");
        room_size.setFont(new Font("Tahoma", Font.PLAIN, 18));
        room_size.setForeground(new Color(188,0,45));
        informationPanel.add(room_size);

        room_status = new JLabel("Room Status: ");
        room_status.setFont(new Font("Tahoma", Font.PLAIN, 18));
        room_status.setForeground(new Color(188,0,45));
        informationPanel.add(room_status);

        room_price = new JLabel("Room Price: ");
        room_price.setFont(new Font("Tahoma", Font.PLAIN, 18));
        room_price.setForeground(new Color(188,0,45));
        informationPanel.add(room_price);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40,0,0,0));
        buttonPanel.setVisible(false);
        roomDetailPanel.add(buttonPanel, BorderLayout.SOUTH);

        outOfServiceButton = new CustomButton("Out of Service",30);
        outOfServiceButton.setForeground(Color.WHITE);
        outOfServiceButton.setBackground(new Color(255, 118, 118));
        outOfServiceButton.setFont(new Font("Times New Roman",Font.BOLD,18));
        buttonPanel.add(outOfServiceButton);

        occupiedButton = new CustomButton("Occupied",30);
        occupiedButton.setForeground(Color.WHITE);
        occupiedButton.setBackground(new Color(255, 118, 118));
        occupiedButton.setFont(new Font("Times New Roman",Font.BOLD,18));
        buttonPanel.add(occupiedButton);

        vacantButton = new CustomButton("Vacant",30);
        vacantButton.setForeground(Color.WHITE);
        vacantButton.setBackground(new Color(255, 118, 118));
        vacantButton.setFont(new Font("Times New Roman",Font.BOLD,18));
        buttonPanel.add(vacantButton);

        backButton = new CustomButton("BACK",30);
        backButton.setBounds(30,730,200,40);
        backButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        backButton.setBackground(new Color(255, 118, 118));
        backButton.setForeground(Color.WHITE);
        bgPanel.add(backButton);

        this.add(bgPanel);
    }

    public void updateRoom(JPanel roomNumberPanel){
        ButtonGroup group = new ButtonGroup();
        for(int i = 4; i > 0; i--){
            if(i == 4){
                JLabel vipLabel = new JLabel("VIP");
                vipLabel.setForeground(new Color(188,0,45));
                vipLabel.setFont(new Font("Tahoma",Font.PLAIN,20));
                roomNumberPanel.add(vipLabel);
            }
            else if(i == 3){
                JLabel deluxeLabel = new JLabel("Deluxe");
                deluxeLabel.setForeground(new Color(188,0,45));
                deluxeLabel.setFont(new Font("Tahoma",Font.PLAIN,20));
                roomNumberPanel.add(deluxeLabel);
            }
            else if(i == 2){
                JLabel standardLabel = new JLabel("Standard");
                standardLabel.setForeground(new Color(188,0,45));
                standardLabel.setFont(new Font("Tahoma",Font.PLAIN,20));
                roomNumberPanel.add(standardLabel);
            }
            else {
                JLabel kapsulLabel = new JLabel("Kapsul");
                kapsulLabel.setForeground(new Color(188,0,45));
                kapsulLabel.setFont(new Font("Tahoma",Font.PLAIN,20));
                roomNumberPanel.add(kapsulLabel);
            }
            for(int j = 1; j < 6; j++){
                CustomToggleButton roomButton = new CustomToggleButton(i+"0"+j,30);
                roomButton.setForeground(Color.WHITE);
                roomButton.setBackground(new Color(102,153,51));
                roomButton.setFont(new Font("Tahoma",Font.BOLD,20));
                roomNumberPanel.add(roomButton);
                group.add(roomButton);
                roomButtons[4-i][j-1] = roomButton;
            }
            if(i == 4){
                JLabel fourthLabel = new JLabel("Lt.4");
                fourthLabel.setForeground(new Color(188,0,45));
                fourthLabel.setFont(new Font("Tahoma",Font.PLAIN,20));
                fourthLabel.setHorizontalAlignment(SwingConstants.LEFT);
                roomNumberPanel.add(fourthLabel);
            }
            else if(i == 3){
                JLabel thirdLabel = new JLabel("Lt.3");
                thirdLabel.setForeground(new Color(188,0,45));
                thirdLabel.setFont(new Font("Tahoma",Font.PLAIN,20));
                thirdLabel.setHorizontalAlignment(SwingConstants.LEFT);
                roomNumberPanel.add(thirdLabel);
            }
            else if(i == 2){
                JLabel secondLabel = new JLabel("Lt.2");
                secondLabel.setForeground(new Color(188,0,45));
                secondLabel.setFont(new Font("Tahoma",Font.PLAIN,20));
                secondLabel.setHorizontalAlignment(SwingConstants.LEFT);
                roomNumberPanel.add(secondLabel);
            }
            else {
                JLabel firstLabel = new JLabel("Lt.1");
                firstLabel.setForeground(new Color(188,0,45));
                firstLabel.setFont(new Font("Tahoma",Font.PLAIN,20));
                firstLabel.setHorizontalAlignment(SwingConstants.LEFT);
                roomNumberPanel.add(firstLabel);
            }
        }
    }
}