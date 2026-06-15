import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MEMBERSHIPINFOFRAMECONTROL implements ActionListener {
    private MEMBERSHIPINFOFRAME frame;
    MEMBERSHIPINFOFRAMECONTROL(MEMBERSHIPINFOFRAME frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frame.backbutton){
            frame.dispose();
            GuestHomeView guestHomeView = new GuestHomeView(frame.model, frame.con, frame.email, frame.photopath);
            guestHomeView.setVisible(true);
        }
        if(e.getSource() == frame.toRegisframeButton){
            frame.dispose();
            MEMBERSHIPREGIS membershipregis = new MEMBERSHIPREGIS(frame.model, frame.con, frame.email, frame.photopath);
            membershipregis.setVisible(true);
        }
    }
}
