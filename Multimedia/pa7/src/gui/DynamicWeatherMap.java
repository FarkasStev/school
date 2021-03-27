package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import io.ResourceFinder;
import map.io.PolygonReader;
import map.io.StationLocationReader;
import resources.Marker;
import visual.statik.sampled.BufferedImageOpFactory;
import visual.dynamic.described.DescribedSprite;
import visual.dynamic.described.Stage;
import visual.statik.described.AggregateContent;
import visual.statik.described.Content;
import visual.statik.sampled.ContentFactory;
import weather.WeatherDatum;
import weather.WeatherObservation;
import weather.WeatherObserver;
import weather.visual.WeatherDatumContent;
import weather.visual.WeatherDatumContentFactory;
import weather.visual.WeatherIconReader;

public class DynamicWeatherMap extends Stage implements WeatherObserver
{
  private static Color BACKGROUND_COLOR = new Color(204, 204, 255);
  private visual.statik.sampled.Content watermark;
  private List<WeatherDatumContent> currentContent;
  DescribedSprite stormTrack;

  public DynamicWeatherMap(final String useWatermark, final String grayWatermark, final int width,
      final int height)
  {
    super(50);
    this.setBackground(BACKGROUND_COLOR);
    PolygonReader shapeReader = new PolygonReader();
    try
    {
      Shape shape = shapeReader.read("harrisonburg.map");
      Content content = new Content(shape, new Color(0, 0, 0), new Color(255, 255, 255),
          new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.CAP_ROUND));

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

    String[] names = {"track-500.map", "track-1500.map", "track-4000.map"};
    Content[] contents = {new Content(), new Content(), new Content()};
    Shape[] shapes = new Shape[3];

    try
    {
      for (int i = 0; i < 3; i++)
      {
        BufferedReader br = new BufferedReader(new FileReader(new File(names[i])));
        String line;
        Path2D bodyShape = new Path2D.Float();

        while ((line = br.readLine()) != null)
        {
          String[] fields = line.split(",");
          if (Integer.parseInt(fields[0]) == 4)
          {
            bodyShape.moveTo(Double.parseDouble(fields[1]), Double.parseDouble(fields[2]));
          }
          else
          {
            bodyShape.lineTo(Double.parseDouble(fields[1]), Double.parseDouble(fields[2]));
          }

        }
        bodyShape.closePath();
        shapes[i] = bodyShape;
        contents[i].setShape(bodyShape);
        contents[i].setPaint(new Color(128, 128, 128, 128));
      }

    }
    catch (IOException | NumberFormatException e)
    {
      JOptionPane.showMessageDialog(null, "There was a problem reading storm track.", "Error",
          JOptionPane.ERROR_MESSAGE);
    }

    AggregateContent track500 = new AggregateContent();
    track500.add(contents[0]);
    AggregateContent track1500 = new AggregateContent();
    track1500.add(contents[1]);
    AggregateContent track4000 = new AggregateContent();
    track4000.add(contents[2]);

    stormTrack = new DescribedSprite();
    stormTrack.addKeyTime(500, new Point(0, 0), 0.0, 1.0, track500);
    stormTrack.addKeyTime(1500, new Point(0, 0), 0.0, 1.0, track1500);
    stormTrack.addKeyTime(4000, new Point(0, 0), 0.0, 1.0, track4000);
    this.add(stormTrack);
    this.setRestartTime(4000);
    this.start();

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
