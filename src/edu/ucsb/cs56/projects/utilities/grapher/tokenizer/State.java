package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class State{
	private HashMap<Character, Integer> nextState=new HashMap<Character,Integer>();
	private boolean isTerminatingState;
	private Token associatedToken;
	private char[] associatedChars;
	public State(char[]associatedChars,Token associatedToken, boolean isTerminatingState){
	    this.associatedChars=associatedChars;
	    this.associatedToken=associatedToken;
	    this.isTerminatingState=isTerminatingState;
	}
	public State(char associatedChar,Token associatedToken, boolean isTerminatingState){
	    this(new char[]{associatedChar},associatedToken,isTerminatingState);
	}
	public State(char associatedChars[], boolean isTerminatingState){
	    this(associatedChars,null, isTerminatingState);
	}
	public State(char associatedChar,boolean isTerminatingState){
	    this(associatedChar,null, isTerminatingState);
	}
	public boolean hasTransitioningStateTo(char c){return nextState.get(c)!=null;}
	public int getTransitioningStateIndex(char c){
	    if(!hasTransitioningStateTo(c))
		throw new RuntimeException("State does not have transitioning state to "+String.valueOf(c));
	    return nextState.get(c);
	}
	public void addNextState(char chars[],int next){
	    for(char c:chars)
		nextState.put(c,next);
	}
	public void addNextState(State s, int next){addNextState(s.getAssociatedCharacters(),next);}
	public void addNextState(char c, int next){nextState.put(c,next);}
	public HashMap<Character,Integer> getNextState(){return nextState;}
	public Token getAssociatedToken(){return associatedToken;}
	public boolean stateIsTerminating(){return isTerminatingState;}
	public char[] getAssociatedCharacters(){return associatedChars;}
	@Override
	public String toString(){
	    String temp="This State\n";
	    temp+="Has associated characters: \n";
	    if(associatedChars!=null)
		for(char c:associatedChars)
   		    temp+=String.valueOf(c)+"\n";
	    temp="Has associated transition states\n";
	    for(Map.Entry<Character,Integer> entry:nextState.entrySet())
		temp+=String.valueOf(entry.getKey())+" "+entry.getValue()+"\n";
	    temp+="And is "+((stateIsTerminating())?"":"not ")+"a terminating state\n";
	    return temp;
	}
}

