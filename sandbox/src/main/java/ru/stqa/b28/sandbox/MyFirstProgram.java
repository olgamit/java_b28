package ru.stqa.b28.sandbox;

public class MyFirstProgram {

	public static void main (String [] args) {

		Point A = new Point(5, 6);
		Point B = new Point(8, 10);

		double d = A.distance(B);
		System.out.println("Distance between points is " + d);
		}




}