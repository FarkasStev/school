import java.awt.*;
import javax.swing.*;

import app.*;
import visual.VisualizationView;
import visual.dynamic.described.*;

/**
 * An example of "sprite animation" that uses: a SampledSprite,
 * rotation tweening, location tweening, and content tweening
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 *
 */
public class CrystalBallDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new CrystalBallDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }
  
  public CrystalBallDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }
 
  public void init()
  {
    // The Stage
    Stage stage = new Stage(10);
    stage.setBackground(Color.white);
    stage.setRestartTime(8000);
    VisualizationView stageView = stage.getView();
    stageView.setBounds(0,0,640,480);       

    // The Sprite 
    CrystalBall ball = new CrystalBall();
    stage.add(ball);

    // The content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(stageView);

    stage.start();
  }
}
