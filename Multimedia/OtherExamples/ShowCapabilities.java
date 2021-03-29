import javax.sound.sampled.*;

/**
 * A simple utility that shows the capabilities of the
 * available auditory output devices 
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class ShowCapabilities
{
  /**
   * The entry-point
   *
   * @param args  The command line arguments
   */
  public static void main(String[] args)
  {
    Line.Info[]    lineinfo;       
    Mixer          mixer;
    Mixer.Info[]   mixerinfo;

    mixerinfo = AudioSystem.getMixerInfo();

    for (int i=0; i<mixerinfo.length; i++)
    {
      System.out.println(mixerinfo[i]); 

      mixer    = AudioSystem.getMixer(mixerinfo[i]);          
      lineinfo = mixer.getTargetLineInfo();

      System.out.println("\n  Target Lines");          
      for (int j=0; j<lineinfo.length; j++)
      {
        System.out.println("  "+lineinfo[j]+
            " ("+mixer.getMaxLines(lineinfo[j])+")");
      }

      lineinfo = mixer.getSourceLineInfo();

      System.out.println("\n  Source Lines");          
      for (int j=0; j<lineinfo.length; j++)
      {
        System.out.println("  "+lineinfo[j]+
            " ("+mixer.getMaxLines(lineinfo[j])+")");
      }

      System.out.println("\n\n");
    }
  }
}
