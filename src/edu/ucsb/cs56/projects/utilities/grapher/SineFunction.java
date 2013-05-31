/**
   A class that wraps up the sine function as an implementer of
   the FunctionR1R1 interface.
   @author Ryan Halbrook
   @version CS56, Spring 2013, Project
 */
public class SineFunction implements FunctionR1R1 {

    /**
       Evaluate the function
       @param indVar the independent variable.
       @return the value of the function.
     */
    public double evaluate(double indVar) {
	return Math.sin(indVar);
    }
}
