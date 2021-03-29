import visual.dynamic.described.*;
import visual.statik.sampled.*;

/**
 * A BigBus is a Sprite that has Sprite objects for the tailpipe
 * exhaust.
 *
 * The Exhaust objects move themsleves but the BigBus object
 * determines their position (so that they are positioned
 * relative to the tailpipe).
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class BigBus extends RuleBasedSprite
{
  private double             maxX, x, y;
  private Exhaust[]          exhaust;

  /**
   * Explicit Value Constructor
   *
   * @param content   The Content to use for the BigBus
   * @param width     The width of the Stage
   * @param height    The height of the Stage
   * @param stage     The Stage
   */
  public BigBus(TransformableContent content,
      double width, double height,
      Stage stage)
  {
    super(content);       

    exhaust = new Exhaust[15];
    for (int i=0; i<exhaust.length; i++)
    {
      exhaust[i] = new Exhaust();
      stage.add(exhaust[i]);          
    }

    x    =   0.0;
    y    = 300.0;       
    maxX = width;
  }

  /**
   * Handle a Metronome tick
   * (required by Sprite [through MetronomeListener])
   *
   * @param millis   The number of milliseconds since the Metronome started
   */
  public void handleTick(int millis)
  {
    // Move the bus
    x = x + 1;
    setLocation(x, y);
    if (x > maxX+50)
    {
      setVisible(false);
      for (int i=0; i<exhaust.length; i++)
        exhaust[i].setVisible(false);
    }

    // Set the origin for the Exhaust objects
    for (int i=0; i<exhaust.length; i++)
      exhaust[i].setOrigin(x, y+63);

    // Inform the Exhaust objects that a tick has occurred
    for (int i=0; i<exhaust.length; i++)
      exhaust[i].handleTick(millis);
  }
}
