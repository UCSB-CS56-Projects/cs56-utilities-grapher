package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

public class MinusToken extends Token implements Binop{
        @Override
        public double getPrecedence(){
                return 5;
        }
        @Override
        public String repr(){
	    return "-";
        }
}






