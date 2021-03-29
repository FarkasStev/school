import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

/**
 * A panel that displays a GlyphVector centered
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class CenteredGlyphCanvas extends GlyphCanvas
{
  private static final long serialVersionUID = 1L;

  /**
   * Default Constructor
   */
  public CenteredGlyphCanvas()
  {
    super();
  }

  /**
   * Render the glyphs centered
   *
   * Note: There are better approaches.  This method
   * is used for illustrative purposes only.
   */
  protected void paintGlyphs(Graphics2D g2, String text)
  {
    FontRenderContext       frc;
    GlyphVector             glyphs;
    Rectangle2D             bounds;
    Shape                   shape;

    frc = g2.getFontRenderContext();
    glyphs = font.createGlyphVector(frc, text);

    g2.setColor(Color.black);

    // Get the outline at (0,0)
    shape = glyphs.getOutline();

    //[bounds
    // Get the bounding box
    bounds = glyphs.getVisualBounds();
    //]bounds

    //[center
    Dimension               d;
    float                   x, y;

    d = getSize();

    // Center the text
    x = (float)(d.width/2.  - bounds.getWidth()/2. );
    y = (float)(d.height/2. + bounds.getHeight()/2.);

    // Get the outline when centered
    shape = glyphs.getOutline(x,y);
    //]center

    g2.draw(shape);
  }
}
