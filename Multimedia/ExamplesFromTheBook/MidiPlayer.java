import java.io.*;
import javax.sound.midi.*;

import io.*;

/**
 * An application that plays a MIDI file
 *
 * @author   Prof. David Bernstein, James Madison University
 * @version  1.0
 */
public class MidiPlayer
{
  /**
   * The entry point
   *
   * @param args   Command-line arguments (args[0] should contain the file)
   */
  public static void main(String[] args) throws Exception
  {
    InputStream          is;
    ResourceFinder       finder;
    Sequencer            sequencer;
    Sequence             seq;

    finder = ResourceFinder.createInstance(resources.Marker.class);
    is = finder.findInputStream(args[0]);
    seq = MidiSystem.getSequence(is);

    sequencer = MidiSystem.getSequencer(); 
    sequencer.open();

    sequencer.setSequence(seq);
    sequencer.start();
  }
}
