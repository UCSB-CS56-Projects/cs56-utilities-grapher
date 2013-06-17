package edu.ucsb.cs56.projects.utilities.grapher;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
   A test class for ArbitraryFunctionR1R1 class.
   @author Ryan Halbrook
   @version CS56, S13, Project
   @see ArbitraryFunctionR1R1
   Sources: CS56 course files for lab02.
 */

public class ArbitraryFunctionR1R1Test {

    public static final double TOLERANCE = 0.0001;

    @Test
    public void test_evaluate01() {
	ArbitraryFunctionR1R1 function = new ArbitraryFunctionR1R1("x+1", 'x');
	assertEquals(5.0, function.evaluate(4.0), TOLERANCE);
    }

    @Test
    public void test_evaluate02() {
	ArbitraryFunctionR1R1 function = new ArbitraryFunctionR1R1("x^2", 'x');
	assertEquals(25.0, function.evaluate(5.0), TOLERANCE);
    }

    @Test
    public void test_evaluate03() {
	ArbitraryFunctionR1R1 function = new ArbitraryFunctionR1R1("sin(x)", 'x');
	assertEquals(0.0, function.evaluate(Math.PI), TOLERANCE);
    }

    @Test
    public void test_evaluate04() {
	ArbitraryFunctionR1R1 function = new ArbitraryFunctionR1R1("5", 'x');
	assertEquals(5.0, function.evaluate(Math.PI), TOLERANCE);
    }

    @Test
    public void test_removeWhitespace() {
	String s = "    x f ";
	String s2 = ArbitraryFunctionR1R1.removeWhitespace(s);
	assertEquals("xf", s2);
    }
}
