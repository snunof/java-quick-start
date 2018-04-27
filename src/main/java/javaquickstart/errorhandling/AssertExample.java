package javaquickstart.errorhandling;

import java.util.*;


// example Java assertions
// Java assertion is enabled with -enableassertions flag
public class AssertExample {
  public static void main(String ...args) {

	int x = sqrt(31);
	System.out.println(x);
	
	WordList wl = new WordList();
	wl.addLast("throw");
	wl.addLast("catch");
	wl.addLast("finally");

	String s = wl.removeFirst();
	System.out.println(s);
  }

  static int sqrt(int x) {
	if (x < 0)
	  throw new IllegalArgumentException("sqrt: negative argument");

	int temp, y = 0, b = 0x8000, bshift = 15, v = x;
	do {
	  if (v >= (temp = (y << 1)+b << bshift--)) {
		y += b; v -= temp;
	  }
	} while ((b >>= 1) > 0);

	// Java assert  used to check the result of an algorithm
	assert (long)y * y <= x && (long)(y+1) * (y+1) > x;
	return y;
  }

  public static class WordList {
	private LinkedList<String> strings = new LinkedList<>();
	private int length = -1;
	public int length() { return length; }

	// java.util.LinkedList<E> addLast and removeFirst
	public void addLast(String s) {
	  strings.addLast(s);
	  length += 1 + s.length();
	  assert length == computeLength() + strings.size() - 1;
	}

	public String removeFirst() {
	  String res = strings.removeFirst();
	  length -= 1 + res.length();
	  assert length == computeLength() + strings.size() - 1;
	  return res;
	}

	// private function called by assert to check invariants
	private int computeLength() {
	  int res = 0;

	  for (String s: strings) {
		res += s.length();
	  }

	  return res;
	}
  }
}
