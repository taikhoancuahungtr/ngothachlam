
public class TinhChuVi {

	public static void main(String[] args) {
		double r = 5.5;
		double chuVi = tinhChuVi(r);
		System.out.printf("Chu vi cua hinh tron co ban kinh %.2f la: %.2f", r, chuVi);
	}
	
	static double tinhChuVi(double r) {
		double chuVi = 2 * Math.PI * r;
		return chuVi;
	}
}
