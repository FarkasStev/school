import app.*;
import auditory.sampled.*;
import io.*;

/**
 * An app that renders sampled audio from a file
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class FilePlayerDemo extends AbstractSampledAuditoryDemo
{
  private String          name;
  
  public static void main(String[] args)
  {
    JApplication demo = new FilePlayerDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public FilePlayerDemo(String[] args, int width, int height)
  {
    super(args, width, height);
    
    if (args.length > 0) name = args[0];
    else                 name = "preface.aif";
  }

  protected BufferedSound createSound()
  {
    BufferedSound             sound;       
    BufferedSoundFactory      factory;       
    ResourceFinder            finder;       

    finder  = ResourceFinder.createInstance(resources.Marker.class);
    factory = new BufferedSoundFactory(finder);

    try
    {
      sound   = factory.createBufferedSound(name);
    }
    catch (Exception e)
    {
      sound = factory.createBufferedSound(
          200,     // frequency
          250000,  // length
          4000.0f, // sampling rate
          1000.0f  // amplitude
          );
    }

    return sound;
  }
}

