package app;

import interface_adapter.DepositViewModel;
import interface_adapter.ViewManagerModel;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Trade Titans");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        DepositViewModel depositViewModel = new DepositViewModel();
        // TODO: add more ViewModels here

        // TODO: add more Views, like DepositView, etc.
        application.pack();
        application.setVisible(true);
    }
}
