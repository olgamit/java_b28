public class MyFirstProgram {

	public static void main (String [] args) {

		Point A = new Point (0.5, 4);
		Point B = new Point (8.5, 3);

		double d = distance(A, B);
		System.out.println("Расстояние между точками составляет " + d);
		}

	public static double distance(Point p1, Point p2){

		return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x) + ((p2.y-p1.y)*(p2.y-p1.y)));
	}


}