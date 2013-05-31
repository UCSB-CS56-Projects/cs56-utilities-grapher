import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
   A widget for changing bounds.
   @author Ryan Halbrook
   @version CS56, S13, Project
 */

public class Grapher2DBoundsPanel extends JPanel implements ActionListener {
    private JTextField xMinField, yMinField, xMaxField, yMaxField;
    private JLabel xLabel = new JLabel(" <= x <= ");
    private JLabel yLabel = new JLabel(" <= y <= ");
    private Bounds2DFloat bounds;

    public Grapher2DBoundsPanel(Bounds2DFloat b) {
	super();
	this.bounds = b;
	bounds.addActionListener(this);
	this.setLayout(new GridLayout(2,0));
	TextChangeListener t = new TextChangeListener();
	xMinField = new JTextField("" + bounds.getXMin());
	yMinField = new JTextField("" + bounds.getYMin());
	xMaxField = new JTextField("" + bounds.getXMax());
	yMaxField = new JTextField("" + bounds.getYMax());
	xMinField.addActionListener(t);
	yMinField.addActionListener(t);
	xMaxField.addActionListener(t);
	yMaxField.addActionListener(t);
	this.add(xMinField);
	xLabel.setHorizontalAlignment(SwingConstants.CENTER);
	yLabel.setHorizontalAlignment(SwingConstants.CENTER);

	JPanel xComp = new JPanel(new BorderLayout());
	xComp.add(xLabel, BorderLayout.CENTER);
	JPanel yComp = new JPanel(new BorderLayout());
	yComp.add(yLabel, BorderLayout.CENTER);
	this.add(xComp);
	this.add(xMaxField);
	this.add(yMinField);
	this.add(yComp);
	this.add(yMaxField);
    }

    private void updateBounds() {
	System.out.println("Update bounds");
	bounds.setXMin(new Float(xMinField.getText()));
	bounds.setYMin(new Float(yMinField.getText()));
	bounds.setXMax(new Float(xMaxField.getText()));
	bounds.setYMax(new Float(yMaxField.getText()));
    }

    public void actionPerformed(ActionEvent e) {
	System.out.println("action");
	xMaxField.setText(""+bounds.getXMax());
	yMaxField.setText(""+bounds.getYMax());
	xMinField.setText(""+bounds.getXMin());
	yMinField.setText(""+bounds.getYMin());
    }

    public class TextChangeListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    updateBounds();
	}
    }
    
}
