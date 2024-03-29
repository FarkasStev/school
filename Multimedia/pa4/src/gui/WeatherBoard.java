package gui;

import weather.WeatherDatum;
import weather.WeatherObserver;

/**
 * @author Steven Farkas
 * 
 *         This code complies with the JMU honor statement. I have neither given noor recieved any
 *         unauthorized aid on this assignment.
 *
 */
public class WeatherBoard extends javax.swing.JTextArea implements WeatherObserver
{

  private static final long serialVersionUID = 1L;

  /**
   * Default Constructor.
   */
  public WeatherBoard()
  {
    this.setText(null);
  }

  @Override
  public void handleWeatherDatum(final WeatherDatum datum)
  {
    this.append(datum.toString(true) + "\n");

  }

  @Override
  public void reset()
  {
    this.setText(null);
  }

}
