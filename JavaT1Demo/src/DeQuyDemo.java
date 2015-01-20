
public class DeQuyDemo {

	public static void main(String[] args) {
		int n = 4;
		int gt = tinhGiaiThuaBangDeQuy(n);
		System.out.println(n + "! = " + gt);
	}
	
	static int tinhGiaiThuaBangDeQuy(int n) {
		if(n == 0) {
			return 1;
		} else {
			return n * tinhGiaiThuaBangDeQuy(n - 1);
		}
	}
	
	static int tinhGiaiThua(int n) { // 5! = 1 * 2 * 3 * 4 * 5 | 0! = 1
		int giaiThua = 1;
		for (int i = 2; i <= n; i++) {
			giaiThua = giaiThua * i;
		}
		return giaiThua;
	}
}
