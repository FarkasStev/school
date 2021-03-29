//[skeleton1
import javax.swing.*;

import app.*;
import io.*;
import visual.*;
import visual.statik.sampled.*;

/**
 * An example that illustrates the use of multiple views
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class StaticPIPDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new StaticPIPDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public StaticPIPDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }
  public void init()
  {
    BufferedImageOpFactory        opFactory;       
    ResourceFinder                finder;       
    Visualization                 model1, model2;
    VisualizationRenderer         renderer1, renderer2;       
    VisualizationView             view1, view2;

    finder  = ResourceFinder.createInstance(resources.Marker.class);       
    ContentFactory factory   = new ContentFactory(finder);       
    opFactory = BufferedImageOpFactory.createFactory();
    //]skeleton1

    //[visualization1
    Content woods  = factory.createContent("woods.png", 3);
    woods.setLocation(0,0);
    woods.setBufferedImageOp(opFactory.createBlurOp(3));

    FancyBuzzy buzzy  = new FancyBuzzy();
    buzzy.setLocation(200, 318);       

    model1 = new Visualization();
    model1.add(woods);
    model1.add(buzzy);

    view1     = model1.getView();
    renderer1 = view1.getRenderer();       
    view1.setRenderer(new ScaledVisualizationRenderer(renderer1, 
        471.0, 
        418.0));       
    view1.setBounds(0,0,471,418);     
    view1.setSize(471,418);       
    //]visualization1

    //[visualization2
    Content house = factory.createContent("house.png", 3);
    house.setLocation(0,0);

    model2 = new Visualization();
    model2.add(house);

    view2     = model2.getView();
    renderer2 = view2.getRenderer();       
    view2.setRenderer(new ScaledVisualizationRenderer(renderer2, 
        525.0, 
        375.0));       
    view2.setBounds(50,50,160,120);     
    view2.setSize(160,120);       
    //]visualization2

    //[contentPane
    // The content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(view2);
    contentPane.add(view1);
    //]contentPane
    //[skeleton2
  }
}
//]skeleton2
