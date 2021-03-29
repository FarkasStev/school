import java.awt.event.*;
import javax.swing.*;

import app.*;
import auditory.described.*;
import io.*;

/**
 * A simple MultimediaApp that uses a Metronome
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class      OrchestraDemo
extends    JApplication
implements ActionListener
{
  private JButton         start;       
  private Orchestra       orchestra;       
  private String          song;

  private static final String       START = "Start";

  public static void main(String[] args)
  {
    JApplication demo = new OrchestraDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public OrchestraDemo(String[] args, int width, int height)
  {
    super(args, width, height);

    if (args.length > 0) song = args[0];
    else                 song = "OdeToJoy.txt";
  }

  public void init()
  {
    ResourceFinder            finder;       

    finder  = ResourceFinder.createInstance(resources.Marker.class);

    ScoreFactory factory = new ScoreFactory(finder);

    JLabel label = new JLabel(song, SwingConstants.CENTER);
    label.setBounds(0,0,600,100);       

    start = new JButton(START);
    start.setBounds(50,300,100,50);
    start.addActionListener(this);

    try
    {
      Score score = factory.createScore(song);
      orchestra = new Orchestra(score);       
    }
    catch (Exception e)
    {
      start.setEnabled(false);          
    }

    JPanel contentPane = (JPanel)getContentPane();   
    contentPane.add(label);    
    contentPane.add(start);
    contentPane.setLayout(null);
  }

  public void actionPerformed(ActionEvent event)
  {
    String   actionCommand;

    actionCommand = event.getActionCommand();
    if      (actionCommand.equals(START))
    {
      orchestra.start();
      start.setEnabled(false);
    }
  }
}
