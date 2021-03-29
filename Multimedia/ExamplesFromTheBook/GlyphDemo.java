import javax.swing.*;

import app.*;

/**
 * An example that illustrates the rendering of glyphs/fonts
 * (using various different classes)
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class   GlyphDemo extends JApplication
{
  private GlyphCanvas    canvas;
  private String         text;

  public static void main(String[] args)
  {
    JApplication demo = new GlyphDemo(args, 400, 400);
    invokeInEventDispatchThread(demo);
  }

  public GlyphDemo(String[] args, int width, int height)
  {
    super(args, width, height);

    String type;
    if (args.length < 1) type = "GlyphCanvas";
    else                 type = args[0];

    if (type.startsWith("AttributedStringCanvas"))
      canvas = new AttributedStringCanvas();
    else if (type.startsWith("CenteredGlyphCanvas"))
      canvas = new CenteredGlyphCanvas();
    else if (type.startsWith("GlyphMetricsCanvas"))
      canvas = new GlyphMetricsCanvas();
    else if (type.startsWith("SpiralGlyphCanvas"))
      canvas = new SpiralGlyphCanvas();
    else
      canvas = new GlyphCanvas();

    if (args.length < 2) text = "Glyphs";
    else                 text = args[1];
  }

  /**
   * <p>
   * The entry point
   * </p>
   *
   * <p>
   * Argument 0 controls the operation of this driver by determining
   * the type of GlyphCanvas to use.  Possibilities include:
   * </p>
   * 
   * <ul>
   *    GlyphCanvas <br/>
   *    GlyphMetricsCanvas <br/>
   *    CenteredGlyphCanvas <br/>
   *    SpiralGlyphCanvas <br/>
   *    AttributedStringCanvas <br/>
   * </ul>
   */
  public void init()
  {
    canvas.setBounds(0,0,400,400);

    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(canvas);

    canvas.setText(text);
    canvas.repaint();
  }
}
