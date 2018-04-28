package javaquickstart.collections;

import java.util.*;
import java.io.*;
import java.util.Map.Entry;

public class SortedMapExample {
  public static void main(String ...args) {
    try {
	  SortedMap<String, SortedSet<Integer>> index = buildIndex("../../preface.txt");

	  for (Map.Entry<String, SortedSet<Integer>> e: index.entrySet()) {
		String word = e.getKey();

		for (Integer line: e.getValue()) {
		  System.out.println(line + ": " + word);
		}
	  }
	} catch (IOException e) {
	  // do nothing
	}
  }

  // returns an abstract class SortedMap
  public static SortedMap<String, SortedSet<Integer>> buildIndex(String filename)
	  throws IOException {
	BufferedReader r = new BufferedReader(new FileReader(filename));
	StreamTokenizer stok = new StreamTokenizer(r);
	stok.quoteChar('"'); stok.ordinaryChars('!', '/');
	stok.nextToken();
	SortedMap<String, SortedSet<Integer>> index = new TreeMap<String, SortedSet<Integer>>();
	while (stok.ttype != StreamTokenizer.TT_EOF) {
	  if (stok.ttype == StreamTokenizer.TT_WORD) {
		SortedSet<Integer> ts;
		if (index.containsKey(stok.sval)) {
		  ts = index.get(stok.sval);
		} else {
		  ts = new TreeSet<Integer>();
		  index.put(stok.sval, ts);
		}
		ts.add(stok.lineno());
	  }
	  stok.nextToken();
	}

	return index;
  }
}
