//[structure1
import java.awt.Image;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import app.*;
import io.*;

/**
 * An example that illustrates the rendering of
 * sampled static visual content
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class ImageCanvasDemo extends JApplication
{
  private String                    name;

  
  public static void main(String[] args)
  {
    JApplication demo = new ImageCanvasDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public ImageCanvasDemo(String[] args, int width, int height)
  {
    super(args, width, height);

    if (args.length >= 1) name = args[0];
    else                  name = "scribble.png";
  }
  //]structure1

  //[init1
  public void init()
  {
    Image                     image;
    InputStream               is;       
    ResourceFinder            finder;

    //]init1

    //[init2
    // Construct a ResourceFinder
    finder = ResourceFinder.createInstance(resources.Marker.class);

    // Read the image
    image  = null;       
    try
    {
      is = finder.findInputStream(name);
      if (is != null) 
      {
        image = ImageIO.read(is);
        is.close();             
      }
    }
    catch (IOException io)
    {
      // image will be null
    }
    //]init2

    //[init3
    // Create the component that will render the image
    ImageCanvas canvas      = new ImageCanvas(image);
    canvas.setBounds(0,
        0,
        image.getWidth(null),
        image.getHeight(null));

    // Add the ImageCanvas to the main window
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(canvas);
  }
  //]init3
  //[structure2
}
//]structure2
