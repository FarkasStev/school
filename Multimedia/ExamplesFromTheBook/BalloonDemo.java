import java.awt.*;
import javax.swing.*;

import app.*;
import io.*;
import visual.VisualizationView;
import visual.dynamic.described.*;
import visual.statik.sampled.*;

/**
 * An example of "sprite animation" that uses: tweening, interaction 
 * between sprites, and interaction with the user.
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class   BalloonDemo extends JApplication
{
  private static final int SIZE = 4; // Of each color

  public static void main(String[] args)
  {
    JApplication demo = new BalloonDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public BalloonDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  /**
   * The entry point of the application
   */
  public void init()
  {
    Balloon                         gold, purple;
    Cupola                          cupola;
    TransformableContent            content, goldContent, purpleContent;

    ResourceFinder finder = ResourceFinder.createInstance(resources.Marker.class);       
    ContentFactory tcFactory = new ContentFactory(finder);

    // The Stage
    int stageWidth   = 640;
    int stageHeight  = 480;       
    Stage stage = new Stage(50);
    VisualizationView stageView = stage.getView();       
    Toolkit toolkit = stageView.getToolkit(); 
    ImageFactory imageFactory = new ImageFactory(finder);       
    Image cursor = imageFactory.createBufferedImage("blankcursor.png",4);
    content = tcFactory.createContent("roof.png", 3, false);
    stage.add(content);
    //stage.setBackground(new Color(116, 126, 156)); 
    stageView.setBounds(0, 0, stageWidth, stageHeight);
    stageView.setCursor(toolkit.createCustomCursor(cursor, new Point(0,0), 
        "Blank"));

    // The cupola
    content = tcFactory.createContent("cupola.png", 
        4, false);
    cupola  = new Cupola(content, stageWidth, stageHeight);
    stage.add(cupola);
    stage.addMouseMotionListener(cupola);


    // The balloons
    for (int i=0; i<SIZE; i++)
    {
      goldContent   = tcFactory.createContent("balloon-gold.png", 4, 
          false);
      purpleContent = tcFactory.createContent("balloon-purple.png", 4, 
          false);

      gold    = new Balloon(goldContent, stageWidth, stageHeight);
      gold.addAntagonist(cupola);       
      stage.add(gold);

      purple    = new Balloon(purpleContent, stageWidth, stageHeight);
      purple.addAntagonist(cupola);       
      stage.add(purple);
    }


    // The content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(stageView);

    stage.start();
  }
}
