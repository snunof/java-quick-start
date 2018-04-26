package javaquickstart.classes;

public class NonStaticMemberClassExample {
  public static void main(String ...args) {
	TLC oo = new TLC();

	// create an instance of non-static member class associated with an object
	// non-static member class always requires an enclosing object
	TLC.NMC io1 = oo.new NMC();

	// create another instance associated with the same object
	TLC.NMC io2 = oo.new NMC();

	// create an instance of static member class
	TLC.SMC sio = new TLC.SMC();
  }
}
