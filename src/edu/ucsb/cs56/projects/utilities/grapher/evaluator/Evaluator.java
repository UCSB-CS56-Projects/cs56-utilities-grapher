package edu.ucsb.cs56.projects.utilities.grapher.evaluator;

import edu.ucsb.cs56.projects.utilities.grapher.parser.TokenNode;
import edu.ucsb.cs56.projects.utilities.grapher.tokenizer.*;
import java.lang.Math;
public class Evaluator{
	public double evaluate(TokenNode root,double num){
		if(root.isLiteral()){
			Token t=root.getData();
			if(t instanceof NumberToken){
				return ((NumberToken)t).getData();
			}else
				return num;
		}else if(root.isUnaryOperator()){
			if(t instanceof MinusToken){
				return -1*evaluate(root.getLeftChild(),num);
			}else if(t instanceof CosineToken){
				return Math.cos(evaluate(root.getLeftChild(),num));
			}else if(t instanceof SineToken){
				return Math.sin(evaluate(root.getLeftChild(),num));
			}else if(t instanceof TangentToken){
				return Math.tan(evaluate(root.getLeftChild(),num));
			}else if(t instanceof LogToken){
				return Math.log10(evaluate(root.getLeftChild(),num));
			}else if(t instanceof NaturalLogToken){
				return Math.log(evaluate(root.getLeftChild(),num));
			}
		}
	}


}
