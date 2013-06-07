package edu.ucsb.cs56.projects.utilities.grapher;
import java.awt.Color;
import java.awt.geom.*;

/**
   @author Ryan Halbrook
   @version CS56, Spring 13, Project
 */

public class FunctionR1R1DisplayData {
    private Color graphColor;
    private boolean isVisible = true;
    private FunctionR1R1 function;
    private GeneralPath gp;
    
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
	pY = function.evaluate(pX+bounds.getXMin()) * yScale;
	ptsCount++;
	gp.moveTo(pX, pY+bounds.getYMin());
	for (float i = 0; i < lastX; i+=(1 / xScale)) {
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
}
