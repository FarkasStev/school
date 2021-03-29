//[skeleton1
import java.awt.*;
import java.awt.geom.*;

import visual.statik.described.*;

/**
 * An example of a  described.CompositeContent object that contains
 * only described content
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class BoringBuzzy extends CompositeContent
{
  //]skeleton1
  //[constructor1
  /**
   * Default Constructor
   */
  public BoringBuzzy()
  {
    super();

    Color black  = Color.BLACK;
    Color gold   = new Color(0xc2,0xa1,0x4d);
    Color gray   = new Color(0xaa,0xaa,0xaa);
    Color purple = new Color(0x45,0x00,0x84);

    BasicStroke stroke = new BasicStroke();
    //]constructor1

    //[body1
    Path2D.Float bodyShape = new Path2D.Float();
    bodyShape.moveTo( 20, 50);
    bodyShape.lineTo( 20, 70);
    bodyShape.lineTo( 20, 90);
    bodyShape.lineTo( 10, 90);
    bodyShape.lineTo( 10,100);
    bodyShape.lineTo( 80,100);
    bodyShape.lineTo( 80, 90);
    bodyShape.lineTo( 40, 90);
    bodyShape.lineTo( 40, 70);
    bodyShape.lineTo( 40, 50);
    bodyShape.closePath();
    //]body1

    //[body2
    Content body = new Content(bodyShape, black, purple, stroke);
    add(body);
    //]body2

    //[head
    CompositeContent head = new CompositeContent();
    add(head);
    //]head

    //[hair
    QuadCurve2D.Float hairShape = new QuadCurve2D.Float(10,2,40,10,30,25);       
    Content hair = new Content(hairShape, purple, null, stroke);
    head.add(hair);
    //]hair

    //[helmet
    Arc2D.Float helmetShape = new Arc2D.Float(2,20,70,40,2,360,Arc2D.OPEN);
    Content helmet=new Content(helmetShape, black, gold, stroke);
    head.add(helmet);
    //]helmet

    //[visor
    Arc2D.Float visorShape = new Arc2D.Float(40,25,35,30,315,90,Arc2D.PIE);
    Content visor=new Content(visorShape, black, gray, stroke);
    head.add(visor);
    //]visor
    //[constructor2
  }
  //]constructor2
  //[skeleton2
}
//]skeleton2
