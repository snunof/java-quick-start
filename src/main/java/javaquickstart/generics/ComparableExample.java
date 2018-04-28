package javaquickstart.generics;

import java.util.*;

public class ComparableExample {
  public static void main(String ...args) {
	List<ComparablePair<Integer, Integer>> cpairs = Arrays.asList(
		new ComparablePair<>(3, 11),
		new ComparablePair<>(4, 11),
		new ComparablePair<>(3, 9),
		new ComparablePair<>(3, 7),
		new ComparablePair<>(9, 11),
		new ComparablePair<>(7, 13),
		new ComparablePair<>(3, 1));

	// using functional interfaces
	cpairs.sort(ComparablePair::compareTo);
	cpairs.forEach(p -> System.out.println(p.pretty()));
  }
}

/**
 * Type constraints
 *     Ti extends c1 & c2 & ... & cn
 * where ci is a type expression: an interface, a non-final class type,
 * or one of the proceeding type parameters
 */
class ComparablePair<T extends Comparable<T>, U extends Comparable<U>>
	implements Comparable<ComparablePair<T,U>> {
  private final T fst;
  private final U snd;

  public ComparablePair(T fst, U snd) { this.fst = fst; this.snd = snd; }
  public int compareTo(ComparablePair<T,U> that) {
	int firstCmp = this.fst.compareTo(that.fst);
	return firstCmp != 0 ? firstCmp : this.snd.compareTo(that.snd);
  }

  public String pretty() { return "(" + this.fst + ", " + this.snd + ")"; }
}
