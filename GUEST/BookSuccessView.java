import javax.swing.*;
import java.awt.*;
import java.awt.print.Book;
import java.sql.Connection;

public class BookSuccessView extends JFrame {
    CustomButton back_button;
    Model model;
    Connection con;
    String email;
    String photopath;

    public BookSuccessView(Model model, Connection con, String guestemail, String photopath) {
        this.model = model;
        this.con = con;
        this.email = guestemail;
        this.photopath = photopath;
        this.setTitle("Book Room");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1536,864);
        this.setLocationRelativeTo(null);

        PanelWithBackground background = new PanelWithBackground("PaymentSuccess.png");
        background.setPreferredSize(new Dimension(1536, 864));
        background.setLayout(new BorderLayout());
        background.setBorder(BorderFactory.createEmptyBorder(110,450,110,450));

        CustomPanel outer_panel = new CustomPanel();
        outer_panel.setBackground(new Color(255, 255, 255,180));
        outer_panel.setLayout(new BorderLayout());
        outer_panel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        background.add(outer_panel, BorderLayout.CENTER);

        CustomPanel main_panel = new CustomPanel();
        main_panel.setBackground(new Color(255, 232, 250,200));
        main_panel.setLayout(new BorderLayout());
        main_panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        outer_panel.add(main_panel, BorderLayout.CENTER);

        JPanel message_panel = new JPanel();
        message_panel.setLayout(new BoxLayout(message_panel, BoxLayout.Y_AXIS));
        message_panel.setOpaque(false);
        main_panel.add(message_panel, BorderLayout.CENTER);

        JLabel title = new JLabel("Your Payment Is Success!!");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Tahoma", Font.BOLD, 33));
        title.setForeground(new Color(204,51,0));
        message_panel.add(title);
        message_panel.add(Box.createVerticalStrut(50));

        JLabel message1 = new JLabel("Your booking at Sakura Stay is confirmed. We're");
        message1.setAlignmentX(Component.CENTER_ALIGNMENT);
        message1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        message1.setForeground(new Color(204,51,0));
        message_panel.add(message1);
        JLabel message2 = new JLabel("excited to welcome you and ensure a comfortable,");
        message2.setAlignmentX(Component.CENTER_ALIGNMENT);
        message2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        message2.setForeground(new Color(204,51,0));
        message_panel.add(message2);
        JLabel message3 = new JLabel("memorable experience.");
        message3.setAlignmentX(Component.CENTER_ALIGNMENT);
        message3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        message3.setForeground(new Color(204,51,0));
        message_panel.add(message3);
        message_panel.add(Box.createVerticalStrut(50));

        JLabel message4 = new JLabel("A confirmation email with your booking details has");
        message4.setAlignmentX(Component.CENTER_ALIGNMENT);
        message4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        message4.setForeground(new Color(204,51,0));
        message_panel.add(message4);
        JLabel message5 = new JLabel("been sent to you.");
        message5.setAlignmentX(Component.CENTER_ALIGNMENT);
        message5.setFont(new Font("Tahoma", Font.PLAIN, 20));
        message5.setForeground(new Color(204,51,0));
        message_panel.add(message5);
        message_panel.add(Box.createVerticalStrut(50));

        JLabel message6 = new JLabel("We look forward to hosting you at Sakura Stay!");
        message6.setAlignmentX(Component.CENTER_ALIGNMENT);
        message6.setFont(new Font("Tahoma", Font.PLAIN, 20));
        message6.setForeground(new Color(204,51,0));
        message_panel.add(message6);
        message_panel.add(Box.createVerticalStrut(50));

        JPanel button_panel = new JPanel(new GridLayout(1,3));
        button_panel.setOpaque(false);
        message_panel.add(button_panel);
        button_panel.add(new JLabel());
        back_button = new CustomButton("BACK",30);
        back_button.setFont(new Font("Tahoma", Font.BOLD, 24));
        back_button.setBackground(new Color(255, 118, 118));
        back_button.setForeground(Color.WHITE);
        back_button.addActionListener(e->{
            BookSuccessView.this.dispose();
            GuestHomeView guestHomeView = new GuestHomeView(model, con, guestemail, photopath);
            guestHomeView.setVisible(true);
        });
        button_panel.add(back_button);
        button_panel.add(new JLabel());

        this.add(background, BorderLayout.CENTER);
    }
}
