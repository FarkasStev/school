import java.awt.*;
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
public class FallingCharacterDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new FallingCharacterDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public FallingCharacterDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    ResourceFinder                 finder;       
    VisualizationView              stageView;

    finder      = ResourceFinder.createInstance(resources.Marker.class);       
    ContentFactory factory     = new ContentFactory(finder);       

    // The Stage
    Stage stage = new Stage(50);
    Content content = factory.createContent("fallingcharacterbg.png");
    stageView   = stage.getView();
    stageView.setBounds(0,0,640,480);       
    stageView.setBackground(Color.BLACK);
    stage.add(content);

    // The Sprites
    for (int i=0; i < 200; i++) 
      stage.add(new FallingCharacter(640, 480));

    // The content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(stageView);

    stage.start();
  }
}
