package edu.ucsb.cs56.projects.utilities.grapher.parser;

import edu.ucsb.cs56.projects.utilities.grapher.tokenizer.*;
import java.util.ArrayList;
public class Parser{

	public static TokenNode parse(ArrayList<Token> input) throws ParenthesesException{
		int parenCheck = 0;
		ArrayList<Integer>lParenIndices=new ArrayList<Integer>();
		ArrayList<Integer>rParenIndices=new ArrayList<Integer>();
		for ( int i = 0; i < input.size(); i ++){
			if (input.get(i) instanceof LParenToken){
				parenCheck++;
				if(parenCheck==1)lParenIndices.add(i);
			}
			if (input.get(i) instanceof RParenToken){
				parenCheck--;
				if(parenCheck==0)rParenIndices.add(i);
				if (parenCheck < 0)
					throw new ParenthesesException();
			}
		}
		while(parenCheck>0){
			input.add(new RParenToken());
			rParenIndices.add(input.size()-1);
			parenCheck--;
		}
		for(int i=lParenIndices.size()-1;i>=0;i--){
			if(rParenIndices.get(i)-lParenIndices.get(i)>1){
				TokenNode ast=parse(getPortionOfArray(input,lParenIndices.get(i)+1,rParenIndices.get(i)-1);
				input=replacePortionOfArray(input,ast,lParenIndices.get(i),rParenIndices.get(i));
			}
		}
		
	}
	public ArrayList<Token> getPortionOfArray(ArrayList<Token> input, int startIndex, int endIndex){
		ArrayList<Token>newArr=new ArrayList<Token>();
		for(int i=startIndex;i<=endIndex;i++)
			newArr.add(input.get(i));
		return newArr;
	}
	public ArrayList<Token> replacePortionOfArray(ArrayList<Token> orig, TokenNode tokenNode, int startIndex, int endIndex){
		orig.add(startIndex,tokenNode);
		orig.removeRange(startIndex+1,endIndex+1);
		return orig;
	}
