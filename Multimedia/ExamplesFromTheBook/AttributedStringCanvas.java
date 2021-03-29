import java.awt.*;
import java.awt.font.*;
import java.text.*;

/**
 * A JComponent that displays an AttributedString
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class AttributedStringCanvas extends GlyphCanvas
{
  private static final long serialVersionUID = 1L;

  /**
   * Default Constructor
   */
  public AttributedStringCanvas()
  {
    super();
  }

  /**
   * Paint this component
   *
   * @param g   The Graphics context to use
   */
  public void paint(Graphics g)
  {
    Graphics2D              g2;

    super.paint(g);
    g2 = (Graphics2D)g;

    // Use antialiasing for text and shapes
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    // Use high-quality rendering
    g2.setRenderingHint(RenderingHints.KEY_RENDERING,
        RenderingHints.VALUE_RENDER_QUALITY);

    // Use floating point for font metrics
    g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
        RenderingHints.VALUE_FRACTIONALMETRICS_ON);


    // Render the glyphs
    if (text != null) paintGlyphs(g2, text);
  }

  /**
   * Render the text using attributes
   */
  protected void paintGlyphs(Graphics2D g2, String text)
  {
    //[step1
    AttributedString        as;

    as = new AttributedString(text);
    for (int i=0; i < text.length(); i++) 
    {
      as.addAttribute(TextAttribute.FONT,
          new Font("Serif", Font.PLAIN, (i+10)), 
          i, i+1);
    }
    //]step1

    //[step2
    g2.setColor(Color.BLACK);
    g2.drawString(as.getIterator(), 0, 100);
    //]step2
  }
}
