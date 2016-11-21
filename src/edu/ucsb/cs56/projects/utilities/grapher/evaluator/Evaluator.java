package edu.ucsb.cs56.projects.utilities.grapher.evaluator;

import edu.ucsb.cs56.projects.utilities.grapher.parser.TokenNode;
import edu.ucsb.cs56.projects.utilities.grapher.tokenizer.*;
import java.lang.Math;
public class Evaluator{
	public static double evaluate(TokenNode root,double num) throws EvaluatorException, DomainException{
		Token t=root.getData();
		if(root.isLiteral()){
			if(t instanceof NumberToken){
				return ((NumberToken)t).getValue();
			}else if(t instanceof Variable){
				return num;
			}else
				throw new EvaluatorException();

		}else if(root.isUnaryOperator()){
			if(t instanceof MinusToken){
				return -1*evaluate(root.getLeftChild(),num);
			}else if(t instanceof CosineToken){
				return Math.cos(evaluate(root.getLeftChild(),num));
			}else if(t instanceof SineToken){
				return Math.sin(evaluate(root.getLeftChild(),num));
			}else if(t instanceof TangentToken){
				double dd=evaluate(root.getLeftChild(),num);
				if(Math.abs((dd-Math.PI/2.0)/Math.PI-(double)((int)((dd-Math.PI/2.0)/Math.PI)))<0.0001)
					throw new DomainException();
				return Math.tan(dd);
			}else if(t instanceof LogToken){
				double dd=evaluate(root.getLeftChild(),num);
				if(dd==0)throw new DomainException();
				return Math.log10(dd);
			}else if(t instanceof NaturalLogToken){
				double dd=evaluate(root.getLeftChild(),num);
				if(dd==0)throw new DomainException();
				return Math.log(dd);
			}else
				throw new EvaluatorException();
		}else{
			if(t instanceof ExponentToken){
				return Math.pow(evaluate(root.getLeftChild(),num),evaluate(root.getRightChild(),num));
			}else if(t instanceof PlusToken){
				return evaluate(root.getLeftChild(),num)+evaluate(root.getRightChild(),num);
			}else if(t instanceof MinusToken){
				return evaluate(root.getLeftChild(),num)-evaluate(root.getRightChild(),num);
			}else if(t instanceof TimesToken){
				return evaluate(root.getLeftChild(),num)*evaluate(root.getRightChild(),num);
			}else if(t instanceof DivideToken){
				double denom=evaluate(root.getRightChild(),num);
				if(denom==0)throw new DomainException();
				return evaluate(root.getLeftChild(),num)/denom;
			}else
				throw new EvaluatorException();
		}
	}


}
