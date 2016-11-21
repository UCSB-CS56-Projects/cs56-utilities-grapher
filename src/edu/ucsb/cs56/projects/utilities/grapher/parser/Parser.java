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
				TokenNode ast=parse(getPortionOfArray(input,lParenIndices.get(i)+1,rParenIndices.get(i)-1));
				input=replacePortionOfArray(input,ast,lParenIndices.get(i),rParenIndices.get(i));
			}
		}
	//	ArrayList<Double> PrecendenceArray = new ArrayList<Double>();
		ArrayList<Token> newArr = input;
		double rightHighestPrecedence = 100;
		int    rightHighestPrecedenceIndex = -2;

	      while(rightHighestPrecedenceIndex != -1){
		rightHighestPrecedence = 100;
		rightHighestPrecedenceIndex = -1;
		for(int i = 0; i < newArr.size(); i++){
//			PrecedenceArray.add(input.get(i).getPrecedence());
			if (newArr.get(i).getPrecedence() >= new LParenToken().getPrecedence() &&
			    newArr.get(i).getPrecedence() <= rightHighestPrecedence){
				rightHighestPrecedenceIndex = i;
				rightHighestPrecedence = newArr.get(i).getPrecedence();
			}
		}
		if(rightHighestPrecedenceIndex == -1){
			TokenNode newNode = new TokenNode(newArr.get(rightHighestPrecedenceIndex),null,null);
		        newArr=replacePortionOfArray(newArr,newNode,rightHighestPrecedenceIndex,rightHighestPrecedenceIndex);
		}
		else if(rightHighestPrecedenceIndex == 0){
			TokenNode leftChild = new TokenNode(newArr.get(rightHighestPrecedenceIndex+1),null,null);
			TokenNode newNode = new TokenNode(newArr.get(rightHighestPrecedenceIndex),leftChild,null);
		        newArr=replacePortionOfArray(newArr,newNode,0,1);
		}
		else if(newArr.get(rightHighestPrecedenceIndex-1).getPrecedence() < 2){
			TokenNode leftChild = new TokenNode(newArr.get(rightHighestPrecedenceIndex+1),null,null);
			TokenNode newNode = new TokenNode(newArr.get(rightHighestPrecedenceIndex),leftChild,null);
		        newArr=replacePortionOfArray(newArr,newNode,rightHighestPrecedenceIndex,rightHighestPrecedenceIndex+1);
		}
		else {
			TokenNode leftChild = new TokenNode(newArr.get(rightHighestPrecedenceIndex-1),null,null);
			TokenNode rightChild = new TokenNode(newArr.get(rightHighestPrecedenceIndex+1),null,null);
			TokenNode newNode = new TokenNode(newArr.get(rightHighestPrecedenceIndex),leftChild,rightChild);
		        newArr=replacePortionOfArray(newArr,newNode,rightHighestPrecedenceIndex-1,rightHighestPrecedenceIndex+1);
		}
	      }
		return (TokenNode)newArr.get(0);

	}
	public static ArrayList<Token> getPortionOfArray(ArrayList<Token> input, int startIndex, int endIndex){
		ArrayList<Token>newArr=new ArrayList<Token>();
		for(int i=startIndex;i<=endIndex;i++)
			newArr.add(input.get(i));
		return newArr;
	}
	public static ArrayList<Token> replacePortionOfArray(ArrayList<Token> orig, TokenNode tokenNode, int startIndex, int endIndex){
		orig.add(startIndex,tokenNode);
//		orig.removeRange(startIndex+1,endIndex+2);
		for(int i=endIndex+2;i>=startIndex+1;i--){
			orig.remove(i);
		}
		return orig;
	}
}
