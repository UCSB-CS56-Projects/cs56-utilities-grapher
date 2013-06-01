package edu.ucsb.cs56.projects.utilities.grapher;
/**
   A class that represents an arbitrary function that is given by a String.
   @author Ryan Halbrook
   @version CS56, S13, Project
 */
public class ArbitraryFunctionR1R1 implements FunctionR1R1 {

    String exp;
    char var;

    private static char[] numerals = {'0','1','2','3','4','5','6','7','8','9'};

    /**
       Creates a new object based on the given expression.
       @param expression the expression to evaluate the function with.
       @param indVar the independent variable in the expression (i.e. 'x').
     */
    public ArbitraryFunctionR1R1(String expression, char indVar) {
	exp = expression;
	var = indVar;
    }

    public double evaluate(double indVar) {
	// Base case
	
	// Smaller case
	
	
	// General case
	
	
	return -1; // STUB
    }

    private boolean isNumber(String s) {
	boolean decimalFound = false;
	for (int i = 0; i < s.length(); i++) {
	    boolean isNumeral = false;
	    boolean isNegativeSign = false;

	    if (s.charAt(i) == '-') isNegativeSign = true;
	    if (s.charAt(i) == '.') {
		if (decimalFound == true) return false;
		decimalFound = true;
	    }
	    for (int j = 0; j < 10; j++) {
		if (s.charAt(i) == numerals[j]) isNumeral = true;
	    }
	    if (isNumeral == false) {
		if (i == 0 && isNegativeSign) continue;
		return false;
	    }
	}
	return true;
    }

    public boolean isInDomain(double arg) {
	return true;
    }

}
