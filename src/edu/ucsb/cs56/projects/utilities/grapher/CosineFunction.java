package edu.ucsb.cs56.projects.utilities.grapher;
/**
   A class that wraps up the cosine function as an implementer of
   the FunctionR1R1 interface.
   @author Ryan Halbrook
   @version CS56, Spring 2013, Project
 */
public class CosineFunction implements FunctionR1R1 {

    /**
       Evaluate the function
       @param indVar the independent variable.
       @return the value of the function.
     */
    public double evaluate(double indVar) {
	return Math.cos(indVar);
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
	return "f(x)=cos(x)";
    }

    @Override
    public boolean equals(Object o){
	if(o == null)
	    return false;
	// Only depends on whether or not it is a cosine function
	if(!(o instanceof CosineFunction))
	    return false;
	
	return true;
    }
}
