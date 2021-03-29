import java.awt.event.*;
import javax.swing.*;

import app.*;

/**
 * An example that illustrates the use of constructive 
 * area geometry
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class      ConstructiveAreaGeometryDemo
       extends    JApplication
       implements ActionListener
{
  private  ConstructiveAreaGeometryCanvas     canvas;

  public static void main(String[] args)
  {
    JApplication demo = new ConstructiveAreaGeometryDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public ConstructiveAreaGeometryDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  /**
   * Handle actionPerformed messages
   * (required by ActionListener)
   *
   * @param evt   The ActionEvent that generated the message
   */
  public void actionPerformed(ActionEvent evt)
  {
    String    command;

    command = evt.getActionCommand();
    canvas.setOperation(command);
    canvas.repaint();
  }

  /**
   * Add a JButton
   *
   * @param p     The JPanel to add it to
   * @param text  The text of the JButton
   * @param x     The x-position
   * @param y     The y-position
   * @param width The width
   */
  private void addButton(JPanel p, String text, 
      int x, int y, int width)
  {
    JButton      button;

    button = new JButton(text);
    button.addActionListener(this);       
    button.setBounds(x, y, width, 30);
    p.add(button);       
  }

  /**
   * The entry point
   */
  public void init()
  {
    canvas = new ConstructiveAreaGeometryCanvas();
    canvas.setDoubleBuffered(false);       
    canvas.setBounds(0,0,500,300);

    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(canvas);

    int y = 300;       
    addButton(contentPane, "Outline",         0, y, 95);       
    addButton(contentPane, "Union",         100, y, 95);       
    addButton(contentPane, "Intersection",  200, y, 95);       
    addButton(contentPane, "Difference",    300, y, 95);       
    addButton(contentPane, "S-Difference",  400, y, 95);       
  }
}
