public class MyFirstProgram {

	public static void main (String [] args) {

		Point A = new Point (0.5, 4);
		Point B = new Point (8.5, 3);

		double d = A.distance(A, B);
		System.out.println("Расстояние между точками составляет " + d);
		}




}