//[skeleton1
import java.awt.*;
import java.util.Random;
import javax.swing.*;

/**
 * A JComponent that renders a Rectangle with randomly
 * generated attributes each time it's paint method is called
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class RandomRectangleCanvas extends JComponent
{
  private static final long serialVersionUID = 1L;

  private Random      generator;

  /**
   * Default Constructor
   */
  public RandomRectangleCanvas()
  {
    super();
    generator = new Random(System.currentTimeMillis());       
  }
  //]skeleton1

  //[paint
  /**
   * Paint this Component
   *
   * @param g   The rendering engine to use
   */
  public void paint(Graphics g)
  {
    Graphics2D g2 = (Graphics2D)g;

    int maxHeight = getHeight();
    int maxWidth  = getWidth();

    int x      = generator.nextInt(maxWidth  - 1);
    int y      = generator.nextInt(maxHeight - 1);
    int width  = generator.nextInt(maxWidth  - x - 1);
    int height = generator.nextInt(maxHeight - y - 1);

    Rectangle rectangle = new Rectangle(x, y, width, height);

    g2.draw(rectangle);       
  }
  //]paint
  //[skeleton2
}
//]skeleton2
