package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

public class RParenToken extends Token{
        @Override
        public double getPrecedence(){
                return 1;
        }

        @Override
        public String repr(){
	    return ")";
        }
}






