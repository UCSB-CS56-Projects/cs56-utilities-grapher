package edu.ucsb.cs56.projects.utilities.grapher;
import java.util.*;
import javax.script.*;
/**
   A class that represents an arbitrary function that is given by a String.
   @author Ryan Halbrook
   @version CS56, S13, Project
 */
public class ArbitraryFunctionR1R1 implements FunctionR1R1 {
    char var;
    String exp;
    
    HashMap<Double, Double> cache = new HashMap<Double, Double>();
    
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
    
    public double evaluate(double indVar) {
        System.out.println("Cached values: " + cache.size());
        if (cache.containsKey(indVar)) {
            System.out.println("Restoring from cache");
        }
        System.out.println("About to render");
        String value = "";
        try {
	    ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine eng = mgr.getEngineByName("JavaScript");
	    value = eng.eval(var + " = " + indVar + ", " + exp).toString();
        
	    //System.out.println("Value of expression: "+value);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        
        try {
            double result = Double.parseDouble(value);
            cache.put(indVar, result);
            return result;
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return -1;
    }
    
    public boolean isInDomain(double arg) {
	return true;
    }
    
    public String toString() {
        return exp;
    }

}
