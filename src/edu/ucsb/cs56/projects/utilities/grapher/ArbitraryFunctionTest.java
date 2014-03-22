package edu.ucsb.cs56.projects.utilities.grapher;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class ArbitraryFunctionTest {

    public static final double DELTA = 0.001;
    public static final char X = 'x';

    @Test
    public void testArbitraryConstructor0(){
	ArbitraryFunction p = new ArbitraryFunction("0", X);
	assertEquals(new Term("0").toString(),p.get(0).toString());
    }

    @Test
    public void testArbitraryConstructor1(){
	ArbitraryFunction p = new ArbitraryFunction("3x^2", X);
	assertEquals("3x^2",p.get(0).toString());
    }

    @Test
    public void testArbitraryConstructor2(){
	ArbitraryFunction p = new ArbitraryFunction("cos(x)", X);
	assertEquals(new Term("cos(x)"),p.get(0));
    }

    @Test
    public void testArbitraryConstructor3(){
	ArbitraryFunction p = new ArbitraryFunction("3x^2 + cos(x)", X);
	assertEquals(new Term("3x^2"),p.get(0));
	assertEquals(new Term("cos(x)"), p.get(1));
    }

    @Test
    public void testArbitraryConstructor4(){
	ArbitraryFunction p = new ArbitraryFunction("3x^2 + x*cos(x)", X);
	assertEquals(new Term("3x^2"),p.get(0));
	assertEquals(new Term("x*cos(x)"), p.get(1));
	assertEquals("f(x)=x", p.get(1).get(0).toString());
    }

    @Test
    public void testArbitraryConstructor5(){
	ArbitraryFunction p = new ArbitraryFunction("sin(x) +3x^3", X);
	assertEquals(new Term("3x^3"),p.get(1));
	assertEquals(new Term("sin(x)"), p.get(0));
    }

    
    @Test
    public void testArbitraryEvaluate0() {
	ArbitraryFunction p = new ArbitraryFunction("3x^2", X);
	assertEquals(12.0, p.evaluate(2.0), DELTA);
    }
    
    @Test
    public void testArbitraryEvaluate1() {
	ArbitraryFunction p = new ArbitraryFunction("cos(x)", X);
	assertEquals(-0.4161, p.evaluate(2.0), DELTA);
    }
    
    @Test
    public void testArbitraryEvaluate2() {
	ArbitraryFunction p = new ArbitraryFunction("sin(x)", X);
	assertEquals(.9093, p.evaluate(2.0), DELTA);
    }
    
    @Test
    public void testArbitraryEvaluate3() {
	ArbitraryFunction p = new ArbitraryFunction("3x^2 + cos(x)", X);
	assertEquals(11.5839, p.evaluate(2.0), DELTA);
    }
}
