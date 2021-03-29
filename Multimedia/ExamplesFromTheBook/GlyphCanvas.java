import java.awt.*;
import java.awt.font.*;
import javax.swing.*;


/**
 * A JComponent that displays a GlyphVector
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class GlyphCanvas extends JComponent
{
  private static final long serialVersionUID = 1L;

  protected Font                font;
  protected String              text;

  /**
   * Default Constructor
   */
  public GlyphCanvas()
  {
    super();

    // Construct a (logical) font
    font  = new Font("Serif", Font.ITALIC, 100);
  }

  /**
   * Paint this component
   *
   * @param g   The Graphics context to use
   */
  public void paint(Graphics g)
  {
    Graphics2D              g2;

    g2 = (Graphics2D)g;

    // Set the font
    g2.setFont(font);

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

  //[paintGlyphs
  /**
   * Render a String
   */
  protected void paintGlyphs(Graphics2D g2, String text)
  {
    FontRenderContext       frc;
    GlyphVector             glyphs;
    Shape                   shape;

    frc = g2.getFontRenderContext();

    glyphs = font.createGlyphVector(frc, text);
    shape = glyphs.getOutline(0.0f, 100.0f);

    g2.setColor(Color.BLACK);
    g2.draw(shape);
  }
  //]paintGlyphs

  /**
   * Set the text associated with this component
   */
  public void setText(String text)
  {
    this.text = text;
  }
}
