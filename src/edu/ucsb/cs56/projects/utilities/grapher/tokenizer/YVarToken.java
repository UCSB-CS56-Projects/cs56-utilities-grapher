package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

public class YVarToken extends Token implements Variable{
        @Override
        public double getPrecedence(){
                return 0.0;
        }

	@Override
        public String repr(){
            return "y";
        }
}

