package edu.ucsb.cs56.projects.utilities.grapher;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.event.*;

/**
   Class to start the grapher utility
   @author Ryan Halbrook
   @author Jenny So
   @version CS56 Winter 2014
*/
public class GrapherApplication {

    public static final String TITLE = "2D Function Grapher";
    public static final int WIDTH = 750;
    public static final int HEIGHT = 480;
    public CustomQuadraticDialog quadDialog = null;
    public ArbitraryFunctionDialog arbiDialog = null;
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

	// Create JSlider that spans from -15 to 15. Default value is 0.
	JSlider zoomSlider = new JSlider(JSlider.HORIZONTAL, -15, 15, 0);
	zoomSlider.addChangeListener(new SliderListener());
	graphPanel.add(zoomSlider);
	
	buildMenuBar();

	appFrame.setJMenuBar(mb);
	appFrame.setVisible(true);

	// Create the dialog that will pop up when selected in menu bar. 6 is the maximum power polynomial minus 1.
	quadDialog = new CustomQuadraticDialog(6);
	arbiDialog = new ArbitraryFunctionDialog();
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

	// Add functions option and suboptions to bar
	JMenu functionsOp = new JMenu("Functions");
	JMenuItem cosine = new JMenuItem("Cosine");
	JMenuItem sine = new JMenuItem("Sine");
	JMenuItem quadratic = new JMenuItem("x^2");
	JMenuItem customQuad = new JMenuItem("Custom Quadratic");
	JMenuItem arbiFunc = new JMenuItem("Arbitrary Function");

	// Set commands
	cosine.setActionCommand("cosine");
	sine.setActionCommand("sine");
	quadratic.setActionCommand("quadratic");
	customQuad.setActionCommand("customQuad");
	arbiFunc.setActionCommand("arbiFunc");

	// Add listener to buttons
	cosine.addActionListener(new ButtonListener());
	sine.addActionListener(new ButtonListener());
	quadratic.addActionListener(new ButtonListener());
	customQuad.addActionListener(new ButtonListener());
	arbiFunc.addActionListener(new ButtonListener());
	
	// Add buttons to the functions options
	functionsOp.add(cosine);
	functionsOp.add(sine);
	functionsOp.add(quadratic);
	functionsOp.add(customQuad);
	functionsOp.add(arbiFunc);

	// Add all option links to the bar
	mb.add(scaleOp);
	mb.add(translateOp);
	mb.add(functionsOp);
    }
    
    /**
       Listener for when the slider is moved.
    */
    public class SliderListener implements ChangeListener {
	public int sliderState = 0; // The most recent slider value

	/** Callback for when the slider is moved.
	    @param e the event object */
	public void stateChanged(ChangeEvent e){

	    // Recieve the source of change (in this case, zoomSlider)
	    JSlider source = (JSlider)e.getSource();
	    
	    // Get the state of slider value after change
	    int state  = (int)source.getValue();
	    
	    // Compare the previous state and new state of slider value
	    int change = state - this.sliderState;

	    // If the change is position, zoom out. If negative, zoom in.
	    if(change > 0)
		b.scale(0.8f);
	    if(change < 0)
		b.scale(1.25f);

	    // Update the most recent state of slider value
	    this.sliderState = state;
	    
	}
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
		// If the functon already exists, do not add it
		FunctionR1R1DisplayData cosineFunction = new FunctionR1R1DisplayData(new CosineFunction(), Color.GREEN);
		if(fnsdd != null){
		    if(fnsdd.contains(cosineFunction)==false){
			// Add the function to the list and refresh
			fnsdd.add(cosineFunction);
			graphPanel.refresh();
			// Update the table
			functionsPanel.displayNewRow();
		    }
		}
	    } else if(e.getActionCommand().equals("sine")) {
		// If the functon already exists, do not add it
		FunctionR1R1DisplayData sineFunction = new FunctionR1R1DisplayData(new SineFunction(), Color.BLUE);
		if(fnsdd != null){
		    if(fnsdd.contains(sineFunction)==false){
			// Add the function to the list and refresh
			fnsdd.add(sineFunction);
			graphPanel.refresh();
			// Update the table
			functionsPanel.displayNewRow();
		    }
		}
		
	    } else if(e.getActionCommand().equals("quadratic")) {
		// If the functon already exists, do not add it
		FunctionR1R1DisplayData quadFunction = new FunctionR1R1DisplayData(new QuadraticFunction(), Color.RED);
		if(fnsdd != null){
		    if(fnsdd.contains(quadFunction)==false){
			// Add the function to the list and refresh
			fnsdd.add(quadFunction);
			graphPanel.refresh();
			// Update the table
			functionsPanel.displayNewRow();
		    }
		}
	    } else if(e.getActionCommand().equals("customQuad")) {
		// If the user has clicked 'OK' on the pop up dialog
		if(quadDialog.display() == true){
		    // Use the user input to create new function, add to list, and refresh
		    fnsdd.add(new FunctionR1R1DisplayData(quadDialog.inputCoeffs(), Color.CYAN));
		    graphPanel.refresh();
		    // Update the table
		    functionsPanel.displayNewRow();
		}
	    } else if(e.getActionCommand().equals("arbiFunc")) {
		// If the user has clicked 'OK' on the pop up dialog
		if(arbiDialog.display() == true){
		    // Use user input to create new function, add to list, and refresh
		    fnsdd.add(new FunctionR1R1DisplayData(arbiDialog.inputFunction(), Color.RED));
		    graphPanel.refresh();
		    // Update the table
		    functionsPanel.displayNewRow();
		}
	    }
	}
    }

} // End of class
