package weatherbitmaps;

import javax.swing.JComponent;

import app.JApplication;
import gui.DynamicWeatherMap;
import weather.WeatherObserver;

public class DynamicMapApplication extends WeatherBitmapsApplication
{

  private DynamicWeatherMap weatherMap;

  public DynamicMapApplication(String[] args)
  {
    super(args);
    if (args.length == 1)
    {
      weatherMap = new DynamicWeatherMap(args[0], null, width, height);
    }
    else if (args.length == 2)
    {
      weatherMap = new DynamicWeatherMap(args[0], args[1], width, height);
    }
    else
    {
      weatherMap = new DynamicWeatherMap(null, null, width, height);
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
    JApplication app = new DynamicMapApplication(args);
    invokeInEventDispatchThread(app);
  }

}
