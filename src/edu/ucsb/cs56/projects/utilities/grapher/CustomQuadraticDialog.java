package edu.ucsb.cs56.projects.utilities.grapher;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

class CustomQuadraticDialog {
    private JTextField[] textFields;

    public CustomQuadraticDialog(int length) {

	// Fill with text fields for user input
	textFields = new JTextField[length];
	for(int i = 0; i<length; i++){
	    // Default value is zero
	    textFields[i] = new JTextField("0");
	}
    }

    public boolean display(){

	// Create the panel
	JPanel panel = new JPanel(new GridLayout(0,1));

	// Show labels and text fields (x^i from largest power to 0)
	for(int i = (textFields.length)-1; i>=0; i--){
	    // x^i
	    panel.add(new JLabel("x^"+i));

	    // Text placed from lowest to highest
	    panel.add(textFields[i]);
	}

	// Reaction to button pressing
	int result = JOptionPane.showConfirmDialog(null,
						   panel,
						   "Test",
						   JOptionPane.OK_CANCEL_OPTION,
						   JOptionPane.PLAIN_MESSAGE);
	// Reaction to "OK" button press
	if(result == JOptionPane.OK_OPTION)
	    return true;
	else
	    return false;
    }

    public CustomQuadraticFunction inputCoeffs(){
	int[] intFields = new int[textFields.length];

	// Convert to integer values
	for(int i = 0; i<textFields.length; i++){
	    intFields[i] = java.lang.Integer.parseInt(textFields[i].getText());
	}

        return(new CustomQuadraticFunction(intFields));
    }
}