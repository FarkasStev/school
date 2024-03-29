package weatherbitmaps;

import javax.swing.JComponent;

import app.JApplication;
import gui.StaticWeatherMap;
import weather.WeatherObserver;

public class StaticMapApplication extends WeatherBitmapsApplication
{

  private StaticWeatherMap weatherMap;

  public StaticMapApplication(String[] args)
  {
    super(args);
    if (args.length == 1)
    {
      weatherMap = new StaticWeatherMap(args[0], null, width, height);
    }
    else if (args.length == 2)
    {
      weatherMap = new StaticWeatherMap(args[0], args[1], width, height);
    }
    else
    {
      weatherMap = new StaticWeatherMap(null, null, width, height);
    }
  }

  @Override
  protected JComponent getGUIComponent()
  {
    return weatherMap.getView();
  }

  @Override
  protected WeatherObserver getWeatherObserver()
  {
    // TODO Auto-generated method stub
    return weatherMap;
  }

  public static void main(String[] args)
  {
    JApplication app = new StaticMapApplication(args);
    invokeInEventDispatchThread(app);
  }

}
