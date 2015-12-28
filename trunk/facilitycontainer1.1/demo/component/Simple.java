package demo.component;

public class Simple {
	public Simple(A a) {
		System.out.println(".a");
	}
	
	public Simple(C a) {
		System.out.println(".c");
	}

	public Simple(B a) {
		System.out.println(".b");
	}	
	
		
	public static void main(String[] args) {
		new Simple(new D());
	}
}

interface A {
	
}

interface B extends A {
	
}

interface C extends B {
	
}

class D implements A, B, C {
	
}

