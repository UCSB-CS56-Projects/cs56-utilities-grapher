package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

public class NaturalLogToken extends Token implements Trig{
        @Override
        public String repr(){
            return "ln";
        }

        @Override
        public double getPrecedence(){
                return 3;
        }

}

