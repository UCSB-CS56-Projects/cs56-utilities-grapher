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
    Tokenizer k;
    edu.ucsb.cs56.projects.utilities.grapher.parser.Parser p;
    Evaluator e;
    double DELTA=0.0001;
    @Before
    public void setUp(){
	k=new Tokenizer();
	p=new edu.ucsb.cs56.projects.utilities.grapher.parser.Parser();
	e=new Evaluator();
    }

    @Test
    public void testIntegers(){
	ArrayList<Token>tokens=k.tokenize("2sincosx+4x^2+5");
	Token[]answer={new NumberToken(2.0),new SineToken(),new CosineToken(),new XVarToken(),new PlusToken(),new NumberToken(4.0),
		new XVarToken(),new ExponentToken(),new NumberToken(2.0), new PlusToken(),new NumberToken(5.0)};
	Token[]tA=new Token[tokens.size()];
	tA=tokens.toArray(tA);
	assertEquals(tA,answer);
	double x=0;
	double answer2=0.0;
	try{
		TokenNode t=p.parse(tokens);
		answer2 = e.evaluate(t,x);
	}catch(Exception e){
		System.out.println(e);
	}
	System.out.println("hi"+answer2);
	assertEquals(2*Math.sin(Math.cos(x))+4*Math.pow(x,2)+5,answer2,DELTA);
    }

    @Test
    public void testBigIntegers(){
	ArrayList<Token>tokens=k.tokenize("cos32234x+12logx^54");
	Token[]answer={new CosineToken(),new NumberToken(32234.0),new XVarToken(), new PlusToken(), new NumberToken(12.0),
		new LogToken(),new XVarToken(), new ExponentToken(), new NumberToken(54)};
	Token[]tA=new Token[tokens.size()];
	tA=tokens.toArray(tA);
	assertEquals(tA,answer);   
	double x=1;
	double answer2=0.0;
	try{
		TokenNode t=p.parse(tokens);
		answer2 = e.evaluate(t,x);
	}catch(Exception e){
		System.out.println(e);
	}
	System.out.println("hi"+answer2);
	assertEquals(Math.cos(32234)*x+12*Math.log10(Math.pow(x,54)),answer2,DELTA);
	
    }

    @Test
    public void testNumbers(){
	ArrayList<Token>tokens=k.tokenize("4235.324*533.31455/23443.12");
	Token[]answer={new NumberToken(4235.324),new TimesToken(),new NumberToken(533.31455),new DivideToken(),
	new NumberToken(23443.12)};
	Token[]tA=new Token[tokens.size()];
	tA=tokens.toArray(tA);
	assertEquals(tA,answer);   
    }

   @Test
   public void testLogAndNaturalLog(){
	ArrayList<Token> tokens = k.tokenize("log(10)*ln(1)");
	Token[] answer = {new LogToken(),
			  new LParenToken(),
			  new NumberToken(10), new RParenToken(),
			  new TimesToken(),
			  new NaturalLogToken(),
			  new LParenToken(), new NumberToken(1),
			  new RParenToken()};
	Token[] tA = new Token[tokens.size()];
	tA = tokens.toArray(tA);
	assertEquals(tA,answer);
   }

   @Test
   public void testTangentAndConsecutiveVariables(){
	ArrayList<Token> tokens = k.tokenize("tan(txy)");
	Token[] answer = {new TangentToken(), new LParenToken(),
			  new TVarToken(), new XVarToken(),
			  new YVarToken(), new RParenToken()};
	Token[] tA = new Token[tokens.size()];
	tA = tokens.toArray(tA);
	System.out.println(tokens);
	assertEquals(tA,answer);
   }

}
