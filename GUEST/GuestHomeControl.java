import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Provider;
import java.sql.SQLException;

public class GuestHomeControl implements ActionListener {
    private GuestHomeView view;
    public GuestHomeControl(GuestHomeView guestHomeView) {
        this.view = guestHomeView;
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.membershipButton){
            if(!view.photopath.isEmpty() || !view.email.isEmpty()){
                int opt = JOptionPane.showConfirmDialog(view, "You're Already a Member! \nAdd Another User?","Change Membership?", JOptionPane.YES_NO_OPTION);
                if(opt == JOptionPane.YES_OPTION){
                    view.dispose();
                    MEMBERSHIPINFOFRAME membershipinfoframe = new MEMBERSHIPINFOFRAME(view.model, view.con, view.email, view.photopath);
                    membershipinfoframe.setVisible(true);
                }
            }
            else{
                view.dispose();
                MEMBERSHIPINFOFRAME membershipinfoframe = new MEMBERSHIPINFOFRAME(view.model, view.con, view.email, view.photopath);
                membershipinfoframe.setVisible(true);
            }
        }
        if(e.getSource() == view.loginButton){
            if(!view.photopath.isEmpty()){
                int opt = JOptionPane.showConfirmDialog(view, "Are You Sure You Want to Log Out?", "Log Out?", JOptionPane.YES_NO_OPTION);
                if(opt == JOptionPane.YES_OPTION){
                    view.dispose();
                    AreYouMemberView areYouMemberView = new AreYouMemberView(view.model, view.con);
                    areYouMemberView.setVisible(true);
                }
            }
            else {
                view.dispose();
                AreYouMemberView areYouMemberView = new AreYouMemberView(view.model, view.con);
                areYouMemberView.setVisible(true);
            }
        }
        if(e.getSource() == view.galleryButton){
            view.dispose();
            GalleryView galleryView = new GalleryView(view.model, view.con, view.email, view.photopath);
            galleryView.setVisible(true);
        }
        if(e.getSource() == view.contactButton){
            view.dispose();
            ContactView contactView = new ContactView(view.model, view.con, view.email, view.photopath);
            contactView.setVisible(true);
        }
        if(e.getSource() == view.roomsButton){
            view.dispose();
            try {
                RoomView roomView = new RoomView(view.model, view.con, view.email, view.photopath);
                roomView.setVisible(true);
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == view.serviceButton){
            view.dispose();
            try {
                ServiceScrollView service = new ServiceScrollView(view.model, view.con, view.email, view.photopath);
                service.setVisible(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == view.bookButton){
            view.dispose();
            try {
                BookRoomView bookRoomView = new BookRoomView(view.model, view.con, view.email, view.photopath);
                bookRoomView.setVisible(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
