import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * A JPanel that performs constructive area geometryt operations
 * on an Ellipse2D and a Rectangle2D
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class   ConstructiveAreaGeometryCanvas extends JComponent
{
  private static final long serialVersionUID = 1L;
  private static final Color GOLD  =new Color(194,161, 77);
  private static final Color PURPLE=new Color( 69,  0,132);

  private Ellipse2D        ellipse;
  private Rectangle2D      rectangle;
  private String           op;


  /**
   * Default Constructor
   */
  public ConstructiveAreaGeometryCanvas()
  {
    ellipse   = new Ellipse2D.Double(20, 20, 100, 100);
    rectangle = new Rectangle2D.Double(60, 40, 200, 200); 

    op = "Outline";
  }

  /**
   * Paint this Component
   *
   * @param g   The rendering engine to use
   */
  public void paint(Graphics g)
  {
    Area               e, r;
    Graphics2D         g2;


    g2 = (Graphics2D)g;

    if (op.equalsIgnoreCase("Outline"))
    {
      g2.setPaint(Color.BLACK);
      g2.draw(ellipse);
      g2.draw(rectangle);

    }
    else
    {
      //[1
      e = new Area(ellipse);
      r = new Area(rectangle);

      if      (op.equalsIgnoreCase("Union"))        
        e.add(r);
      else if (op.equalsIgnoreCase("Intersection")) 
        e.intersect(r);
      else if (op.equalsIgnoreCase("Difference"))   
        e.subtract(r);
      else                                          
        e.exclusiveOr(r);

      g2.setPaint(GOLD);
      g2.fill(e);
      g2.setPaint(PURPLE);
      g2.draw(e);
      //]1
    }
  }

  /**
   * Set the CAG operation (Outline, Union, Intersection,
   * Difference, or Symmetric_Difference)
   *
   * @op  The operation
   */
  public void setOperation(String op)
  {
    this.op = op;
  }
}
