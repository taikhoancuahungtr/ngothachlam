
public class Helloworld {
	
	public static void main(String[] args) {
//		System.out.println("Hello world");
		
		int[] a = {1, 3, 5};
		int[] b = {2, 4, 6, 8};
		a = b;
		b[0] = 20;
		System.out.println(a[0]);
		a[1] = 40;
		System.out.println(b[1]);
	}
}
