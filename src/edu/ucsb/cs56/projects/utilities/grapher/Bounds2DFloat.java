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

    public void addActionListener(ActionListener listener) {
	listeners.add(listener);
    }

    public Bounds2DFloat() {
	listeners = new ArrayList<ActionListener>();
    };

    public void scale(float factor) {
	xMin /= factor;
	yMin /= factor;
	xMax /= factor;
	yMax /= factor;
	sendEvents();
    }

    public Bounds2DFloat(float xMin,float yMin,float xMax,float yMax) {
	listeners = new ArrayList<ActionListener>();
	this.xMin = xMin;
	this.xMax = xMax;
	this.yMin = yMin;
	this.yMax = yMax;
    }

    public void sendEvents() {
	for (ActionListener l : listeners) {
	    l.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_FIRST, "BoundsChanged"));
	}
    }

    public float getXMin() { return xMin; }
    public float getYMin() { return yMin; }
    public float getXMax() { return xMax; }
    public float getYMax() { return yMax; }

    public void setXMin(float m) { xMin = m; sendEvents(); };
    public void setYMin(float m) { yMin = m; sendEvents(); };
    public void setXMax(float m) { xMax = m; sendEvents(); };
    public void setYMax(float m) { yMax = m; sendEvents(); };

}
