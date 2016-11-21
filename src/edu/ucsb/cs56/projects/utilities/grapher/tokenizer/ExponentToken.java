package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

public class ExponentToken extends Token implements Binop{
        @Override
        public double getPrecedence(){
                return 2;
        }

	@Override
        public String repr(){
	    return "^";
        }
}
