import java.awt.*;
import javax.swing.*;

import app.*;
import visual.VisualizationView;
import visual.dynamic.described.*;

/**
 * An app that illustrates user interaction
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class   RCBuzzyDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new RCBuzzyDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public RCBuzzyDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    VisualizationView         stageView;

    // The Stage
    Stage stage = new Stage(10);
    stage.setBackground(Color.white);
    stage.setRestartTime(7000);
    stageView = stage.getView();
    stageView.setBounds(0,0,640,480);       

    // The Sprite 
    RCBuzzy buzzy = new RCBuzzy(640,480);
    stage.add(buzzy);
    stage.addKeyListener(buzzy);

    // The content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(stageView);

    stage.start();
  }
}
