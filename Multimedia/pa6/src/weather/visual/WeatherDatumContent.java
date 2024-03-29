package weather.visual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;

import weather.WeatherDatum;

public class WeatherDatumContent implements visual.statik.SimpleContent
{
  protected Color color;
  protected Image image;
  protected Point2D location;
  protected WeatherDatum datum;

  public WeatherDatumContent(final WeatherDatum datum, final Color color, final Point2D location,
      final Image image)
  {
    this.datum = datum;
    this.color = color;
    this.location = location;
    this.image = image;
  }

  @Override
  public void render(Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;
    g2.drawImage(image, (int) location.getX() - image.getWidth(null) / 2,
        (int) location.getY() - image.getHeight(null) / 2, null);
    g2.setColor(color);
    g2.drawString(datum.toString(), (int) location.getX() - datum.toString().length()*3,
        (int) location.getY() + image.getHeight(null));
  }

}
