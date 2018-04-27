package javaquickstart.concurrencies;

import java.io.IOException;

// declare a subclass of java.lang.Thread
class Incrementer extends Thread {
  public volatile int i;

  // overwrite void run()
  public void run() {
	for (;;) {
	  i++;
	  yield();
	}
  }
}

class ThreadDemo {
  public static void main(String ...args) {
	/**
	 * Declares an instance of a subclass U of java.lang.Thread and invoke
	 * method `start` with the instance.
	 *
	 * Another way of enabling concurrent execution may be creating an
	 * instance of java.lang.Thread with an instance of a class that
	 * implements java.lang.Runnable.
	 */
	Incrementer u = new Incrementer();

	// Java8 lambda expression 
	Thread t = new Thread(() -> {
		try {
		  while (true) {
			System.in.read();
			System.out.println("Hello from Thread");
		  }
		} catch (IOException e) {
		  // do nothing
		}
	  });

	// `start()` will enable the thread to run concurrently
	u.start();
	t.start();

	System.out.println("Press Enter to get the current value i:");
	for (;;) {
	  try {
		System.in.read();
		System.out.println(u.i);
	  } catch (IOException e) {
		// do nothing
	  }
	}
  }
}
