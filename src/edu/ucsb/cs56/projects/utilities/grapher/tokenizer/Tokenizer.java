
package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Map;

public class Tokenizer{
	public static final int NUMBER_OF_STATES=25;
	private State[]states=new State[NUMBER_OF_STATES];
	private ArrayList<Token>stateChars=new ArrayList<Token>();
	private int indexOfNextEmptyState=0;
	public Tokenizer(){
	    addStaticTokens();
	    addState(new State(null,false));//default state zero
	    State lastState=null;
	    for(Token i:stateChars){//static tokens
		lastState=getDefaultState();
		String repr=i.repr();
		for(int j=0;j<repr.length();j++){
		    char c=repr.charAt(j);
		    if(lastState.hasTransitioningStateTo(c))continue;
		    //j==repr.length()-1 is a boolean indicating whether j is the index of the last character in the string
		    State temp=new State(c,i,j==(repr.length()-1));
		    lastState.addNextState(c,indexOfNextEmptyState);
		    lastState=temp;
		    addState(temp);
		}
	    }
	    addNumberStates();
	}
	public void addStaticTokens(){
	    stateChars.add(new PlusToken());
	    stateChars.add(new MinusToken());
	    stateChars.add(new TimesToken());
	    stateChars.add(new DivideToken());
	    stateChars.add(new LogToken());
	    stateChars.add(new CosineToken());
	    stateChars.add(new SineToken());
	    stateChars.add(new ExponentToken());
	    stateChars.add(new VarToken());
	    stateChars.add(new LParenToken());
	    stateChars.add(new RParenToken());
	}
	public void addNumberStates(){
	    State defaultState=getDefaultState();
            State numbers=new State(NumberToken.DIGITS,new NumberToken(0.0),true);
	    defaultState.addNextState(numbers,indexOfNextEmptyState);
	    numbers.addNextState(numbers,indexOfNextEmptyState);
	    addState(numbers);
	    char periodChar[]={'.'};
	    State period=new State(periodChar,new NumberToken(0.0),true);
	    numbers.addNextState(period, indexOfNextEmptyState);
	    addState(period);
	    State numbersAfterDecimalPoint=new State(NumberToken.DIGITS,new NumberToken(0.0),true);
	    numbersAfterDecimalPoint.addNextState(numbersAfterDecimalPoint,indexOfNextEmptyState);
	    period.addNextState(numbersAfterDecimalPoint,indexOfNextEmptyState);
	    addState(numbersAfterDecimalPoint);
	    State leadingPeriod=new State(periodChar,false);
	    defaultState.addNextState(leadingPeriod,indexOfNextEmptyState);
	    leadingPeriod.addNextState(numbersAfterDecimalPoint, indexOfNextEmptyState-1);
	    addState(leadingPeriod);
	}
	public ArrayList<Token> tokenize(String input){
	    ArrayList<Token>tokens=new ArrayList<Token>();
	    State currentState=getDefaultState();
	    int endIndexOfLastToken=-1;
	    input+=" ";//adds a space: this is padding
	    for(int i=0;i<input.length();i++){
	    	char c=input.charAt(i);
//		System.out.println("hihi"+c);
		if(currentState.hasTransitioningStateTo(c)&&!Character.isWhitespace(c)){
//		    System.out.println("wowowo");
		    int index=currentState.getTransitioningStateIndex(c);
		    currentState=states[index];
		}else if(currentState.stateIsTerminating()){
//		    System.out.println("ababa");
		    Token t=currentState.getAssociatedToken();
		    if(t==null)throw new RuntimeException("All terminating states must have some associated token");
		    if(t instanceof NumberToken){
		    	int startNumberIndex=endIndexOfLastToken+1;
			double tokenValueIntegerPart=0.;
			double tokenValueFractionalPart=0.;
			boolean hasPassedPeriod=false;
			for(int j=startNumberIndex; j<=i-1;j++){
			    char c2=input.charAt(j);
			    if(c2=='.'){
				hasPassedPeriod=true;
				continue;
			    }
			    double digit=(double)(c2-'0');
			    if(!hasPassedPeriod)tokenValueIntegerPart=tokenValueIntegerPart*10.0+digit;
			    else tokenValueFractionalPart=tokenValueFractionalPart*10.0+digit;
			}
			if(tokenValueFractionalPart>=0.99)
			    tokenValueFractionalPart/=Math.pow(10.0,Math.floor(Math.log10(tokenValueFractionalPart))+1);
			double tokenValue=tokenValueIntegerPart+tokenValueFractionalPart;
//			System.out.println("hello idiots"+tokenValue+" "+tokenValueIntegerPart+" "+tokenValueFractionalPart);
			tokens.add(new NumberToken(tokenValue));
		    }else{
			tokens.add(t);
		    }
		    currentState=getDefaultState();
		    endIndexOfLastToken=i-1;
		    if(!Character.isWhitespace(c))i--;
		}else{
		    tokens.add(new ErrorToken());
		}
	    }
	    return tokens;
	}
	public void addState(State s){
	    if(indexOfNextEmptyState>=states.length)
		throw new RuntimeException("The number of states exceeded the 'state' array's length. Please manually increase it");
	    states[indexOfNextEmptyState++]=s;
	}
	public int numberOfStates(){return indexOfNextEmptyState;}
	public State getDefaultState(){return (states.length!=0)?(states[0]):null;}
	@Override
	public String toString(){
	    String temp="Tokenizer\n";
	    temp+="has these "+numberOfStates()+" states\n";
	    for(int i=0;i<numberOfStates();i++){
	   	temp+=states[i];
	    }
	    return temp;
	}
}
