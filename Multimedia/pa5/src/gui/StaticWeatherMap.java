package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.ResourceFinder;
import map.io.PolygonReader;
import map.io.StationLocationReader;
import resources.Marker;
import visual.statik.sampled.BufferedImageOpFactory;
import visual.statik.described.Content;
import visual.statik.sampled.ContentFactory;
import weather.WeatherDatum;
import weather.WeatherObservation;
import weather.WeatherObserver;
import weather.visual.WeatherDatumContent;
import weather.visual.WeatherDatumContentFactory;
import weather.visual.WeatherIconReader;

public class StaticWeatherMap extends visual.Visualization implements WeatherObserver
{
  private static Color BACKGROUND_COLOR = new Color(204, 204, 255);
  private visual.statik.sampled.Content watermark;
  private List<WeatherDatumContent> currentContent;

  public StaticWeatherMap(final String useWatermark, final String grayWatermark, final int width,
      final int height)
  {
    super();
    this.setBackground(BACKGROUND_COLOR);
    PolygonReader shapeReader = new PolygonReader();
    try
    {
      Shape shape = shapeReader.read("harrisonburg.map");
      Content content = new Content(shape, new Color(0, 0, 0), new Color(255, 255, 255),
          new BasicStroke(5.0f, BasicStroke.CAP_BUTT, BasicStroke.CAP_ROUND));

      this.add(content);
    }
    catch (IOException e)
    {

    }
    if (useWatermark != null)
    {
      ResourceFinder watermarkFinder = ResourceFinder.createInstance(new Marker());
      ContentFactory cf = new ContentFactory(watermarkFinder);
      watermark = cf.createContent("logoWeatherBits.png");

      if (grayWatermark != null)
      {
        BufferedImageOpFactory factory = BufferedImageOpFactory.createFactory();
        watermark.setBufferedImageOp(factory.createGrayOp());
      }
      watermark.setLocation(width / 2, height - 20);
      add(watermark);
    }

    currentContent = new ArrayList<WeatherDatumContent>();

  }

  public void handleWeatherDatum(final WeatherDatum datum)
  {
    ResourceFinder rf = ResourceFinder.createInstance(new Marker());
    StationLocationReader locs = new StationLocationReader(rf);
    WeatherIconReader imgs = new WeatherIconReader(rf);
    WeatherDatumContentFactory factory;
    try
    {
      factory = new WeatherDatumContentFactory(locs.read("stations.loc"), imgs.read());
      if (datum instanceof WeatherObservation)
      {
        currentContent.add(factory.createContent(datum, new Color(255, 0, 0)));
      }
      else
      {
        currentContent.add(factory.createContent(datum, new Color(0, 0, 255)));
      }

    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    Iterator<WeatherDatumContent> itr = currentContent.iterator();
    while (itr.hasNext())
    {
      add(itr.next());
    }

  }

  public void reset()
  {
    Iterator<WeatherDatumContent> itr = currentContent.iterator();
    while (itr.hasNext())
    {
      remove(itr.next());
    }
    currentContent = new ArrayList<WeatherDatumContent>();
  }

}
