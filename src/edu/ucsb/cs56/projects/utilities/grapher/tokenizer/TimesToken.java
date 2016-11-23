package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

public class TimesToken extends Token implements Binop{
        @Override
        public double getPrecedence(){
                return 4;
        }
        @Override
        public String repr(){
	    return "*";
        }
}






