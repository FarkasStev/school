import javax.swing.*;

import app.*;
import visual.*;

/**
 * An example that illustrates the use of described
 * static visual content
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class BuzzyDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new BuzzyDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public BuzzyDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }


  /**
   * This method is called just before the main window
   * is first made visible
   */
  public void init()
  {
    // Construct the Visualization
    Visualization visualization = new Visualization();

    // Add Buzzy to the Visualization
    BoringBuzzy buzzy  = new BoringBuzzy();
    visualization.add(buzzy);       

    // Get the VisualizationView
    VisualizationView view = visualization.getView();       
    view.setDoubleBuffered(false);

    // Add the VisualizationView to the main window
    JPanel contentPane = (JPanel)getContentPane();
    view.setBounds(0,0,100,100);       
    contentPane.add(view);
  }
}
