import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JFrame;

public class MainPanel extends JFrame {

  Random rand = new Random();
  PointObject pointA = new PointObject(10, 50, 0);
  PointObject pointB = new PointObject(1000, 50, 1);
  PointObject pointC = new PointObject((970 - 10) / 2, 970, 2);
  PointObject currentSeed = new PointObject(rand.nextInt(970), rand.nextInt(970), 3);

  public MainPanel() {
    super("Sierpinski Triangle");

    setSize(1000, 1000);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
  }


  public void paint(Graphics g) {
    super.paint(g);
    drawInitialPoints(g);
    generateNewSeedPoints(g);
  }

  void drawInitialPoints(Graphics g) {
    Graphics2D graphics2d = (Graphics2D) g;

    graphics2d.drawOval(pointA.getX(), pointA.getY(), 1, 1);
    graphics2d.drawOval(pointB.getX(), pointB.getY(), 1, 1);
    graphics2d.drawOval(pointC.getX(), pointC.getY(), 1, 1);
    graphics2d.drawOval(currentSeed.getX(), currentSeed.getY(), 1, 1);
  }


  void generateNewSeedPoints(Graphics g) {
    Graphics2D graphics2d = (Graphics2D) g;
    int nextIdNumber;

    for (int i = 0; i < 1000000; i++) {
      nextIdNumber = rand.nextInt(3);
      PointObject pointToApproach = getPointById(nextIdNumber);
      int nextSeedX = calculateNextSeedX(currentSeed, pointToApproach);
      int nextSeedY = calculateNextSeedY(currentSeed, pointToApproach);
      currentSeed = new PointObject(nextSeedX, nextSeedY, 3);
      graphics2d.drawOval(currentSeed.getX(), currentSeed.getY(), 1, 1);
    }
  }

  private int calculateNextSeedX(PointObject currentSeed, PointObject pointToApproach) {
    return Math.abs((currentSeed.getX() - pointToApproach.getX())) / 2;
  }

  private int calculateNextSeedY(PointObject currentSeed, PointObject pointToApproach) {
    return Math.abs((currentSeed.getY() - pointToApproach.getY())) / 2;
  }

  PointObject getPointById(int id) {
    if (id == 0) {
      return pointA;
    } else if (id == 1) {
      return pointB;
    } else if (id == 2) {
      return pointC;
    } else {
      return null;
    }
  }
}
