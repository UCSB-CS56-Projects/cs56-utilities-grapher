package edu.ucsb.cs56.projects.utilities.grapher.parser;

import edu.ucsb.cs56.projects.utilities.grapher.tokenizer.Token;

public class TokenNode extends Token{

    private Token data;
    private TokenNode leftChild;
    private TokenNode rightChild;

    public TokenNode(Token data, TokenNode leftChild, TokenNode rightChild){
	this.data = data;
	this.leftChild = leftChild;
	this.rightChild = rightChild;
    }

    public TokenNode(Token data, TokenNode leftChild){
	this(data, leftChild, null);
    }

    public TokenNode(Token data){
	this(data, null, null);
    }

    @Override
    public String repr(){
	return null;
    }

    @Override
    public String toString(){
	return "TokenNode";
    }

    @Override
    public boolean equals(Object o){
	if (o == null)
		return false;
	if (o.getClass() != this.getClass())
		return false;
	TokenNode t = (TokenNode) o;
	if (!this.data.equals(t.data))
		return false;
	if (this.leftChild!=null){
		if(!this.leftChild.equals(t.leftChild))return false;
	}else if(t.leftChild!=null)return false;

	if(this.rightChild!=null){
		if(!this.rightChild.equals(t.rightChild))return false;
	}else if(t.rightChild!=null)return false;

	return true;
}
    @Override
    public double getPrecedence(){
        return 0;
    }

    public boolean isLiteral(){
	return leftChild==null&&rightChild==null;
   }

    public boolean isUnaryOperator(){
	return !isLiteral()&&!isBinaryOperator();
   }

    public boolean isBinaryOperator(){
	return leftChild!=null&&rightChild!=null;
   }
    public Token getData(){
	return data;
    }
    public TokenNode getLeftChild(){
	return leftChild;
    }
    public TokenNode getRightChild(){
	return rightChild;
    }
    public void printNode(){
	System.out.println(this.data);
	//System.out.println(this.getLeftChild().getData());
	if (this.leftChild!=null)
	    this.getLeftChild().printNode();
	if (this.rightChild!=null)
	    this.getRightChild().printNode();
	return;
    }
}
