
public class LoopDemo {

	public static void main(String[] args) {
//		for (int i = 0, j = 10; i < 10 && j > 0; ++i, j-=2) {
//			System.out.println(i + " : " + j);
//		}
		
		int i = 10;
		while(i-- > 0) {
			System.out.print(i + ", ");			
		}
	}
}
