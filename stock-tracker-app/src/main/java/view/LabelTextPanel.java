package view;

import javax.swing.*;

public class LabelTextPanel extends JPanel {
    LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }

    LabelTextPanel(JLabel label, JComboBox<String> textField) {
        this.add(label);
        this.add(textField);
    }

}
