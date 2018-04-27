package javaquickstart.concurrencies;

import java.util.concurrent.*;
import java.util.*;

public class SynchronizedExample {
  public static void main(String ...args) {
	int nThreads = 4;
	
	Printer[] p = new Printer[nThreads];

	for (int i = 0; i < nThreads; i++) {
	  p[i] = new Printer();
	  p[i].start();
	}

	// An object that protect all of its shared state by synchronization
	// is called a monitor
	Bank bank = new Bank();

	Clerk[] c = new Clerk[nThreads];
	
	for (int i = 0; i < nThreads; i++) {
	  c[i] = new Clerk(bank);
	  c[i].start();
	}
  }
}

class Printer extends Thread {
  private static Object mutex = new Object();

  public void run() {
	for (int i = 0; i < 10; i++) {
	  // After evaluating the expression following `synchronized`,
	  // current thread becomes _Locking_ state on the mutext object.
	  // Once the theread acquires the lock, it's enabled and can be
	  // run. The thread releases lock when it leaves the block.
	  synchronized (mutex) {
		System.out.print("-");

		try {
		  Thread.sleep(100);
		} catch (InterruptedException e) {
		  Thread.currentThread().interrupt();
		}
		
		System.out.print("/");
	  }
	}
  }
}

class Bank {
  private static String name = "Rich Bank";
  private int account1 = 10, account2 = 20;

  // synchronized non-static member methods can be thought of shorthand
  // for synchronized (this) at the head of the method.
  synchronized public void transfer(int amount) {
	int new1 = account1 - amount;
	
	try {
	  Thread.sleep(100);
	} catch (InterruptedException e) {
	  Thread.currentThread().interrupt();
	}

	account1 = new1;
	account2 = account2 + amount;

	System.out.println("Sum is " + (account1 + account2));
  }

  // synchronized static member methods can be thought of shorthand
  // for synchronized (C.class) at the head of the method, where
  // `C.class` is a unique class associated with the class C.
  synchronized static public String name() { return name; }
}

// Clerk class extends Thread and run invokes synchronized non-static method
// on Bank class
class Clerk extends Thread {
  private Bank bank;
  public Clerk(Bank b) { this.bank = b; }
  public void run() {
	for (int i = 0; i < 4; i++) {
	  bank.transfer(ThreadLocalRandom.current().ints(1, -10, 10).limit(1).toArray()[0]);

	  try {
		Thread.sleep(100);
	  } catch (InterruptedException e) {
		Thread.currentThread().interrupt();
	  }
	}
  }
}
					
