import app.*;
import auditory.sampled.*;
import io.*;

/**
 * An app that renders sampled audio
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class BufferedSoundDemo extends AbstractSampledAuditoryDemo
{
  private BufferedSoundFactory      factory;       
  private ResourceFinder            finder;       


  public static void main(String[] args)
  {
    JApplication demo = new BufferedSoundDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public BufferedSoundDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    finder  = ResourceFinder.createInstance(resources.Marker.class);
    factory = new BufferedSoundFactory(finder);

    super.init();       
  }

  /**
   * Create the BufferedSound
   * (required by AbstractSampledAuditoryApp)
   *
   * @return   The BufferedSound
   */
  protected BufferedSound createSound()
  {
    BufferedSound             a, b, sound;       
    BufferedSoundBinaryOp     binaryOp;       
    BufferedSoundUnaryOp      unaryOp;       
    String                    operatorType, value;

    sound        = null;      
    operatorType = null;
    if (args.length > 0) operatorType = args[0];

    if      (operatorType == null)
    {
      sound = createSound("200");          
    }
    else if (operatorType.equalsIgnoreCase("NONE"))
    {
      value = args[1];
      sound = createSound(value);          
    }
    else if (operatorType.equalsIgnoreCase("UNARY"))
    {
      value    = args[1];
      a        = createSound(value);

      value    = args[2];
      unaryOp  = createUnaryOp(value);
      sound    = unaryOp.filter(a, null);
    }
    else if (operatorType.equalsIgnoreCase("BINARY"))
    {
      value    = args[1];
      a        = createSound(value);

      value    = args[2];
      binaryOp = createBinaryOp(value);

      value    = args[3];
      b        = createSound(value);

      sound    = binaryOp.filter(a, b, null);
    }

    return sound;
  }


  /**
   * Create a BufferedSound based on the value of a String (which may 
   * contain the frequency or the name of a file)
   */
  private BufferedSound createSound(String s)
  {
    BufferedSound    sound;       
    int              frequency;       

    sound     = null;
    frequency = 200;       

    try
    {
      frequency = Integer.parseInt(s);             
    }
    catch (NumberFormatException nfe)
    {
      try
      {
        sound   = factory.createBufferedSound(s);
      }
      catch (Exception e)
      {
        frequency = 200;
      }
    }

    if (sound == null)
    {
      sound = factory.createBufferedSound(frequency,
          500000,  // length
          4000.0f, // sampling rate
          1000.0f  // amplitude
          );
    }

    return sound;       
  }



  /**
   * Create a BufferedSoundBinaryOp from a String
   */
  private BufferedSoundBinaryOp createBinaryOp(String s)
  {
    BufferedSoundBinaryOp           op;

    op = null;

    if      (s == null)
    {
      op = new AddOp();          
    }
    else if (s.equalsIgnoreCase("ADD"))
    {
      op = new AddOp();          
    }
    else if (s.equalsIgnoreCase("MULTIPLY"))
    {
      op = new MultiplyOp();          
    }

    return op;       
  }




  /**
   * Create a BufferedSoundUnaryOp from a String
   */
  private BufferedSoundUnaryOp createUnaryOp(String s)
  {
    BufferedSoundUnaryOp           op;
    double[]                       weights;       
    FIRFilter                      firFilter;       
    int                            n;



    op = null;

    if      (s == null)
    {
      op = new NoiseOp(0.0);          
    }
    else if (s.equalsIgnoreCase("FIR"))
    {
      n       = 1000;
      weights = new double[n];

      // The weight on sample i is 1.0
      weights[0] = 1.0;

      // The weights on the "oldest" 1/4 of the samples
      for (int i=3*n/4; i<n; i++) weights[i] = 0.1;

      firFilter = new FIRFilter(weights);
      op        = new FIRFilterOp(firFilter);
    }
    else if (s.equalsIgnoreCase("INVERT"))
    {
      op = new InvertOp();          
    }
    else if (s.equalsIgnoreCase("NOISE"))
    {
      op = new NoiseOp(100.0);          
    }
    else if (s.equalsIgnoreCase("REVERSE"))
    {
      op = new ReverseOp();          
    }
    else if (s.equalsIgnoreCase("SPEEDCHANGE"))
    {
      op = new SpeedChangeOp(1.5);          
    }


    return op;       
  }

}

