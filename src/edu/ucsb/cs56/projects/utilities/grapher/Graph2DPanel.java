package edu.ucsb.cs56.projects.utilities.grapher;
import javax.swing.*; // JPanel
import java.awt.*; // Graphics, Graphics2D
import java.awt.geom.*; // GeneralPath
import java.awt.event.*;
import java.util.ArrayList;

/**
   A JPanel subclass that draws a function to the screen.
   @author Ryan Halbrook
   @version CS56, Spring 2013
 */
public class Graph2DPanel extends JPanel implements ActionListener {
    private ArrayList<FunctionR1R1DisplayData> functions
	= new ArrayList<FunctionR1R1DisplayData>();

    private Bounds2DFloat bounds;

    private Color background = Color.BLACK;
    private Color foreground = Color.WHITE;
    
    // Used to only redraw the graph if necessary.
    private boolean graphIsValid = false;
    // A path of the graph that can be drawn
    private GeneralPath graph = null;

    private float xScale = 10.0f;
    private float yScale = 10.0f;

    private boolean debug = true;

    /**
       Constructs a new object that will graph the supplied function
       within the given bounds on the function. When the function is
       redrawn it will use this same object.
       @param function the function to graph.
       @param b the rectangular boundary that determines how much of the graph will be shown.
     */
    public Graph2DPanel(FunctionR1R1 function, Bounds2DFloat b) {
	super();
	functions.add(new FunctionR1R1DisplayData(function, Color.GREEN));
	this.bounds = b;
	if(this.bounds == null) {
	    this.bounds = new Bounds2DFloat();
	}
	this.bounds.addActionListener(this);
    }

    /**
       Tell the graph to redraw itself, usually because the
       rendering information, such as the bounds has changed.
     */
    public void refresh() {
	graphIsValid = false;
	repaint();
    }

    /**
       Paints the component onscreen. Only re-renders from scratch if
       graphIsValid is false. This should not be called directly. 
       Use repaint().
       @param g the graphics object which will perform onscreen drawing.
     */
    public void paintComponent(Graphics g) {
	Graphics2D g2 = (Graphics2D)g; // Cast for more features.
	g2.setColor(background);
	g2.fillRect(0, 0, ((int)this.getSize().getWidth()),
		    (int)(this.getSize().getHeight()));
	g2.setColor(foreground);
	drawAxes(g2);
	
	if (!graphIsValid) updatePaths();
	for (FunctionR1R1DisplayData fdd : functions) {
	   
	    double height = this.getSize().getHeight();
	    AffineTransform at = new AffineTransform();
	    at.translate(0, (height / 2));
	
	    g2.setColor(fdd.getColor());
	    g2.draw(fdd.getPath().createTransformedShape(at));
	}
    }

    /**
       Draws a set of axes with the graphics object g.
       @param g the destination for drawing.
     */
    private void drawAxes(Graphics g) {
	
        float width = (float)this.getSize().getWidth();
	float height = (float)this.getSize().getHeight();
	
	// Draw the x axis
	g.drawLine(0, (int)(height / 2), (int)width, (int)(height / 2));
	
	// Draw the y axis
	g.drawLine(0, 0, 0, (int)width);
    }

    /**
       Updates the paths representing each graph.
     */
    private void updatePaths() {
	double width = this.getSize().getWidth();
	double height = this.getSize().getHeight();
	int i = 0;
	for (FunctionR1R1DisplayData fdd : functions) {
	    fdd.buildPath(bounds, width, height);
	    i++;
	}
    }

    /**
       Call back for the event that the bounds are changed.
       @param e the ActionEvent object.
     */
    public void actionPerformed(ActionEvent e) {
	this.refresh();
    }

}
