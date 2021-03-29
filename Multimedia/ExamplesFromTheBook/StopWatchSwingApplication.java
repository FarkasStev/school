//[skeleton1
import java.awt.event.*;
import javax.swing.*;

import event.*;

/**
 * A simple application uses a Metronome
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class      StopWatchSwingApplication
       implements ActionListener, MetronomeListener, Runnable
{
    private JLabel                    label;       
    private Metronome                 metronome;
    
    private static final String       START = "Start";
    private static final String       STOP  = "Stop";

    /**
     * The entry point of the application
     *
     * @param args   The command-line arguments
     */
    public static void main(String[] args) throws Exception
    {
       SwingUtilities.invokeAndWait(
                      new StopWatchSwingApplication());
    }
//]skeleton1

//[actionPerformed
    /**
     * Handle actionPerformed messages
     * (required by ActionListener)
     *
     * @param event  The ActionEvent that generated the message
     */
    public void actionPerformed(ActionEvent event)
    {
       String   actionCommand;
       
       actionCommand = event.getActionCommand();
       if      (actionCommand.equals(START))
       {
          label.setText("0");          
          metronome.reset();          
          metronome.start();
       }
       else if (actionCommand.equals(STOP))
       {
          metronome.stop();
       }
    }
//]actionPerformed

//[handleTick
    /**
     * Respond to handleTick messages
     * (required by MetronomeListener)
     *
     * @param millis   The time
     */
    public void handleTick(int millis)
    {
       label.setText(""+millis/1000);       
    }
//]handleTick

//[run
    /**
     * The code to be executed in the event dispatch thread
     * (required by Runnable)
     */
    public void run()
    {
       JButton                   start, stop;       
       JFrame                    window;       
       JPanel                    contentPane;       
              
       window = new JFrame();
       window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       
       window.setSize(600,400);

       contentPane = (JPanel)window.getContentPane();   
       contentPane.setLayout(null);
       
       label       = new JLabel("0");
       label.setBounds(250,100,100,100);       
       contentPane.add(label);    

       start = new JButton(START);
       start.setBounds(50,300,100,50);
       start.addActionListener(this);
       contentPane.add(start);
       

       stop  = new JButton(STOP);
       stop.setBounds(450,300,100,50);
       stop.addActionListener(this);
       contentPane.add(stop);
       

       metronome = new Metronome(1000, true);
       metronome.addListener(this);

       window.setVisible(true);
    }
//]run
//[skeleton2
}
//]skeleton2
