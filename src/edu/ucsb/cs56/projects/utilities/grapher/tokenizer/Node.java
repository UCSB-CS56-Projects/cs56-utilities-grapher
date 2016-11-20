package edu.ucsb.cs56.projects.utilities.grapher.tokenizer;
import java.util.ArrayList;
import java.util.List;
public class Node<T>{
    private T data;
    private Node<T> parent;
    private List<Node<T>>children;
    public Node(T data){
	this.data=data;
	children=new ArrayList<Node<T>>();
    }
    public void addChild(Node t){
	children.add(t);
	t.parent=this;
    }
}
