package weatherbitmaps;

//Java libraries
import javax.swing.*;

//Multimedia libraries
import app.*;
import gui.*;

//Application libraries
import weather.*;

/**
 * An application that displays textual descriptions of weather 
 * observations or forecasts.
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
   * @param args   The command line arguments
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
   * Construct and invoke  (in the event dispatch thread) 
   * an instance of this JApplication.
   * 
   * @param args The command line arguments
   */
  public static void main(final String[] args)
  {
    JApplication app = new TextApplication(args);
    invokeInEventDispatchThread(app);
  }
}
