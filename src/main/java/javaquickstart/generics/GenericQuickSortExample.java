package javaquickstart.generics;

import java.util.*;


public class GenericQuickSortExample {
  public static void main(String ...args) {
	// explicitly call type arguments after object of class name
	List<Integer> al = Arrays.<Integer>asList(3, 4, 1, 2, 5, 11, 17, 6, 9);

	// how you convert a list to an array
	Integer[] as = al.<Integer>toArray(new Integer[al.size()]);

	qsort(as, 0, al.size() - 1);

	for (int i = 0; i < al.size(); i++) {
	  System.out.println(as[i]);
	}
  }

  
  // type parameters in front of the return type 
  public static <T extends Comparable<T>> void qsort(T[] arr, int a, int b) {
	if (a < b) {
	  int i = a, j = b;
	  T x = arr[(i + j) / 2];
	  do {
		while (arr[i].compareTo(x) < 0) i++;
		while (x.compareTo(arr[j]) < 0) j--;
		
		if (i <= j) {
		  T tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
		  i++; j--;
		}
		
	  } while (i <= j);
	  
	  qsort(arr, a, j);
	  qsort(arr, i, b);
	}
  }
}
