
package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

public class LogToken extends Token implements Trig{
        @Override
        public String repr(){
	    return "log";
        }

        @Override
        public double getPrecedence(){
                return 3;
        }

}






