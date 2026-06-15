import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class GuestHomeController{
    private GuestHomeView view;

    public GuestHomeController(GuestHomeView view){
        this.view = view;

        JButton[] buttons = {
                view.roomsButton, view.serviceButton, view.galleryButton, view.bookButton, view.contactButton, view.loginButton
        };

        for (JButton btn : buttons) {
            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 51, 0)));
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0))); // transparan kembali
                }
            });
        }
    }
}
