package edu0;

public class MainClass {

	public static void main(String[] arg) {
		System.out.println("Summ of array: " + arraySum(new int[] { 1, 6, 2, 2, 5, 9 }));
	}

	static public int arraySum(int array[]) {

		int summ = 0;
		for (int x : array) {

			summ += x;
		}
		return summ;
	}
}