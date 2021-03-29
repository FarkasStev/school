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
 * @see     "The Design and Implementation of Multimedia Software (c) 2011"
 * @version 1.0
 */
public class      SurveillanceDemo
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
  private String                               name;
  private Visualization                        visualization;
  private VisualizationView                    view;


  private static final Font   DEFAULT_FONT = 
      new Font("Sans Serif", Font.PLAIN, 11);
  private static final String DEFAULT_IMAGE = "wilson-hall.png";  


  public static void main(String[] args)
  {
    JApplication demo = new SurveillanceDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public SurveillanceDemo(String[] args, int width, int height)
  {
    super(args, width, height);
    BufferedImageOpFactory   opFactory;

    opFactory       = BufferedImageOpFactory.createFactory();       
    this.identityOp = new IdentityOp();

    ops = new HashMap<String, BufferedImageOp>();

    ops.put("Sharpen",
        opFactory.createSharpenOp(3));

    ops.put("Zoom",
        opFactory.createScaleOp(2.0, 2.0));

    if (args.length > 0) name = args[0];
    else name = DEFAULT_IMAGE;

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

    if (command.startsWith("Crop"))
    {
      current = crop(command, current);             
    }
    else if (command.startsWith("Reset"))
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
   * Crop an image (to one of four quadrants)
   *
   * @param quadrant   The quadrant
   * @param before     The image to crop
   */
  private BufferedImage crop(String       quadrant,
      BufferedImage before)
  {
    BufferedImage   cropped;       
    int             height, x, y, width;


    x      = 0;
    y      = 0;       
    width  = before.getWidth(); 
    height = before.getHeight();

    if         (quadrant.endsWith("NW"))
    {
      x      = 0; 
      y      = 0; 
      width  = width/2; 
      height = height/2;
    } 
    else if (quadrant.endsWith("NE"))
    {
      x      = width/2;
      y      = 0;
      width  = width/2;
      height = height/2;
    } 
    else if (quadrant.endsWith("SE"))
    {
      x      = width/2; 
      y      = height/2; 
      width  = width/2;
      height = height/2;
    }
    else if (quadrant.endsWith("SW"))
    {
      x      = 0; 
      y      = height/2;
      width  = width/2; 
      height = height/2;
    }

    cropped = before.getSubimage(x, y, width, height);

    return cropped;
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
    addButton(contentPane, "CropNW",       0, y, 75);       
    addButton(contentPane, "CropNE",      80, y, 75);       
    addButton(contentPane, "CropSE",     160, y, 75);       
    addButton(contentPane, "CropSW",     240, y, 75);       
    addButton(contentPane, "Sharpen",    320, y, 75);       
    addButton(contentPane, "Zoom",       400, y, 75);       

    addButton(contentPane, "Reset",      560, y, 75);       
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

