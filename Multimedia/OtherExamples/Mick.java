
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import io.ResourceFinder;
import visual.statik.sampled.*;
import visual.dynamic.described.*;

/**
 * The main character in the game Flakey.
 *
 * Mick is a Sprite that responds to user interaction. It is an example of a Sprite that uses
 * multiple pieces of visual content that vary depending on the state (e.g., walking left, walking
 * right, etc...).
 *
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class Mick extends AbstractSprite implements KeyListener
{
  private boolean bleeding, nearStreetlight, tongueOnPole, tongueOut;
  private int lastTickTime, tongueRetractionTime, xMick, yMick;
  private int direction, position;
  private Content[][] images;
  private Content[] tongues;
  private Content streetlight, tree;
  private Drop blood;

  private static final int TONGUE_TIME = 1000;

  private static final int LEFT = 0;
  private static final int RIGHT = 1;

  private static final int LEFT_FORWARD = 0;
  private static final int RIGHT_FORWARD = 1;
  private static final int UPRIGHT = 2;

  private static final int[] SEQUENCE = {UPRIGHT, RIGHT_FORWARD, UPRIGHT, LEFT_FORWARD};

  private static final int[] TONGUE_X = {4, 23};
  private static final int[] TONGUE_Y = {28, 28, 25};

  /**
   * Explicit Value Constructor
   *
   * @param tree
   *          The tree that he might bump into
   * @param streetlight
   *          The streetlight that he might bump into
   */
  public Mick(Content tree, Content streetlight)
  {
    super();

    this.streetlight = streetlight;
    this.tree = tree;
    tongueOut = false;
    tongueOnPole = false;
    bleeding = false;
    xMick = 320;
    yMick = 480 - 60;
    ;

    ResourceFinder finder = ResourceFinder.createInstance(resources.Marker.class);
    ContentFactory factory = new ContentFactory(finder);
    images = factory.createContents("mick.png", 2, 3, 4);
    tongues = factory.createContents("tongue.png", 2, 4);
    direction = RIGHT;
    position = 0;

    setLocation(xMick, yMick);
    setVisible(true);
  }

  /**
   * Invoked when a key is pressed (required by KeyListener).
   *
   * @param ke          The GameButtonEvent
   */
  public void keyPressed(KeyEvent ke)
  {
    int keyCode;

    keyCode = ke.getKeyCode();

    if ((keyCode == KeyEvent.VK_KP_RIGHT) || (keyCode == KeyEvent.VK_RIGHT))
      handleRight();
    else if ((keyCode == KeyEvent.VK_KP_LEFT) || (keyCode == KeyEvent.VK_LEFT))
      handleLeft();
    else if (keyCode == KeyEvent.VK_SPACE)
      handleFire();
  }

  /**
   * Invoked when a key is released (required by KeyListener).
   *
   * @param ke          The GameButtonEvent
   */
  public void keyReleased(KeyEvent ke)
  {
  }

  /**
   * Invoked when a key is pressed and released (required by KeyListener).
   *
   * @param ke          The GameButtonEvent
   */
  public void keyTyped(KeyEvent ke)
  {
  }

  /**
   * Get the bounding rectangle for this Sprite
   *
   * @return The bounding rectangle
   */
  public Rectangle2D getBounds2D()
  {
    return images[direction][SEQUENCE[position]].getBounds2D();
  }

  /**
   * Get the visual content associated with this Sprite (required by Sprite)
   */
  public visual.statik.TransformableContent getContent()
  {
    return images[direction][SEQUENCE[position]];
  }

  /**
   * Handle a FIRE GameButtonEvent.
   */
  public void handleFire()
  {
    tongueOut = true;
    tongueRetractionTime = lastTickTime + TONGUE_TIME;
  }

  /**
   * Handle a LEFT GameButtonEvent.
   */
  public void handleLeft()
  {
    if (xMick > 40)
      xMick -= 10;

    if (tongueOnPole)
    {
      bleeding = true;
    }

    // Handle normal movement
    if (direction == LEFT)
    {
      increasePosition();
    }
    else // Facing RIGHT
    {
      direction = LEFT;
      position = UPRIGHT;
    }

  }

  /**
   * Handle a RIGHT GameButtonEvent.
   */
  public void handleRight()
  {
    if (!nearStreetlight)
      xMick += 10;

    if (direction == RIGHT)
    {
      increasePosition();
    }
    else // Facing LEFT
    {
      direction = RIGHT;
      position = UPRIGHT;
    }

  }

  /**
   * Increase the position index.
   */
  private void increasePosition()
  {
    position = (position + 1) % SEQUENCE.length;
  }

  /**
   * Render this SpriteView.
   *
   * @param g          The rendering engine to use
   */
  public void render(Graphics g)
  {
    int tx, ty;

    if (tongueOnPole)
    {
      tongues[RIGHT].render(g);
    }
    else if (tongueOut)
    {
      tx = xMick + TONGUE_X[direction];
      ty = yMick + TONGUE_Y[SEQUENCE[position]];

      tongues[direction].setLocation(tx, ty);
      tongues[direction].render(g);

      if (nearStreetlight)
      {
        tongueOnPole = true;
        blood = new Drop();
        blood.setStartingValues(tx - 2, ty, 5);
      }
    }

    super.render(g);

    if (bleeding)
      blood.render(g);
  }

  /**
   * Handle a tick event.
   *
   * @param time          The current time (in milliseconds)
   */
  public void handleTick(int time)
  {
    lastTickTime = time;

    if (xMick > 580)
      nearStreetlight = true;
    else
      nearStreetlight = false;

    if (tongueOut && (time >= tongueRetractionTime))
    {
      tongueOut = false;
    }

    if (bleeding)
      blood.handleTick(time);

    setLocation(xMick, yMick);
  }
}
