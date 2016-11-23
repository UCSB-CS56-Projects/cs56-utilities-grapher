package edu.ucsb.cs56.projects.utilities.grapher;
import edu.ucsb.cs56.projects.utilities.grapher.tokenizer.Tokenizer;
import edu.ucsb.cs56.projects.utilities.grapher.parser.Parser;
import edu.ucsb.cs56.projects.utilities.grapher.parser.TokenNode;
import edu.ucsb.cs56.projects.utilities.grapher.evaluator.*;


import java.util.*;
import javax.script.*;
import java.lang.Character;
import java.lang.Math;
/**
*/
public class CustomFunction implements FunctionR1R1 {
    
    private String exp;
    private TokenNode expressionSyntaxTree;
    public CustomFunction(String expression) {
	exp=expression;
	try{
		expressionSyntaxTree=Parser.parse(Tokenizer.tokenize(exp));
	}catch(Exception e){
		expressionSyntaxTree=null;//this line shouldn't ever execute if isValidFunction() is called beforehand.
	}
    }
    /**
       Evaluates the function at an independent variable.
    */
    public double evaluate(double indVar){
	try{
		return Evaluator.evaluate(expressionSyntaxTree,indVar);
	}catch(Exception e){
		return -1*Double.MAX_VALUE;//hopefully never happens, FunctionR1R1DisplayData should call isInDomain() first
	}
    }
    
    public boolean isInDomain(double arg) {
	try{
		Evaluator.evaluate(expressionSyntaxTree,arg);
	}catch(Exception e){
		return false;
	}
	return true;
    }
    
    public String toString() {
        return "f(x)="+exp;
    }
    public static boolean isValidFunction(String expr){
	TokenNode temp;
	try{
	   temp=Parser.parse(Tokenizer.tokenize(expr));
	}catch(Exception e){
	    return false;
	}
	int outOfDomainCount=0;
        for(double i=0;i<10;i++){
	    try{
		Evaluator.evaluate(temp,Math.pow(i,1.4346));//some uncommon arbitrary number
	    }catch(SyntaxException e){
		return false;//if there are any syntax errors, function is not valid
	    }catch(DomainException e){
		outOfDomainCount++;
	    }
	}
	//if all points we test happens to be out of bounds (nearly impossible), a working function could be seen as invalid
	if(outOfDomainCount==10)return false;
	return true;
    }

}
