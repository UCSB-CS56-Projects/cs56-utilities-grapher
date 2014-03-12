package edu.ucsb.cs56.projects.utilities.grapher;
import java.util.regex.*;
import java.util.ArrayList;
/**
   A class that wraps up a custom quadratic function as an implementer of
   the FunctionR1R1 interface. Quadratic takes coefficients from input.
   @author Jenny So
   @author Phill Conrad (wrote constructor that takes String parameter)
   @version CS56, Winter 2014, Project
 */
public class CustomQuadraticFunction extends ArrayList<Integer> implements FunctionR1R1 {

    public static boolean debug = false;
    /** 
	Constructs from int array of coefficients. 
	The coefficients are listed in order from lowest to highest degree

	@param coeffsHighToLow array of coefficients from lowest to highest degree
    */
    public CustomQuadraticFunction(int [] coeffsLowToHigh){
	int j = 0;

	// Skip any trailing zeros after the highest power
	for(j = coeffsLowToHigh.length-1; j>0; j--){
	    if(coeffsLowToHigh[j] != 0)
		break;
	}
	// Take coeffs from low to high
	for(int i = 0; i < j+1; i++)
	    this.add(coeffsLowToHigh[i]);
    }
    
    /**
       Constructs from a String expression. 
       Taken from Lab03 W14.

       @param s String version of quadratic
    */
    public CustomQuadraticFunction(String s) {
	// invoke superclass constructor, i.e. the
	// constructor of ArrayList<Integer> with 

	super(1); // we want capacity at least 1 

	if (debug) {System.out.println("In Polynomial(String s), s=" + s);}

	// For information on regular expressions in Java,
	// see http://docs.oracle.com/javase/tutorial/essential/regex

	// First check for special case of only digits,
	// with possibly a - in front
	// i.e. a degree 0 polynomial that is just an integer constant

	Pattern integerConstantPattern = 
            Pattern.compile("^-?\\d+$");
	Matcher integerConstantMatcher = integerConstantPattern.matcher(s);
	
	// if that pattern matches, then the whole string is just
	// an integer constant.  So we can safely call Integer.parseInt(s)
	// to convert to int, and add in this parameter.

	if (integerConstantMatcher.matches()) {
	    this.add(0,Integer.parseInt(s)); 
	    return; // we are done!
	}

	// now, try for polynomials of degree 1

	Pattern degreeOnePattern = 
            Pattern.compile("^(-?)(\\d*)x( ([+-]) (\\d+))?$");
	// Explanation: 
	// start/end         ^                            $
	// sign for x term    (-?)                            group(1)
	// coeff for x term       (\\d*)                      group(2)
	// x in x term                  x
	// optional constant part        (               )?   group(3)
	// sign for constant                ([+-])            group(4)
	// coeff for constant                      (\\d+)     group(5)

	Matcher degreeOneMatcher = degreeOnePattern.matcher(s);

	if (degreeOneMatcher.matches()) {
	    
	    int xCoeff = 1;
	    int constantTerm = 0;

	    String xCoeffSign = degreeOneMatcher.group(1);
	    String xCoeffString = degreeOneMatcher.group(2);
	    String constantTermSign = degreeOneMatcher.group(4);
	    String constantTermString = degreeOneMatcher.group(5);

	    if (xCoeffString != null && !xCoeffString.equals("")) {
		xCoeff = Integer.parseInt(xCoeffString);
	    }

	    if (xCoeffSign != null && xCoeffSign.equals("-")) {
		xCoeff *= -1;
	    }

	    if (constantTermString != null && !constantTermString.equals("")) {
		constantTerm = Integer.parseInt(constantTermString);
	    }

	    if (constantTermSign != null && constantTermSign.equals("-")) {
		constantTerm *= -1;
	    }

	    this.add(0,constantTerm); 
	    this.add(1,xCoeff); 
	    return;
	}

	// then try for higher degree

	String twoOrMoreRe = 
	    "^" // start of the string 
	    + "([-]?)(\\d*)x\\^(\\d+)" // first x^d term, groups 1,2,3
	    + "(( [+-] \\d*x\\^\\d+)*)" // zero or more x^k terms group 4 (and 5)
	    + "( [+-] \\d*x)?" // optional x term (group 6)
	    + "( [+-] \\d+)?" // optional constant term (group 7)
	    + "$"; // the end of the string

	Pattern degreeTwoOrMorePattern  = Pattern.compile(twoOrMoreRe);
	Matcher degreeTwoOrMoreMatcher = degreeTwoOrMorePattern.matcher(s);

	// if we have a match...
	if (degreeTwoOrMoreMatcher.matches()) {
	    
	    int firstCoeff = 1;
	    String startSign      = degreeTwoOrMoreMatcher.group(1);
	    String coeffString    = degreeTwoOrMoreMatcher.group(2);
	    String degreeString   = degreeTwoOrMoreMatcher.group(3);
	    String middleXtoTheTerms = degreeTwoOrMoreMatcher.group(4);
	    String optionalXTermPart = degreeTwoOrMoreMatcher.group(6);
	    String optionalConstantTermPart = degreeTwoOrMoreMatcher.group(7);

	    if (coeffString != null && !coeffString.equals("")) {
		firstCoeff = Integer.parseInt(coeffString);
	    }

	    if (startSign != null && startSign.equals("-")) {
		firstCoeff *= -1;
	    }
	    
	    int degree = Integer.parseInt(degreeString);

	    this.ensureCapacity(degree+1); // method of ArrayList<Integer>
	    for(int i=0; i<=degree; i++) // initialize all to zero
		this.add(0,0);

	    this.set(degree,firstCoeff);

	    if (middleXtoTheTerms!=null && !middleXtoTheTerms.equals("")) {
		    
		Pattern addlXtoThePowerTermPattern  = 
		    Pattern.compile(" ([+-]) (\\d+)(x\\^)(\\d+)");
		Matcher addlXtoThePowerTermMatcher 
		    = addlXtoThePowerTermPattern.matcher(middleXtoTheTerms);

		while (addlXtoThePowerTermMatcher.find()) {
		    
		    int coeff = 1;
		    String sign           = addlXtoThePowerTermMatcher.group(1);
		    String nextCoeffString    = addlXtoThePowerTermMatcher.group(2);
		    String nextDegreeString   = addlXtoThePowerTermMatcher.group(4);
		    
		    if (nextCoeffString != null && !nextCoeffString.equals("")) {
			coeff = Integer.parseInt(nextCoeffString);
		    }

		    if (sign != null && sign.equals("-")) {
			coeff *= -1;
		    }
		    
		    this.set(Integer.parseInt(nextDegreeString),coeff);
		    
		}
	    } // if middleXToTheTerms has something
	    
	    // Now all that is left is, possibly, an x term and a constant
	    // term.    We need to select them out if they are there.
	    
	    if (optionalXTermPart != null && !optionalXTermPart.equals("")) {

		if (debug) {System.out.println("optionalXTermPart=" +
					       optionalXTermPart);}

		Pattern optXTermPattern = 
		    Pattern.compile("^ ([+-]) (\\d*)x$");
		Matcher optXTermMatcher = optXTermPattern.matcher(optionalXTermPart);
		optXTermMatcher.find();
	    
		int xCoeff = 1;
		int constantTerm = 0;
		String xCoeffSign = optXTermMatcher.group(1);
		String xCoeffString = optXTermMatcher.group(2);
		
		if (xCoeffString != null && !xCoeffString.equals("")) {
		    xCoeff = Integer.parseInt(xCoeffString);
		}
		
		if (xCoeffSign != null && xCoeffSign.equals("-")) {
		    xCoeff *= -1;
		}
		
		this.set(1,xCoeff); 
	    } // optionalXTerm part

	    if (optionalConstantTermPart != null 
		&& !optionalConstantTermPart.equals("")) {

		Pattern constantTermPattern = 
		    Pattern.compile("^ ([+-]) (\\d+)$");
		Matcher constantTermMatcher 
		    = constantTermPattern.matcher(optionalConstantTermPart);
		
		constantTermMatcher.find();

		int constant = 0;
		String sign = constantTermMatcher.group(1);
		String constantString = constantTermMatcher.group(2);

		if (constantString != null && !constantString.equals("")) {
		    constant = Integer.parseInt(constantString);
		}

		if (sign!=null && sign.equals("-")) {
		    constant *= -1;
		}
		
		this.set(0,constant); 
	    } // a constant term is present

	    return;
	} // degreeTwoOrMore
	
	if (debug) {System.out.println("at bottom");}

	// in the end, if we don't find what we need,
	// through an exception

	throw new IllegalArgumentException("Bad Polynomial String: [" + s + "]");

    }

    /**
       Evaluate the function at an independent variable.
       The solution depends on the custom function defined by the user.

       @param indVar the independent variable.
       @return the value of the function.
     */
    public double evaluate(double indVar) {
	double solution = 0.0;
	double product = 1.0; //because x^0 = 1

	// For each coefficient
        for(int i = 0; i<this.size(); i++){

	    // Evaluate x^i
	    for(int j = 1; j<=i; j++){
		product *= indVar;
	    }

	    // Evaluate coefficient times x^i
	    solution += product*this.get(i);

	    // Reset product to 1
	    product = 1.0;
	}
	
	return solution;
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

    public String toString() {
        String poly = "0";
	// Single constant
	if(this.size() == 1){
	    poly = Integer.toString(this.get(0));
	    return "f(x)="+poly;
	}

	// Degree 1
	if(this.size() == 2){
	    if(this.get(1) == 1)
		poly = "x";
	    else if(this.get(1) == -1)
		poly = "-x";
	    else if(this.get(1) > 0)
		poly = this.get(1) + "x";

	    if(this.get(0) > 0)
		poly += " + " + this.get(0);
	    if(this.get(0) < 0)
		poly += " - " + (-1)*this.get(0);

	    return "f(x)="+poly;
	}

	// Configure initial term
	if(this.get(this.size() -1) == 1){
	    poly = "x^" + (this.size()-1);
	}
	else if(this.get(this.size() -1) == -1){
	    poly = "-x^" + (this.size()-1);
	}
	else if(this.get(this.size()-1) != 0){
	    poly = this.get(this.size()-1) + "x^" + (this.size()-1);
	}

	// Run through things without "^" character
	for(int i = (this.size() -2); i>=0; i--){
	    // Skip any 0 terms
	    if(this.get(i) == 0){
		continue;
	    }
	    // For those that needs "^" symbol
	    if(i > 1){
		if(this.get(i) == 1)
		    poly += (" + x^" + i);
		else if(this.get(i) > 0)
		    poly += (" + " + this.get(i) + "x^" + i);
		else if(this.get(i) < 0)
		    poly += (" - " + (-1)*this.get(i) + "x^" + i);
	    }
	    // For a constant
	    if(i == 0){
		if(this.get(i) > 0)
		    poly += (" + " + this.get(i));
		else if(this.get(i) < 0)
		    poly += (" - " + (-1)*this.get(i));
	    }
	    // For which no "^" symbol is needed
	    if(i == 1){
		if(this.get(i) == 1)
		    poly += (" + x");
		else if(this.get(i) > 0)
		    poly += (" + " + this.get(i) + "x");
		else if(this.get(i) < 0)
		    poly += (" - " + (-1)*this.get(i) + "x");	
	    }
	}
	return "f(x)="+poly; // Return full string
    }
}
