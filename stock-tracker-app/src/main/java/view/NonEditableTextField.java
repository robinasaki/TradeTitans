package view;

import javax.swing.*;
import javax.swing.text.Document;

public class NonEditableTextField extends JTextField {
    public NonEditableTextField() {
        super();
    }

    public NonEditableTextField(String text) {
        super(text);
    }

    public NonEditableTextField(int columns) {
        super(null, null, columns);
    }

    public NonEditableTextField(String text, int columns) {
        super(null, text, columns);
    }

    public NonEditableTextField(Document doc, String text, int columns) {
        super(doc, text, columns);
    }

    @Override
    public boolean isEditable() {
        return false;
    }
}
