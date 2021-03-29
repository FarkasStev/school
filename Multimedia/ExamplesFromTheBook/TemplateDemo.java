import app.*;

public class TemplateDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new TemplateDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }
  
  public TemplateDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }
  
  public void init()
  {
    
  }
}
