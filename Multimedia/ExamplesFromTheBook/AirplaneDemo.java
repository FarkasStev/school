import java.awt.*;
import javax.swing.*;

import app.*;
import visual.VisualizationView;
import visual.dynamic.described.*;

/**
 * An example of "sprite animation" that uses a SampledSprite,
 * rotation tweening, and location tweening.
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class AirplaneDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new AirplaneDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public AirplaneDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    Airplane           plane;
    JPanel             contentPane;
    Stage              stage;
    VisualizationView  stageView;       

    // The Stage
    stage = new Stage(10);
    stage.setBackground(Color.white);
    stage.setRestartTime(12000);
    stageView = stage.getView();
    stageView.setBounds(0,0,640,480);       

    // The Sprite 
    plane = new Airplane();
    stage.add(plane);

    // The content pane
    contentPane = (JPanel)getContentPane();
    contentPane.add(stageView);

    stage.start();
  }
}
