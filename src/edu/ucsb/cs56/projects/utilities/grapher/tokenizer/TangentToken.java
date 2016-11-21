package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

public class TangentToken extends Token implements Trig{
        @Override
        public double getPrecedence(){
                return 3;
        }
        @Override
        public String repr(){
            return "tan";
        }
}

