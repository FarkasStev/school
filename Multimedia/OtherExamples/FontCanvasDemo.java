import java.awt.event.*;
import javax.swing.*;

import app.*;

/**
 * An example that uses the FontCanvas
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class      FontCanvasDemo
       extends    JApplication
       implements ActionListener
{
  private FontCanvas             canvas;
  private JComboBox<String>      fontList;    

  public static void main(String[] args)
  {
    JApplication demo = new FontCanvasDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public FontCanvasDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }


  public void actionPerformed(ActionEvent evt)
  {
    canvas.setFont(fontList.getSelectedIndex());
    canvas.repaint();       
  }

  /**
   * The entry point
   */
  public void init()
  {
    int fSize = 24;

    canvas = new FontCanvas(fSize);
    canvas.setBounds(0,0,800,300);

    String[] names  = canvas.getFontFamilies();
    fontList = new JComboBox<String>(names);       
    fontList.setBounds(0, 300, 800, 30);
    fontList.addActionListener(this);       

    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(canvas);
    contentPane.add(fontList);       
  }
}

