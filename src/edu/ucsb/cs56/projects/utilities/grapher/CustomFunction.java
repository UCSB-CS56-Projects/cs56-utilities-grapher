package edu.ucsb.cs56.projects.utilities.grapher;
import edu.ucsb.cs56.projects.utilities.grapher.tokenizer.Tokenizer;
import edu.ucsb.cs56.projects.utilities.grapher.parser.Parser;
import edu.ucsb.cs56.projects.utilities.grapher.evaluator.Evaluator;

import java.util.*;
import javax.script.*;
import java.lang.Character;
/**
*/
public class CustomFunction implements FunctionR1R1 {
    
    private String exp;
    private Tokenizer t;
    public CustomFunction(String expression) {
		exp=expression;
	t=new Tokenizer();
    }
    /**
       Evaluates the function at an independent variable.
    */
    public double evaluate(double indVar)throws Exception{
	return Evaluator.evaluate(Parser.parse(t.tokenize(exp)),indVar);
    }
    
    public boolean isInDomain(double arg) {
	return true;
    }
    
    public String toString() {
        return "f(x)="+exp;
    }
      

}
