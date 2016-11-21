package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

public class NumberToken extends Token{
	public static final double DELTA=0.001;
	public static final char[] DIGITS={'0','1','2','3','4','5','6','7','8','9'};
	private double value;
	public NumberToken(double value){
    	    this.value=value;
	}
	public NumberToken(){
	    this(0.0);
	}
	@Override
	public String repr(){
	    return String.valueOf(value);
	}
	@Override
	public boolean equals(Object o){
	    if(o==null)return false;
	    if(o.getClass()!=getClass())return false;
	    NumberToken n=(NumberToken)o;
	    double ratioOfValues=getValue()/n.getValue();
	    return (ratioOfValues<1.0+DELTA&&ratioOfValues>1.0-DELTA);
	}
	public double getValue(){return value;}
	@Override
	public double getPrecedence(){return 0;}
}
