package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;
import java.lang.Math;
public class PIToken extends Token implements Constant{
	@Override
	public String repr(){
	    return "PI";
	}
	@Override
	public double getValue(){
	    return Math.PI;
	}
	@Override
	public double getPrecedence(){
	    return 0.0;
	}
}
