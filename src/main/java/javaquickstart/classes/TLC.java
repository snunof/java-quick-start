package javaquickstart.classes;

class TLC {
  int nf;
  static int sf;
  
  // package access non-static member (inner) class
  class NMC {
	int nnf1 = sf + nf;

	// each inner class has reference to the enclosing class
	int nnf2 = TLC.sf + TLC.this.nf;
 }

  // static member class can have own static fields
  // but cannot access non-static fields of top-level class
  static class SMC {
	static int ssf = sf + TLC.sf;
	int snf = sf + TLC.sf;
  }

  void nm() {

	// local class is declared within methods, constructors of initializers
	class NLC {
	  int m(int p) { return sf + nf + p; }
	}
  }
}
