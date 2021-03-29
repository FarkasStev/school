// Java libraries
import java.io.*;
import java.util.*;
import javax.swing.*;

// Multimedia libraries
import app.*;
import event.*;
import io.*;

//[0
/**
 * A simple example of a JApplication that uses external resources.
 * 
 * @author  Prof. David Bernstein, James Madison University
 * @version 2.0
 */
public class TimedMessageDemo extends    JApplication 
                              implements MetronomeListener
{
  private ArrayList<String> messages = new ArrayList<String>();
  
  private int               index;
  private JLabel            label;
  private Metronome         metronome;
//]0  
  /**
   * Construct and invoke  (in the event dispatch thread) 
   * an instance of this JApplication.
   * 
   * @param args The command line arguments
   */
  public static void main(String[] args)
  {
    JApplication demo = new TimedMessageDemo(args, 400, 200);
    invokeInEventDispatchThread(demo);
  }
  
  /**
   * Explicit value constructor.
   * 
   * @param args   The command line arguments
   * @param width  The width (in pixels) of the content pane
   * @param height The height (in pixels) of the content pane
   */
  public TimedMessageDemo(String[] args, int width, int height)
  {
    super(args, width, height);
    index = -1;
//[ResourceFinder    
    ResourceFinder rf = ResourceFinder.createInstance(new resources.Marker());
    InputStream    is = rf.findInputStream("messages.txt");
//]ResourceFinder
//[read
    BufferedReader in     = new BufferedReader(new InputStreamReader(is));
    
    String line;
    try
    {
      while ((line = in.readLine()) != null)
      {
        messages.add(line);
      }
    }
    catch (IOException ioe)
    {
      messages.add("Best book ever!");
    }
//]read
  }
  
  /**
   * Handle a tick from a Metronome
   * (required by MetronomeListener). Specifically,
   * change the message.
   * 
   * @param millis  The number of milliseconds
   */
  public void handleTick(int millis)
  {
    index = (index + 1) % messages.size();
    label.setText(messages.get(index));
  }
  
  /**
   * Initialize this JApplication (required by Japplication).
   * Specifically, construct and
   * layout the JFrame and start the Metronome.
   */
  public void init()
  {
    // Setup the content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.setLayout(null);

    // Add a component to the container
    label = new JLabel("  ", SwingConstants.CENTER);
    label.setBounds(0, 0, 400, 200);       
    contentPane.add(label);
    
    metronome = new Metronome(1000);
    metronome.addListener(this);
  }
  
  /**
   * Start/re-start this JApplication. 
   */
  public void start()
  {
    metronome.start();
  }
  
  /**
   * Stop (perhaps temporarily) this JApplication.
   */
  public void stop()
  {
    metronome.stop();
  }
//[0
}
//]0