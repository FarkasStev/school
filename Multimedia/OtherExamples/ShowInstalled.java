import javax.sound.sampled.*;

/**
 * A utility that displays information about installed
 * mixers
 *
 * @version  1.0
 * @author   David Bernstein, James Madison University
 */
public class ShowInstalled
{
  /**
   * The entry point of the application
   *
   * @param args   The command line arguments
   */
  public static void main(String[] args) throws Exception
  {
    Control[]                controls;
    Line.Info[]              targets;
    Mixer                    mixer;
    Mixer.Info[]             minfo;


    minfo = AudioSystem.getMixerInfo();

    System.out.println("Mixers: ");
    for (int i=0; i < minfo.length; i++) {

      System.out.println(i+"\t"+minfo[i]);

      mixer = AudioSystem.getMixer(minfo[i]);

      System.out.println("\tControls: ");
      controls = mixer.getControls();
      for (int j=0; j < controls.length; j++) {

        System.out.println("\t"+j+"\t"+controls[j]);
      }

      System.out.println("\tTarget Lines: ");
      targets = mixer.getTargetLineInfo();
      for (int j=0; j < targets.length; j++) {

        System.out.println(j+"\t"+targets[j]);
      }


      System.out.println(" ");
    }


    // Working with EnumControl objects

//    Control.Type             ctype;
//    EnumControl              reverb;
//    Object[]                 values;
//
//    reverb = (EnumControl)(controls[0]);
//    values = reverb.getValues();
//
//    for (int i=0; i < values.length; i++) {
//
//      System.out.println(i+"\t"+values[i]);
//    }
//    ctype  = reverb.getType();
//    System.out.println(ctype);
  }
}
