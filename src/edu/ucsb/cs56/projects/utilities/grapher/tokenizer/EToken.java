package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;
import java.lang.Math;
public class EToken extends Token implements Constant{
	@Override
	public String repr(){
	    return "e";
	}
	@Override
	public double getValue(){
	    return Math.E;
	}
	@Override
	public double getPrecedence(){
	    return 0.0;
	}
}
