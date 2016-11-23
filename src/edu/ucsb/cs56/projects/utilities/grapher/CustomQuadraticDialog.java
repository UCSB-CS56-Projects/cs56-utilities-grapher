package edu.ucsb.cs56.projects.utilities.grapher;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
   <p>A widget that the user inputs coefficients into to create a custom quadratic function. It acts as the middleman between user input and creating a new function.
   <p>The widget contains the text fields to input numbers into and displays a JPanel with and input area and button options.
   <p>The widget creates and returns a new custom quadratic function when the form is submitted.

   @author Jenny So
   @version CS56, W14, Project
*/

public class CustomQuadraticDialog {
    private JTextField[] textFields;
    private JLabel syntaxError;
    /** 
	Construct from the desired length of polynomial
	@param length is the desired length of the polynomial
    */
    public CustomQuadraticDialog(int length) {
	syntaxError=new JLabel("");
	// Fill with text fields for user input
	textFields = new JTextField[length];
	for(int i = 0; i<length; i++){
	    // Default value is zero
	    textFields[i] = new JTextField("0");
	}
    }

    /**
        Displays the dialog and returns if "OK" has been clicked.
    */
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
	panel.add(syntaxError);
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

    /** 
	Creates a new custom quadratic function from the text fields of the widget and returns it
     */
    public CustomQuadraticFunction inputCoeffs(){
	int[] intFields = new int[textFields.length];
	// Convert to integer values
	for(int i = 0; i<textFields.length; i++){
	    intFields[i] = java.lang.Integer.parseInt(textFields[i].getText());
	}

        return(new CustomQuadraticFunction(intFields));
    }
    public String getText(){
	String result="";
	int lastNonZeroTerm=0;
	for(int i=0;i<textFields.length;i++){
		if(!(textFields[i].getText().equals("0")||textFields[i].getText().equals(""))){
			lastNonZeroTerm=i;
			break;
		}
	}
	for(int i=textFields.length-1;i>=0;i--){
	    if(textFields[i].getText().equals("0")||textFields[i].getText().equals(""))continue;
	    result+="(";
	    result+=textFields[i].getText();
	    result+=" ";
	    if(i!=0){
		result+="x";
		if(i!=1){
		    result+=" ^ ";
		    result+=i;
		}
	    }
	    result+=")";
	    if(i>lastNonZeroTerm){
		result+=" + ";
	    }else
		break;
	}
	if(result.equals(""))result="0";
	return result;

    }
    public void addSyntaxError(){
	syntaxError.setText("Syntax Error");
    }
}
