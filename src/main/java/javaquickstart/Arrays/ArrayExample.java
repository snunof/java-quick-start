package javaquickstart.arrays;

import java.util.*;
import java.util.Arrays;
import java.util.concurrent.*;

public class ArraysExample {
  public static void main(String ...args) {
	int n = 9;
	Vec2D[] a = new Vec2D[n];

	Arrays.parallelSetAll(a, i -> Vec2D.randomUnit());
	Arrays.parallelPrefix(a, (p1, p2) -> p1.add(p2));

	Arrays.stream(a).forEach(p -> {
		System.out.println(p.length());
	  });
  }

  private static class Vec2D {
	public final double x, y;
	public double length() {
	  return length(this.x, this.y);
	}
	public static double length(double x, double y) {
	  return Math.sqrt(x * x + y * y);
	}
	public Vec2D add(Vec2D that) {
	  return new Vec2D(this.x + that.x, this.y + that.y);
	}
	public static Vec2D randomUnit() {
	  // java.util.concurrent ThreadLocalRandom is preferred for multi-threads
	  double p[] = ThreadLocalRandom.current().doubles(2, -1.0, 1.0).limit(2).toArray();
	  double len = length(p[0], p[1]);
	  return new Vec2D(p[0] / len, p[1] / len);
	}
	public Vec2D(double x, double y) {
	  this.x = x; this.y = y;
	}
  }
}
