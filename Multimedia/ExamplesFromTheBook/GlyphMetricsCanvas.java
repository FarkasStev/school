import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

/**
 * A VisualComponent that displays a GlyphVector and LineMetrics
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class GlyphMetricsCanvas extends GlyphCanvas
{
  private static final long serialVersionUID = 1L;

  /**
   * Default Constructor
   */
  public GlyphMetricsCanvas()
  {
    super();

    // Construct a (physical) font
    font  = new Font("Default", Font.PLAIN, 100);
  }

  /**
   * Render the glyphs and the metrics
   *
   * Note: There are better approaches.  This method
   * is used for illustrative purposes only.
   */
  @SuppressWarnings("unused")
  protected void paintGlyphs(Graphics2D g2, String text)
  {
    //[step1
    FontRenderContext       frc;
    GlyphVector             glyphs;

    frc     = g2.getFontRenderContext();
    glyphs  = font.createGlyphVector(frc, text);
    //]step1

    //[step2
    LineMetrics             lm;

    lm      = font.getLineMetrics(text, frc);
    //]step2

    //[step3
    // Get the various metrics
    float ascent  = lm.getAscent();
    float descent = lm.getDescent();
    float height  = lm.getHeight();
    float leading = lm.getLeading();
    //]step3

    Dimension d = getSize();
    g2.setColor(Color.BLACK);

    float baseline = (float)(d.height)/2.0f;
    float pWidth   = (float)(d.width);

    // Draw the metrics
    float y = baseline - ascent;
    g2.setColor(Color.BLUE);
    g2.draw(new Line2D.Float(0.0f, y, pWidth, y));

    y = baseline;
    g2.setColor(Color.YELLOW);
    g2.draw(new Line2D.Float(0.0f, y, pWidth, y));

    g2.setColor(Color.RED);
    y = baseline + descent;
    g2.draw(new Line2D.Float(0.0f, y, pWidth, y));

    if (leading > 0) 
    {
      y = baseline + descent + leading;
      g2.setColor(Color.GREEN);
      g2.draw(new Line2D.Float(0.0f, y, pWidth, y));
    }

    // Get the outline at (0,baseline)
    Shape shape = glyphs.getOutline(0, baseline);

    g2.setColor(Color.BLACK);
    g2.draw(shape);
    g2.fill(shape);
  }
}
