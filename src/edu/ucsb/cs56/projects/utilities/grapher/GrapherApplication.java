package edu.ucsb.cs56.projects.utilities.grapher;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
   Class to start the grapher utility
   @author Ryan Halbrook
   @version CS56 Spring 2013
*/
public class GrapherApplication {

    public static final String TITLE = "2D Function Grapher";
    public static final int WIDTH = 750;
    public static final int HEIGHT = 550;
    public JMenuBar mb = null;
    public Bounds2DFloat b = null;
    public Graph2DPanel graphPanel = null;

    /**
       Start the Grapher Application. 
     */
    public void start() {
	JFrame appFrame = new JFrame(TITLE);
	appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	appFrame.setSize(WIDTH, HEIGHT);
	
	b = new Bounds2DFloat(0, -1, (float)(Math.PI * 2), 1);
	graphPanel = new Graph2DPanel(new SineFunction(),b);
	appFrame.getContentPane().add(graphPanel);
	appFrame.getContentPane().add(new Grapher2DBoundsPanel(b), BorderLayout.SOUTH);
	buildMenuBar();
	appFrame.setJMenuBar(mb);
	appFrame.setVisible(true);
    }

    /**
       Main that created the grapher application and starts it.
       @param args not used.
     */
    public static void main(String [] args) {
	GrapherApplication application = new GrapherApplication();
	application.start();
    }

    /**
       Build the menu bar object for the grapher application.
     */
    public void buildMenuBar() {
	mb = new JMenuBar();
	JMenu scale = new JMenu("Scale");
	JMenuItem scaleTimesTwo = new JMenuItem("Scale x2");
	JMenuItem scaleHalf = new JMenuItem("Scale x1/2");
	scaleTimesTwo.setActionCommand("s2times");
	scaleHalf.setActionCommand("s0.5times");
	scaleTimesTwo.addActionListener(new ButtonListener());
	scaleHalf.addActionListener(new ButtonListener());
	scale.add(scaleTimesTwo);
	scale.add(scaleHalf);

	JMenu translate = new JMenu("Translate");
	JMenuItem translateFive = new JMenuItem ("Right 5 Units");
	translateFive.setActionCommand("translateX5");
	translateFive.addActionListener(new ButtonListener());
	translate.add(translateFive);

	mb.add(scale);
	mb.add(translate);
    }

    /**
       Listener for when a button is pressed.
     */
    public class ButtonListener implements ActionListener {
	/** Callback for when a button is pressed.
	    @param e the event object */
	public void actionPerformed(ActionEvent e) {
	    if (e.getActionCommand().equals("s2times")) {
		b.scale(2.0f);
		graphPanel.refresh();
	    } else if (e.getActionCommand().equals("s0.5times")) {
		b.scale(0.5f);
		graphPanel.refresh();
	    } else if (e.getActionCommand().equals("translateX5")) {
		b.translate(5, 0);
		graphPanel.refresh();
	    }
	}
    }

} // End of class
