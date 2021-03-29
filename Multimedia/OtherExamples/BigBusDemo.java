import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import app.*;
import io.*;
import visual.*;
import visual.dynamic.described.*;
import visual.statik.sampled.*;

/**
 * An example of Sprite objects that move together
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class BigBusDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new BigBusDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public BigBusDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    BufferedImage                image;       
    Content                      content;
    ResourceFinder               finder;       
    VisualizationView            stageView;       


    int width  = 640;
    int height = 480;       

    finder       = ResourceFinder.createInstance(resources.Marker.class);       
    ContentFactory factory      = new ContentFactory(finder);       
    ImageFactory imageFactory = new ImageFactory(finder);

    // The Stage
    Stage stage = new Stage(50);
    stage.setBackground(Color.BLUE);
    content = factory.createContent("street.png", 3, false);
    stage.add(content);
    stageView = stage.getView();
    stageView.setBounds(0,0,width,height);       

    // The BigBus
    content = factory.createContent("bigbus.png", 4, false);
    BigBus bus = new BigBus(content, width, height, stage);
    stage.add(bus);

    // The streetlights in the foreground
    image   = imageFactory.createBufferedImage("streetlight.png", 4);
    content = factory.createContent(image, false);
    content.setLocation(60, 270);       
    stage.add(content);
    content = factory.createContent(image, false);
    content.setLocation(370, 270);       
    stage.add(content);

    // The content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(stageView);

    stage.start();
  }
}
