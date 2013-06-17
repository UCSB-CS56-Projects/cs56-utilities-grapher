package edu.ucsb.cs56.projects.utilities.grapher;
import java.util.*;
import java.awt.event.*;

/**
@author Ryan Halbrook
@version CS56, Spring 13, Project
 */
public class FunctionR1R1DisplayDataList extends ArrayList<FunctionR1R1DisplayData> {
    //private ArrayList<FunctionR1R1DisplayData> fdds = new ArrayList<FunctionR1R1DisplayData>();
    private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();
    public void addActionListener(ActionListener listener) {
	listeners.add(listener);
    }
    public void sendEvents() {
	for (ActionListener l : listeners) {
	    l.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_FIRST, "Functions Changed"));
	}
    }
    public boolean add(FunctionR1R1DisplayData fdd) {
	super.add(fdd);
	sendEvents();
	return true;
    }
}
