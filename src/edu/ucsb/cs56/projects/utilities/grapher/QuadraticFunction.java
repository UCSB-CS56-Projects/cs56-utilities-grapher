package edu.ucsb.cs56.projects.utilities.grapher;
/**
   A class that wraps up a quadratic function as an implementer of
   the FunctionR1R1 interface.
   @author Ryan Halbrook
   @version CS56, Spring 2013, Project
 */
public class QuadraticFunction implements FunctionR1R1 {

    /**
       Evaluate the function
       @param indVar the independent variable.
       @return the value of the function.
     */
    public double evaluate(double indVar) {
	return indVar * indVar;
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

    @Override
    public String toString() {
	return "f(x)=x^2";
    }

    @Override
    public boolean equals(Object o) {
	if(o == null)
	    return false;
	// Only depends on if it is our predefined quadratic function
	if(!(o instanceof QuadraticFunction))
	    return false;

	return true;
    }
}
