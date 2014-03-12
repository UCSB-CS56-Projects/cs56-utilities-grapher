package edu.ucsb.cs56.projects.utilities.grapher;
import java.util.*;
import javax.script.*;
import java.lang.Character;
/**
   A class that represents an arbitrary function that is given by a String.
   @author Jenny So
   @version CS56, W14, Project
*/
public class ArbitraryFunction extends ArrayList<Term> implements FunctionR1R1 {
    char var;
    String exp;
    
    /**
       Creates a new object based on the given expression.

       @param expression the expression to evaluate the function with.
       @param indVar the independent variable in the expression (i.e. 'x').
    */
    public ArbitraryFunction(String expression, char indVar) {
	exp = removeWhitespace(expression);
	var = indVar;
	int k =0;
	//exp = expression;

	// Create array of terms disregarding signs
	String[] terms = exp.split("[-+]");

	// Add terms to Arraylist
	for(String s: terms)
	    this.add(new Term(s));
	
	// Split string by negative signs to find negative terms
	String[] n = exp.split("\\-");

	// Special Case: if the expression begins positive, then skip first index
	if(!(exp.charAt(0) == '-'))
	    k = 1;
	
	// Running through array
	for(int j=k; j<n.length; j++){

	    String negativeTerm = (n[j].split("\\+"))[0];

	    if(this.contains(new Term(negativeTerm))){

		// Find the term in the arraylist
		int index = this.indexOf(new Term(negativeTerm));
		
		// Turn to negative
		this.get(index).toNegative();
	    }
	    
	}
	
    }
    /**
       Removes whitespace from a string and returns it.
       @param str the String whose whitespace will be taken out.
    */
    public static String removeWhitespace(String str) {
	str = str.trim();
	String result = "";
	String[] parts = str.split(" ");
	for(String s: parts)
	    result += s;

	return result;
    }
    
    /**
       Evaluates the function at an independent variable.
       Returns the sum of the evaluation of each term.
    */
    public double evaluate(double indVar) {
        double sum = 0.0;
	for (int i = 0; i<this.size(); i++) {
	    sum += this.get(i).evaluate(indVar);
	}
	return sum;
    }
    
    public boolean isInDomain(double arg) {
	return true;
    }
    
    public String toString() {
        return "f(x)="+exp;
    }
      

}
