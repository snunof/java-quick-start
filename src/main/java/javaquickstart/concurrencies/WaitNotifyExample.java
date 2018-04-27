package javaquickstart.concurrencies;

import java.util.concurrent.*;

public class WaitNotifyExample {
  public static void main(String ...args) {
	MyBuffer q = new MyBuffer(8);

	MyProducer p = new MyProducer(q);
	p.start();

	try {
	  Thread.sleep(2000);
	} catch (InterruptedException e) {}

	MyConsumer[] c = new MyConsumer[2];

	for (int i = 0; i < 2; i++) {
	  c[i] = new MyConsumer(q);
	  c[i].start();
	}

	try {
	  p.join();

	  for (int i = 0; i < 2; i++) {
		c[i].join();
	  }
	} catch (InterruptedException e) {}
  }
}

class MyProducer extends Thread {
  private final MyBuffer mb;

  public MyProducer(MyBuffer mb) {
	this.mb = mb;
  }

  public void run() {
	for (int i = 0; i < 10; i++) {
	  mb.put(i);
	  System.out.println("put(" + i + ")");
	}
  }
}

class MyConsumer extends Thread {
  private final MyBuffer mb;

  public MyConsumer(MyBuffer mb) {
	this.mb = mb;
  }

  public void run() {
	for (int i = 0; i < 3; i++) {
	  int v = mb.get();
	  System.out.println(v + " <- get()");
	}
  }
}

class MyBuffer {
  private int size;
  private int head = 0, tail = 0;
  private int[] contents;

  public MyBuffer(int size) {
	this.size = size;
	this.contents = new int[size];
  }

  public int size() {
	return size;
  }

  synchronized public int get() {
	int res;
	
	while (head == tail) {
	  try { this.wait(); } catch (InterruptedException e) {}
	}

	tail = (tail + 1) % size;
	res = contents[tail];
	
	this.notifyAll();
	return res;
  }

  synchronized public void put(int v) {
	// WARNING: incomplte algorithm!
	while (head == (tail + size - 1)) {
	  try { this.wait(); } catch (InterruptedException e) {}
	}

	contents[head] = v;
	head = (head + 1) % size;

	this.notifyAll();
  }
}
