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

    @Before
    public void setUp(){
	p=new Parser();
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
	assertEquals(c,z);
    }
}
