
public class MethodDemo {

	public static void main(String[] args) {
		int a = 100;
		int b = 20;
		int r = cong(a, b);
		System.out.println(a + " + " + b + " = " + r);
	}
	
	static int cong(int a, int b) {
		int r = a + b;
		return r;
	}
}
