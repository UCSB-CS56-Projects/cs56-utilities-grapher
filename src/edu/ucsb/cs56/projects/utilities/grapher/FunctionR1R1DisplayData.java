package edu.ucsb.cs56.projects.utilities.grapher;
import java.awt.Color;
import java.awt.geom.*;
import edu.ucsb.cs56.projects.utilities.grapher.tokenizer.Tokenizer;
import edu.ucsb.cs56.projects.utilities.grapher.parser.Parser;
import edu.ucsb.cs56.projects.utilities.grapher.evaluator.Evaluator;


/**
   Displays the data on the graph.
   @author Ryan Halbrook
   @version CS56, Spring 13, Project
 */

public class FunctionR1R1DisplayData {
    private Color graphColor;
    private boolean isVisible = true;
    private FunctionR1R1 function;
    private GeneralPath gp;
 //   private String input=null;
//    private Tokenizer t;
//    public FunctionR1R1DisplayData(String input,Color graphColor){
//	this.input=input;
//	this.graphColor=graphColor;
//	t=new Tokenizer();
  //  }
    public FunctionR1R1DisplayData(FunctionR1R1 function, Color graphColor) {
	this.function = function;
	this.graphColor = graphColor;
    }

    public void buildPath(Bounds2DFloat bounds, double width, double height) {
	GeneralPath gp = new GeneralPath();

	double pX = 0;
	double pY = 0;
	
	int ptsCount = 0;
	
	float xScale = (float)(width / (bounds.getXMax() - bounds.getXMin()));
	float yScale = (float)(height / (bounds.getYMax() - bounds.getYMin()));
	float lastX = (float)(width / xScale);

	pX = 0;
//	if(input==null)

//		pY = function.evaluate(pX+bounds.getXMin()) * yScale;
		
	ptsCount++;
	gp.moveTo(pX, pY+bounds.getYMin());
	for (float i = 0; i < lastX; i+=(1 / xScale)) {
	    if(!function.isInDomain(i+bounds.getXMin()))continue;//if function is out of bounds don't display
	    pX = i * xScale;
	    pY =  -(function.evaluate(i+bounds.getXMin()) * yScale);
	    ptsCount++;
	    gp.lineTo(pX, pY+bounds.getYMin());
	}
	//System.out.println("Points rendered: " + ptsCount);
	this.gp = gp;
    }

    // ---- Setters and Getters ---- //

    public GeneralPath getPath() {
	
	return this.gp;
    }

    public Color getColor() {
	return this.graphColor;
    }

    public FunctionR1R1 getFunction() {
	return this.function;
    }

    public boolean isVisible() {
	return this.isVisible;
    }

    public void setColor(Color c) {
	this.graphColor = c;
    }

    public void setFunction(FunctionR1R1 function) {
	this.function = function;
    }

    public void setVisible(boolean visible) {
	this.isVisible = visible;
    }

    @Override
    public boolean equals(Object o){
	if(o == null)
	    return false;
	if(!(o instanceof FunctionR1R1DisplayData))
	    return false;

	FunctionR1R1DisplayData f = (FunctionR1R1DisplayData)o;
	
	//check color
	if(!(this.graphColor.equals(f.graphColor)))
	    return false;

	//check function
	if(!(this.function.equals(f.function)))
	    return false;

	return true;
    }
}
