package weatherbitmaps;

//Java libraries
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

//Multimedia libraries
import app.*;
import io.*;
//Application libraries
import weather.*;
import weather.io.*;
import resources.Marker;

/**
 * An application that displays weather observations and forecasts in a generic way.
 * 
 * The way in which the information is displayed is determined by the constructWeatherOberverPanel()
 * method in the concrete specialization.
 * 
 * @author Prod. David Bernstein, James Madison University
 * @version 1.0
 */
public abstract class WeatherBitmapsApplication extends JApplication implements ActionListener
{
  public static final int WIDTH = 600;
  public static final int HEIGHT = 800;

  protected static final String ABOUT = "About";
  protected static final String LOAD = "Load";

  private JButton aboutButton, loadButton;
  private JTextField fileField;
  private String aboutText;

  /**
   * Explicit value constructor.
   * 
   * @param args
   *          The command line arguments
   */
  public WeatherBitmapsApplication(final String[] args)
  {
    super(args, WIDTH, HEIGHT);

    ResourceFinder rf = ResourceFinder.createInstance(new Marker());
    InputStream is = rf.findInputStream("about.txt");
    BufferedReader in = new BufferedReader(new InputStreamReader(is));

    String line;
    aboutText = "";
    try
    {
      while ((line = in.readLine()) != null)
      {
        aboutText += line + "\n";
      }
    }
    catch (IOException ioe)
    {
      aboutText = "WeatherBits";
    }
  }

  /**
   * Handle actionPerformed messages (required by ActionListener). In particular, get the input,
   * perform the requested conversion, and display the result.
   * 
   * @param evt
   *          The ActionEvent that generated the actionPerformed message
   */
  @Override
  public void actionPerformed(final ActionEvent evt)
  {
    String ac = evt.getActionCommand();

    if (ac.equalsIgnoreCase(LOAD))
      handleLoad();
    else if (ac.equalsIgnoreCase(ABOUT))
      handleAbout();
  }

  /**
   * Handle the ABOUT button.
   */
  protected void handleAbout()
  {
    JOptionPane.showMessageDialog(getGUIComponent(), aboutText, ABOUT,
        JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Handle the LOAD button.
   */
  protected void handleLoad()
  {
    String fileName = fileField.getText();
    WeatherDatumReader in;
    try
    {
      BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
      if (fileName.endsWith("for"))
        in = new WeatherForecastReader(br);
      else
        in = new WeatherObservationReader(br);

      getWeatherObserver().reset();
      in.addObserver(getWeatherObserver());
      in.readAll();
    }
    catch (IOException ioe)
    {
      JOptionPane.showMessageDialog(getGUIComponent(), "There was a problem reading " + fileName,
          "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Get the GUI components to use to display the weather information.
   * 
   * @return The WeatherObserverPanel
   */
  protected abstract JComponent getGUIComponent();

  /**
   * Get the WeatherObserver to inform of changes.
   * 
   * @return The WeatherObserverPanel
   */
  protected abstract WeatherObserver getWeatherObserver();

  /**
   * Initialize this JApplication (required by JApplication). Specifically, construct and layout the
   * JFrame.
   */
  @Override
  public void init()
  {
    // Setup the content pane
    JPanel contentPane = (JPanel) getContentPane();
    contentPane.setLayout(null);

    JLabel label = new JLabel("File: ");
    label.setBounds(30, 30, 40, 30);
    contentPane.add(label);

    fileField = new JTextField();
    fileField.setBounds(80, 30, 200, 30);
    contentPane.add(fileField);

    loadButton = new JButton(LOAD);
    loadButton.setBounds(320, 30, 70, 30);
    loadButton.addActionListener(this);
    contentPane.add(loadButton);

    aboutButton = new JButton(ABOUT);
    aboutButton.setBounds(400, 30, 70, 30);
    aboutButton.addActionListener(this);
    contentPane.add(aboutButton);


    JComponent weatherObserverComponent = getGUIComponent();
    weatherObserverComponent.setBounds(0, 60, WIDTH, HEIGHT - 60);

    contentPane.add(weatherObserverComponent);

  }
}
