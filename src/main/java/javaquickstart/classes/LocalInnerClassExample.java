package javaquickstart.classes;

import java.util.Iterator;


class LocalInnerClassExample {
  public static void main(String ...args) {
	Iterator<String> seq = suffixes(args[0]);
	while (seq.hasNext()) {
	  System.out.println(seq.next());
	}
  }

  static Iterator<String> suffixes(final String s) {
	// example of a local class
	class SuffixIterator implements Iterator<String> {
	  int startIndex = 0;
	  public boolean hasNext() { return startIndex < s.length(); }
	  public String next() { return s.substring(startIndex++); }
	  public void remove() { throw new UnsupportedOperationException(); }
	}

	return new SuffixIterator();
  }

  static Iterator<String> suffixesWithAnonymousClass(final String s) {
	// example of an anonymous class
	return new Iterator<String>() {
	  int startIndex = 0;
	  public boolean hasNext() { return startIndex < s.length(); }
	  public String next() { return s.substring(startIndex++); }
	  public void remove() { throw new UnsupportedOperationException(); }
	};
  }
}
