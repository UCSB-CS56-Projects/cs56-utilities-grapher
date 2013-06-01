import java.util.ArrayList;
import java.awt.event.*;

/**
   A class reprenting a rectangular boundary.
   @author Ryan Halbrook
   @version CS56, S13, Project
 */
public class Bounds2DFloat {
    private float xMin = 10.0f;
    private float xMax = 10.0f;
    private float yMin = 10.0f;
    private float yMax = 10.0f;
    
    private ArrayList<ActionListener> listeners;

    /**
       Start sending action events to an object.
       @param listener the object that will now recieve events.
     */
    public void addActionListener(ActionListener listener) {
	listeners.add(listener);
    }

    /**
       Constructs a new object with default bounds.
     */
    public Bounds2DFloat() {
	listeners = new ArrayList<ActionListener>();
    };

    /**
       Scale the bounds in both directions.
       @param factor the factor to scale the bounds.
     */
    public void scale(float factor) {
	xMin /= factor;
	yMin /= factor;
	xMax /= factor;
	yMax /= factor;
	sendEvents();
    }

    /**
       Translates the bounds
       @param x the number of units to translate the bounds in the x direction.
       @param y the number of untis to translate the bounds in the y direction.
    */
    public void translate(int x, int y) {
	xMin += x;
	xMax += x;
	yMin += y;
	yMax += y;
	sendEvents();
    }

    /**
       Constructs a new object with the given values.
       @param xMin the lower boundary on the x coordinate.
       @param yMin the lower boundary on the y coordinate.
       @param xMax the upper boundary on the x coordinate.
       @param yMax the upper boundary on the y coordinate.
     */
    public Bounds2DFloat(float xMin,float yMin,float xMax,float yMax) {
	listeners = new ArrayList<ActionListener>();
	this.xMin = xMin;
	this.xMax = xMax;
	this.yMin = yMin;
	this.yMax = yMax;
    }

    /**
       Sends events to all action listeners.
     */
    public void sendEvents() {
	for (ActionListener l : listeners) {
	    l.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_FIRST, "BoundsChanged"));
	}
    }

    /** Getter for xMin
	@return the lower boundary on the x coordinate. */
    public float getXMin() { return xMin; }
    /** Getter for yMin
	@return the lower boundary on the y coordinate. */
    public float getYMin() { return yMin; }
    /** Getter for xMax
	@return the upper boundary on the x coordinate. */
    public float getXMax() { return xMax; }
    /** Getter for yMax
	@return the upper boundary on the y coordinate. */
    public float getYMax() { return yMax; }

    /** Setter for xMin
	@param m the lower boundary on the x coordinate. */
    public void setXMin(float m) { xMin = m; sendEvents(); };
    /** Setter for xMin
        @param m  the lower boundary on the y coordinate. */
    public void setYMin(float m) { yMin = m; sendEvents(); };
    /** Setter for xMin
	@param m the upper boundary on the x coordinate. */
    public void setXMax(float m) { xMax = m; sendEvents(); };
    /** Setter for xMin
	@param m the upper boundary on the y coordinate. */
    public void setYMax(float m) { yMax = m; sendEvents(); };

}
