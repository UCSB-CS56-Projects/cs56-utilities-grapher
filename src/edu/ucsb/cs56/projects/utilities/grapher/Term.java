package edu.ucsb.cs56.projects.utilities.grapher;
import java.util.*;
import javax.script.*;
import java.lang.Character;

/** 
    A class that represents a term in a function expression.
    @author Jenny So
    @version CS56, W14, Project
*/
public class Term extends ArrayList<FunctionR1R1> implements FunctionR1R1{

    public int sign; //-1 or 1
    public String exp; //string version of term

    /**
       Constructs from a String representing the term.
       @param input the term in written form
    */
    Term(String input){
	//super(1);

	this.sign = 1;
	this.exp = input;

	int i = 0;
	    
	/* How do we deal with paranthesis terms?
	   if(input.substr(i,1).equals("(")){
	   type = "p";
	   i = 1;
	   }
	*/
	
	// Iterate through input string
	while(i < input.length()) {

	    // If a digit, we add a polynomial
	    if(input.charAt(i) == 'x' || Character.isDigit(input.charAt(i))) {

		String poly = "";

		// Build string to pass to constructor		
		while(input.charAt(i)=='x' || input.charAt(i) == '^' || Character.isDigit(input.charAt(i))) {
		    poly += input.charAt(i);
		    i++;
		    
		    // If we reach the end of the term, stop parsing
		    if(i >= input.length())
			break;
		}
		
		// Add a new CustomQuadraticFunction to the end of the list
		this.add(new CustomQuadraticFunction(poly));
	    }

	    // Cosine function
	    else if(i+3 < input.length() && input.substring(i,i+3).equals("cos")) {
		this.add(new CosineFunction());
		i += 6;
	    }

	    // Sine function
	    else if(i+3 < input.length() && input.substring(i,i+3).equals("sin")) {
		this.add(new SineFunction());
		i += 6;
	    }

	    // If there is a multiplication sign, we need to add another function to the list
	    else if(input.substring(i,i+1).equals("*"))
		i++;

	}
    }

    /**
       Changes the sign of the term to negative
    */
    public void toNegative(){
	this.sign = -1;
    }

    /**
       Evaluates the function at an independent variable.
       The term runs through a collection of functions that are multiplied together.

       @param indVar the independent variable
       @return the value of the term
    */
    public double evaluate(double indVar) {
	// Multiply each function in term list together
	double product = 1;

	for(int i =0; i<this.size(); i++)
	    product *= this.get(i).evaluate(indVar);

	// Set sign
	product *= sign;
	return product;
    }

    /**
       Check if a number is within the domain of this function.
       In this case, any number is within the domain of the function.
       @param arg the value to check
       @return true if the value is within the domain of the function.
     */
    public boolean isInDomain(double arg) {
	return true;
    }

    /**
       Returns the function in it's written form.
       @return the written form of the term
    */
    public String toString() {
	return exp;
    }
}

