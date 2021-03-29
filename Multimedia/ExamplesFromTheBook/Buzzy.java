import java.awt.*;

import visual.statik.described.*;

/**
 * The abstract parent of all Buzzy objects
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public abstract class Buzzy extends AggregateContent
{
  /**
   * Default Constructor
   */
  public Buzzy()
  {
    super();
    BasicStroke     stroke;
    Color           black, gold, gray, purple;
    Content         body, hair, helmet, visor;

    black  = Color.BLACK;
    gold   = new Color(0xc2,0xa1,0x4d);
    gray   = new Color(0xaa,0xaa,0xaa);
    purple = new Color(0x45,0x00,0x84);

    stroke = new BasicStroke();

    // Hair (rendered first)
    hair = new Content(getHairShape(), purple, null, stroke);
    add(hair);

    // Body
    body = new Content(getBodyShape(), black, purple, stroke);
    add(body);

    // Helmet
    helmet=new Content(getHelmetShape(), black, gold, stroke);
    add(helmet);

    // Visor (rendered last)
    visor=new Content(getVisorShape(), black, gray, stroke);
    add(visor);
  }

  /**
   * Get the Shape for Buzzy's body
   *
   * Note: This Shape should consist of 9 segments 
   *       and a closePath()
   */
  public abstract Shape getBodyShape();

  /**
   * Get the Shape for Buzzy's hair
   */
  public abstract Shape getHairShape();

  /**
   * Get the Shape for Buzzy's helmet
   */
  public abstract Shape getHelmetShape();

  /**
   * Get the Shape for Buzzy's visor
   */
  public abstract Shape getVisorShape();
}
