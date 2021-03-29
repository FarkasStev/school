import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * A JComponent that renders some simple geometric shapes
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class   SimpleShapeCanvas extends JComponent
{
  private static final long serialVersionUID = 1L;

  /**
   * Default Constructor
   */
  public SimpleShapeCanvas()
  {
    super();
  }

  /**
   * Paint this Component
   *
   * @param g   The rendering engine to use
   */
  @SuppressWarnings("unused")
  public void paint(Graphics g)
  {

    Graphics2D g2 = (Graphics2D)g;

    // Use antialiasing for text and shapes
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    // Use high-quality rendering
    g2.setRenderingHint(RenderingHints.KEY_RENDERING,
        RenderingHints.VALUE_RENDER_QUALITY);

    //[point
    // Construct a point
    Point2D point = new Point2D.Double(20.0, 30.0);
    //]point

    //[line
    // Construct a line
    Line2D line = new Line2D.Double(0.0, 0.0, 50.0, 75.0);
    //]line

    // Draw the line in black
    g2.setColor(Color.BLACK);
    g2.draw(line);

    //[quadcurve
    // Construct a quadratic curve
    QuadCurve2D quadraticCurve = new QuadCurve2D.Double(
        120.0, 120.0,  // End 1
        300.0, 180.0,  // Control
        130.0, 190.0); // End 2
    //]quadcurve

    // Draw the quadratic curve in blue
    g2.setColor(Color.BLUE);
    g2.draw(quadraticCurve);

    //[cubiccurve
    // Construct a cubic curve
    CubicCurve2D cubicCurve = new CubicCurve2D.Double(
        320.0, 320.0,  // End 1
        300.0, 180.0,  // Control 1
        330.0, 370.0,  // End 2
        360.0, 390.0); // Control 2
    //]cubiccurve

    // Draw the cubic curve in green
    g2.setColor(Color.GREEN);
    g2.draw(cubicCurve);

    //[rectangle
    // Construct a rectangle
    Rectangle2D rectangle = new Rectangle2D.Double(
        10.0, 20.0, // Upper Left
        30.0,       // Width
        60.0);      // Height
    //]rectangle

    // Draw the rectangle in red
    g2.setColor(Color.RED);
    g2.draw(rectangle);

    //[arc
    // Construct an arc
    Arc2D arc = new Arc2D.Double(
        10.0, 200.0, // Upper Left
        150.0,       // Width
        100.0,       // Height
        0.0,         // Starting Angle
        135.0,       // Angular Extent        
        Arc2D.PIE);  // Type        
    //]arc

    // Draw the arc in magenta
    g2.setColor(Color.MAGENTA);
    g2.draw(arc);
  }
}

