package code;

/**
 * Cloning and Inheritance
 *
 */
public class Cloning extends Parent implements Cloneable {
	int j;

	Cloning() {
		j = 200;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public static void main(String[] args) {
		Cloning obj1 = new Cloning();
		Cloning obj2 = null;
		try {
			obj2 = (Cloning) obj1.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		System.out.println(obj1.hashCode() == obj2.hashCode());
		System.out.println(obj2.i);
		obj1.setI(20);
		System.out.println(obj2.i);
	}
}

class Parent {
	int i;

	public Parent() {
		i = 100;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

}

// op: false, 100, 100
