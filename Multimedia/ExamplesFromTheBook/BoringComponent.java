import java.awt.*;
import javax.swing.*;

/**
 * A concrete extension of a JComponent that illustrates
 * the process of obtaining and using a rendering engine
 *
 * @version 1.0
 * @author  Prof. David Bernstein, James Madison University
 */
public class BoringComponent extends JComponent
{
  private static final long serialVersionUID = 1L;

  /**
   * Render this BoringComponent
   *
   * @param g   The rendering engine to use
   */
  @SuppressWarnings("unused")
  public void paint(Graphics g)
  {
    Graphics2D         g2;

    // Cast the rendering engine appropriately
    g2 = (Graphics2D)g;


    // Put the rendering code here
  }
}

