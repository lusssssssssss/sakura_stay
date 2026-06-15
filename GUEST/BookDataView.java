import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDataView extends JFrame {
    public JTextField nik_field = new JTextField();
    public JTextField name_field = new JTextField();
    public JTextField email_field = new JTextField();
    public JTextField phone_field = new JTextField();
    public JButton back_button, continue_button;
    Model model;
    Connection con;
    String email, photopath;
    String[]data, extras;
    int totalprice;
    public BookDataView(Model model, Connection con, String email, String photopath, String[] data, int totalprice, String[] extras) {
        this.model = model;
        this.con = con;
        this.email = email;
        this.photopath = photopath;
        this.data = data;
        this.extras = extras;
        this.totalprice = totalprice;
        this.setTitle("Book Room");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(255, 209, 229));
        try {
            if (!photopath.isEmpty() || !email.isEmpty()) {
                loadmember(nik_field,name_field, email_field, phone_field);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        ImageIcon bgImage = new ImageIcon("BookBackground.jpg");
        JLabel title_label = new JLabel(new ImageIcon(bgImage.getImage().getScaledInstance(1536, 190, Image.SCALE_SMOOTH)));
        title_label.setBounds(0, 40, 1536, 190);
        this.add(title_label);

        JPanel main_panel = new JPanel(new BorderLayout());
        main_panel.setBorder(BorderFactory.createEmptyBorder(20, 340, 10, 340));
        main_panel.setBackground(Color.WHITE);
        main_panel.setBounds(25, 260, 1475, 500);
        this.add(main_panel);

        JPanel form_panel = new JPanel(new GridLayout(1,2,40,0));
        form_panel.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 60));
        form_panel.setBackground(new Color(255, 232, 250));
        main_panel.add(form_panel, BorderLayout.CENTER);

        JPanel left_panel = new JPanel(new GridLayout(4,2,-100,60));
        left_panel.setBackground(new Color(255, 232, 250));
        form_panel.add(left_panel);
        left_panel.add(new JLabel());
        left_panel.add(new JLabel());

        JLabel nik_label = new JLabel("NIK:");
        nik_label.setFont(new Font( "Times New Roman", Font.BOLD, 30));
        left_panel.add(nik_label);

        nik_field.setFont(new Font( "Times New Roman", Font.PLAIN, 18));
        left_panel.add(nik_field);

        JLabel name_label = new JLabel("Name:");
        name_label.setFont(new Font( "Times New Roman", Font.BOLD, 30));
        left_panel.add(name_label);

        name_field.setFont(new Font( "Times New Roman", Font.PLAIN, 18));
        left_panel.add(name_field);

        left_panel.add(new JLabel());
        left_panel.add(new JLabel());

        JPanel right_panel = new JPanel(new GridLayout(4,2,-100,60));
        right_panel.setBackground(new Color(255, 232, 250));
        form_panel.add(right_panel);
        right_panel.add(new JLabel());
        right_panel.add(new JLabel());

        JLabel email_label = new JLabel("Email:");
        email_label.setFont(new Font( "Times New Roman", Font.BOLD, 30));
        right_panel.add(email_label);

        email_field.setFont(new Font( "Times New Roman", Font.PLAIN, 18));
        right_panel.add(email_field);

        JLabel phone_label = new JLabel("Phone:");
        phone_label.setFont(new Font( "Times New Roman", Font.BOLD, 30));
        right_panel.add(phone_label);

        phone_field.setFont(new Font( "Times New Roman", Font.PLAIN, 18));
        right_panel.add(phone_field);

        right_panel.add(new JLabel());
        right_panel.add(new JLabel());

        JPanel button_panel = new JPanel(new GridLayout(1,4,40,0));
        button_panel.setBackground(Color.WHITE);
        button_panel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        main_panel.add(button_panel, BorderLayout.SOUTH);
        button_panel.add(new JLabel());

        back_button = new CustomButton("BACK", 30);
        back_button.setFont(new Font("Times New Roman", Font.BOLD, 24));
        back_button.setBackground(new Color(255, 118, 118));
        back_button.setForeground(Color.WHITE);
        back_button.addActionListener(new BookDataControl(this));
        button_panel.add(back_button);

        continue_button = new CustomButton("CONTINUE", 30);
        continue_button.setFont(new Font("Times New Roman", Font.BOLD, 20));
        continue_button.setBackground(new Color(255, 118, 118));
        continue_button.setForeground(Color.WHITE);
        continue_button.addActionListener(new BookDataControl(this));
        button_panel.add(continue_button);
        button_panel.add(new JLabel());
    }

    private void loadmember(JTextField nikField, JTextField nameField, JTextField emailField, JTextField phoneField) throws SQLException {
        ArrayList<String> datauser = model.getdatauser(con, email);
        if(datauser.isEmpty()){
            return;
        }
        nikField.setText(datauser.get(0));
        emailField.setText(datauser.get(1));
        nameField.setText(datauser.get(2));
        phoneField.setText(datauser.get(3));
    }
}