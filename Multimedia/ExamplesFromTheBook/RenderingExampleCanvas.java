import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * A VisualComponent that illustrates the rendering of
 * described visual content
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class RenderingExampleCanvas extends JComponent
{
  private static final long serialVersionUID = 1L;

  /**
   * Default Constructor
   */
  public RenderingExampleCanvas()
  {
    super();
  }

  /**
   * Paint this Component
   *
   * @param g   The rendering engine to use
   */
  public void paint(Graphics g)
  {
    Graphics2D g2 = (Graphics2D)g;

    // Use antialiasing for text and shapes
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    // Use high-quality rendering
    g2.setRenderingHint(RenderingHints.KEY_RENDERING,
        RenderingHints.VALUE_RENDER_QUALITY);


    //[1
    // The first rectangle
    Rectangle2D rectangle = new Rectangle2D.Double(
        10.0,   20.0,
        100.0, 150.0);     

    // Fill in JMU Gold
    g2.setPaint(new Color(0xC2, 0xA1, 0x4D));
    g2.fill(rectangle);


    // Stroke in JMU purple
    Stroke stroke = new BasicStroke(5.0f, 
        BasicStroke.CAP_BUTT, 
        BasicStroke.JOIN_MITER);
    g2.setStroke(stroke);       
    g2.setColor(new Color(0x45, 0x00, 0x84));
    g2.draw(rectangle);

    //]1

    //[2
    // The second rectangle
    rectangle = new Rectangle2D.Double( 200.0, 200.0,
        100.0,       
        150.0);      

    // Fill using a gradient
    GradientPaint gradient=new GradientPaint(
        200.0f, 275.0f, 
        Color.CYAN,
        300.0f, 275.0f, 
        Color.WHITE);       
    g2.setPaint(gradient);
    g2.fill(rectangle);

    // Stroke in black
    stroke = new BasicStroke(10.0f, 
        BasicStroke.CAP_BUTT, 
        BasicStroke.JOIN_ROUND);
    g2.setStroke(stroke);       
    g2.setColor(Color.BLACK);
    g2.draw(rectangle);
    //]2

    //[3
    // The third rectangle
    rectangle = new Rectangle2D.Double( 50.0, 50.0,
        200.0,      
        250.0);     

    // Use alpha blending to achieve a transparency effect
    Composite composite = AlphaComposite.getInstance(
        AlphaComposite.SRC_OVER,
        0.5f);
    g2.setComposite(composite);

    // Fill in yellow
    g2.setPaint(Color.YELLOW);
    g2.fill(rectangle);

    // Don't stroke 
    //]3
  }
}

