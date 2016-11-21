package edu.ucsb.cs56.projects.utilities.grapher.parser;
import edu.ucsb.cs56.projects.utilities.grapher.tokenizer.*;

//import edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import java.util.ArrayList;
import org.junit.Before;

public class ParserTest {
    Parser p;
    Tokenizer t;

    @Before
    public void setUp(){
	p=new Parser();
	t = new Tokenizer();
    }
    @Test
    public void testWhiteSpaceOperators(){
	ArrayList<Token>tt=t.tokenize("5    sin 34 x");
	System.out.println(tt);
	TokenNode z = null;
	try{
		z = p.parse(tt);
	}catch(Exception e){
	}
	z.printNode();
	
    }
    @Test
    public void testWhiteSpaceNumbers(){
	ArrayList<Token>tt=t.tokenize("3 5");
	System.out.println(tt);
	try{
		p.parse(tt);
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
		z=p.parse(tokens);
	}catch(Exception e){
		System.out.println("hi");
	}
	z.printNode();

	assertEquals(c,z);
    }
    @Test
    public void testParserNotSoSimple(){
	ArrayList<Token> tokens = t.tokenize("5*6+sinx");
	System.out.println(tokens);
	TokenNode z=null;
	TokenNode sT=new TokenNode(new SineToken(),new TokenNode(new XVarToken()));
	TokenNode tT=new TokenNode(new TimesToken(),new TokenNode(new NumberToken(5)),new TokenNode(new NumberToken(6)));
	TokenNode pT=new TokenNode(new PlusToken(),tT,sT);
	try{
		z=p.parse(tokens);
	}catch(Exception e){
		System.out.println("hi");
	}
//	System.out.println("ahjgklnhsalksld");
	assertEquals(pT,z);
//	z.printNode();
    }
    @Test
	public void testParserNotSoSimple2(){
        ArrayList<Token> tokens = t.tokenize("2+3-2");
        System.out.println(tokens);
        TokenNode z=null;
        try{
	    z=p.parse(tokens);
        }catch(Exception e){
	    System.out.println("hi");
        }
  //      z.printNode();
    }
    @Test
	public void testParserNotSoSimple3(){
        ArrayList<Token> tokens = t.tokenize("2-3+2");
        System.out.println(tokens);
        TokenNode z=null;
        try{
	    z=p.parse(tokens);
        }catch(Exception e){
	    System.out.println("hi");
        }
//        z.printNode();
    }
    @Test
        public void testParserNotSoSimple4(){
	ArrayList<Token> tokens = t.tokenize("3*4/5*6");
	System.out.println(tokens);
	TokenNode z=null;
        try{
	    z=p.parse(tokens);
	}catch(Exception e){
            System.out.println("hi");
	}
  //      z.printNode();
    }

}
