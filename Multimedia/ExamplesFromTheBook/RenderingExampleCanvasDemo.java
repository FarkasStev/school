import javax.swing.*;

import app.*;

/**
 * An example that illustrates the rendering of described visual
 * content(using the RenderingExampleCanvas class)
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class   RenderingExampleCanvasDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new RenderingExampleCanvasDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public RenderingExampleCanvasDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    RenderingExampleCanvas         canvas;

    canvas = new RenderingExampleCanvas();
    canvas.setBounds(0,0,400,400);

    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(canvas);
  }
}
