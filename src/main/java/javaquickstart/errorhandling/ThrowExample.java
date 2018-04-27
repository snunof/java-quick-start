package javaquickstart.errorhandling;

import java.io.*;


public class ThrowExample {
  
  public static void main(String ...args) {
	double[] res = new double[3];

	// Java7 try-with-resource
	try (BufferedReader breader = new BufferedReader(new FileReader("../data.txt"))) {
	  res[0] = Double.parseDouble(breader.readLine());
	  res[1] = Double.parseDouble(breader.readLine());
	  res[2] = Double.parseDouble(breader.readLine());
	} catch (IOException e) {
	  // Do nothing
	}

	for (double d: res) {
	  System.out.println(d);
	}
  }
}
