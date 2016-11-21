package edu.ucsb.cs56.projects.utilities.grapher.parser;

import edu.ucsb.cs56.projects.utilities.grapher.tokenizer.Token;

public class TokenNode extends Token{

    public static final int IS_LITERAL = 0;
    public static final int IS_UN_OP = 1;
    public static final int IS_BIN_OP = 2;

    private Token data;
    private TokenNode leftChild;
    private TokenNode rightChild;
    private int type;

    public TokenNode(Token data, TokenNode leftChild, TokenNode rightChild){
	this.data = data;
	this.leftChild = leftChild;
	this.rightChild = rightChild;
	this.type = TokenNode.IS_BIN_OP;
    }

    public TokenNode(Token data, TokenNode leftChild){
	this(data, leftChild, null);
	this.type = TokenNode.IS_UN_OP;
    }

    public TokenNode(Token data){
	this(data, null, null);
	this.type = TokenNode.IS_LITERAL;
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
	if (type == TokenNode.IS_LITERAL)
		return true;
	return false;
   }

    public boolean isUnaryOperator(){
	if (type == TokenNode.IS_UN_OP)
		return true;
	return false;
   }

    public boolean isBinaryOperator(){
	if (type == TokenNode.IS_BIN_OP)
		return true;
	return false;
   }

}
