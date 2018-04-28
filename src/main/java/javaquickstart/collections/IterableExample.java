package javaquickstart.collections;

import java.util.*;
import java.util.stream.*;

public class IterableExample {

  public static void main(String ...args) {
	Iterable<Integer> ns1 = fromTo(3, 8);
	Iterable<Integer> ns2 = fromToStream(3, 8);

	for (Integer i: ns1) {
	  System.out.println(i);
	}

	for (Integer j: ns2) {
	  System.out.println(j);
	}
  }

  public static Iterable<Integer> fromTo(final int m, final int n) {

	/**
	 * Iterator<T> is an interface that describes hasNext(), next() and
	 * remove() methods. It can throw `UnsupportedOperationException` or
	 * `NoSucElementException`. Iterator<T> also has `forEachRemainig`
	 * default method.
	 */

	// define a local class that implements an iterator
	class FromToIterator implements Iterator<Integer> {
	  private int i = m;
	  public boolean hasNext() { return i < n ? true : false; }
	  public Integer next() {
		if (i <= n) {
		  return i++;
		} else {
		  throw new NoSuchElementException();
		}
	  }
	  public void remove() { throw new UnsupportedOperationException(); }
	}

	// use anonymous class
	return new Iterable<Integer>() {
	  public Iterator<Integer> iterator() {
		return new FromToIterator();
	  }		
	};
  }
  
  /**
   * Iterable<T> is a functional interface that describes iterator() that
   * returns Iterator<T>. In addition to iterator(), Iterable<T> has a
   * default method `forEach`, which takes in Consumer<T> functional
   * interface and invokes cons.accept() on items.
   */

  // returns an iterable instance using a lambda expression
  public static Iterable<Integer> fromToStream(final int m, final int n) {
	return () -> IntStream.rangeClosed(m, n).iterator();
  }
}
