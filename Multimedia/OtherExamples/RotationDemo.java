import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

import app.*;
import io.*;
import visual.statik.sampled.*;
import visual.*;

/**
 * An example that illustrates the rotation of sampled static visual
 * content.
 *
 * @author  Prof. David Bernstein, James Madison University
 * @see     "The Design and Implementation of Multimedia Software © 2011"
 * @version 2.0
 */
public class      RotationDemo
       extends    JApplication
       implements ActionListener
{
    private BufferedImage                        current;    
    private BufferedImageOpFactory               opFactory;
    private ContentFactory                       contentFactory;    
    private Image                                original;    
    private ImageFactory                         imageFactory;       
    private int                                  imageHeight, imageWidth;    
    private JPanel                               contentPane;       
    private JTextField                           angleField;    
    private Visualization                        visualization;
    private VisualizationView                    view;


    private static final Font   DEFAULT_FONT = 
                                   new Font("Sans Serif", Font.PLAIN, 11);
    private static final String DEFAULT_IMAGE = "car.png";  

    public static void main(String[] args)
    {
      JApplication demo = new RotationDemo(args, 650, 300);
      invokeInEventDispatchThread(demo);
    }

    
    public RotationDemo(String[] args, int width, int height)
    {
      super(args, width, height);
      opFactory = BufferedImageOpFactory.createFactory();
    }

    /**
     * Handle actionPerformed messages
     * (required by ActionListener)
     *
     * @param evt   The ActionEvent that generated the message
     */
    public void actionPerformed(ActionEvent evt)
    {
       AffineTransformOp      op;
       double                 theta;       
       String                 command, text;
       
       theta   = 0.0;       
       command = evt.getActionCommand();       

       if (command.startsWith("Reset"))
       {
          reset();
       }
       else if (command.startsWith("Rotate"))
       {
          text = angleField.getText();          
          if (text != null) 
          {
             try
             {
                theta = Double.parseDouble(text);
             }
             catch (NumberFormatException nfe)
             {
                theta = 0.0;                
             }
          }

             
          op    = opFactory.createRotateOp(theta, 
                                           current.getWidth(), 
                                           current.getHeight());
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
       String                    name;//, opString;
       
       original  = null;
       if (args.length > 0) name      = args[0];
       else                 name = DEFAULT_IMAGE;

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
       contentPane = (JPanel)getContentPane();
       contentPane.add(view);


       // Add the buttons to the content pane
       y = imageHeight + 10;       
       angleField = new JTextField();
       angleField.setBounds(                  0, y, 80, 30);       
       contentPane.add(angleField);       
       addButton(contentPane, "Rotate",      80, y, 75);

       addButton(contentPane, "Reset",      160, y, 75);       
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

