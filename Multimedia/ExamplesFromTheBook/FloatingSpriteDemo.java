import java.awt.*;
import javax.swing.*;

import app.*;
import io.*;
import visual.*;
import visual.dynamic.described.*;
import visual.statik.sampled.*;

/**
 * A simple example of described dynamic visual content
 * 
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class FloatingSpriteDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new FloatingSpriteDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }
  
  public FloatingSpriteDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }
  
  public void init()
    {
//[skeleton0
       FloatingSprite                  sprite;
       ResourceFinder                  finder;       
       TransformableContent            content;
              
       int width   = 640;
       int height  = 480;       

       finder  = ResourceFinder.createInstance(resources.Marker.class);       
       ContentFactory factory = new ContentFactory(finder);

       // The Stage
       Stage stage = new Stage(50);
       stage.setBackground(new Color(255, 255, 255)); 
       VisualizationView stageView = stage.getView();
       stageView.setBounds(0,0,width,height);       

       // The Sprite
       content = factory.createContent("snowflake.png", 4, false);
       sprite  = new FloatingSprite(content, width, height);
       stage.add(sprite);

       // The content pane
       JPanel contentPane = (JPanel)getContentPane();
       contentPane.add(stageView);

       // Start the dynamics
       stage.start();
//]skeleton0
    }
}
