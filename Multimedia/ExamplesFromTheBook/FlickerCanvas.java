import java.awt.*;
import java.awt.geom.Rectangle2D;

import event.*;
import visual.statik.TransformableContent;

/**
 * A Canvas that renders a sequence of "images" without double-buffering
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class FlickerCanvas extends    Canvas
                           implements MetronomeListener
{
  private static final long serialVersionUID = 1L;

  private Dimension                 frameSize;
  private int                       currentFrame, numberOfFrames;
  private Metronome                 metronome;
  private TransformableContent[]    frames;

  /**
   * Default Constructor
   */
  public FlickerCanvas()
  {
    super();

    currentFrame = -1;
    metronome = new Metronome(250);
    metronome.addListener(this);
    metronome.start();
  }

  /**
   * Handle tick events (required by MetronomeListener)
   *
   * Specifically, render the next image
   *
   * @param millis   The number of milliseconds since the Metronome started
   */
  public void handleTick(int millis)
  {
    currentFrame++;
    if (currentFrame >= numberOfFrames) currentFrame = 0;

    repaint();
  }

  /**
   * Get the minimum size
   */
  public Dimension getMinimumSize()
  {
    return getPreferredSize();
  }

  /**
   * Get the preferred size
   */
  public Dimension getPreferredSize()
  {
    return frameSize;
  }

  /**
   * Render this component
   *
   * @param g  The rendering engine
   */
  public void paint(Graphics g)
  {
    super.paint(g);        
    if (currentFrame >= 0) frames[currentFrame].render(g);
  }

  /**
   * Set the frames to render
   *
   * @param frames  The sequence of static visual content
   */
  public void setFrames(TransformableContent[] frames)
  {
    int              h, w;
    Rectangle2D      bounds;

    this.frames = frames;       
    if (frames.length >= 0) numberOfFrames = frames.length;

    bounds = frames[0].getBounds2D(false);
    w      = (int)bounds.getWidth();
    h      = (int)bounds.getHeight();
    if ((w > 0) && (h > 0)) frameSize = new Dimension(w, h);
  }
}
