import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PAYMENTKAMARCONTROL implements ActionListener {
    private PAYMENTKAMAR view;
    PAYMENTKAMARCONTROL(PAYMENTKAMAR view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String voucher = "";
        if(view.vouchers.getSelectedItem() == null){
            voucher = "";
        }
        else{
            voucher = view.vouchers.getSelectedItem().toString();
        }
        if(e.getSource() == view.vouchers){
            if(voucher.equals("")){
                view.price.setForeground(Color.BLACK);
                Locale indo = new Locale("in", "ID");
                NumberFormat formatter = NumberFormat.getCurrencyInstance(indo);
                String hasil = formatter.format(view.totalprice);
                view.price.setText(hasil);
                return;
            }
            try {
                String membershiptype = view.model.getmembertype(view.con, view.guestemail);
                if (membershiptype.equalsIgnoreCase("B")) {
                    String pricebefore = String.valueOf(view.totalprice);
                    double price = Double.parseDouble(pricebefore);
                    double diskon = 0.05*price;
                    int finalprice = (int) (price-diskon);
                    Locale indo = new Locale("in", "ID");
                    NumberFormat formatter = NumberFormat.getCurrencyInstance(indo);
                    String hasil = formatter.format(finalprice);
                    view.price.setText(hasil);
                    view.price.setForeground(Color.RED);
                }
                else if(membershiptype.equalsIgnoreCase("S")) {
                    String pricebefore = String.valueOf(view.totalprice);
                    double price = Double.parseDouble(pricebefore);
                    double diskon = 0.1*price;
                    int finalprice = (int) (price-diskon);
                    Locale indo = new Locale("in", "ID");
                    NumberFormat formatter = NumberFormat.getCurrencyInstance(indo);
                    String hasil = formatter.format(finalprice);
                    view.price.setText(hasil);
                    view.price.setForeground(Color.RED);
                }
                else if(membershiptype.equalsIgnoreCase("G")) {
                    String pricebefore = String.valueOf(view.totalprice);
                    double price = Double.parseDouble(pricebefore);
                    double diskon = 0.15*price;
                    int finalprice = (int) (price-diskon);
                    Locale indo = new Locale("in", "ID");
                    NumberFormat formatter = NumberFormat.getCurrencyInstance(indo);
                    String hasil = formatter.format(finalprice);
                    view.price.setText(hasil);
                    view.price.setForeground(Color.RED);
                }
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        if(e.getSource() == view.payment){
            StringBuilder idbaru = new StringBuilder();
            if(view.selectedpayment.getIcon() == null){
                JOptionPane.showMessageDialog(view, "Please select a Payment Method", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                ArrayList<String> existingguest = view.model.getguests(view.con);
                boolean found = false;
                for(String i : existingguest){
                    if(i.equalsIgnoreCase(view.guestemail)){
                        found = true;
                        break;
                    }
                }
                if(!found){
                    String guestnik = view.guestdata[0];
                    String guestname = view.guestdata[1];
                    String guestemail = view.guestdata[2];
                    String guestphone = view.guestdata[3];
                    ArrayList<String> ids = view.model.guestids(view.con);
                    int counter = 1;
                    String[] parts = guestname.trim().split("\\s+");

                    String hurufDepan = parts[0].substring(0, 1); // 'k'
                    String hurufBelakang = parts[parts.length - 1].substring(0, 1);

                    String inisial = (hurufDepan + hurufBelakang).toLowerCase();
                    idbaru = new StringBuilder();
                    for(String i : ids){
                        if(i.substring(0,2).toLowerCase().equals(inisial)){
                            counter++;
                        }
                    }
                    if(counter > 9){
                        idbaru.append(inisial.toUpperCase());
                        idbaru.append("0");
                        idbaru.append(counter);
                    }
                    else{
                        idbaru.append(inisial.toUpperCase());
                        idbaru.append("00");
                        idbaru.append(counter);
                    }
                    String membership = "0";
                    if(!view.photopath.isEmpty() || !view.guestemail.isEmpty()){
                        membership = "1";
                    }
                    view.model.insertguest(view.con, guestnik, idbaru.toString(),guestemail, guestname, guestphone,membership);
                }
                ArrayList<String> invoiceid = view.model.invoiceid(view.con);
                String lastinvoice = invoiceid.get(invoiceid.size()-1);
                String getinvoiceid = lastinvoice.substring(lastinvoice.indexOf("-")+1);
                int count = Integer.parseInt(getinvoiceid);
                count += 1;
                StringBuilder idinvoicebaru = new StringBuilder();
                if(count > 99){
                    idinvoicebaru.append("INV-");
                    idinvoicebaru.append(count);
                }
                else{
                    idinvoicebaru.append("INV-0");
                    idinvoicebaru.append(count);
                }
                String invid = idinvoicebaru.toString(); //1
                String guestnik = view.guestdata[0]; //2
                String guestemail = view.guestdata[2]; //4
                String guestid = ""; //3
                if(!found){
                    guestid = idbaru.toString();
                }
                else{
                    guestid = view.model.getid(view.con, guestemail);
                }
                String roomnumber = view.data[0]; //5
                String selectedpaymentmethod = view.paymentmethod; //6
                String checkin = view.data[8].substring(0, 10); // 7
                String checkout = view.data[8].substring(13, 23); // 8
                String jmlhmalam = view.data[9]; //9
                String usefas = ""; //10
                boolean using = false;
                for(int i = 0; i <view.extras.length; i++){
                    if(!view.extras[i].isEmpty()){
                        using = true;
                        break;
                    }
                }
                if(using){
                    usefas = "1";
                }
                else{
                    usefas = "0";
                }
                String total = view.price.getText();
                String cleaned = total.replace("Rp", "")
                        .replace(".", "")
                        .replace(",00", "")
                        .trim();
                int totalint = Integer.parseInt(cleaned);
                view.model.insertinvoice(view.con, invid,guestnik,guestid,guestemail,roomnumber,selectedpaymentmethod,checkin,checkout,jmlhmalam,usefas, String.valueOf(totalint));
                if(usefas.equals("1")){
                    String yukata = view.extras[3];
                    String photo = view.extras[4];
                    int jumlahyukata = 0;
                    int jumlahphoto = 0;
                    if(!yukata.isEmpty()){
                        jumlahyukata = Integer.parseInt(yukata.substring(1,2));
                    }
                    if(!photo.isEmpty()){
                        jumlahphoto = Integer.parseInt(photo.substring(1,2));
                    }
                    try{
                        view.model.updateservice(view.con, "SERVE-Y", jumlahyukata);
                        view.model.updateservice(view.con, "SERVE-P", jumlahphoto);
                    }
                    catch (Exception er){
                        er.printStackTrace();
                    }
                }
                if(!voucher.isEmpty()){
                    view.model.voucherdipakek(view.con, view.guestemail,voucher);
                }
                view.dispose();
                BookSuccessView bsv = new BookSuccessView(view.model, view.con, view.guestemail, view.photopath);
                bsv.setVisible(true);
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
