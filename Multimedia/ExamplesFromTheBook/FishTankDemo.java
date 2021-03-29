import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import app.*;
import io.*;
import visual.VisualizationView;
import visual.dynamic.described.*;
import visual.statik.sampled.*;

/**
 * An example of described dynamic visual content that uses: several
 * RuleBasedSprite objects, and interaction
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class FishTankDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new FishTankDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public FishTankDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    BufferedImage                image;
    ResourceFinder               finder;       
    VisualizationView            stageView;       

    //[skeleton
    int width  = 640;
    int height = 480;       

    finder       = ResourceFinder.createInstance(resources.Marker.class);       
    ContentFactory factory = new ContentFactory(finder);       
    ImageFactory imageFactory = new ImageFactory(finder);

    // The Stage
    Stage stage = new Stage(50);
    stage.setBackground(Color.blue);
    Content content = factory.createContent("ocean.png", 3, false);
    stage.add(content);
    stageView = stage.getView();
    stageView.setBounds(0,0,width,height);       

    // The Shark
    content = factory.createContent("shark.png", 4, false);
    Fish shark = new Fish(content, width, height, 8.);
    stage.add(shark);

    // The school of Fish
    // (Use the same BufferedImage object for all Fish)
    image   = imageFactory.createBufferedImage("fish.png", 4);
    for (int i=0; i<10; i++)
    {
      content = factory.createContent(image, false);
      Fish fish = new Fish(content, width, height, 3.);
      fish.addAntagonist(shark);       
      stage.add(fish);
    }

    // The content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(stageView);

    stage.start();
    //]skeleton
  }
}
