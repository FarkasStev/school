//[0
// Java libraries
import javax.swing.*;

// Multimedia libraries
import app.*;
import event.*;

/**
 * A simple example of a JApplication.
 * 
 * @author  Prof. David Bernstein, James Madison University
 * @version 2.0
 */
public class TimedMessageJApplication extends JApplication 
                                      implements MetronomeListener
{
  private static final String[] MESSAGES = {
     "What a great book.","Bring on the exercises.",
     "Author, author!","I hope it never ends."};
  
  private int       index;
  private JLabel    label;
  private Metronome metronome;
//]0
//[main  
  /**
   * Construct and invoke  (in the event dispatch thread) 
   * an instance of this JApplication.
   * 
   * @param args The command line arguments
   */
  public static void main(String[] args)
  {
    JApplication demo = new TimedMessageJApplication(args, 400, 200);
    invokeInEventDispatchThread(demo);
  }
//]main

//[constructor  
  /**
   * Explicit value constructor.
   * 
   * @param args   The command line arguments
   * @param width  The width (in pixels) of the content pane
   * @param height The height (in pixels) of the content pane
   */
  public TimedMessageJApplication(String[] args, int width, int height)
  {
    super(args, width, height);
    index = -1;
  }
//]constructor  

//[handleTick
  /**
   * Handle a tick from a Metronome
   * (required by MetronomeListener). Specifically,
   * change the message.
   * 
   * @param millis  The number of milliseconds
   */
  public void handleTick(int millis)
  {
    index = (index + 1) % MESSAGES.length;
    label.setText(MESSAGES[index]);
  }
//]handleTick
//[init  
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
//]init  

//[transitions  
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
//]transitions  
//[0
}
//]0
