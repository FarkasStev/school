import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

/**
 * A panel that displays a GlyphVector
 * in a spiral
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class SpiralGlyphCanvas extends GlyphCanvas
{
  private static final long serialVersionUID = 1L;

  /**
   * Constructor
   */
  public SpiralGlyphCanvas()
  {
    super();
  }

  //[paintGlyphs
  /**
   * Render the glyphs
   */
  protected void paintGlyphs(Graphics2D g2, String text)
  {
    Dimension d = getSize();

    FontRenderContext frc = g2.getFontRenderContext();
    GlyphVector glyphs = font.createGlyphVector(frc, text);
    Shape shape = glyphs.getOutline(0.0f, 100.0f);
    g2.setColor(Color.BLACK);

    for (int i=0; i < 6; i++) 
    {
      float angle = (float)(Math.PI/6.0 * i);
      float x = (float)(d.width/2.0);
      float y = (float)(d.height/2.0);
      AffineTransform at = AffineTransform.getRotateInstance(angle,x,y);
      AffineTransform trans = AffineTransform.getTranslateInstance(x,y);
      at.concatenate(trans);

      Shape transformedShape = at.createTransformedShape(shape);

      g2.fill(transformedShape);
    }
  }
  //]paintGlyphs
}
