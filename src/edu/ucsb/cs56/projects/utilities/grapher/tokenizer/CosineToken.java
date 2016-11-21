package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

public class CosineToken extends Token{
        @Override
        public String repr(){
	    return "cos";
        }

        @Override
        public double getPrecedence(){
                return 3;
        }

}






