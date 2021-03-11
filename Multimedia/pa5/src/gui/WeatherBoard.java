package gui;

// Java libraries
import javax.swing.*;

// Application libraries
import weather.*;

/**
 * A JComponent that displays information about WeatherDatum objects.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class WeatherBoard extends JTextArea implements WeatherObserver
{
  private static final long serialVersionUID = 1L;

  /**
   * Default Constructor.
   */
  public WeatherBoard()
  {
    super();
  }

  /**
   * Handle a new/changed WeatherDatum object (required by WeatherObserver).
   *
   * @param datum The new/changed WeatherDatum object
   */
  @Override
  public void handleWeatherDatum(final WeatherDatum datum)
  {
    append(datum.toString(true) + "\n");
  }
  
  /**
   * Reset this WeatherObserver.
   */
  @Override
  public void reset()
  {
    setText("");
  }
  
  /**
   * Set the bounds of this WeatherBoard.
   *
   * @param x The x-coordinate (in pixels)
   * @param y The y-coordinate (in pixels)
   * @param width The width (in pixels)
   * @param height The height (in pixels)
   */
  @Override
  public void setBounds(final int x, final int y, final int width, final int height)
  {
    super.setBounds(x, y, width, height);
  }
}
