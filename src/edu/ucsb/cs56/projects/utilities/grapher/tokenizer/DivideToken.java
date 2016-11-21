package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

public class DivideToken extends Token{
        @Override
        public double getPrecedence(){
                return 4;
        }
        @Override
        public String repr(){
	    return "/";
        }
}






