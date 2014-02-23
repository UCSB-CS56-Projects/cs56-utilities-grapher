package edu.ucsb.cs56.projects.utilities.grapher;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
/**
   Class to start the grapher utility
   @author Ryan Halbrook
   @version CS56 Spring 2013
*/
public class GrapherApplication {

    public static final String TITLE = "2D Function Grapher";
    public static final int WIDTH = 750;
    public static final int HEIGHT = 480;
    public JMenuBar mb = null;
    public Bounds2DFloat b = null;
    public Graph2DPanel graphPanel = null;
    public FunctionsPanel functionsPanel= null;
    public JPanel mainPanel = null;
    private FunctionR1R1DisplayDataList fnsdd = new FunctionR1R1DisplayDataList();

    /**
       Start the Grapher Application. 
     */
    public void start() {
	// Create the window
	JFrame appFrame = new JFrame(TITLE);
	appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	appFrame.setSize(WIDTH, HEIGHT);
	
	// Set bounds for graph
	b = new Bounds2DFloat(0, -1, 2, 1);

	// Add Sine, Cosine, Quadratic functions to the list of functions
	//fnsdd.add(new FunctionR1R1DisplayData(new SineFunction(), Color.GREEN));
	//fnsdd.add(new FunctionR1R1DisplayData(new CosineFunction(), Color.BLUE));
	//fnsdd.add(new FunctionR1R1DisplayData(new QuadraticFunction(), Color.RED));

	// Create panel for graph that will draw functions
	graphPanel = new Graph2DPanel(new SineFunction(),b, fnsdd);

	// Create panel for functions table
	functionsPanel = new FunctionsPanel(fnsdd);
	
	mainPanel = new JPanel(new BorderLayout());
	//mainPanel.setBorder(BorderFactory.createLineBorder(BevelBorder.LOWERED));
	mainPanel.add(graphPanel);
	mainPanel.add(new Grapher2DBoundsPanel(b), BorderLayout.EAST);
	//mainPanel.add(new FunctionsPanel(fnsdd), BorderLayout.WEST);
	mainPanel.add(functionsPanel, BorderLayout.WEST);
	appFrame.getContentPane().add(mainPanel);
	//appFrame.getContentPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
	
	//Add scale option and suboptions to bar
	JMenu scaleOp = new JMenu("Scale");
	JMenuItem scaleTimesTwo = new JMenuItem("x2");
	JMenuItem scaleHalf = new JMenuItem("x0.5");

	scaleTimesTwo.setActionCommand("s2times");
	scaleHalf.setActionCommand("s0.5times");

	scaleTimesTwo.addActionListener(new ButtonListener());
	scaleHalf.addActionListener(new ButtonListener());

	scaleOp.add(scaleTimesTwo);
	scaleOp.add(scaleHalf);

	//Add translate option and suboptions to bar
	JMenu translateOp = new JMenu("Translate");
	JMenuItem translateFive = new JMenuItem ("Right 5");
	JMenuItem translateFiveUp = new JMenuItem("Up 5");
	JMenuItem translateFiveLeft = new JMenuItem("Left 5");
	JMenuItem translateFiveDown = new JMenuItem("Down 5");

	translateFive.setActionCommand("translateX5");
	translateFiveUp.setActionCommand("translateY5");
	translateFiveLeft.setActionCommand("translateX-5");
	translateFiveDown.setActionCommand("translateY-5");

	translateFive.addActionListener(new ButtonListener());
	translateFiveUp.addActionListener(new ButtonListener());
	translateFiveLeft.addActionListener(new ButtonListener());
	translateFiveDown.addActionListener(new ButtonListener());

	translateOp.add(translateFive);
	translateOp.add(translateFiveUp);
	translateOp.add(translateFiveLeft);
	translateOp.add(translateFiveDown);

	//Add functions option and suboptions to bar
	JMenu functionsOp = new JMenu("Functions");
	JMenuItem cosine = new JMenuItem("Cosine");
	JMenuItem sine = new JMenuItem("Sine");
	JMenuItem quadratic = new JMenuItem("x^2");
	JMenuItem customQuad = new JMenuItem("Custom Quadratic");

	cosine.setActionCommand("cosine");
	sine.setActionCommand("sine");
	quadratic.setActionCommand("quadratic");
	customQuad.setActionCommand("customQuad");

	cosine.addActionListener(new ButtonListener());
	sine.addActionListener(new ButtonListener());
	quadratic.addActionListener(new ButtonListener());
	customQuad.addActionListener(new ButtonListener());

	functionsOp.add(cosine);
	functionsOp.add(sine);
	functionsOp.add(quadratic);
	functionsOp.add(customQuad);

	mb.add(scaleOp);
	mb.add(translateOp);
	mb.add(functionsOp);
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
	    } else if (e.getActionCommand().equals("translateY5")) {
		b.translate(0, 5);
		graphPanel.refresh();
	    } else if (e.getActionCommand().equals("translateY-5")) {
		b.translate(0,-5);
		graphPanel.refresh();
	    } else if (e.getActionCommand().equals("translateX-5")) {
		b.translate(-5,0);
		graphPanel.refresh();
	    } else if(e.getActionCommand().equals("cosine")) {
		fnsdd.add(new FunctionR1R1DisplayData(new CosineFunction(), Color.GREEN));
		graphPanel.refresh();
	    } else if(e.getActionCommand().equals("sine")) {
		fnsdd.add(new FunctionR1R1DisplayData(new SineFunction(), Color.BLUE));
		graphPanel.refresh();
		
	    } else if(e.getActionCommand().equals("quadratic")) {
		fnsdd.add(new FunctionR1R1DisplayData(new QuadraticFunction(), Color.RED));
		graphPanel.refresh();

	    }
	}
    }

} // End of class
