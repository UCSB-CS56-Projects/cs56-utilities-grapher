package edu.ucsb.cs56.projects.utilities.grapher;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
/**
   A class that wraps up a custom quadratic function as an implementer of
   the FunctionR1R1 interface. Quadratic takes coefficients from input.
   @author Jenny So
   @version CS56, Winter 2014, Project
 */
public class CustomQuadraticFunctionTest{


    /**
        Test constructor that initializes from int array
        @see CustomQuadraticFunction#CustomQuadraticFunction(int [] coeffs)
    */

    @Test public void testConstructorIntArray(){
	CustomQuadraticFunction p = new CustomQuadraticFunction(new int[] {5,1,2});
	assertEquals(2, (int)p.get(2));
	assertEquals(1, (int)p.get(1));
	assertEquals(5, (int)p.get(0));
    }
    /**
       Test constructor that initializes from a String
       @see CustomQuadraticFunction#CustomQuadraticFunction(String)
    */
    @Test public void testConstructorString() {
	CustomQuadraticFunction p = new CustomQuadraticFunction("3x^3 + 4x^2 + 5x + 6");
	CustomQuadraticFunction a = new CustomQuadraticFunction(new int[] {6,5,4,3});
	assertEquals(p,a);
    }

    @Test public void testConstructorString2() {
	CustomQuadraticFunction p = new CustomQuadraticFunction("3x^2");
	CustomQuadraticFunction a = new CustomQuadraticFunction(new int[] {0,0,3});
	assertEquals(p,a);
    }
     /**
        Test evaluate method that evaluates using an independent variable
	In this case, independent variable is 0
        @see CustomQuadraticFunction#evaluate(double indVar)
    */
    @Test public void testEvaluate0() {
	CustomQuadraticFunction p = new CustomQuadraticFunction(new int[] {5,1,2});
	assertEquals(5, (int)p.evaluate(0));
    }

    /**
        Test evaluate method that evaluates using an independent variable
	In this case, independent variable is 1
        @see CustomQuadraticFunction#evaluate(double indVar)
    */
     @Test public void testEvaluate1() {
	CustomQuadraticFunction p = new CustomQuadraticFunction(new int[] {5,1,2});
	assertEquals(8, (int)p.evaluate(1));
    }

     /**
        Test evaluate method that evaluates using an independent variable
	In this case, independent variable is 2
        @see CustomQuadraticFunction#evaluate(double indVar)
    */
     @Test public void testEvaluate2() {
	CustomQuadraticFunction p = new CustomQuadraticFunction(new int[] {5,1,2});
	assertEquals(15, (int)p.evaluate(2));
    }

     /**
        Test evaluate method that evaluates using an independent variable
	In this case, independent variable is -3
        @see CustomQuadraticFunction#evaluate(double indVar)
    */
     @Test public void testEvaluate3() {
	CustomQuadraticFunction p = new CustomQuadraticFunction(new int[] {5,1,2});
	assertEquals(20, (int)p.evaluate(-3));
    }

     /**
	Test toString method
	@see CustomQuadraticFunction#toString()
     */
    @Test public void testToString1() {
	CustomQuadraticFunction p1 = new CustomQuadraticFunction(new int[] {5,1,2});
	assertEquals("f(x)=2x^2 + x + 5", p1.toString());
    }

    /**
	Test toString method
	@see CustomQuadraticFunction#toString()
     */
    @Test public void testToString2() {
	CustomQuadraticFunction p2 = new CustomQuadraticFunction(new int[] {5,1,0});
	assertEquals("f(x)=x + 5", p2.toString());
    }
}
