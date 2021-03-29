import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

/**
 * A VisualComponent that contains CubicCurve2D and QuadCurve2D objects
 * that can be edited interactively
 *
 * @author  Prof. David Bernstein, James Madison University
 * @see     "The Design and Implementation of Multimedia Software © 2011"
 * @version 1.0
 */
public class      CurveCanvas 
       extends    JComponent
       implements MouseListener, MouseMotionListener
{
  private static final long serialVersionUID = 1L;

  private HashSet<Object> curves;
  private int             pointIndex;
  private Object          selected;
  private Point2D[]       selectedPoints;
  private Stroke          curveStroke, tangentStroke;


  private static final float[] dash = {10.0f, 5.0f};

  /**
   * Default Constructor
   */
  public CurveCanvas()
  {
    curves = new HashSet<Object>();

    pointIndex     = -1;
    selected       = null;
    selectedPoints = null;

    addMouseListener(this);
    addMouseMotionListener(this);

    curveStroke   = new BasicStroke(2.0f, 
        BasicStroke.CAP_ROUND,
        BasicStroke.JOIN_MITER);

    tangentStroke = new BasicStroke(1.0f, 
        BasicStroke.CAP_ROUND,
        BasicStroke.JOIN_MITER,	
        1.0f, 
        dash, 0.0f);
  }



  /**
   * Add a curve
   *
   * @param curve  Either a CubicCruve2D or a QuadCurve2D
   */
  public void addCurve(Object curve)
  {
    curves.add(curve);
  }

  /**
   * Handle mouseClicked events (required by MouseListener)
   * 
   * Specifically, determine which curve was selected
   *
   * @param evt  The MouseEvent
   */
  public void mouseClicked(MouseEvent evt)
  {
    boolean           done;
    CubicCurve2D      cubic;
    Iterator<Object>  i;
    Object            o;
    QuadCurve2D       quad;

    selected = null;
    done     = false;

    // Find the selected curve
    i = curves.iterator();
    while (i.hasNext() && !done) 
    {
      o = i.next();

      // Handle CubicCurve2D objects
      if (o instanceof CubicCurve2D) 
      {
        cubic = (CubicCurve2D)o;
        if (cubic.contains(evt.getPoint())) 
        {
          selected = cubic;
          selectedPoints = new Point2D.Double[4];
          selectedPoints[0] = cubic.getP1();
          selectedPoints[1] = cubic.getCtrlP1();
          selectedPoints[2] = cubic.getCtrlP2();
          selectedPoints[3] = cubic.getP2();
          done = true;
        }
      } // Handle QuadCurve2D objects
      else if (o instanceof QuadCurve2D) 
      {
        quad = (QuadCurve2D)o;
        if (quad.contains(evt.getPoint())) 
        {
          selected = quad;
          selectedPoints = new Point2D.Double[3];
          selectedPoints[0] = quad.getP1();
          selectedPoints[1] = quad.getCtrlPt();
          selectedPoints[2] = quad.getP2();
          done = true;
        }
      }
    }
    repaint();
  }

  /**
   * Handle mouseDragged events (required by MouseMotionListener)
   *
   * Specifically, change the selected point
   *
   * @param evt  The MouseEvent
   */
  public void mouseDragged(MouseEvent evt)
  {
    Point        p;

    if (selected != null) 
    {
      p = evt.getPoint();
      selectedPoints[pointIndex].setLocation(p.getX(), 
          p.getY());

      if (selected instanceof CubicCurve2D) 
      {
        ((CubicCurve2D)selected).setCurve(selectedPoints, 
            0);
      } 
      else if (selected instanceof QuadCurve2D) 
      {
        ((QuadCurve2D)selected).setCurve(selectedPoints, 
            0);
      }
      repaint();
    }
  }

  /**
   * Handle mouseEntered events (required by MouseListener)
   *
   * @param evt  The MouseEvent
   */
  public void mouseEntered(MouseEvent evt)
  {
    // Ignore
  }

  /**
   * Handle mouseExited events (required by MouseListener)
   *
   * @param evt  The MouseEvent
   */
  public void mouseExited(MouseEvent evt)
  {
    // Ignore
  }

  /**
   * Handle mouseMoved events (required by MouseMotionListener)
   *
   * @param evt  The MouseEvent
   */
  public void mouseMoved(MouseEvent evt)
  {
    // Ignore
  }

  /**
   * Handle mousePressed events (required by MouseListener)
   * 
   * Specifically, determine which point of the
   * selected curve was chosen
   *
   * @param evt  The MouseEvent
   */
  public void mousePressed(MouseEvent evt)
  {
    double            d, minDist;
    int               i;
    Point             p;

    if (selected != null) 
    {
      p          = evt.getPoint();
      pointIndex = 0;
      minDist=selectedPoints[pointIndex].distance(p.getX(), 
          p.getY());

      for (i=1; i < selectedPoints.length; i++) 
      {
        d = selectedPoints[i].distance(p.getX(), p.getY());

        if (d < minDist) 
        {
          minDist = d;
          pointIndex  = i;
        }
      }
    }
  }

  /**
   * Handle mouseReleased events (required by MouseListener)
   *
   * Specifically, de-select any curves and points
   *
   * @param evt  The MouseEvent
   */
  public void mouseReleased(MouseEvent evt)
  {
    selected       = null;
    selectedPoints = null;
    pointIndex     = -1;

    repaint();
  }

  /**
   * Paint this Component
   *
   * @param g   The rendering engine to use
   */
  public void paint(Graphics g)
  {
    CubicCurve2D           cubic;
    Graphics2D             g2;
    Iterator<Object>       i;
    Object                 o;
    QuadCurve2D            quad;

    g2 = (Graphics2D)g;

    // Use antialiasing for text and shapes
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    // Use high-quality rendering
    g2.setRenderingHint(RenderingHints.KEY_RENDERING,
        RenderingHints.VALUE_RENDER_QUALITY);


    // Paint the curves
    i = curves.iterator();
    while (i.hasNext()) 
    {
      g2.setColor(Color.BLACK);
      g2.setStroke(curveStroke);
      o = i.next();

      // Handle CubicCurve2D objects
      if (o instanceof CubicCurve2D) 
      {
        cubic = (CubicCurve2D)o;
        g2.draw(cubic);

        if (cubic == selected) 
        {
          g2.setColor(Color.GRAY);
          g2.setStroke(tangentStroke);
          g2.draw(new Line2D.Double(cubic.getP1(), 
              cubic.getCtrlP1()));

          g2.draw(new Line2D.Double(cubic.getP2(), 
              cubic.getCtrlP2()));
        }
      } // Handle QuadCurve2D objects
      else if (o instanceof QuadCurve2D) 
      {
        quad = (QuadCurve2D)o;
        g2.draw(quad);

        if (quad == selected) 
        {
          g2.setColor(Color.GRAY);
          g2.setStroke(tangentStroke);
          g2.draw(new Line2D.Double(quad.getP1(), 
              quad.getCtrlPt()));

          g2.draw(new Line2D.Double(quad.getP2(), 
              quad.getCtrlPt()));
        }
      }
    }
  }
}
