/**
   An interface for a class that represents a function of domain R1 and
   codomain R1, where R1 is the set of real numbers.
   @author Ryan Halbrook
   @version CS56, Spring 2013, Grapher Utility Project
 */
public interface FunctionR1R1 {
    /**
       Evaluate the function.
       @param indVar the independent variable.
       @return the value of the function evaluated with the independent variable.
     */
    public double evaluate(double indVar);
}
