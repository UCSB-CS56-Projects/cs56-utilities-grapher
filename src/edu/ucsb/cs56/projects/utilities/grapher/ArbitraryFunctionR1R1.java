package edu.ucsb.cs56.projects.utilities.grapher;
import java.util.*;
import java.util.regex.*;
/**
   A class that represents an arbitrary function that is given by a String.
   @author Ryan Halbrook
   @version CS56, S13, Project
 */
public class ArbitraryFunctionR1R1 implements FunctionR1R1 {
    String exp;
    char var;

    private static char[] numerals = {'0','1','2','3','4','5','6','7','8','9'};
    private static char[] binOps = {'+', '-', '*', '^'};
    
    /**
       Creates a new object based on the given expression.
       @param expression the expression to evaluate the function with.
       @param indVar the independent variable in the expression (i.e. 'x').
     */
    public ArbitraryFunctionR1R1(String expression, char indVar) {
	exp = expression;
	var = indVar;
    }

    public static String removeWhitespace(String str) {
	str = str.trim();
	char[] s = str.toCharArray();
	int removed = 0;
	for (int i = 0; i < str.length(); i++) {
	    if (s[i] == ' ') {
		removed++;
		s[i] = s[i+1];
	    }
	}
	return new String(s, 0, str.length() - removed);
    }
    
    private boolean containsSubexpressions(String str) {
	Pattern left  = Pattern.compile("()");
	Pattern right = Pattern.compile("()");
	Matcher lm = left.matcher(str);
	Matcher rm = right.matcher(str);
	if (lm.matches() || rm.matches()) return true;
	return false;
	
    }

    public double evaluate(double indVar) {
	exp = removeWhitespace(exp);
	return evaluate(indVar, exp);
    }

    private double evaluate(double indVar, String exp) {
	if (isNumber(exp)) return Double.parseDouble(exp);
	if (!containsSubexpressions(exp)) {
	    for (int i = 0; i < exp.length(); i++) {
		
	    }
	}
	// Find subexpressions and evaluate them.
	
	int start = -1;
	int end = -1;
	for (int i = 0; i < exp.length(); i++) {
	    char c = exp.charAt(i);
	    if (c == '(') {
		start = i;
	    } else if (c == ')') {
		end = i;
		
	    }
	}
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
