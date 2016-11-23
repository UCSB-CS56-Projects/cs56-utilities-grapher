package edu.ucsb.cs56.projects.utilities.grapher.evaluator;

import edu.ucsb.cs56.projects.utilities.grapher.tokenizer.*;
import edu.ucsb.cs56.projects.utilities.grapher.parser.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import java.util.ArrayList;
import org.junit.Before;
import java.lang.Math;
public class EvaluatorTest {
    double DELTA=0.0001;

    @Test
    public void testIntegers(){
	ArrayList<Token>tokens=Tokenizer.tokenize("2sincosx+4x^2+5");
	Token[]answer={new NumberToken(2.0),new SineToken(),new CosineToken(),new XVarToken(),new PlusToken(),new NumberToken(4.0),
		new XVarToken(),new ExponentToken(),new NumberToken(2.0), new PlusToken(),new NumberToken(5.0)};
	Token[]tA=new Token[tokens.size()];
	tA=tokens.toArray(tA);
	assertEquals(tA,answer);
	double x=0;
	double answer2=0.0;
	try{
		TokenNode t=Parser.parse(tokens);
		answer2 = Evaluator.evaluate(t,x);
	}catch(Exception e){
		System.out.println(e);
	}
	assertEquals(2*Math.sin(Math.cos(x))+4*Math.pow(x,2)+5,answer2,DELTA);
    }

    @Test
    public void testBigIntegers(){
	ArrayList<Token>tokens=Tokenizer.tokenize("cos32234x+12logx^54");
	Token[]answer={new CosineToken(),new NumberToken(32234.0),new XVarToken(), new PlusToken(), new NumberToken(12.0),
		new LogToken(),new XVarToken(), new ExponentToken(), new NumberToken(54)};
	Token[]tA=new Token[tokens.size()];
	tA=tokens.toArray(tA);
	assertEquals(tA,answer);   
	double x=1;
	double answer2=0.0;
	try{
		TokenNode t=Parser.parse(tokens);
		answer2 = Evaluator.evaluate(t,x);
	}catch(Exception e){
		System.out.println(e);
	}
	assertEquals(Math.cos(32234)*x+12*Math.log10(Math.pow(x,54)),answer2,DELTA);
	
    }
}
