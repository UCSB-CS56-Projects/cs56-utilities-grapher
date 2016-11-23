package edu.ucsb.cs56.projects.utilities.grapher;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.event.*;

/**
   Class to start the grapher utility
   Uses a JFrame with JPanels
   @author Alexander Bauer
   @author Hanqing Wang
   @version CS56 F16
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
	b = new Bounds2DFloat(-10, -10, 10, 10);//x1 y1 x2 y2 HW,AB Reset defualt window

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
	createGraphPanelWidgets();
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
	Creates the slider and the origin button here
    */
    public void createGraphPanelWidgets(){
	// Create JSlider that spans from -15 to 15. Default value is 0.
	JSlider zoomSlider = new JSlider(JSlider.HORIZONTAL, -15, 15, 0);
	zoomSlider.addChangeListener(new ChangeListener(){
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
	});
	graphPanel.add(zoomSlider);
	    
	// HW, AB creates origin button that when pressed resets view to default view.
	JButton origin=new JButton("Origin");
	origin.addActionListener(s->{
	    b.setXMin(-10.0f);
	    b.setXMax(10.0f);
	    b.setYMin(-10.0f);
	    b.setYMax(10.0f);
	});
	graphPanel.add(origin);
    }

    /**
       Build the menu bar object for the grapher application.
     */
    public void buildMenuBar() {
	mb = new JMenuBar();
	mb.add(getMenuScaleOptions());
	mb.add(getMenuTranslateOptions());
	mb.add(getMenuFunctionOptions());
    }

    /**
	Adds scale option and suboptions to menubar
	@return The JMenu containing all the scale options and suboptions
    */
    public JMenu getMenuScaleOptions(){
	JMenu scaleOp = new JMenu("Scale");
	JMenuItem scaleTimesTwo = new JMenuItem("x2");
	JMenuItem scaleHalf = new JMenuItem("x0.5");
	scaleTimesTwo.setActionCommand("s2times");
	scaleHalf.setActionCommand("s0.5times");
	scaleTimesTwo.addActionListener(s->{
		b.scale(2.0f);
		graphPanel.refresh();
	});
	scaleHalf.addActionListener(s->{
		b.scale(0.5f);
		graphPanel.refresh();
	});
	scaleOp.add(scaleTimesTwo);
	scaleOp.add(scaleHalf);
	return scaleOp;
    }
    /**
	Add translate option and suboptions to bar
	@return The Jmenu containing all the translate options
    */
    public JMenu getMenuTranslateOptions(){
	JMenu translateOp = new JMenu("Translate");
	JMenuItem translateFive = new JMenuItem ("Right 5");
	JMenuItem translateFiveUp = new JMenuItem("Up 5");
	JMenuItem translateFiveLeft = new JMenuItem("Left 5");
	JMenuItem translateFiveDown = new JMenuItem("Down 5");

	translateFive.addActionListener(s->{
		b.translate(5, 0);
		graphPanel.refresh();
	});
	translateFiveUp.addActionListener(s->{
		b.translate(0,5);
		graphPanel.refresh();
	});
	translateFiveLeft.addActionListener(s->{
		b.translate(-5,0);
		graphPanel.refresh();
	});
	translateFiveDown.addActionListener(s->{
		b.translate(0,-5);
		graphPanel.refresh();
	});
	
	translateOp.add(translateFive);
	translateOp.add(translateFiveUp);
	translateOp.add(translateFiveLeft);
	translateOp.add(translateFiveDown);
	return translateOp;
    }
    /**
      Adds to menu the options and suboptions for different types of functions
    */
    public JMenu getMenuFunctionOptions(){
	JMenu functionsOp = new JMenu("Functions");
	JMenuItem cosine = new JMenuItem("Cosine");
	JMenuItem sine = new JMenuItem("Sine");
	JMenuItem quadratic = new JMenuItem("x^2");
	JMenuItem customQuad = new JMenuItem("Custom Quadratic");
	JMenuItem arbiFunc = new JMenuItem("Arbitrary Function");

	cosine.addActionListener(s->{
		// If the functon already exists, do not add it
		FunctionR1R1DisplayData cosineFunction = new FunctionR1R1DisplayData(new CosineFunction(), Color.GREEN);
		if(fnsdd != null){
		    if(fnsdd.contains(cosineFunction)==false){
			// Add the function to the list and refresh
			fnsdd.add(cosineFunction);
			graphPanel.refresh();// Update the table
			functionsPanel.displayNewRow();
		    }
		}
	});
	sine.addActionListener(s->{
		// If the functon already exists, do not add it
		FunctionR1R1DisplayData sineFunction = new FunctionR1R1DisplayData(new SineFunction(), Color.BLUE);
		if(fnsdd != null){
		    if(fnsdd.contains(sineFunction)==false){
			// Add the function to the list and refresh
			fnsdd.add(sineFunction);
			graphPanel.refresh();// Update the table
			functionsPanel.displayNewRow();
		    }
		}
	});
	quadratic.addActionListener(s->{
		// If the functon already exists, do not add it
		FunctionR1R1DisplayData quadFunction = new FunctionR1R1DisplayData(new QuadraticFunction(), Color.RED);
		if(fnsdd != null){
		    if(fnsdd.contains(quadFunction)==false){
			// Add the function to the list and refresh
			fnsdd.add(quadFunction);
			graphPanel.refresh();// Update the table
			functionsPanel.displayNewRow();
		    }
		}
	});
	customQuad.addActionListener(s->{
		// If the user has clicked 'OK' on the pop up dialog
		if(quadDialog.display() == true){
		    // Use the user input to create new function, add to list, and refresh
		    fnsdd.add(new FunctionR1R1DisplayData(quadDialog.inputCoeffs(), Color.CYAN));
		    graphPanel.refresh();// Update the table
		    functionsPanel.displayNewRow();
		}
	});
	arbiFunc.addActionListener(s->{
				// If the user has clicked 'OK' on the pop up dialog
		boolean inputedValidFunction=false;
		while(!inputedValidFunction){
			if(arbiDialog.display() == true){
			    // Use user input to create new function, add to list, and refresh
			        String userInput=arbiDialog.getText();
    		 	  	if(CustomFunction.isValidFunction(userInput)){
				   fnsdd.add(new FunctionR1R1DisplayData(new CustomFunction(userInput), Color.RED));
			   	   inputedValidFunction=true;
				}else{
			    	    arbiDialog.addSyntaxError();
		    		}
		   		graphPanel.refresh(); // Update the table
		    		functionsPanel.displayNewRow();
			}else{
				inputedValidFunction=true;//user clicked cancel, exit loop
			}
		}
	});
	// Add buttons to the functions options
	functionsOp.add(cosine);
	functionsOp.add(sine);
	functionsOp.add(quadratic);
	functionsOp.add(customQuad);
	functionsOp.add(arbiFunc);
	return functionsOp;
    }
} // End of class
