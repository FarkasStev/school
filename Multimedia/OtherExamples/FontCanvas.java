import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * A JComponent that contains all of the font families available
 * on the system
 *
 * @version 1.0
 * @author  Prof. David Bernstein, James Madison University
 */
public class FontCanvas extends JComponent
{
  private static final long serialVersionUID = 1L;

  private Font[]                 fonts;
  private int                    current;
  private String[]               fontFamilies, sample;

  /**
   * Explicit Value Constructor
   *
   * @param fSize  The font size to use (in points)
   */
  //[constructor
  public FontCanvas(int fSize)
  {
    GraphicsEnvironment    ge;
    int                    i;

    current    = 0;

    // Get the available font families
    ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    fontFamilies = ge.getAvailableFontFamilyNames();

    // Construct the fonts and text samples
    fonts  = new Font[fontFamilies.length];
    sample = new String[fontFamilies.length];

    for (i=0; i < fontFamilies.length; i++) 
    {
      fonts[i] = new Font(fontFamilies[i], 
          Font.PLAIN, fSize);

      sample[i] = fontFamilies[i]+
          "     abcdefABCDEF123456!@#^" + 
          "     Av     fi   " + 
          "     \u00c0 \u00c1 \u00c2 \u00c7";

      if (fontFamilies[i].equalsIgnoreCase("Dialog"))
      {
        current = i;             
      }
    }
  }
  //]constructor

  /**
   * Get an array of available font families
   *
   * @return   The array of font family names
   */
  public String[] getFontFamilies()
  {
    return fontFamilies;       
  }

  /**
   * Paint this component
   *
   * @param g   The Graphics context to use
   */
  public void paint(Graphics g)
  {
    Dimension           d;
    float               ascent, baseline, descent, height, leading, pWidth;
    FontRenderContext   frc;
    Graphics2D          g2;
    LineMetrics         lm;

    super.paint(g);
    g2 = (Graphics2D)g;

    d = getSize();

    g2.setFont(fonts[current]);
    // Use antialiasing for text and shapes
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    // Use high-quality rendering
    g2.setRenderingHint(RenderingHints.KEY_RENDERING,
        RenderingHints.VALUE_RENDER_QUALITY);

    // Use floating point for font metrics
    g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
        RenderingHints.VALUE_FRACTIONALMETRICS_ON);

    frc = g2.getFontRenderContext();
    lm = fonts[current].getLineMetrics(sample[current], frc);

    baseline = (float)(d.height)/2.0f;
    pWidth    = (float)(d.width);

    ascent  = lm.getAscent();
    descent = lm.getDescent();
    height  = lm.getHeight();
    leading = lm.getLeading(); // Pronounced led-ing (as in the metal)

    g2.setColor(Color.BLACK);
    g2.drawString(sample[current], 0.0f, baseline);
    g2.drawString("Second Line in English: Hello"
        , 0.0f, baseline+height);
    g2.drawString("A Line in Thai: "+
        "\u0e44\u0e1b\u0e44\u0e2b\u0e19 "
        , 0.0f, baseline+2*height);
    g2.drawString("A Line in Arabic: "+
        "\u0645\u0631\u062d\u0628\u0627 "
        , 0.0f, baseline+height*3);

    g2.setColor(Color.BLUE);
    g2.draw(new Line2D.Float(0.0f, baseline-ascent, 
        pWidth, baseline-ascent));

    g2.setColor(Color.WHITE);
    g2.draw(new Line2D.Float(0.0f, baseline, 
        pWidth, baseline));

    g2.setColor(Color.RED);
    g2.draw(new Line2D.Float(0.0f, baseline+descent, 
        pWidth, baseline+descent));

    if (leading > 0) 
    {
      g2.setColor(Color.GREEN);
      g2.draw(new Line2D.Float(0.0f, baseline+descent+leading, 
          pWidth, baseline+descent+leading));
    }

    g2.setColor(Color.BLACK);
  }

  /**
   * Set the Font
   *
   * @param i   The index of the Font
   */
  public void setFont(int i)
  {
    current = i;
  }
}
