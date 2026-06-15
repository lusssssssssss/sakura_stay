package controller;

import model.SqlModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.FilteredImageSource;

public class ServiceController {
    private ServiceView view;
    private SqlModel model;

    public ServiceController(SqlModel model, ServiceView view) {
        this.model = model;
        this.view = view;

        String status = model.checkServiceStatus("Breakfast");
        if(status.equals("0")){
            view.description.setText("AVAILABLE");
        }
        else{
            view.description.setText("NOT AVAILABLE");
            ImageIcon icon = (ImageIcon) view.photo.getIcon();
            Image grayImage = Toolkit.getDefaultToolkit().createImage(
                    new FilteredImageSource(icon.getImage().getSource(),
                            new GrayFilter(true, 50))
            ).getScaledInstance(850, 300, Image.SCALE_SMOOTH);
            ImageIcon tidaktersedia = new ImageIcon(grayImage);
            view.photo.setIcon(tidaktersedia);
        }

        view.serviceList.addActionListener(e -> {
            String selectedService = view.serviceList.getSelectedItem().toString();
            if(selectedService.equals("Breakfast")){
                String status1 = model.checkServiceStatus(selectedService);
                if(status1.equals("0")){
                    ImageIcon image = new ImageIcon("picture/japanese breakfast.png");
                    Image editImage = image.getImage().getScaledInstance(850, 300, Image.SCALE_SMOOTH);
                    ImageIcon scaledImage = new ImageIcon(editImage);
                    view.photo.setIcon(scaledImage);
                    view.description.setText("AVAILABLE");
                }
                else{
                    view.description.setText("NOT AVAILABLE");
                    ImageIcon icon =  new ImageIcon("picture/japanese breakfast.png");
                    Image grayImage = Toolkit.getDefaultToolkit().createImage(
                            new FilteredImageSource(icon.getImage().getSource(),
                                    new GrayFilter(true, 50))
                    ).getScaledInstance(850, 300, Image.SCALE_SMOOTH);
                    ImageIcon tidaktersedia = new ImageIcon(grayImage);
                    view.photo.setIcon(tidaktersedia);
                }
            }
            else if(selectedService.equals("Extra Bed")){
                String status1 = model.checkServiceStatus(selectedService);
                if(status1.equals("0")){
                    ImageIcon image = new ImageIcon("picture/extra bed.jpg");
                    Image editImage = image.getImage().getScaledInstance(850, 300, Image.SCALE_SMOOTH);
                    ImageIcon scaledImage = new ImageIcon(editImage);
                    view.photo.setIcon(scaledImage);
                    view.description.setText("AVAILABLE");
                }
                else{
                    view.description.setText("NOT AVAILABLE");
                    ImageIcon icon =  new ImageIcon("picture/extra bed.jpg");
                    Image grayImage = Toolkit.getDefaultToolkit().createImage(
                            new FilteredImageSource(icon.getImage().getSource(),
                                    new GrayFilter(true, 50))
                    ).getScaledInstance(850, 300, Image.SCALE_SMOOTH);
                    ImageIcon tidaktersedia = new ImageIcon(grayImage);
                    view.photo.setIcon(tidaktersedia);
                }
            }
            else if(selectedService.equals("Baby Cot")){
                String status1 = model.checkServiceStatus(selectedService);
                if(status1.equals("0")){
                    ImageIcon image = new ImageIcon("picture/baby cot.jpg");
                    Image editImage = image.getImage().getScaledInstance(850, 300, Image.SCALE_SMOOTH);
                    ImageIcon scaledImage = new ImageIcon(editImage);
                    view.photo.setIcon(scaledImage);
                    view.description.setText("AVAILABLE");
                }
                else{
                    view.description.setText("NOT AVAILABLE");
                    ImageIcon icon =  new ImageIcon("picture/baby cot.jpg");
                    Image grayImage = Toolkit.getDefaultToolkit().createImage(
                            new FilteredImageSource(icon.getImage().getSource(),
                                    new GrayFilter(true, 50))
                    ).getScaledInstance(850, 300, Image.SCALE_SMOOTH);
                    ImageIcon tidaktersedia = new ImageIcon(grayImage);
                    view.photo.setIcon(tidaktersedia);
                }
            }
            else if(selectedService.equals("Yukata")){
                ImageIcon image = new ImageIcon("picture/yukata.png");
                Image editImage = image.getImage().getScaledInstance(850, 300, Image.SCALE_SMOOTH);
                ImageIcon scaledImage = new ImageIcon(editImage);
                view.photo.setIcon(scaledImage);
                int amount = model.checkAmount(selectedService,0);
                if(amount == 0){
                    view.description.setText("OUT OF STOCK");
                    ImageIcon icon =  new ImageIcon("picture/yukata.png");
                    Image grayImage = Toolkit.getDefaultToolkit().createImage(
                            new FilteredImageSource(icon.getImage().getSource(),
                                    new GrayFilter(true, 50))
                    ).getScaledInstance(850, 300, Image.SCALE_SMOOTH);
                    ImageIcon tidaktersedia = new ImageIcon(grayImage);
                    view.photo.setIcon(tidaktersedia);
                }
                else{
                    view.description.setText("AVAILABLE: " + amount);
                }
            }
            else if(selectedService.equals("Photobooth")){
                ImageIcon image = new ImageIcon("picture/photo session.png");
                Image editImage = image.getImage().getScaledInstance(850, 300, Image.SCALE_SMOOTH);
                ImageIcon scaledImage = new ImageIcon(editImage);
                view.photo.setIcon(scaledImage);
                int amount = model.checkAmount(selectedService,0);
                if(amount == 0){
                    view.description.setText("OUT OF STOCK");
                    ImageIcon icon =  new ImageIcon("picture/photo session.png");
                    Image grayImage = Toolkit.getDefaultToolkit().createImage(
                            new FilteredImageSource(icon.getImage().getSource(),
                                    new GrayFilter(true, 50))
                    ).getScaledInstance(850, 300, Image.SCALE_SMOOTH);
                    ImageIcon tidaktersedia = new ImageIcon(grayImage);
                    view.photo.setIcon(tidaktersedia);
                }
                else{
                    view.description.setText("AVAILABLE: " + amount);
                }
            }
        });

        view.backButton.addActionListener(e -> {
            AdminHomeView adminHomeView = new AdminHomeView();
            new AdminHomeController(model, adminHomeView);
            adminHomeView.setVisible(true);
            view.dispose();
        });

        view.returnButton.addActionListener(e -> {
            ReturnServiceView returnServiceView = new ReturnServiceView();
            new ReturnServiceController(model, returnServiceView);
            returnServiceView.setVisible(true);
            view.dispose();
        });

        view.editButton.addActionListener(e -> {
            EditServiceView editServiceView = new EditServiceView();
            new EditServiceController(model, editServiceView);
            editServiceView.setVisible(true);
            view.dispose();
        });


        view.historyButton.addActionListener(e -> {
            ServiceHistoryView serviceHistoryView = new ServiceHistoryView();
            new ServiceHistoryController(model, serviceHistoryView);
            serviceHistoryView.setVisible(true);
            view.dispose();
        });
    }
}