package javaquickstart.strings;

import java.util.*;

public class StringBuilderExample {
  public static void main(String ...args) {
	// StringBuilder extends `AbstractStrinBuilder` and implements
	// `Serializable` and `CharSequence`. We can avoid unnecessary creating new
	// String instances by using builder class.
	String s = new StringBuilder().append("append").append(4).append('c').toString();

	String c = toCamelcase("awesome_class_name");
	
	System.out.println(s);
	System.out.println(c);
  }

  // StringBuilder can efficiently create a dynamic String. Raw String addition
  // repeatedly creates new instances of String.
  static String toCamelcase(String s) {
	StringBuilder res = new StringBuilder();

	for (int i = 0; i < s.length(); i++) {
	  if (s.charAt(i) == '_' ) {
		res.append(Character.toUpperCase(s.charAt(++i)));
	  } else {
		res.append(s.charAt(i));
	  }
	}

	return res.toString();
  }
}
