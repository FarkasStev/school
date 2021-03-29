import java.awt.*;
import java.awt.image.*;
import java.util.Random;

import javax.swing.*;

import app.*;
import io.*;
import visual.VisualizationView;
import visual.dynamic.described.*;
import visual.statik.sampled.*;

/**
 * A game in which the player attempts to catch snowflakes on his/her tongue.
 *
 * This example illustrates how a single Sprite can use different pieces of visual content.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class FlakeyDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new FlakeyDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public FlakeyDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    int width = 640;
    int height = 480;

    ResourceFinder finder = ResourceFinder.createInstance(resources.Marker.class);
    ContentFactory factory = new ContentFactory(finder);
    ImageFactory imageFactory = new ImageFactory(finder);

    // The Stage
    Stage stage = new Stage(40);
    stage.setBackground(Color.WHITE);
    VisualizationView stageView = stage.getView();
    stageView.setBounds(0, 0, width, height);

    // Add the streetlight on the right
    Content streetlight = factory.createContent("streetlight.png", 4);
    streetlight.setLocation(640 - 18, 480 - 132);
    stage.add(streetlight);

    // Add the tree on the left
    Content tree = factory.createContent("tree.png", 4);
    tree.setLocation(0, 480 - 144);
    stage.add(tree);

    // Add the player's character (i.e., Mick)
    Mick mick = new Mick(tree, streetlight);
    stage.add(mick);
    stage.addKeyListener(mick);

    // Add the snowflakes (one type of FallingSprite)
    BufferedImage[] flakes = imageFactory.createBufferedImages("snowflakes.png", 8, 4);

    Content temp;
    Random rng = new Random();
    for (int i = 0; i < 10; i++)
    {
      temp = factory.createContent(flakes[rng.nextInt(8)], 4);
      temp.setLocation(0, 0);
      Snowflake flake = new Snowflake(temp);
      stage.add(flake);
    }

    // Add the pinecones
    Content cone = factory.createContent("pinecone.png", 4);
    Pinecone pinecone = new Pinecone(cone);
    pinecone.setStartingValues(15, 480 - 109, 5);
    stage.add(pinecone);

    pinecone = new Pinecone(cone);
    pinecone.setStartingValues(26, 480 - 90, 5);
    stage.add(pinecone);

    pinecone = new Pinecone(cone);
    pinecone.setStartingValues(15, 480 - 35, 5);
    stage.add(pinecone);

    // The content pane
    JPanel contentPane = (JPanel) getContentPane();
    contentPane.add(stageView);

    stage.start();
  }
}
