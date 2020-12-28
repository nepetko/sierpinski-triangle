import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.JFrame;

public class MainPanel extends JFrame {

  private static final long serialVersionUID = 1L;
  private static final String PANEL_TITLE = "Sierpiński triangle";
  private static final int PIXEL_SIZE_FOR_DOT = 1;

  private final int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
  private final int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

  private final Random randomGenerator = new Random();

  private final PointWithId pointA = new PointWithId(0, 0, 0);
  private final PointWithId pointB = new PointWithId(screenWidth, 0, 1);
  private final PointWithId pointC = new PointWithId(screenWidth / 2, screenHeight, 2);
  private PointWithId currentSeed = new PointWithId(randomGenerator.nextInt(screenWidth),
      randomGenerator.nextInt(screenHeight), 3);

  MainPanel() {
    super(PANEL_TITLE);

    setSize(screenWidth, screenHeight);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
  }


  @Override
  public void paint(final Graphics g) {
    try {
      generateNewSeedPoints(g);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }


  private void generateNewSeedPoints(final Graphics g) throws InterruptedException {
    final Graphics2D graphics2d = (Graphics2D) g;
    int nextIdNumber;

    for (int i = 0; i < 1000000; i++) {
      if (i % 10 == 0) {
        Thread.sleep(1);
      }
      nextIdNumber = randomGenerator.nextInt(3);
      final PointWithId pointToApproach = getPointById(nextIdNumber);
      final int nextSeedX = calculateNextSeedX(currentSeed, pointToApproach);
      final int nextSeedY = calculateNextSeedY(currentSeed, pointToApproach);
      currentSeed = new PointWithId(nextSeedX, nextSeedY, 3);
      graphics2d.drawOval(currentSeed.getX(), currentSeed.getY(), PIXEL_SIZE_FOR_DOT,
          PIXEL_SIZE_FOR_DOT);
    }
  }

  private int calculateNextSeedX(final PointWithId currentSeed, final PointWithId pointToApproach) {
    return (currentSeed.getX() + pointToApproach.getX()) / 2;
  }

  private int calculateNextSeedY(final PointWithId currentSeed, final PointWithId pointToApproach) {
    return (currentSeed.getY() + pointToApproach.getY()) / 2;
  }

  private PointWithId getPointById(final int id) {
    if (id == 0) {
      return pointA;
    } else if (id == 1) {
      return pointB;
    } else if (id == 2) {
      return pointC;
    } else {
      throw new UnsupportedOperationException("Unsupported id!");
    }
  }
}
