import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

import app.*;
import io.*;
import visual.statik.sampled.*;
import visual.*;

/**
 * An example that illustrates the processing of
 * sampled static visual content
 *
 * @author  Prof. David Bernstein, James Madison University
 * @see     "The Design and Implementation of Multimedia Software © 2011"
 * @version 1.0
 */
public class      ImageOperationsDemo
       extends    JApplication
       implements ActionListener
{

  private BufferedImage                        current;    
  private ContentFactory                       contentFactory;    
  private HashMap<String, BufferedImageOp>     ops;
  private IdentityOp                           identityOp;
  private Image                                original;    
  private ImageFactory                         imageFactory;       
  private int                                  imageHeight, imageWidth;    
  private String                               name, opString;
  private Visualization                        visualization;
  private VisualizationView                    view;


  private static final Font   DEFAULT_FONT = 
      new Font("Sans Serif", Font.PLAIN, 11);
  private static final String DEFAULT_IMAGE = "firstsnow.png";  
  private static final String DEFAULT_OP    = "Blur";  


  public static void main(String[] args)
  {
    JApplication demo = new ImageOperationsDemo(args, 600, 800);
    invokeInEventDispatchThread(demo);
  }

  /**
   * Default Constructor
   */
  public ImageOperationsDemo(String[] args, int width, int height)
  {
    super(args, width, height);

    if (args.length > 0) name = args[0];
    else  name = DEFAULT_IMAGE;

    if (args.length > 1) opString = args[1];
    else opString = DEFAULT_OP;



    BufferedImageOpFactory   opFactory;

    opFactory       = BufferedImageOpFactory.createFactory();       
    this.identityOp = new IdentityOp();


    ops = new HashMap<String, BufferedImageOp>();

    ops.put("Blur",       
        opFactory.createBlurOp(3));

    ops.put("Brighten",
        opFactory.createBrightenOp());

    ops.put("Darken",
        opFactory.createDarkenOp());

    ops.put("Edge",
        opFactory.createEdgeDetectionOp(3));

    ops.put("Emboss",
        opFactory.createEmbossOp(3));

    ops.put("Gray",
        opFactory.createGrayOp());

    ops.put("GrayExcept",
        opFactory.createGrayExceptOp(69, 0, 119));

    ops.put("Metal",
        opFactory.createMetalOp());

    ops.put("Negative",
        opFactory.createNegativeOp());

    ops.put("Night",
        opFactory.createNightVisionOp());

    ops.put("Sharpen",
        opFactory.createSharpenOp(3));

    if (ops.get(opString) == null)  opString = DEFAULT_OP;
}

  /**
   * Handle actionPerformed messages
   * (required by ActionListener)
   *
   * @param evt   The ActionEvent that generated the message
   */
  public void actionPerformed(ActionEvent evt)
  {
    BufferedImageOp        op;
    String                 command;

    command = evt.getActionCommand();       

    if (command.startsWith("Reset"))
    {
      reset();
    }
    else
    {
      op = ops.get(command);
      if (op == null) op = identityOp;

      current = op.filter(current, null);
    }

    setContent();
  }

  /**
   * Add a JButton
   *
   * @param p     The JPanel to add it to
   * @param text  The text of the JButton
   * @param x     The x-position
   * @param y     The y-position
   * @param width The width
   */
  private void addButton(JPanel p, String text, 
      int x, int y, int width)
  {
    JButton      button;

    button = new JButton(text);
    button.addActionListener(this);       
    button.setBounds(x, y, width, 30);
    button.setFont(DEFAULT_FONT);       
    p.add(button);       
  }


  /**
   * This method is called just before the main window
   * is first made visible
   */
  public void init()
  {
    int                       y;       
    ResourceFinder            finder;

    original  = null;       

    // Construct a ResourceFinder
    finder    = ResourceFinder.createInstance(resources.Marker.class);          

    // Construct the factories
    imageFactory    = new ImageFactory(finder);
    contentFactory  = new ContentFactory(finder);       

    // Read the BufferedImage
    original        = imageFactory.createBufferedImage(name);
    reset();

    imageHeight = original.getHeight(null);
    imageWidth  = original.getWidth(null);

    // Create the visualization
    visualization = new Visualization();
    view          = visualization.getView();

    view.setBounds(0,0,imageWidth,imageHeight);     
    view.setSize(imageWidth,imageHeight);       
    setContent();

    // Add the ImageCanvas to the main window
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(view);

    // Add the buttons to the content pane
    y = imageHeight + 10;       
    addButton(contentPane, opString,       0, y, 100);       
    addButton(contentPane, "Reset",      150, y, 100);       
  }

  /**
   * Reset to the original image
   */
  protected void reset()
  {
    current      = imageFactory.createBufferedImage(original);          
  }

  /**
   * Set the Content
   */
  protected void setContent()
  {
    Content                temp;

    temp = contentFactory.createContent(current);
    temp.setLocation(0,0);     

    visualization.clear();
    visualization.add(temp);
  }
}

