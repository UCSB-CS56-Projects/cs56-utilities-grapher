package edu.ucsb.cs56.projects.utilities.grapher;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class TermTest {
    
    public static final double DELTA = 0.0001;

    @Test
    public void testTermConstructor0() {
	Term t = new Term("cos(x)");
	assertEquals("cos(x)", t.toString());
    }

    @Test
    public void testTermConstructor1() {
	Term t = new Term("3x^2");
	assertEquals("3x^2", t.toString());
    }
    
    @Test
    public void testTermConstructor2() {
	Term t = new Term("2x*sin(x)");
	assertEquals(t.get(1).toString(), "f(x)=sin(x)");
	assertEquals(t.get(0).toString(), "f(x)=2x");
    }

    @Test
    public void testTermConstructor3() {
	Term t = new Term("2x^2*sin(x)");
	assertEquals(t.get(1).toString(), "f(x)=sin(x)");
	assertEquals(t.get(0).toString(), "f(x)=2x^2");
    }

    @Test
    public void testTermConstructor4() {
	Term t = new Term("cos(x)*2x^3");
	assertEquals(t.get(1).toString(), "f(x)=2x^3");
	assertEquals(t.get(0).toString(), "f(x)=cos(x)");
    }
    
    @Test
    public void testTermConstructor5() {
	Term t = new Term("0");
	assertEquals(t.get(0).toString(), "f(x)=0");
    }

    @Test
    public void testTermConstructor6() {
	Term t = new Term("x*cos(x)*2x^3");
	assertEquals(t.get(2).toString(), "f(x)=2x^3");
	assertEquals(t.get(1).toString(), "f(x)=cos(x)");
	assertEquals(t.get(0).toString(), "f(x)=x");
    }

    @Test
    public void testTermEvaluate0() {
	Term t = new Term("3x^2");
	assertEquals(12, t.evaluate(2.0), DELTA);
    }

    @Test
    public void testTermEvaluate1() {
	Term t = new Term("cos(x)");
	assertEquals(-0.4161, t.evaluate(2.0), DELTA);
    }

    @Test
    public void testTermEvaluate2() {
	Term t = new Term("sin(x)");
	assertEquals(.9093, t.evaluate(2.0), DELTA);
    }

    @Test
    public void testTermEvaluate3() {
	Term t = new Term("3x^2*cos(x)");
	assertEquals(-4.9937, t.evaluate(2.0), DELTA);
    }

    @Test
    public void testTermEvaluate4() {
	Term t = new Term("3x^2*sin(x)");
	assertEquals(10.9116, t.evaluate(2.0), DELTA);
    }

    @Test
    public void testTermEvaluate5() {
	Term t = new Term("x*cos(x)*3x^2");
	assertEquals(-9.9875, t.evaluate(2.0), DELTA);
    }
}
