package javaquickstart.generics;

public class GenericBasicExample {
  public static void main(String ...args) {
	// type parameters are not primitive types.
	Pair<Integer, Integer> p1 = new Pair<>(3, 4);
	Pair<String, Double> p2 = new Pair<>("version", 8.0);

	System.out.println(p1.first.intValue() + ", " + p1.second.intValue());
	System.out.println(p2.first + ", " + p2.second.doubleValue());

	PrettyPair<Integer, Integer> pp1 = new PrettyPair<>(3, 4);
	System.out.println(pp1.pretty());
	System.out.println(PrettyPair.counter);

	PrettyPair<String, Integer> pp2 = new PrettyPair<>("id", 1000);
	System.out.println(pp2.pretty());
	System.out.println(pp2.counter);
  }
}

// Generic classes are indeed not a class, instead it is a template
// from which we can create a type instance, which is a class.
class Pair<T,U> {
  public final T first;
  public final U second;

  public Pair(T t, U u) { this.first = t; this.second = u;}
}

// If D<T1...Tn> is a subclass of C<T1...Tn>, then a type instance D<T1...Tn>
// is a subutype of a type instance C<T1...Tn>. But they are invariant in their
// type parameters: C<T2> is not a subtype of C<T1>, where T2 is a subtype of
// T1.
class PrettyPair<T,U> extends Pair<T,U> {
  // All type instances share the static variables regardless of their
  // type parameters.
  public static volatile int counter = 0;

  public String pretty() {
	return "(" + first.toString() + ", " + second.toString() + ")";
  }

  public PrettyPair(T t, U u) { super(t, u); counter++; }
}
