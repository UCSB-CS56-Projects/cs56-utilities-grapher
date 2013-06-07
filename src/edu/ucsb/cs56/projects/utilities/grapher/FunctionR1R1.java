package edu.ucsb.cs56.projects.utilities.grapher;
/**
   An interface for a class that represents a function of domain and range that
   are subsets of R1, where R1 is the set of real numbers.
   @author Ryan Halbrook
   @version CS56, Spring 2013, Grapher Utility Project
 */
public interface FunctionR1R1 {
    
    /**
       Evaluate the function.
       @param indVar the independent variable.
       @return the value of the function evaluated
       with the independent variable.
     */
    public double evaluate(double indVar);

    /**
      Checks if an argument is in the domain of the function.
      @param arg the function argument to check.
      @return returns true if the argument is inside the domain of the function.
     */
    public boolean isInDomain(double arg);
}
