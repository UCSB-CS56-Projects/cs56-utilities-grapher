import javax.swing.JFrame;

/**
   Class to start the grapher utility
   @author Ryan Halbrook
   @version CS56 Spring 2013
*/
public class GrapherApplication {

    public static final String TITLE = "2D Function Grapher";
    public static final int WIDTH = 500;
    public static final int HEIGHT = 350;

    public static void main(String [] args) {
	JFrame appFrame = new JFrame(TITLE);
	appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	appFrame.setSize(WIDTH, HEIGHT);

	Graph2DPanel graphPanel = new Graph2DPanel(new SineFunction());
	appFrame.getContentPane().add(graphPanel);

	appFrame.setVisible(true);
    }

} // End of class
