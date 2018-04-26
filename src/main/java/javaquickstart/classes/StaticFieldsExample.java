package javaquickstart.classes;

import java.util.ArrayList;


public class StaticFieldsExample {

  public static void main(String ...args) {
	SPoint s1 = new SPoint(3, 4);
	SPoint s2 = new SPoint(2, 5);

	for (SPoint p: SPoint.allPoints) {
	  System.out.println(p.toString());
	}
  }

  // static member class
  static class SPoint {
	// static member class can have a static member
	// but can not access non-static members of the top-level class
	static ArrayList<SPoint> allPoints = new ArrayList<>();
	int x, y;

	SPoint(int x, int y) { allPoints.add(this); this.x = x; this.y = y; }
	void move(int dx, int dy) { this.x += dx; this.y += dy; }
	public String toString() { return "(" + x + ", " + y + ")"; }
	int getIndex() { return allPoints.indexOf(this); }
	static int getSize() { return allPoints.size(); }
	static SPoint getPoint(int i) { return allPoints.get(i); }
  }
}
