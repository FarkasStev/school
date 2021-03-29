import javax.sound.midi.*;

/**
 * An application that shows the instruments installed 
 * in the default MIDI soundbank
 */
public class ShowSoundbank
{
  /**
   * The entry point
   *
   * @param args  The command line arguments
   */
  public static void main(String[] args) throws Exception
  {
    Instrument[]    loaded;
    Soundbank       defaultSB;
    Synthesizer     synthesizer;


    synthesizer = MidiSystem.getSynthesizer();
    synthesizer.open();

    System.out.println("Simultaneous Instruments: " +
        synthesizer.getMaxPolyphony());
    System.out.println("\n");



    defaultSB = synthesizer.getDefaultSoundbank();

    synthesizer.loadAllInstruments(defaultSB);

    loaded = synthesizer.getLoadedInstruments();
    for (int i=0; i<loaded.length; i++)
    {
      System.out.println(loaded[i].getName());
    }
  }
}
