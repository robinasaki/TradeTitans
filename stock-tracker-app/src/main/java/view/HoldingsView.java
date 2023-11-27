package view;

import interface_adapter.holdings.HoldingsViewModel;
import interface_adapter.holdings.HoldingsState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HoldingsView extends JFrame {

    public final String viewName = "holdings";
    private final HoldingsViewModel holdingsViewModel;

    public HoldingsView(HoldingsViewModel holdingsViewModel) {
        this.holdingsViewModel = holdingsViewModel;

    }
}

