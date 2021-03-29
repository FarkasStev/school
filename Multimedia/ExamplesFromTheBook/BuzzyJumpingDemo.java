import java.awt.*;
import javax.swing.*;

import app.*;
import visual.VisualizationView;
import visual.dynamic.described.*;

/**
 * An example of "sprite animation" that uses a DescribedSprite,
 * rotation tweening, location tweening, and shape tweening
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class BuzzyJumpingDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new BuzzyJumpingDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public BuzzyJumpingDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }


  /**
   * The entry point of the application
   */
  public void init()
  {
    // The Stage
    Stage stage = new Stage(10);
    stage.setBackground(Color.white);
    stage.setRestartTime(7000);
    VisualizationView stageView = stage.getView();
    stageView.setBounds(0,0,640,480);       

    // The Sprite 
    BuzzyJumping buzzy = new BuzzyJumping();
    stage.add(buzzy);

    // The content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(stageView);

    stage.start();
  }
}
