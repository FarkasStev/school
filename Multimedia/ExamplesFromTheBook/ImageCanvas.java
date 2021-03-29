//[skeleton1
import java.awt.*;
import javax.swing.*;

/**
 * A concrete extension of a JComponent that illustrates
 * the rendering of sampled static visual content
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class ImageCanvas extends JComponent
{
  private static final long serialVersionUID = 1L;

  private Image        image;

  /**
   * Explicit Value Constructor
   *
   * @param image   The new Image
   */
  public ImageCanvas(Image image)
  {
    this.image = image;
  }
  //]skeleton1

  //[paint
  /**
   * Render this ImageCanvas
   *
   * @param g   The rendering engine to use
   */
  public void paint(Graphics g)
  {
    Graphics2D         g2;

    // Cast the rendering engine appropriately
    g2 = (Graphics2D)g;

    // Render the image
    g2.drawImage(image,  // The Image to render
        0,      // The horizontal coordinate
        0,      // The vertical coordinate
        null);  // An ImageObserver
  }
  //]paint

  //[skeleton2
}
//]skeleton2

