import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class BookServiceView extends JFrame {
    public JCheckBox breakfast_cb, baby_cb, bed_cb, yukata_cb, photo_cb;
    public CustomButton continue_button, back_button;
    public JSpinner yukata_amount, photo_amount;
    Model model;
    Connection con;
    String email, photopath;
    String[] data;
    int yukata;
    int photo;
    public BookServiceView(Model model, Connection con, String email, String photopath, String[] data) {
        this.model = model;
        this.con = con;
        this.email = email;
        this.photopath = photopath;
        this.data = data;
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
        main_panel.setBorder(BorderFactory.createEmptyBorder(20, 340, 10, 340));
        main_panel.setBackground(Color.WHITE);
        main_panel.setBounds(25, 260, 1475, 500);
        this.add(main_panel);

        JPanel form_panel = new JPanel(new BorderLayout());
        form_panel.setBackground(new Color(255, 232, 250));
        main_panel.add(form_panel, BorderLayout.CENTER);

        JPanel title_panel = new JPanel(new GridLayout(2,1,0,-15));
        title_panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        title_panel.setBackground(new Color(255, 232, 250));
        form_panel.add(title_panel, BorderLayout.NORTH);

        JLabel add_service_label = new JLabel("ADD SERVICE");
        add_service_label.setFont(new Font( "Times New Roman", Font.BOLD, 40));
        add_service_label.setHorizontalAlignment(SwingConstants.CENTER);
        add_service_label.setForeground(new Color(241,34,0));
        title_panel.add(add_service_label);

        JLabel optional_label = new JLabel("(Optional)");
        optional_label.setFont(new Font( "Times New Roman", Font.BOLD, 20));
        optional_label.setHorizontalAlignment(SwingConstants.CENTER);
        optional_label.setForeground(new Color(241,34,0));
        title_panel.add(optional_label);

        JPanel service_panel = new JPanel(new BorderLayout());
        service_panel.setBackground(new Color(255, 232, 250));
        service_panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        form_panel.add(service_panel, BorderLayout.CENTER);

        JPanel information_panel = new JPanel();
        information_panel.setLayout(new BoxLayout(information_panel, BoxLayout.Y_AXIS));
        information_panel.setBackground(new Color(255, 232, 250));

        JScrollPane information_scroll = new JScrollPane(information_panel);
        information_scroll.setBackground(new Color(255, 232, 250));
        information_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        information_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        information_scroll.setBorder(null);
        service_panel.add(information_scroll, BorderLayout.CENTER);

        JPanel breakfast = new JPanel();
        breakfast.setLayout(new BoxLayout(breakfast, BoxLayout.X_AXIS));
        breakfast.setOpaque(false);
        breakfast.setAlignmentX(Component.LEFT_ALIGNMENT);
        information_panel.add(breakfast);

        breakfast_cb = new JCheckBox("Breakfast");
        breakfast_cb.setFocusable(false);
        breakfast_cb.setFont(new Font( "Times New Roman", Font.BOLD, 30));
        breakfast_cb.setBackground(new Color(255, 232, 250));
        breakfast_cb.setIconTextGap(20);
        breakfast.add(breakfast_cb);
        breakfast.add(Box.createHorizontalStrut(10));

        JLabel breakfast_price = new JLabel("(+Rp150.000)");
        breakfast_price.setFont(new Font( "Times New Roman", Font.BOLD, 30));
        breakfast_price.setForeground(new Color(241,34,0));
        breakfast.add(breakfast_price);
        information_panel.add(Box.createVerticalStrut(30));

        JPanel baby = new JPanel();
        baby.setLayout(new BoxLayout(baby, BoxLayout.X_AXIS));
        baby.setOpaque(false);
        baby.setAlignmentX(Component.LEFT_ALIGNMENT);
        information_panel.add(baby);

        baby_cb = new JCheckBox("Baby Cot");
        baby_cb.setFocusable(false);
        baby_cb.setFont(new Font( "Times New Roman", Font.BOLD, 30));
        baby_cb.setBackground(new Color(255, 232, 250));
        baby_cb.setIconTextGap(20);
        baby.add(baby_cb);
        baby.add(Box.createHorizontalStrut(10));

        JLabel baby_price = new JLabel("+(Rp100.000)");
        baby_price.setFont(new Font( "Times New Roman", Font.BOLD, 30));
        baby_price.setForeground(new Color(241,34,0));
        baby.add(baby_price);
        information_panel.add(Box.createVerticalStrut(30));

        JPanel bed = new JPanel();
        bed.setLayout(new BoxLayout(bed, BoxLayout.X_AXIS));
        bed.setOpaque(false);
        bed.setAlignmentX(Component.LEFT_ALIGNMENT);
        information_panel.add(bed);

        bed_cb = new JCheckBox("Extra Bed");
        bed_cb.setFocusable(false);
        bed_cb.setFont(new Font( "Times New Roman", Font.BOLD, 30));
        bed_cb.setBackground(new Color(255, 232, 250));
        bed_cb.setIconTextGap(20);
        bed.add(bed_cb);
        bed.add(Box.createHorizontalStrut(10));

        JLabel bed_price = new JLabel("+(Rp150.000)");
        bed_price.setFont(new Font( "Times New Roman", Font.BOLD, 30));
        bed_price.setForeground(new Color(241,34,0));
        bed.add(bed_price);
        information_panel.add(Box.createVerticalStrut(30));

        JPanel yukata = new JPanel();
        yukata.setLayout(new BoxLayout(yukata, BoxLayout.X_AXIS));
        yukata.setOpaque(false);
        yukata.setAlignmentX(Component.LEFT_ALIGNMENT);
        information_panel.add(yukata);

        yukata_cb = new JCheckBox("Yukata Rental");
        yukata_cb.setFocusable(false);
        yukata_cb.setFont(new Font( "Times New Roman", Font.BOLD, 30));
        yukata_cb.setBackground(new Color(255, 232, 250));
        yukata_cb.setIconTextGap(20);
        yukata.add(yukata_cb);
        yukata.add(Box.createHorizontalStrut(10));

        JLabel yukata_price = new JLabel("(+Rp50.000)");
        yukata_price.setFont(new Font( "Times New Roman", Font.BOLD, 30));
        yukata_price.setForeground(new Color(241,34,0));
        yukata.add(yukata_price);
        yukata.add(Box.createHorizontalStrut(15));

        yukata_amount = new JSpinner();
        int maxy = 0;
        try{
            maxy = model.getservisjumlahyukata(con);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(maxy == 0){
            yukata_cb.setEnabled(false);
        }
        yukata_amount.setModel(new SpinnerNumberModel(0,0,maxy,1));
        yukata_amount.setVisible(false);
        yukata_amount.setFont(new Font( "Times New Roman", Font.PLAIN, 20));
        yukata_amount.setPreferredSize(new Dimension(200,30));
        JComponent editor = yukata_amount.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
            JTextField textField = spinnerEditor.getTextField();
            textField.setHorizontalAlignment(JTextField.LEFT);
        }
        yukata.add(yukata_amount);
        yukata.add(Box.createHorizontalStrut(20));
        information_panel.add(Box.createVerticalStrut(30));

        JPanel photo = new JPanel();
        photo.setLayout(new BoxLayout(photo, BoxLayout.X_AXIS));
        photo.setOpaque(false);
        photo.setAlignmentX(Component.LEFT_ALIGNMENT);
        information_panel.add(photo);

        photo_cb = new JCheckBox("Photo Session");
        photo_cb.setFocusable(false);
        photo_cb.setFont(new Font( "Times New Roman", Font.BOLD, 30));
        photo_cb.setBackground(new Color(255, 232, 250));
        photo_cb.setIconTextGap(20);
        photo.add(photo_cb);
        photo.add(Box.createHorizontalStrut(10));

        JLabel photo_price = new JLabel("+(Rp200.000)");
        photo_price.setFont(new Font( "Times New Roman", Font.BOLD, 30));
        photo_price.setForeground(new Color(241,34,0));
        photo.add(photo_price);
        photo.add(Box.createHorizontalStrut(15));

        photo_amount = new JSpinner();
        int maxp = 0;
        try{
            maxp = model.getservisjumlahphoto(con);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(maxp == 0){
            photo_cb.setEnabled(false);
        }
        photo_amount.setModel(new SpinnerNumberModel(0, 0, maxp, 1));
        photo_amount.setVisible(false);
        photo_amount.setFont(new Font( "Times New Roman", Font.PLAIN, 20));
        photo_amount.setPreferredSize(new Dimension(200,30));
        editor = photo_amount.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
            JTextField textField = spinnerEditor.getTextField();
            textField.setHorizontalAlignment(JTextField.LEFT);
        }
        photo.add(photo_amount);
        photo.add(Box.createHorizontalStrut(20));
        information_panel.add(Box.createVerticalStrut(30));

        JPanel button_panel = new JPanel(new GridLayout(1,4,40,0));
        button_panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        button_panel.setBackground(Color.WHITE);
        form_panel.add(button_panel, BorderLayout.SOUTH);
        button_panel.add(new JLabel());

        back_button = new CustomButton("BACK", 30);
        back_button.setFont(new Font("Times New Roman", Font.BOLD, 24));
        back_button.setBackground(new Color(255, 118, 118));
        back_button.setForeground(Color.WHITE);
        back_button.addActionListener(new BookServiceControl(this));
        button_panel.add(back_button);

        continue_button = new CustomButton("CONTINUE", 30);
        continue_button.setFont(new Font("Times New Roman", Font.BOLD, 20));
        continue_button.setBackground(new Color(255, 118, 118));
        continue_button.setForeground(Color.WHITE);
        button_panel.add(continue_button);
        continue_button.addActionListener(new BookServiceControl(this));
        button_panel.add(new JLabel());

        yukata_cb.addActionListener(e->{
            if (yukata_cb.isSelected()) {
                yukata_amount.setVisible(true);
            }
            else{
                yukata_amount.setVisible(false);
            }
        });
        photo_cb.addActionListener(e->{
            if (photo_cb.isSelected()) {
                photo_amount.setVisible(true);
            }
            else{
                photo_amount.setVisible(false);
            }
        });
    }
}