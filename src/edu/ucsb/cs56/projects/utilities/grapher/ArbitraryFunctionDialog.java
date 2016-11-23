package edu.ucsb.cs56.projects.utilities.grapher;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
   <p>A widget that the user types into to create an arbitrary function. It acts as the middleman between user input and creating a new function.
   <p>The widget contains the text fields to input numbers into and displays a JPanel with and input area and button options.
   <p>The widget creates and returns a new arbitrary function when the form is submitted.

   @author Jenny So
   @version CS56, W14, Project
*/

public class ArbitraryFunctionDialog {
    private JTextField textField;
    private JLabel syntaxError;
    /** 
	Construct from the desired length of polynomial
	@param length is the desired length of the polynomial
    */
    public ArbitraryFunctionDialog() {
	syntaxError=new JLabel("");
	// Fill with text field for user input
	textField = new JTextField("0");

    }

    /**
        Displays the dialog and returns if "OK" has been clicked.
    */
    public boolean display(){

	// Create the panel
	JPanel panel = new JPanel(new GridLayout(0,1));

	// Show labels and text fields
	panel.add(new JLabel("Input function:"));
	panel.add(syntaxError);
	panel.add(textField);

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
    public void addSyntaxError(){
	syntaxError.setText("Syntax Error");
    }
    public String getText(){
	return textField.getText();
    }
}
