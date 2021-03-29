import java.awt.geom.*;
import javax.swing.*;

import app.*;

/**
 * An example that illustrates different kinds of curves (using
 * the CurveCanvas class)
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class CurveDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new CurveDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public CurveDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    CurveCanvas canvas = new CurveCanvas();
    canvas.setBounds(0,0,400,400);

    canvas.addCurve(new CubicCurve2D.Double(
        200,200,
        200,150,
        250,174,
        300,300));

    canvas.addCurve(new QuadCurve2D.Double(
        20, 20,
        50, 25,
        100,100));

    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(canvas);
  }
}
