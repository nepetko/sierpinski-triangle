import java.awt.Point;

public class PointWithId {

  private final int id;
  private final Point location;


  PointWithId(final int x, final int y, final int id) {
    location = new Point(x, y);
    this.id = id;
  }

  int getX() {
    return (int) location.getX();
  }

  int getY() {
    return (int) location.getY();
  }

  public int getId() {
    return id;
  }
}
