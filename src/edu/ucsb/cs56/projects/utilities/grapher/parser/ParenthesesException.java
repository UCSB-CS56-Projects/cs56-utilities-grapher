package edu.ucsb.cs56.projects.utilities.grapher.parser;

public class ParenthesesException extends Exception{
	public ParenthesesException(){
		super("Parentheses mismatch");
	}
}
