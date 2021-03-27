package weatherbitmaps;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
//Java libraries
import javax.swing.*;

//Multimedia libraries
import app.*;
import gui.*;
import io.ResourceFinder;
import resources.Marker;
import visual.VisualizationView;
import visual.dynamic.sampled.Screen;
import visual.statik.sampled.Content;
//Application libraries
import weather.*;

/**
 * An application that displays textual descriptions of weather observations or forecasts.
 * 
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class TextApplication extends WeatherBitmapsApplication
{
  private WeatherBoard weatherBoard;

  /**
   * Explicit value constructor.
   * 
   * @param args
   *          The command line arguments
   */
  public TextApplication(final String[] args)
  {
    super(args);
    weatherBoard = new WeatherBoard();
  }

  /**
   * Get the GUI component that will be used to display the weather information.
   * 
   * @return The WeatherObserverPanel
   */
  @Override
  protected JComponent getGUIComponent()
  {
    return weatherBoard;
  }

  /**
   * Get the WeatherObserver to inform of changes.
   * 
   * @return The WeatherObserverPanel
   */
  @Override
  protected WeatherObserver getWeatherObserver()
  {
    return weatherBoard;
  }

  /**
   * Handle the ABOUT button.
   */
  protected void handleAbout()
  {
    Screen screen = new Screen(6);

    ResourceFinder rf = ResourceFinder.createInstance(new Marker());
    String[] resourceNames = rf.loadResourceNames("vortex.txt");
    Content currentFrame;
    InputStream is;
    for (int i = 0; i < resourceNames.length; i++)
    {
      is = rf.findInputStream(resourceNames[i]);
      BufferedImage bi;
      try
      {
        bi = ImageIO.read(is);
        currentFrame = new Content(bi, 0, 0);
        screen.add(currentFrame);
      }
      catch (IOException e)
      {
        JOptionPane.showMessageDialog(getGUIComponent(),
            "There was a problem reading " + resourceNames[i], "Error", JOptionPane.ERROR_MESSAGE);
      }

    }
    JFrame frame = new JFrame("About WeatherBits");
    frame.setSize(300, 330);
    frame.add(screen.getView());
    PopupFactory pfac = PopupFactory.getSharedInstance();
    pfac.getPopup(frame, screen.getView(), 0, 0);
    frame.setVisible(true);

    screen.setRepeating(false);

    Iterator<VisualizationView> frames = screen.getViews();
    VisualizationView temp;
    JLabel superLabel = new JLabel("Text Application v2.0");
    superLabel.setBounds(75, 110, 200, 20);
    screen.start();

    while (frames.hasNext())
    {
      frame.add(superLabel);
      temp = frames.next();
      frame.add(temp);

    }

  }

  /**
   * Construct and invoke (in the event dispatch thread) an instance of this JApplication.
   * 
   * @param args
   *          The command line arguments
   */
  public static void main(final String[] args)
  {
    JApplication app = new TextApplication(args);
    invokeInEventDispatchThread(app);
  }
}
