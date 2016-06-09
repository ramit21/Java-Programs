package code;
import java.lang.ref.WeakReference;
import java.util.*;

public class HashEqaulsContract {
	public static void main(String[] args) {
		Employee emp1 = new Employee();
		Employee emp2 = new Employee();
		emp1.id = 10;
		emp1.name = "Ramit";

		emp2.id = 10;
		emp2.name = "Sharma";

		Map myMap = new HashMap();
		myMap.put(emp1, emp1.name);
		myMap.put(emp2, emp2.name);
		emp1.func(null);
		System.out.println("size = " + myMap.size());
		System.out.println("equals?? = " + emp1.equals(emp2));
		System.out.println("map: " + myMap.get(emp2));
		A obj = new B();
		obj.func(5);
		WeakReference<String> weekObj = new WeakReference<String>("Hello");
		Set set = new HashSet();
		set.add(null);
		set.add(null);
		System.out.println("SIZE = " + set.size());
		List<String> list = new ArrayList<String>();
		System.out.println(list.getClass());
		x obj2 = new y();
		System.out.println(obj2.getClass());
		byte b = 127;
		byte c = 1;
		b *= c;
		b += c;
		List<String> l = new ArrayList<String>();
		l.add("hello");
		B1 b1 = new B1();
		B1 b2 = null;
		try {
			b2 = (B1) b1.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		System.out.println(b2.x);
	}

	Set s = new TreeSet();
	// s.add(b1);
}

class Employee {

	public void func(Integer x) {
		System.out.println("string");
	}

	public void func(Object o) {
		System.out.println("Object");
	}

	int id;
	String name;

	/*
	 * public boolean equals(Object o){ if( ((Employee)o).id == id ){ return
	 * true; } return false; }
	 */
	public int hashCode() {
		return 5;
	}
}

class A {
	static void func(int x) {
		System.out.println("Class A");
	}
}

class B extends A implements Comparable {
	static void func(int x) {
		System.out.println("Class B");
	}

	public int compareTo(Object o) {
		return 1;
	}
}

interface x {

}

class y implements x {

}

class z implements x {

}

class Brother {
	Sister sister;

	Brother(Sister sister) {
		this.sister = sister;
	}
}

class Sister {
	Brother brother;

	Sister(Brother brother) {
		this.brother = brother;
	}
}

class A1 implements Cloneable {
	int x = 15;
}

class B1 extends A1 {
	int x = 15;

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}