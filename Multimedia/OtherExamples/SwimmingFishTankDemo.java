import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import app.*;
import io.*;
import visual.VisualizationView;
import visual.dynamic.described.*;
import visual.statik.sampled.*;

/**
 * An example of described dynamic visual content that uses
 * multiple Content objects for each Sprite
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class   SwimmingFishTankDemo extends JApplication
{
	public static void main(String[] args)
	{
		JApplication demo = new SwimmingFishTankDemo(args, 640, 480);
		invokeInEventDispatchThread(demo);
	}

	public SwimmingFishTankDemo(String[] args, int width, int height)
	{
		super(args, width, height);
	}

	public void init()
	{
		ResourceFinder               finder;       

		int width  = 640;
		int height = 480;       

		finder       = ResourceFinder.createInstance(resources.Marker.class);       
		ContentFactory factory = new ContentFactory(finder);       
		ImageFactory imageFactory = new ImageFactory(finder);

		// The Stage
		Stage stage = new Stage(50);
		stage.setBackground(Color.BLUE);
		Content content = factory.createContent("ocean.png", 3, false);
		stage.add(content);
		VisualizationView stageView = stage.getView();
		stageView.setBounds(0,0,width,height);       

		// The Shark
		BufferedImage[] images = imageFactory.createBufferedImages("swimmingshark.png", 
				3, 4);
		Content[] contents = new Content[3];          
		for (int j=0; j<contents.length; j++)
		{
			contents[j] = factory.createContent(images[j], false);
		}
		SwimmingFish shark = new SwimmingFish(contents, width, height, 8.);
		stage.add(shark);

		// The school of Fish
		// (Use the same BufferedImage object for all Fish)
		images  = imageFactory.createBufferedImages("swimmingfish.png", 
				3, 4);
		for (int i=0; i<10; i++)
		{
			contents = new Content[3];          
			for (int j=0; j<contents.length; j++)
			{
				contents[j] = factory.createContent(images[j], false);
			}

			SwimmingFish fish = new SwimmingFish(contents, width, height, 3.);
			fish.addAntagonist(shark);       
			stage.add(fish);
		}

		// The content pane
		JPanel contentPane = (JPanel)getContentPane();
		contentPane.add(stageView);

		stage.start();
	}
}
