import java.awt.event.*;
import javax.swing.*;

import app.*;

/**
 * An example that illustrates the rendering of 
 * described visual content
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class      RandomRectangleDemo
       extends    JApplication
       implements ActionListener
{
    private RandomRectangleCanvas       canvas;

    public static void main(String[] args)
    {
      JApplication demo = new RandomRectangleDemo(args, 640, 480);
      invokeInEventDispatchThread(demo);
    }
    
    public RandomRectangleDemo(String[] args, int width, int height)
    {
      super(args, width, height);
    }
    
    public void actionPerformed(ActionEvent evt)
    {
       canvas.repaint();       
    }

    public void init()
    {
       canvas = new RandomRectangleCanvas();
       canvas.setBounds(0,0,400,300);
       
       JPanel contentPane = (JPanel)getContentPane();
       contentPane.add(canvas);

       JButton button = new JButton("Repaint");
       button.addActionListener(this);
       button.setBounds(0,300,100,40);
       contentPane.add(button);       
    }
}
