package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

public abstract class Token{

	public abstract String repr();

	@Override
	public String toString(){
	    return this.getClass().getSimpleName();
	}
	@Override
	public boolean equals(Object o){
		if(o==null)return false;
		return o.getClass()==getClass();
	}
	@Override
	public int hashCode(){
		return getClass().getName().hashCode();
	}

}
