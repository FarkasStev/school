import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.sound.sampled.*;
import javax.swing.*;

import app.*;
import auditory.sampled.*;
import event.*;
import visual.*;
import visual.statik.described.Content;


/**
 * An abstract app that renders sampled auditory content
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public abstract class      AbstractSampledAuditoryDemo 
                extends    JApplication
                implements ActionListener, 
                           LineListener, 
                           MetronomeListener
{
  private boolean                   showCursor;    
  private BoomBox                   boombox;    
  private BufferedSoundGraph        graph;
  private DataLine                  line;
  private Content                   cursor;    
  private double                    frame;
  private Metronome                 metronome;
  private VisualizationView         component;       

  public AbstractSampledAuditoryDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }


  public void init()
  {
    Visualization             visualization;

    visualization = new Visualization();
    BufferedSound sound = createSound();

    graph  = new BufferedSoundGraph(sound);
    visualization.add(graph);       
    boombox = new BoomBox(sound);       
    boombox.addLineListener(this);

    component = visualization.getView();       
    JPanel contentPane = (JPanel)getContentPane();
    component.setBounds(0,0,800,400);       
    contentPane.add(component);

    metronome = new Metronome(20);
    metronome.addListener(this);
    this.showCursor = true;

    cursor = new Content(new Line2D.Double(0, 0, 0, 400), 
        Color.RED, null, null);

    if (showCursor) visualization.add(cursor);

    JButton start = new JButton("Play");
    start.addActionListener(this);       
    start.setBounds(50,450,75,25);
    contentPane.add(start);       
  }

  /**
   * Create the BufferedSound object to use
   *
   * @return   The BufferedSound
   */
  protected abstract BufferedSound createSound();

  /**
   * Handle actionPerformed messages
   * (required by ActionListener)
   *
   * @param ae  The ActionEvent that generated the message
   */
  public void actionPerformed(ActionEvent ae)
  {
    String        ac;

    ac = ae.getActionCommand();
    if (ac.equals("Play"))
    {
      try
      {
        boombox.start(false);       
      }
      catch (Exception e)
      {
        System.out.println("Can't play!");          
      }
    }
  }

  /**
   * Handle Metronome ticks
   * (required by MetronomeListener)
   *
   * Specifically, display a cursor showing the current frame
   *
   * @param millis  The "time" (in milliseconds)
   */
  public void handleTick(int millis)
  {
    updateCursor();
  }

  /**
   * Start the animation
   */
  public void startAnimation()
  {
    metronome.start();
  }

  /**
   * Stop the animation
   */
  public void stopAnimation()
  {
    metronome.stop();
  }

  /**
   * Handle update events (required by LineListener)
   *
   * @param event  The LineEvent of interest
   */
  public void update(LineEvent event)
  {
    LineEvent.Type     type;

    type = event.getType();       
    if       (type.equals(LineEvent.Type.START))
    {
      line = (DataLine)event.getSource();
      startAnimation();
    }
    else if ((type.equals(LineEvent.Type.STOP)) || 
        (type.equals(LineEvent.Type.CLOSE))   ) 
    {
      stopAnimation();
      updateCursor();          
      line = null;          
    }
  }

  /**
   * Update the cursor
   */
  private void updateCursor()
  {
    if (line != null)
    {
      frame = (double)line.getFramePosition();
      cursor.setLocation(graph.sampleToPixel(frame), 0);
      component.repaint();
    }
  }
}
