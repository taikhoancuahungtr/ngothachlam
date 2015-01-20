
public class TinhTongDemo {

	public static void main(String[] args) {
		int n = 10;
		int tong = tinhTong(n);
		System.out.println("Tong = " + tong);
	}
	
	static int tinhTong(int n) {
		int s = 0;
		for (int i = 1; i <= n; i++) {
			s = s + i;
		}
		return s;
	}
}
