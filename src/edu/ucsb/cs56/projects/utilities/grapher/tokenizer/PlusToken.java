package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

public class PlusToken extends Token{
	@Override
	public double getPrecedence(){
		return 5;
	}
        @Override
        public String repr(){
	    return "+";
        }
}
