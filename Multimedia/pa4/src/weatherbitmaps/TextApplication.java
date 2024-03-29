package weatherbitmaps;

import javax.swing.JComponent;

import app.JApplication;
import gui.WeatherBoard;
import weather.WeatherObserver;

/**
 * @author Steven Farkas
 * 
 *         This code complies with the JMU honor statement. I have neither given noor recieved any
 *         unauthorized aid on this assignment.
 *
 */
public class TextApplication extends WeatherBitmapsApplication
{
  private WeatherBoard weatherBoard;

  /**
   * The explicit value constructor is passed the command-line arguments. It must pass them to its
   * parent�s constructor. It must then construct the WeatherBoard attribute.
   * 
   * @param args
   *          from command line
   */
  public TextApplication(final String[] args)
  {
    super(args);
    weatherBoard = new WeatherBoard();
  }

  /**
   * Must return the WeatherBoard attribute.
   * 
   * @return the JComponent
   */
  protected JComponent getGUIComponent()
  {
    return weatherBoard;
  }

  /**
   * Must return the WeatherBoard attribute.
   * 
   * @return the WeatherObserver
   */
  protected WeatherObserver getWeatherObserver()
  {
    return weatherBoard;
  }

  /**
   * In keeping with the application multimedia framework being used, this method must be
   * implemented as follows.
   * 
   * @param args
   *          from command line
   */
  public static void main(final String[] args)
  {
    JApplication app = new TextApplication(args);
    invokeInEventDispatchThread(app);

  }
}
