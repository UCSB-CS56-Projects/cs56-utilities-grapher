package edu.ucsb.cs56.projects.utilities.grapher.parser;

import edu.ucsb.cs56.projects.utilities.grapher.tokenizer.*;

//import edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import java.util.ArrayList;
import org.junit.Before;

public class ParserTest {
    @Test
    public void testWhiteSpaceOperators(){
	ArrayList<Token>tt=Tokenizer.tokenize("5    sin 34 x");
	TokenNode z = null;
	try{
		z = Parser.parse(tt);
	}catch(Exception e){
	    assertEquals(0,1);//if there's an exception, fail test
	}
	z.printNode();
    }
    @Test
    public void testWhiteSpaceNumbers(){
	ArrayList<Token>tt=Tokenizer.tokenize("3 5");
	try{
		Parser.parse(tt);
		assertEquals(1,0);//must give exception or test fails
	}catch(Exception e){
	}
    }
	
    @Test
    public void testTokenNodeEquals() {
	TokenNode c=new TokenNode(new PlusToken(), null,null);
	TokenNode d=new TokenNode(new MinusToken(),c,null);
	TokenNode e=new TokenNode(new TimesToken(),c,d);
	TokenNode f=new TokenNode(new TimesToken(),null,d);

	TokenNode g=new TokenNode(new PlusToken(), null,null);
	TokenNode h=new TokenNode(new MinusToken(),g,null);
	TokenNode i=new TokenNode(new TimesToken(),g,h);
	TokenNode j=new TokenNode(new TimesToken(),null,h);
	
	assertEquals(c,g);
	assertEquals(d,h);
	assertEquals(e,i);
	assertEquals(f,j);
    }
    @Test
    public void testParserSimplePlus(){
	ArrayList<Token>tokens=new ArrayList<Token>();
	tokens.add(new NumberToken(1));
	tokens.add(new PlusToken());
	tokens.add(new NumberToken(2));
	TokenNode c=new TokenNode(new PlusToken(),new TokenNode(new NumberToken(1)),new TokenNode(new NumberToken(2)));
	TokenNode z=null;
	try{
		z=Parser.parse(tokens);
	}catch(Exception e){
		assertEquals(0,1);
	}
	z.printNode();

	assertEquals(c,z);
    }
    @Test
    public void testParserNotSoSimple(){
	ArrayList<Token> tokens = Tokenizer.tokenize("5*6+sinx");
	TokenNode z=null;
	TokenNode sT=new TokenNode(new SineToken(),new TokenNode(new XVarToken()));
	TokenNode tT=new TokenNode(new TimesToken(),new TokenNode(new NumberToken(5)),new TokenNode(new NumberToken(6)));
	TokenNode pT=new TokenNode(new PlusToken(),tT,sT);
	try{
		z=Parser.parse(tokens);
	}catch(Exception e){
		assertEquals(0,1);
	}
//	System.out.println("ahjgklnhsalksld");
	assertEquals(pT,z);
//	z.printNode();
    }
    @Test
	public void testParserNotSoSimple2(){
        ArrayList<Token> tokens = Tokenizer.tokenize("2+3-2");
        TokenNode z=null;
        try{
	    z=Parser.parse(tokens);
        }catch(Exception e){
		assertEquals(0,1);
        }
  //      z.printNode();
    }
    @Test
	public void testParserNotSoSimple3(){
        ArrayList<Token> tokens = Tokenizer.tokenize("2-3+2");
        TokenNode z=null;
        try{
	    z=Parser.parse(tokens);
        }catch(Exception e){
	    assertEquals(0,1);
        }
//        z.printNode();
    }
    @Test
        public void testParserNotSoSimple4(){
	ArrayList<Token> tokens = Tokenizer.tokenize("3*4/5*6");
	System.out.println(tokens);
	TokenNode z=null;
        try{
	    z=Parser.parse(tokens);
	}catch(Exception e){
	    assertEquals(0,1);
	}
  //      z.printNode();
    }

}
