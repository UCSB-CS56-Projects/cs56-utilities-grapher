package edu.ucsb.cs56.projects.utilities.grapher;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
A widget that holds a list of functions.
@author Ryan Halbrook
@author Jenny So
@version CS56, W14, Project
*/
public class FunctionsPanel extends JPanel{
    private FunctionR1R1DisplayDataList list;
    public JTable t = null;//public so we can update the table

    public FunctionsPanel(FunctionR1R1DisplayDataList list) {
	super();
	setBackground(Color.BLACK);
	this.list = list;

	t = new JTable(new TableData(list));
	TableColumn c = null;
	for (int i = 0; i < t.getColumnCount(); i++) {
	    c = t.getColumnModel().getColumn(i);
	    if (i==0) c.setPreferredWidth(10);
	    c.setPreferredWidth(10);
	}
	//this.setPreferredSize(new Dimension(230, 400));
	//t.setBackground(Color.GRAY);
	JScrollPane sp = new JScrollPane(t);
	sp.setPreferredSize(new Dimension(200,400));
	this.add(sp);
    }
    /** 
        Adds a row in the table display.
    */
    public void displayNewRow() {
	// getModel returns as TableModel, so cast as TableData
	TableData dataModel = (TableData)t.getModel();

	// Notify the data model that a row has been inserted
	dataModel.fireTableRowsInserted(list.size()-1, list.size()-1);
    }

    public class TableData extends AbstractTableModel {
	private FunctionR1R1DisplayDataList list;

	public TableData(FunctionR1R1DisplayDataList list) {
	    this.list = list;
	}
	public int getColumnCount() { return 2; }
	public int getRowCount() { return list.size(); }
	public Object getValueAt(int row, int col) {
	    if (col == 0) {
		int num = row+1;
		return new String("f" + num);
	    } else if (col == 1) {
		return list.get(row).getFunction().toString();
	    }
	    return new Integer(row);
	}
	
    }
}
