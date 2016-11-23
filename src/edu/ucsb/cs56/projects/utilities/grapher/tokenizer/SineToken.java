package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

public class SineToken extends Token implements Trig{
        @Override
        public String repr(){
	    return "sin";
        }

        @Override
        public double getPrecedence(){
                return 3;
        }

}
