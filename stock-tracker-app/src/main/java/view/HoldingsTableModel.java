package view;

import javax.swing.table.DefaultTableModel;

public class HoldingsTableModel extends DefaultTableModel {

    public HoldingsTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    public HoldingsTableModel() {
        super();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Make all cells non-editable
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        // Do nothing or throw an UnsupportedOperationException
        // to prevent setting new values
    }
}
