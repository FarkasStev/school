import java.awt.*;
import java.util.*;

import auditory.sampled.*;

/**
 * A graph of a BufferedSound
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class BufferedSoundGraph implements visual.statik.SimpleContent
{
  private BufferedSound    sound;    
  private double           max, min;
  private double           pixelsPerX, pixelsPerY;       
  private int              height, left, top, width;       

  /**
   * Explicit Value Constructor
   *
   * @param sound   The BufferedSound to visualize
   */
  public BufferedSoundGraph(BufferedSound sound)
  {
    double[]                  signal;       
    Iterator<double[]>        iterator;

    this.sound = sound;       
    iterator   = sound.getSignals();

    max        = Double.NEGATIVE_INFINITY;
    min        = Double.POSITIVE_INFINITY;

    while (iterator.hasNext())
    {
      signal = iterator.next();
      for (int i=0; i<signal.length; i++)
      {
        if (signal[i] > max) max = signal[i];
        if (signal[i] < min) min = signal[i];
      }
    }
  }

  /**
   * Render this BufferedSoundgraph
   * (required by SimpleContent)
   *
   * @param g   The rendering engine to use
   */
  public void render(Graphics g)
  {
    double[]                  y;       
    int                       channel, n, oldx, oldy;
    int                       px, py, step;       
    Iterator<double[]>        iterator;
    Rectangle                 bounds;       

    g.setColor(Color.BLACK);       

    bounds   = g.getClipBounds();       

    n        = sound.getNumberOfChannels();       
    height   = (int)bounds.getHeight() / n;
    width    = (int)bounds.getWidth();       
    left     = (int)bounds.getX();
    top      = (int)bounds.getY();       

    pixelsPerY = (double)height / (max - min);       
    pixelsPerX = (double)width  / (double)sound.getNumberOfSamples();       

    //step       = (int)(1.0/pixelsPerX);       
    step       = 1;       

    channel  = 0;       
    iterator = sound.getSignals();
    while (iterator.hasNext())
    {
      y    = iterator.next();
      oldx = -1;
      oldy = -1;          

      for (int i=0; i<y.length; i+=step)
      {
        py = amplitudeToPixel(channel, y[i]);

        px = sampleToPixel(i);             

        if ((oldx != px) || (oldy != py)) g.drawLine(oldx, oldy, px, py);

        oldx = px;
        oldy = py;             
      }

      ++channel;          
    }
  }

  /**
   * Convert the amplitude to a pixel number (in the x direction)
   *
   * @param channel   The channel number
   * @param amplitude The amplitude
   */
  public int amplitudeToPixel(double channel, double amplitude)
  {
    return (int)(top + (channel * height) + 
        (height - ((amplitude-min)*pixelsPerY)));
  }

  /**
   * Convert a sample number to a pixel number (in the y direction)
   *
   * @param sampleNumber  The sample number
   */
  public int sampleToPixel(double sampleNumber)
  {
    return left + (int)(sampleNumber * pixelsPerX);       
  }
}
