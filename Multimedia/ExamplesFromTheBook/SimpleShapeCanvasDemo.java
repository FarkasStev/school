import javax.swing.*;

import app.*;

/**
 * An example that illustrates the rendering of described visual
 * content
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class   SimpleShapeCanvasDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new SimpleShapeCanvasDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public SimpleShapeCanvasDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    SimpleShapeCanvas canvas = new SimpleShapeCanvas();
    canvas.setBounds(0,0,400,400);

    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(canvas);
  }
}
