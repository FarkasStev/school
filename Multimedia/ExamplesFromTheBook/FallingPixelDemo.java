import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import app.*;
import io.*;
import visual.VisualizationView;
import visual.dynamic.described.*;
import visual.statik.sampled.*;

/**
 * An example of "sprite animation" that uses: rules
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class   FallingPixelDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new FallingPixelDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public FallingPixelDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    ResourceFinder                 finder;       
    VisualizationView              stageView;

    finder      = ResourceFinder.createInstance(resources.Marker.class);
    ImageFactory factory     = new ImageFactory(finder);       

    // The Stage
    int stageWidth  = 138;
    int stageHeight = 100;
    Stage stage       = new Stage(50);
    BufferedImage image = factory.createBufferedImage("pixels.png");
    stageView   = stage.getView();       
    stageView.setBackground(Color.BLACK);
    stageView.setBounds(0,0,stageWidth,stageHeight);       

    int height = image.getHeight();
    int width = image.getWidth();

    // The Sprites
    for (int x=0; x<width; x++) 
    {
      for (int y=0; y<height; y++)
      {
        int rgb = image.getRGB(x, y);
        stage.add(new FallingPixel(new Color(rgb), x, y));             
      }
    }

    // The content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(stageView);

    stage.start();
  }
}
