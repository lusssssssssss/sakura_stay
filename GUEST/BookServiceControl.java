import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BookServiceControl implements ActionListener {
    private BookServiceView view;
    BookServiceControl(BookServiceView view) {
        this.view = view;
    }
    public void actionPerformed(ActionEvent e) {
        int howmanyyukata = (int) view.yukata_amount.getValue();
        view.yukata = howmanyyukata;

        int howmanyphoto = (int) view.photo_amount.getValue();
        view.photo = howmanyphoto;

        String breakfast = "";
        String babycot = "";
        String extrabed = "";
        String yukataya = "";
        String photoya = "";

        int servisprice = 0;
        int breakfastprice = 150000;
        int babycoprice = 100000;
        int extrabedprice = 150000;
        int yukataprice = 50000 * view.yukata;
        int photoprice = 200000 * view.photo;

        if(e.getSource() == view.back_button) {
            view.dispose();
            try {
                BookRoomView bookRoomView = new BookRoomView(view.model, view.con, view.email, view.photopath);
                bookRoomView.setVisible(true);
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource() == view.continue_button) {
            if (view.breakfast_cb.isSelected()) {
                breakfast = "+Breakfast";
                servisprice += breakfastprice;
            }
            if (view.baby_cb.isSelected()) {
                babycot = "+Babycot";
                servisprice += babycoprice;
            }
            if (view.bed_cb.isSelected()) {
                extrabed = "+Extrabed";
                servisprice += extrabedprice;
            }
            if (view.yukata_cb.isSelected()) {
                yukataya = "+" + view.yukata + " Yukata";
                servisprice += yukataprice;
            }
            if (view.photo_cb.isSelected()) {
                photoya = "+" + view.photo + " Photo";
                servisprice += photoprice;
            }
            int totalprice = servisprice + (Integer.parseInt(view.data[7])*Integer.parseInt(view.data[9]));
            String[]extras = {breakfast,babycot,extrabed,yukataya,photoya};
            view.dispose();
            BookDataView bookDataView = new BookDataView(view.model, view.con, view.email, view.photopath, view.data, totalprice, extras);
            bookDataView.setVisible(true);
        }
    }
}
