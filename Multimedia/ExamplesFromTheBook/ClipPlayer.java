import java.io.*;
import javax.sound.sampled.*;

import io.ResourceFinder;

/**
 * A simple application that can be used to present
 * sampled auditory content that is stored in a file
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class ClipPlayer
{
  /**
   * The entry point of the application
   *
   * args[0] should contain the file to load and present
   *
   * @param args  The command-line arguments
   */
  public static void main(String[] args) throws Exception
  {
    AudioInputStream     stream;   
    BufferedInputStream  bis;       
    Clip                 clip;       
    InputStream          is;
    ResourceFinder       finder;

    if ((args != null) && (!args[0].equals("")))
    {
      //[step1
      // Get the resource
      finder = ResourceFinder.createInstance(resources.Marker.class);
      is     = finder.findInputStream(args[0]);

      // Decorate the InputStream as a BufferedInputStream 
      // so mark and reset are supported
      bis = new BufferedInputStream(is);          

      // Create an AudioInputStream from the InputStream
      stream = AudioSystem.getAudioInputStream(bis);
      //]step1
      //[step2
      // Create a Clip (i.e., a Line that can be pre-loaded)
      clip = AudioSystem.getClip();          
      //]step2
      //[step3
      // Tell the Clip to acquire any required system 
      // resources and become operational
      clip.open(stream);
      //]step3
      //[step4
      // Present the Clip (without blocking the 
      // thread of execution)
      clip.start();
      //]step4
      System.out.println("Press [Enter] to exit...");          
      System.in.read();
    }
    else
    {
      System.out.println("You forgot the file name");
    }
  }

}
