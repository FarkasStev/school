//[skeleton1
import java.awt.*;
import java.awt.geom.*;
import io.*;


/**
 * An example of CompositeContent
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class FancyBuzzy extends visual.statik.CompositeContent
{
  //]skeleton1
  /**
   * Default Constructor
   */
  public FancyBuzzy()
  {
    super();
    //[declarations
    ResourceFinder                        finder;       
    visual.statik.CompositeContent        head;       
    visual.statik.described.Content       body, hair, helmet, visor;
    visual.statik.sampled.Content         screw;       
    visual.statik.sampled.ContentFactory  factory;
    //]declarations

    Color black  = Color.BLACK;
    Color gold   = new Color(0xc2,0xa1,0x4d);
    Color gray   = new Color(0xaa,0xaa,0xaa);
    Color purple = new Color(0x45,0x00,0x84);

    BasicStroke stroke = new BasicStroke();

    //[body
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
    body = new visual.statik.described.Content(bodyShape, 
        black, purple, 
        stroke);
    add(body);
    //]body

    //[helmet
    Arc2D.Float helmetShape = new Arc2D.Float(2,20,70,40,2,360,Arc2D.OPEN);
    helmet=new visual.statik.described.Content(helmetShape, 
        black, gold, stroke);
    //]helmet

    //[logo
    finder  = ResourceFinder.createInstance(resources.Marker.class);       
    factory = new visual.statik.sampled.ContentFactory(finder);
    screw = factory.createContent("screw.png", 4);
    //]logo

    //[visor
    Arc2D.Float visorShape = new Arc2D.Float(40,25,35,30,315,90,Arc2D.PIE);
    visor=new visual.statik.described.Content(visorShape, 
        black, gray, stroke);
    //]visor

    //[hair
    QuadCurve2D.Float hairShape = new QuadCurve2D.Float(10,2,40,10,30,25);       
    hair = new visual.statik.described.Content(hairShape, 
        purple, null, stroke);
    add(hair);
    //]hair

    //[head
    head = new visual.statik.CompositeContent();
    head.add(hair);       
    head.add(helmet);
    head.add(screw);
    head.add(visor);

    add(head);
    //]head
  }
  //[skeleton2
}
//]skeleton2
